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

        assertEquals("Title page", "Home", homepagePO.getTitle());
    }

    @Test
    public void testLoginFAIL() {
        LoginPO loginPO = new LoginPO(driver);
        loginPO.gotoLink();

        loginPO.insertUsername("admin");
        loginPO.insertPassword("...");

        ErrorPO errorPO = new ErrorPO(loginPO.clickSubmit());

        assertEquals("Tile page", "Login fallito", errorPO.getTitle());
    }

}