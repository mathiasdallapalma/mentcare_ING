package mentcare;

import mentcare.models.Patient;
import mentcare.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @Autowired
    private PatientRepository repository;

    @RequestMapping("/")
    public String index(){
        return "list";
    }

    @RequestMapping("/input")
    public String input(){
        return "input"; //TODO: input fa riferimento a "input.html" da cambiare nome e la pagina
    }




}