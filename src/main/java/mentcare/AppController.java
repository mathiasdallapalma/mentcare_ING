package mentcare;

import mentcare.models.Patient;
import mentcare.repositories.PatientRepository;
import mentcare.repositories.PrescriptionRepository;
import mentcare.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/login")
    public String login(){


        return "login";
    }

    @RequestMapping("/validateLogin")
    public String update(
            @RequestParam(name="username", required=true) String username,
            @RequestParam(name="password", required=true) String password,
            Model model) {
        if(Utils.validateLogin(username,password))
            return "redirect:/home";
        else
            model.addAttribute("error_title", "Login fallito");
            model.addAttribute("error_message", "Username o password errati");
            model.addAttribute("redirect_link", "/login");
            return "error";
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