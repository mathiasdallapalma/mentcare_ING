package mentcare;

import mentcare.models.Patient;
import mentcare.models.Visit;
import mentcare.repositories.PatientRepository;
import mentcare.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import mentcare.models.Prescription;
import mentcare.repositories.PrescriptionRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private VisitRepository visitRepo;

    @RequestMapping("/")
    public String home(Model model){
        Iterable<Visit> lVisite = visitRepo.findAll();
        model.addAttribute("visits", lVisite);
        Iterable<Patient> lPatients = patientRepository.findAll();
        model.addAttribute("patients", lPatients);
        return "home";
    }


    @RequestMapping("/addPatient")
    public String addPatientPage(){
        return "addPatient";
    }

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

    @PostMapping("/addPatient")
    public String addPatient(Model model,
            @RequestParam(name="firstname")String firstname,
            @RequestParam(name="lastname")String lastname,
            @RequestParam(name="weight")String weight,
            @RequestParam(name="height")String height,
            @RequestParam(name="age")String age,
            @RequestParam(name="birthdate")String birthdate,
            @RequestParam(name="sex")String sex,
            @RequestParam(name="phonenumber")String phonenumber,
            @RequestParam(name="email")String email,
            @RequestParam(name="address")String address,
            @RequestParam(name="allergies")String allergies,
            @RequestParam(name="cf")String cf){

        //TODO cambiare string to Integer e inserire "selfcheck()"
        //TODO cambiare il file html type = text -> number per i 2 campi
        //TODO togliere Età e calcola automaticamente con il birthdate
        try{
            //i valori inseriti dovrebbero essere int, ma se si inseriscono cose strane va in error
            Integer intweight = Integer.decode(weight);
            Integer intheight = Integer.decode(height);
            Integer intage = Integer.decode(age);

            Patient pat = new Patient(firstname, lastname, intweight, intheight, intage, birthdate, sex,
                    phonenumber, email, address, allergies, cf);
            if(pat.selfCheck().isEmpty()) { //.equals("") me lo corregge così
                patientRepository.save(pat);
                return "home";
            }else{
                model.addAttribute("error", pat.selfCheck());
                return "error";
            }
        }catch (NumberFormatException e){
            model.addAttribute("error", "Errore valori mancanti: \n" + e);
            return "error";
        }
    }


}
