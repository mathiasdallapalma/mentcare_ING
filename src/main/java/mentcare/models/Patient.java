package mentcare.models;

import mentcare.utils.MyUtils;
import net.sf.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private Integer weight;
    private Integer height;
    private Integer age;
    private String birthdate;
    private String sex;
    private String phonenumber;
    private String email;
    private String address;
    @ElementCollection(targetClass=String.class)
    private List<String> allergies;
    private String cf; //codice fiscale

    protected Patient() {}

    public Patient(String firstname, String lastname, Integer weight, Integer height, String birthdate, String sex,
                   String phonenumber, String email, String address, String allergies, String cf) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.weight = weight;
        this.height = height;
        this.age = calculateAge(birthdate);
        this.birthdate = birthdate;
        this.sex = sex;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.allergies = getAllergiesList(allergies);
        this.cf = cf;
    }

    private Integer calculateAge(String bdate) {
        if(bdate.isEmpty()){
            return null;
        }
        System.out.println(bdate);
        LocalDate now = LocalDate.now();
        LocalDate birthdate = LocalDate.parse(bdate);
        System.out.println(birthdate);
        return LocalDate.now().getYear() - LocalDate.parse(bdate).getYear();
    }

    private List<String> getAllergiesList(String allergies) {
        return MyUtils.createAllergiesList(allergies);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getAge() {
        return age;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getSex() {
        return sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public String getCf() {
        return cf;
    }
    public Long getId() {
        return id;
    }

    /**
     * Metodo controllo valori inseriti per inserimento corretto. Per i vari valori presenti si controlla
     * la validità ad esempio peso > 0 e < 450 kg.
     * Per ogni voce, si aggiunge un messaggio di errore in base al campo sbagliato
     */
    public String selfCheck(){
        String errorToRet = "";
        if(this.getWeight() == null || this.getHeight() == null || this.getAge() == null || this.getCf() == null){
            return "Alcuni valori non sono stati inseriti!";
        }
        if(weight > 300 || weight < 0){
            errorToRet = errorToRet.concat("Il peso inserito dovrebbe essere maggiore di 0 e minore di 300<br>");
        }
        if(this.getHeight() > 250 || this.getHeight() < 0){
            errorToRet = errorToRet.concat("L'altezza inserita dovrebbe essere maggiore di 0 e minore di 250<br>");
        }
        if(this.getAge() > 150 || this.getAge() < 0){
            errorToRet = errorToRet.concat("La data di nascita dovrebbe essere maggiore di 150 anni fa e minore dell'anno corrente<br>");
        }
        return errorToRet;
    }


}