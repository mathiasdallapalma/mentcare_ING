package mentcare;

import mentcare.models.Evaluation;
import mentcare.models.Patient;
import mentcare.models.Prescription;
import mentcare.utils.MyUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UnitTest{

    private Patient patient;
    private ArrayList<Patient> patients;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<Evaluation> evaluations;

    @Before
    public void createmodels(){
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
        patient =  new Patient(firstname, lastname, weight, height, birthdate, sex, phonenumber, email, address,
                allergies, cf);

        patients=new ArrayList<>();
        patients.add(new Patient("A", "1", 60, 150, "2000-01-01", "Femmina", "", "", "",
                "", ""));
        patients.add(new Patient("B", "2", 80, 180, "2003-01-01", "Femmina", "", "", "",
                "", ""));
        patients.add(new Patient("c", "3", 90, 200, "2002-01-01", "Maschio", "", "", "",
                "", ""));



        Prescription prescription1 = new Prescription("medicinale", 10, "nota", 10L);
        Prescription prescription2 = new Prescription("medicinale2", 20, "nota", 10L);
        Prescription prescription3 = new Prescription("medicinale2", 40, "nota", 10L);
        prescriptions=new ArrayList<>();
        prescriptions.add(prescription1);
        prescriptions.add(prescription2);
        prescriptions.add(prescription3);

        Evaluation evaluation1 = new Evaluation("2024-02-07", 70, "note", "", 10L);
        Evaluation evaluation2 = new Evaluation("2024-02-08", 100, "note", "", 10L);
        Evaluation evaluation3 = new Evaluation("2024-02-09", 90, "note", "", 10L);
        evaluations=new ArrayList<>();
        evaluations.add(evaluation1);
        evaluations.add(evaluation2);
        evaluations.add(evaluation3);
    }

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
        LocalDate now = LocalDate.now();
        String newBdate = "2010-05-05";
        LocalDate bdate = LocalDate.parse(newBdate);
        Integer expectedAge = now.getYear()- bdate.getYear();
        patient.setBirthdateAndAge(newBdate);
        Integer calculatedAge = patient.getAge();
        Assert.assertEquals(expectedAge, calculatedAge);
    }

    @Test
    public void utilsGetAllergiesList(){
        String allergies = "tachipirina,pioppi,pesce";
        List<String> expectedAllergies =new ArrayList<>();
        expectedAllergies.add("tachipirina");
        expectedAllergies.add("pioppi");
        expectedAllergies.add("pesce");
        List<String> allergiyList = MyUtils.createAllergiesList(allergies);
        Assert.assertEquals("Allergies: ",expectedAllergies,allergiyList);
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

    @Test
    public void patientReportCalc(){

        Map<String, Object> res=MyUtils.calc2(patient,prescriptions,evaluations);

        //(70+100+80)/3=86.666664
        Assert.assertEquals("averageValue",86.666664f,res.get("averageValue"));

        //mostAssumedDrugs is medicinale2 : 2 times;
        Assert.assertEquals("mostAssumedDrugs","medicinale2",res.get("mostAssumedDrugs"));

        HashMap<String, Integer> mapDrugs = new HashMap<>();
        mapDrugs.put("medicinale2", 2);
        mapDrugs.put("medicinale", 1);

        Assert.assertEquals("mapDrugs",mapDrugs,res.get("mapDrugs"));

    }

    @Test
    public void generalReportCalc(){

        Map<String, Object> res=MyUtils.calc1(patients);

        //(150+180+200)/3=176.66667
        Assert.assertEquals("avgHeight",176.66667f,res.get("avgHeight"));

        //(60+80+90)/3=76.666664
        Assert.assertEquals("avgWeight",76.666664f,res.get("avgWeight"));

        //(24+21+22)/3=22.333334
        Assert.assertEquals("avgAge",22.333334f,res.get("avgAge"));

        Assert.assertEquals("maleCount",1,res.get("maleCount"));
        Assert.assertEquals("femaleCount",2,res.get("femaleCount"));
        Assert.assertEquals("count",3,res.get("count"));

    }




}
