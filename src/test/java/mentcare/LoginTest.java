package mentcare;




/*---*/


import mentcare.pageObjects.LoginPO;
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
        HomepagePO homepagePO=loginPO.clickSubmit();

        assertEquals("Patient list message expected", "Patient list", homepagePO.getTitle());
    }

    public void testLoginFAIL() {
        LoginPO loginPO = new LoginPO(driver);
        loginPO.gotoLink();

        loginPO.insertUsername("admin");
        loginPO.insertPassword("");
        int status=loginPO.clickSubmit().getStatus(); //TODO come dimostro che il login è fallito?

        assertEquals("Login failed, status code:", 403, status);
    }

}