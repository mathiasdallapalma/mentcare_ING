package mentcare;

import mentcare.models.Evaluation;
import mentcare.models.Patient;
import mentcare.repositories.EvaluationRepository;
import mentcare.repositories.PatientRepository;
import mentcare.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping("/")
    public String index(){
        return "list";
    }

    @RequestMapping("/input")
    public String input(){
        return "input"; //TODO: input fa riferimento a "inputOLD.html" da cambiare nome e la pagina
    }

    @RequestMapping("/patient/{id}/addEvaluation")
    public String addEvaluation(
            @PathVariable(name="id", required=true) Long id,
            Model model
    ){
        //roba che serve per testare
        Patient p=new Patient("Mario","Rossi",10,10,10,"10","10","10","10","10","a,b,c","asdw");
        patientRepository.save(p);
        //fine roba che serve per testare

        Optional<Patient> patient = patientRepository.findById(id);

        if(patient.isPresent()) {
            model.addAttribute("patient", patient.get());
            return  "addEvaluation";
        }
        else {
            model.addAttribute("error_title", "Paziente non trovato");
            model.addAttribute("error_message", "Nessun paziente con id: "+id);
            model.addAttribute("redirect_link", "/home");
            return "error";
        }

    }

    @RequestMapping("patient/{id}/validateEvaluation")
    public String validateEvaluation(
            @PathVariable(name="id", required=true) Long patientID,
            @RequestParam(name="date", required=true) String data,
            @RequestParam(name="value", required=true) Integer value,
            @RequestParam(name="motivation", required=true) String motivation,
            @RequestParam(name="notes", required=true) String notes,
            Model model) {

        Evaluation evaluation = new Evaluation(data,value,notes,motivation,patientID);
        String error = evaluation.selfCheck();
        if(error.isEmpty()){
            evaluationRepository.save(evaluation);
            return "redirect:/patient/{id}";
        }
        else {
            model.addAttribute("error_title", "Aggiunta valutazione fallita");
            model.addAttribute("error_message", error);
            model.addAttribute("redirect_link", "/patient/"+patientID+"/addEvaluation");
            System.out.println("Error");
            return "error";
        }
    }


    /*
    @RequestMapping("/create")
    public String create(
            @RequestParam(name="firstname", required=true) String firstname,
            @RequestParam(name="lastname", required=true) String lastname) {
        repository.save(new Patient(firstname,lastname));
        return "redirect:/list";
    }
    */


}