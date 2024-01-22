package mentcare;


import mentcare.models.Evaluation;
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

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepo;

    @Autowired
    private VisitRepository visitRepo;
    private EvaluationRepository evaluationRepository;
    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping("/")
    public String home(Model model){
        Iterable<Visit> lVisite = visitRepo.findAll();
        model.addAttribute("visits", lVisite);
        Iterable<Patient> lPatients = patientRepository.findAll();
        model.addAttribute("patients", lPatients);
        return "home";
   

    @RequestMapping("/patient/{id}")
    public String patientView(
            @PathVariable(name="id", required=true) Long id,
            Model model) {

        /* roba che mi serve per testare verranno sostituiti con funzioni tipo repository.find(...) */ //
        Patient patient1 = new Patient("Mario", "Rossi", 80, 180, 30, "01/01/1990", "M", "1234567890", "gino@gmail.com", "Via Pistacchio 21, Roma (RM)", "Zobrepil, Aspirina, Amoxicillina","chdwdd99d17d569");
        patientRepository.save(patient1);
        //evaluations
        Evaluation evaluation1= new Evaluation("01/01/2024",1,"note","urgenza",patient1.getId());
        Evaluation evaluation2= new Evaluation("02/01/2024",1,"note","urgenza",patient1.getId());
        Evaluation evaluation3= new Evaluation("03/01/2024",1,"note","urgenza",patient1.getId());
        evaluationRepository.save(evaluation1);
        evaluationRepository.save(evaluation2);
        evaluationRepository.save(evaluation3);
        //prescriptions
        Prescription prescription1= new Prescription("Tachipirina",10,"note",patient1.getId());
        Prescription prescription2= new Prescription("Tachipirina",20,"note",patient1.getId());
        Prescription prescription3= new Prescription("Luxopedrina",30,"note",patient1.getId());
        prescriptionRepository.save(prescription1);
        prescriptionRepository.save(prescription2);
        prescriptionRepository.save(prescription3);

        /* fine delle robe che mi servono da testare*/



        Optional<Patient> result = patientRepository.findById(id);

        if (result.isPresent()){
            Patient patient = result.get();
            model.addAttribute("patient", patient);

            List<Prescription> prescriptions = prescriptionRepository.findByPatientID(id);
            model.addAttribute("prescriptions", prescriptions);
            List<Evaluation> evaluations = evaluationRepository.findByPatientID(id);
            model.addAttribute("evaluations", evaluations);

            return "patientView";
        }
        else
            model.addAttribute("error_title", "Paziente non trovato");
            model.addAttribute("error_message", "Nessun paziente trovato con questo ID");
            model.addAttribute("redirect_link", "/home");
            return "error";
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
    @RequestMapping("/patient/{id}/report")
    public String patientReport(
            @PathVariable(name="id", required=true) Long id,
            Model model) {

        Optional<Patient> result = patientRepository.findById(id);

        if (result.isPresent()){
            List<Prescription> prescriptions = prescriptionRepository.findByPatientID(id);
            List<Evaluation> evaluations = evaluationRepository.findByPatientID(id);


            String report=MyUtils.generateReport(result.get(),prescriptions,evaluations);
            model.addAttribute("report", report);
            return "report";
        }
        else {
            model.addAttribute("error_title", "Paziente non trovato");
            model.addAttribute("error_message", "Nessun paziente trovato con questo ID");
            model.addAttribute("redirect_link", "/home");
            return "error";

        }


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
        patientRepositorysave(new Patient(firstname,lastname));
        return "redirect:/list";
    }


}
