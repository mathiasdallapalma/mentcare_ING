package mentcare;




/*---*/


import mentcare.pageObjects.ErrorPO;
import mentcare.pageObjects.HomePO;
import mentcare.pageObjects.LoginPO;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;


public class LoginTest extends SystemTest {

    @Test
    public void testLoginOK() {
        LoginPO loginPO = new LoginPO(driver);
        loginPO.gotoLink();

        loginPO.insertUsername("admin");
        loginPO.insertPassword("admin");
        HomePO homepagePO=new HomePO(loginPO.clickSubmit());

        assertEquals("Patient list message expected", "Patient list", homepagePO.getTitle());
    }

    public void testLoginFAIL() {
        LoginPO loginPO = new LoginPO(driver);
        loginPO.gotoLink();

        loginPO.insertUsername("admin");
        loginPO.insertPassword("");

        ErrorPO ret = new ErrorPO(loginPO.clickSubmit());
        Assert.assertTrue(ret.getErrorMessage().toLowerCase().contains("valori mancanti"));
        Assert.assertTrue(ret.getErrorMessage().toLowerCase().contains(""));
        //assertEquals("Login failed, status code:", "Login failed", errorPO.getErrorMessage());
    }

}