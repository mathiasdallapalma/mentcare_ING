package mentcare;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String age;
    private String birthdate;
    private String sex;
    private String phonenumber;
    private String email;
    private String address;
    private List<String> allergies;

    protected Patient() {}

    public Patient(String firstname, String lastname, Integer weight, Integer height, String age, String birthdate, String sex,
                   String phonenumber, String email, String address, String allergies) {
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
    }

    private List<String> getAllergiesList(String allergies) {
        //TODO
        return new ArrayList<>();
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

    public String getAge() {
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
}