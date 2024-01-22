package mentcare;

import mentcare.models.Patient;
import mentcare.models.Prescription;
import mentcare.repositories.PatientRepository;
import mentcare.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepo;

    @RequestMapping("/")
    public String index(){
        return "list";
    }

    @RequestMapping("/input")
    public String input(){
        return "input"; //TODO: input fa riferimento a "inputOLD.html" da cambiare nome e la pagina
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

    @RequestMapping("/patient/{idPatient}/addPrescription")
    public String addPrescription(Model model, @PathVariable(name="idPatient")Long idPatient){
        Optional<Patient> opt = patientRepository.findById(idPatient);
        if(opt.isPresent()){
            model.addAttribute("idPatient",idPatient);
            model.addAttribute("name", opt.get().getFirstname() + " " + opt.get().getLastname());
            model.addAttribute("prescriptions", prescriptionRepo.findByPatientID(idPatient));
            return "addPatient";
        }else {
            model.addAttribute("error_title","Paziente non trovato");
            model.addAttribute("error_message",
                    "Il paziente con id ["+idPatient.toString()+"] Non è stato trovato :(\n");
            model.addAttribute("redirect_link","/patient/"+idPatient.toString()+"/addPrescription");
            return "error";
        }
    }

    @PostMapping("/patient/{idPatient}/addPrescription")
    public String addPrescription(Model model, @PathVariable(name="idPatient")Long idPatient,
                                  @RequestParam(name="drug")String drug,
                                  @RequestParam(name="quantity")Integer quantity,
                                  @RequestParam(name="note")String note){

        Prescription prescr = new Prescription(drug, quantity, note, idPatient);
        if(patientRepository.findById(idPatient).isPresent()){
            List<String> allergiesList = patientRepository.findById(idPatient).get().getAllergies();
            String errorMsg = prescr.selfCheck(allergiesList);
            if(errorMsg.isEmpty()){
                prescriptionRepo.save(prescr);
                return "patient/"+idPatient.toString(); //TODO: funziona così??
            }else {
                model.addAttribute("error_title","Inserimento prescrizione non riuscito");
                model.addAttribute("error",errorMsg);
                model.addAttribute("redirect_link","/patient/"+idPatient+"/addPrescription");
                return "error";
            }
        }else{
            model.addAttribute("error_title",
                    "Inserimento prescrizione non riuscito");
            model.addAttribute("error","Paziente con id ["+ idPatient.toString() +"] Non trovato");
            model.addAttribute("redirect_link","patient/"+idPatient);
            return "error";
        }
    }


}