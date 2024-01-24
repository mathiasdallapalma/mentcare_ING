package mentcare;

import mentcare.pageObjects.AddPatientPO;
import mentcare.pageObjects.ErrorPO;
import mentcare.pageObjects.HomePO;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AddPatientTest extends SystemTest{

    @Test
    public void addPatientTestOK(){
        String firstname = "Marianna";
        String lastname = "Marroni";
        String weight = "70";
        String height = "170";
        String birthdate = "2000-01-01";
        String sex = "Femmina";
        String phonenumber = "123 4567890";
        String email = "default@mentcare.org";
        String address = "via vittoria 2";
        String allergies = "tachipirina,pioppi,pesce";
        String cf = "codice fiscale 1";

        HomePO home = new HomePO(driver);
        home.loadPage();
        AddPatientPO addPatientPage = new AddPatientPO(home.clickAddPatient());

        addPatientPage.addFirstname(firstname);
        addPatientPage.addLastname(lastname);
        addPatientPage.addWeight(weight);
        addPatientPage.addHeight(height);
        addPatientPage.addBirthdate(birthdate);
        addPatientPage.addSex(sex);
        addPatientPage.addPhonenumber(phonenumber);
        addPatientPage.addEmail(email);
        addPatientPage.addAddress(address);
        addPatientPage.addAllergies(allergies);
        addPatientPage.addCf(cf);

        HomePO home2 = new HomePO(addPatientPage.clickSubmit());


        assertEquals("home",home2.getTitle().toLowerCase());

    }

    @Test
    public void addPatientTestWrongValues(){
        String firstname = "Andrei";
        String lastname = "Di là";
        Integer weight = 700;
        Integer height = 1700;
        String birthdate = "1800-03-05";
        String sex = "Maschio";
        String phonenumber = "123 4567890";
        String email = "elicottero@rosacanina.com";
        String address = "default";
        String allergies = "vita";
        String cf = "codice fiscale 2";

        HomePO home = new HomePO(driver);
        home.loadPage();
        AddPatientPO addPatientPage = new AddPatientPO(home.clickAddPatient());

        addPatientPage.addFirstname(firstname);
        addPatientPage.addLastname(lastname);
        addPatientPage.addWeight(weight.toString());
        addPatientPage.addHeight(height.toString());
        addPatientPage.addBirthdate(birthdate);
        addPatientPage.addSex(sex);
        addPatientPage.addPhonenumber(phonenumber);
        addPatientPage.addEmail(email);
        addPatientPage.addAddress(address);
        addPatientPage.addAllergies(allergies);
        addPatientPage.addCf(cf);

        ErrorPO errPage = new ErrorPO(addPatientPage.clickSubmit());
        String errMess = errPage.getErrorMessage().toLowerCase();

        assertEquals("Error expected", "Aggiunta paziente fallita", errPage.getTitle());
        assertTrue(errMess.contains("peso"));
        assertTrue(errMess.contains("altezza"));
        assertTrue(errMess.contains("data di nascita"));
    }

    @Test
    public void addPatientTestErrorSameCF(){
        String firstname = "Marianna";
        String lastname = "Marroni";
        String weight = "70";
        String height = "170";
        String birthdate = "2000-01-01";
        String sex = "Femmina";
        String phonenumber = "123 4567890";
        String email = "default@mentcare.org";
        String address = "via vittoria 2";
        String allergies = "tachipirina,pioppi,pesce";
        String cf = "codice fiscale 3";

        HomePO home = new HomePO(driver);
        home.loadPage();
        AddPatientPO addPatientPage = new AddPatientPO(home.clickAddPatient());

        addPatientPage.addFirstname(firstname);
        addPatientPage.addLastname(lastname);
        addPatientPage.addWeight(weight);
        addPatientPage.addHeight(height);
        addPatientPage.addBirthdate(birthdate);
        addPatientPage.addSex(sex);
        addPatientPage.addPhonenumber(phonenumber);
        addPatientPage.addEmail(email);
        addPatientPage.addAddress(address);
        addPatientPage.addAllergies(allergies);
        addPatientPage.addCf(cf);

        home = new HomePO(addPatientPage.clickSubmit());
        AddPatientPO addPatientPage2 = new AddPatientPO(home.clickAddPatient());

        addPatientPage2.addFirstname(firstname);
        addPatientPage2.addLastname(lastname);
        addPatientPage2.addWeight(weight);
        addPatientPage2.addHeight(height);
        addPatientPage2.addBirthdate(birthdate);
        addPatientPage2.addSex(sex);
        addPatientPage2.addPhonenumber(phonenumber);
        addPatientPage2.addEmail(email);
        addPatientPage2.addAddress(address);
        addPatientPage2.addAllergies(allergies);
        addPatientPage2.addCf(cf);

        ErrorPO err = new ErrorPO(addPatientPage2.clickSubmit());

        assertEquals("Error expected", "Aggiunta paziente fallita", err.getTitle());
        assertEquals("Utente con lo stesso CF e' gia' registrato.", err.getErrorMessage());
    }

    @Test
    public void addPatientCancel(){
        HomePO home = new HomePO(driver);
        home.loadPage();
        AddPatientPO addPatPage = new AddPatientPO(home.clickAddPatient());
        home = new HomePO(addPatPage.clickCancel());
        assertEquals("Home",home.getTitle());
    }

    @Test
    public void addPatientNoValues(){
        HomePO home = new HomePO(driver);
        home.loadPage();
        AddPatientPO addPatPage = new AddPatientPO(home.clickAddPatient());
        ErrorPO errPage = new ErrorPO(addPatPage.clickSubmit());
        assertEquals(errPage.getTitle().toLowerCase(),"aggiunta paziente fallita");
    }

}
