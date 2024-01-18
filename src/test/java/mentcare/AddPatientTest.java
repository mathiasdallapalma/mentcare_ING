package mentcare;

import mentcare.models.Patient;
import mentcare.pageObjects.AddPatientPO;
import mentcare.pageObjects.HomePO;
import mentcare.pageObjects.PageObject;
import org.junit.Assert;
import org.junit.Test;

public class AddPatientTest extends SystemTest{

    @Test
    public void addPatientTest(){
        String firstname = "default";
        String lastname = "default";
        Integer weight = 70;
        Integer height = 170;
        Integer age = 40;
        String birthdate = "default";
        String sex = "default";
        String phonenumber = "default";
        String email = "default";
        String address = "default";
        String allergies = "med1 med2 pioppi";
        String cf = "codice fiscale 1";

        HomePO home = new HomePO(driver);
        AddPatientPO page = home.clickAddPatient();

        page.addFirstname(firstname);
        page.addLastname(lastname);
        page.addWeight(weight.toString());
        page.addHeight(height.toString());
        page.addAge(age);
        page.addBirthdate(birthdate);
        page.addSex(sex);
        page.addPhonenumber(phonenumber);
        page.addEmail(email);
        page.addAddress(address);
        page.addAllergies(allergies);
        page.addCf(cf);

        home = page.clickSubmit();
        Assert.assertTrue(home.getTableLastRow().contains(firstname)); //non sono sicuro sia corretto
    }

    @Test
    public void addPatientTestWrongValues(){
        String firstname = "default";
        String lastname = "default";
        Integer weight = 700;
        Integer height = 1700;
        Integer age = 400;
        String birthdate = "default";
        String sex = "default";
        String phonenumber = "default";
        String email = "default";
        String address = "default";
        String allergies = "med1 med2 pioppi";
        String cf = "codice fiscale 2";

        HomePO home = new HomePO(driver);
        AddPatientPO page = home.clickAddPatient();

        page.addFirstname(firstname);
        page.addLastname(lastname);
        page.addWeight(weight.toString());
        page.addHeight(height.toString());
        page.addAge(age);
        page.addBirthdate(birthdate);
        page.addSex(sex);
        page.addPhonenumber(phonenumber);
        page.addEmail(email);
        page.addAddress(address);
        page.addAllergies(allergies);
        page.addCf(cf);

        home = page.clickSubmit();

        Assert.assertTrue(home.isError());
        //TODO
    }

    @Test
    public void addPatientTestErrorSameCF(){
        String firstname = "default";
        String lastname = "default";
        Integer weight = 70;
        Integer height = 170;
        Integer age = 400;
        String birthdate = "default";
        String sex = "default";
        String phonenumber = "default";
        String email = "default";
        String address = "default";
        String allergies = "med1 med2 pioppi";
        String cf = "codice fiscale 1";

        HomePO home = new HomePO(driver);
        AddPatientPO page = home.clickAddPatient();

        page.addFirstname(firstname);
        page.addLastname(lastname);
        page.addWeight(weight.toString());
        page.addHeight(height.toString());
        page.addAge(age);
        page.addBirthdate(birthdate);
        page.addSex(sex);
        page.addPhonenumber(phonenumber);
        page.addEmail(email);
        page.addAddress(address);
        page.addAllergies(allergies);
        page.addCf(cf);

        home = page.clickSubmit();
        //TODO
    }

}
