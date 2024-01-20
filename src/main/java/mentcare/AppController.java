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

    @RequestMapping("/addPrescription/{idPatient}")
    public String addPrescription(Model model, @PathVariable(name="idPatient")Long idPatient){
        Optional<Patient> opt = patientRepository.findById(idPatient);
        if(opt.isPresent()){
            model.addAttribute("idPatient",idPatient);
            model.addAttribute("name", opt.get().getFirstname() + " " + opt.get().getLastname());
            model.addAttribute("prescriptions", prescriptionRepo.findByPatientID(idPatient));
            return "addPatient";
        }else {
            model.addAttribute("error", "Il paziente con id ["+idPatient.toString()+"] Non è stato trovato :(\n");
            return "error";
        }
    }

    @PostMapping("/addPrescription/{idPatient}")
    public String addPrescription(Model model, @PathVariable(name="idPatient")Long idPatient,
                                  @RequestParam(name="drug")String drug,
                                  @RequestParam(name="quantity")String quantity,
                                  @RequestParam(name="note")String note){
        try{
            Integer quant = Integer.getInteger(quantity);
            Prescription prescr = new Prescription(drug, quant, note, idPatient);
            prescriptionRepo.save(prescr);
            return "patientView/"+idPatient.toString();
        }catch (NumberFormatException e){
            model.addAttribute("error","e");
            return "error";
        }
    }


}