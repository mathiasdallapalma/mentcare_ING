package mentcare.models;

import mentcare.utils.Utils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public Patient(String firstname, String lastname, Integer weight, Integer height, Integer age, String birthdate, String sex,
                   String phonenumber, String email, String address, String allergies, String cf) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.birthdate = birthdate;
        this.sex = sex;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.allergies = getAllergiesList(allergies);
        this.cf = cf;
    }

    private List<String> getAllergiesList(String allergies) {
        return Utils.createAllergiesList(allergies);
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
     * @return
     */
    public String selfCheck(){
        //TODO
        return "";
    }


}