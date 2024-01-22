package mentcare;

import mentcare.models.Patient;
import mentcare.models.Visit;
import mentcare.repositories.PatientRepository;
import mentcare.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepo;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Visit> lVisite = visitRepo.findAll();
        model.addAttribute("visits", lVisite);
        Iterable<Patient> lPatients = patientRepository.findAll();
        model.addAttribute("patients", lPatients);
        return "home";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
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


}