package mentcare;

import mentcare.models.Evaluation;
import mentcare.models.Patient;
import mentcare.models.Visit;
import mentcare.models.Prescription;

import mentcare.repositories.PatientRepository;
import mentcare.repositories.VisitRepository;
import mentcare.repositories.PrescriptionRepository;
import mentcare.repositories.EvaluationRepository;

import mentcare.utils.MyUtils;

import mentcare.utils.VerificaLoginMOCK;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private VisitRepository visitRepository;


    @RequestMapping("/home")
    public String home(Model model) {
        Iterable<Visit> lVisite = visitRepository.findAll();
        model.addAttribute("visits", lVisite);
        Iterable<Patient> lPatients = patientRepository.findAll();
        model.addAttribute("patients", lPatients);
        return "home";
    }

    @RequestMapping("/patient/{id}")
    public String patientView(
            @PathVariable(name = "id", required = true) Long id,
            Model model) {

        /* roba che mi serve per testare verranno sostituiti con funzioni tipo repository.find(...) */ //

        /* fine delle robe che mi servono da testare*/

        Optional<Patient> result = patientRepository.findById(id);

        if (result.isPresent()) {
            Patient patient = result.get();
            model.addAttribute("patient", patient);

            List<Prescription> prescriptions = prescriptionRepository.findByPatientID(id);
            model.addAttribute("prescriptions", prescriptions);
            List<Evaluation> evaluations = evaluationRepository.findByPatientID(id);
            model.addAttribute("evaluations", evaluations);

            return "patientView";
        } else
            model.addAttribute("error_title", "Paziente non trovato");
        model.addAttribute("error_message", "Nessun paziente trovato con questo ID");
        model.addAttribute("redirect_link", "/home");
        return "error";
    }

    @RequestMapping("/addPatient")
    public String addPatientPage() {
        return "addPatient";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/validateLogin")
    public String validateLogin(
            @RequestParam(name = "username", required = true) String username,
            @RequestParam(name = "password", required = true) String password,
            Model model) {
        VerificaLoginMOCK verificaLogin = new VerificaLoginMOCK();
        if (verificaLogin.checkLogin(username, password)) //TODO sostituire con mock
            return "redirect:/home";
        else
            model.addAttribute("error_title", "Login fallito");
        model.addAttribute("error_message", "Username o password errati");
        model.addAttribute("redirect_link", "/login");
        return "error";
    }

    @RequestMapping("/patient/{idPatient}/addPrescription")
    public String addPrescription(Model model, @PathVariable(name = "idPatient") Long idPatient) {
        Optional<Patient> opt = patientRepository.findById(idPatient);
        if (opt.isPresent()) {
            model.addAttribute("idPatient", idPatient);
            model.addAttribute("name", opt.get().getFirstname() + " " + opt.get().getLastname());
            model.addAttribute("prescriptions", prescriptionRepository.findByPatientID(idPatient));
            return "addPrescription";
        } else {
            model.addAttribute("error_title", "Paziente non trovato");
            model.addAttribute("error_message",
                    "Il paziente con id [" + idPatient.toString() + "] Non è stato trovato :(\n");
            model.addAttribute("redirect_link", "/patient/" + idPatient.toString() + "/addPrescription");
            return "error";
        }
    }

    @PostMapping("/patient/{idPatient}/addPrescription")
    public String addPrescription(Model model, @PathVariable(name = "idPatient") Long idPatient,
                                  @RequestParam(name = "drug") String drug,
                                  @RequestParam(name = "quantity") Integer quantity,
                                  @RequestParam(name = "note") String note) {

        Prescription prescr = new Prescription(drug, quantity, note, idPatient);
        if (patientRepository.findById(idPatient).isPresent()) {
            List<String> allergiesList = patientRepository.findById(idPatient).get().getAllergies();
            String errorMsg = prescr.selfCheck(allergiesList);
            if (errorMsg.isEmpty()) {
                prescriptionRepository.save(prescr);
                return "redirect:/patient/" + idPatient.toString(); //TODO: funziona così??
            } else {
                model.addAttribute("error_title", "Inserimento prescrizione non riuscito");
                model.addAttribute("error", errorMsg);
                model.addAttribute("redirect_link", "/patient/" + idPatient + "/addPrescription");
                return "error";
            }
        } else {
            model.addAttribute("error_title",
                    "Inserimento prescrizione non riuscito");
            model.addAttribute("error", "Paziente con id [" + idPatient.toString() + "] Non trovato");
            model.addAttribute("redirect_link", "patient/" + idPatient);
            return "error";
        }
    }

    @PostMapping("/addPatient")
    public String addPatient(Model model,
                             @RequestParam(name = "firstname") String firstname,
                             @RequestParam(name = "lastname") String lastname,
                             @RequestParam(name = "weight") Integer weight,
                             @RequestParam(name = "height") Integer height,
                             @RequestParam(name = "birthdate") String birthdate,
                             @RequestParam(name = "sex") String sex,
                             @RequestParam(name = "phonenumber") String phonenumber,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "address") String address,
                             @RequestParam(name = "allergies") String allergies,
                             @RequestParam(name = "cf") String cf) {
        Patient pat = new Patient(firstname, lastname, weight, height, birthdate, sex,
                phonenumber, email, address, allergies, cf);
        if (pat.selfCheck().isEmpty()) { //.equals("") me lo corregge così
            patientRepository.save(pat);
            return "redirect:/home";
        } else {
            model.addAttribute("error_title", "Aggiunta paziente fallita");
            model.addAttribute("error_message", pat.selfCheck());
            model.addAttribute("redirect_link", "/home");
            return "error";
        }
    }

    @RequestMapping("/patient/{id}/report")
    public String patientReport(
            @PathVariable(name = "id", required = true) Long id,
            Model model) {
        Optional<Patient> result = patientRepository.findById(id);
        if (result.isPresent()) {
            List<Prescription> prescriptions = prescriptionRepository.findByPatientID(id);
            List<Evaluation> evaluations = evaluationRepository.findByPatientID(id);

            String report = MyUtils.generatePatientReport(result.get(), prescriptions, evaluations);
            model.addAttribute("report", report);
            return "report";
        } else {
            model.addAttribute("error_title", "Paziente non trovato");
            model.addAttribute("error_message", "Nessun paziente trovato con questo ID");
            model.addAttribute("redirect_link", "/home");
            return "error";
        }
    }

    @RequestMapping("/patient/{id}/addEvaluation")
    public String addEvaluation(
            @PathVariable(name = "id", required = true) Long id,
            Model model
    ) {
        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());
            return "addEvaluation";
        } else {
            model.addAttribute("error_title", "Paziente non trovato");
            model.addAttribute("error_message", "Nessun paziente con id: " + id);
            model.addAttribute("redirect_link", "/home");
            return "error";
        }

    }

    @RequestMapping("patient/{id}/validateEvaluation")
    public String validateEvaluation(
            @PathVariable(name = "id", required = true) Long patientID,
            @RequestParam(name = "date", required = true) String data,
            @RequestParam(name = "value", required = true) Integer value,
            @RequestParam(name = "motivation", required = true) String motivation,
            @RequestParam(name = "notes", required = true) String notes,
            Model model) {

            Evaluation evaluation = new Evaluation(data, value, notes, motivation, patientID);
            String error = evaluation.selfCheck();
            if (error.isEmpty()) {
                evaluationRepository.save(evaluation);
                return "redirect:/patient/{id}";
            } else {
                model.addAttribute("error_title", "Aggiunta valutazione fallita");
                model.addAttribute("error_message", error);
                model.addAttribute("redirect_link", "/patient/" + patientID + "/addEvaluation");
                System.out.println("Error");
                return "error";
            }
        }
    }

