package mentcare;

import mentcare.pageObjects.AddPatientPO;
import mentcare.pageObjects.ErrorPO;
import mentcare.pageObjects.HomePO;

import org.junit.Assert;
import org.junit.Test;


public class AddPatientTest extends SystemTest{

    @Test
    public void addPatientTestOK(){
        String firstname = "Marianna";
        String lastname = "Marroni";
        Integer weight = 70;
        Integer height = 170;
        String birthdate = "default";
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
        addPatientPage.addWeight(weight.toString());
        addPatientPage.addHeight(height.toString());
        addPatientPage.addBirthdate(birthdate);
        addPatientPage.addSex(sex);
        addPatientPage.addPhonenumber(phonenumber);
        addPatientPage.addEmail(email);
        addPatientPage.addAddress(address);
        addPatientPage.addAllergies(allergies);
        addPatientPage.addCf(cf);

        HomePO home2 = new HomePO(addPatientPage.clickSubmit());
        Assert.assertTrue(home2.isCfPresent().contains(firstname)); //non sono sicuro sia corretto
    }

    @Test
    public void addPatientTestWrongValues(){
        String firstname = "Andrei";
        String lastname = "Di là";
        Integer weight = 700;
        Integer height = 1700;
        String birthdate = "05-03-1800";
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
        Assert.assertTrue(errMess.contains("peso"));
        Assert.assertTrue(errMess.contains("altezza"));
        Assert.assertTrue(errMess.contains("data di nascita"));
    }

    @Test
    public void addPatientTestErrorSameCF(){
        String firstname = "John";
        String lastname = "Wick";
        Integer weight = 80;
        Integer height = 177;
        String birthdate = "default";
        String sex = "Maschio";
        String phonenumber = "123 4567890";
        String email = "default@easykills.org";
        String address = "??Unknown??";
        String allergies = "";
        String cf = "AAA";

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

        home = new HomePO(addPatientPage.clickSubmit());
        AddPatientPO addPatientPage2 = new AddPatientPO(home.clickAddPatient());

        addPatientPage2.addFirstname(firstname);
        addPatientPage2.addLastname(lastname);
        addPatientPage2.addWeight(weight.toString());
        addPatientPage2.addHeight(height.toString());
        addPatientPage2.addBirthdate(birthdate);
        addPatientPage2.addSex(sex);
        addPatientPage2.addPhonenumber(phonenumber);
        addPatientPage2.addEmail(email);
        addPatientPage2.addAddress(address);
        addPatientPage2.addAllergies(allergies);
        addPatientPage2.addCf(cf);

        ErrorPO err = new ErrorPO(addPatientPage2.clickSubmit());
        Assert.assertEquals("Utente con lo stesso CF e' gia' registrato.", err.getErrorMessage());
    }

    @Test
    public void addPatientCancel(){
        HomePO home = new HomePO(driver);
        home.loadPage();
        AddPatientPO addPatPage = new AddPatientPO(home.clickAddPatient());
        home = new HomePO(addPatPage.clickCancel());
        Assert.assertEquals("home",home.getTitle());
    }

    @Test
    public void addPatientNoValues(){
        HomePO home = new HomePO(driver);
        home.loadPage();
        AddPatientPO addPatPage = new AddPatientPO(home.clickAddPatient());
        ErrorPO errPage = new ErrorPO(addPatPage.clickSubmit());
        Assert.assertEquals(errPage.getTitle().toLowerCase(),"aggiunta paziente fallita");
    }

}
