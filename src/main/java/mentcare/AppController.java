package mentcare;

import mentcare.models.Patient;
import mentcare.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @Autowired
    private PatientRepository patientRepository;

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

    @RequestMapping("/addPatient")
    public String addPatientPage(){
        return "addPatient";
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