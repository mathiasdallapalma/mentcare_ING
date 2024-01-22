package mentcare;


import mentcare.models.Evaluation;
import mentcare.models.Patient;
import mentcare.models.Prescription;
import mentcare.repositories.EvaluationRepository;
import mentcare.repositories.PatientRepository;
import mentcare.repositories.PrescriptionRepository;
import mentcare.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private PrescriptionRepository prescriptionRepository;


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

    @RequestMapping("/input")
    public String input(){
        return "input"; //TODO: input fa riferimento a "inputOLD.html" da cambiare nome e la pagina
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

    /*
    @RequestMapping("/create")
    public String create(
            @RequestParam(name="firstname", required=true) String firstname,
            @RequestParam(name="lastname", required=true) String lastname) {
        patientRepositorysave(new Patient(firstname,lastname));
        return "redirect:/list";
    }
    */


}