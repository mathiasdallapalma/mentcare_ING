package mentcare;

import mentcare.models.Evaluation;
import mentcare.models.Patient;
import mentcare.models.Prescription;
import mentcare.utils.MyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UnitTest{

    @Test
    public void evaluationSelfCheckOK(){
        Evaluation e = new Evaluation("2024-02-08", 20, "note", "motivo", 10L);
        Assert.assertEquals("", e.selfCheck());
    }

    @Test
    public void evaluationSelfCheckFail(){
        Evaluation e = new Evaluation("2024-02-08", 100, "note", "", 10L);
        Assert.assertTrue(e.selfCheck().contains("\nIl valore deve essere compreso tra 0 e 50<br>"));
        Assert.assertTrue(e.selfCheck().contains("\nLa motivazione non può essere vuota<br>"));
    }

    @Test
    public void patientCalculateAge(){
        String firstname = "Marianna";
        String lastname = "Marroni";
        Integer weight = 70;
        Integer height = 170;
        String birthdate = "2000-01-01";
        String sex = "Femmina";
        String phonenumber = "123 4567890";
        String email = "default@mentcare.org";
        String address = "via vittoria 2";
        String allergies = "tachipirina,pioppi,pesce";
        String cf = "codice fiscale 1";
        Patient p =  new Patient(firstname, lastname, weight, height, birthdate, sex, phonenumber, email, address,
                allergies, cf);

        LocalDate now = LocalDate.now();
        String newBdate = "2010-05-05";
        LocalDate bdate = LocalDate.parse(newBdate);
        Integer expectedAge = now.getYear()- bdate.getYear();
        p.setBirthdateAndAge(newBdate);
        Integer calculatedAge = p.getAge();
        Assert.assertEquals(expectedAge, calculatedAge);
    }

    @Test
    public void utilsGetAllergiesList(){
        String allergies = "tachipirina,pioppi,pesce";
        List<String> allergiyList = MyUtils.createAllergiesList(allergies);
        for(String s: allergiyList){
            Assert.assertTrue(allergies.contains(s));
        }
    }

    @Test
    public void patientSelfCheckOK(){
        String firstname = "Marianna";
        String lastname = "Marroni";
        Integer weight = 70;
        Integer height = 170;
        String birthdate = "2000-01-01";
        String sex = "Femmina";
        String phonenumber = "123 4567890";
        String email = "default@mentcare.org";
        String address = "via vittoria 2";
        String allergies = "tachipirina,pioppi,pesce";
        String cf = "codice fiscale 1";
        Patient p =  new Patient(firstname, lastname, weight, height, birthdate, sex, phonenumber, email, address,
                allergies, cf);
        Assert.assertEquals("", p.selfCheck());
    }

    @Test
    public void patientSelfCheckFail(){
        String firstname = "Marianna";
        String lastname = "Marroni";
        Integer weight = 1000;
        Integer height = 1700;
        String birthdate = "1650-01-01";
        String sex = "Femmina";
        String phonenumber = "123 4567890";
        String email = "default@mentcare.org";
        String address = "via vittoria 2";
        String allergies = "tachipirina,pioppi,pesce";
        String cf = "codice fiscale 1";
        Patient p =  new Patient(firstname, lastname, weight, height, birthdate, sex, phonenumber, email, address,
                allergies, cf);
        Assert.assertNotEquals("", p.selfCheck());
        Assert.assertTrue(p.selfCheck().contains("Il peso inserito dovrebbe essere maggiore di 0 e minore di 300<br>"));
        Assert.assertTrue(p.selfCheck().contains("L'altezza inserita dovrebbe essere maggiore di 0 e minore di 250<br>"));
        Assert.assertTrue(p.selfCheck().contains("La data di nascita dovrebbe essere maggiore di 150 anni fa e minore dell'anno corrente<br>"));
    }

    @Test
    public void prescriptionSelfCheckOK(){
        Prescription p = new Prescription("medicinale", 10, "nota", 10L);
        Assert.assertEquals("", p.selfCheck(new ArrayList<String>()));
    }

    @Test
    public void prescriptionSelfCheckFail(){
        ArrayList<String> listaAllergie = new ArrayList<>();
        String med = "pericillina";
        listaAllergie.add(med);
        Prescription p = new Prescription(med, 1000, "nota", 10L);
        Assert.assertTrue(p.selfCheck(listaAllergie).contains(med));
        Assert.assertTrue(p.selfCheck(listaAllergie).contains("La quantità inserita dovrebbe essere maggiore di 0 e minore di 100 mg !<br>"));
    }
}
