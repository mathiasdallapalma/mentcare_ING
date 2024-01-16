package mentcare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @Autowired
    private PazienteRepository repository;

    @RequestMapping("/")
    public String index(){
        return "list";
    }

    @RequestMapping("/input")
    public String input(){
        return "input"; //TODO: input fa riferimento a "input.html" da cambiare nome e la pagina
    }

    @RequestMapping("/create")
    public String create(
            @RequestParam(name="firstname", required=true) String firstname,
            @RequestParam(name="lastname", required=true) String lastname) {
        repository.save(new Paziente(firstname,lastname));
        return "redirect:/list";
    }


}