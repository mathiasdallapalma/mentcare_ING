package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPO extends PageObject{

    @FindBy(id = "username_input")
    private WebElement usernameField;

    @FindBy(id = "password_input")
    private WebElement passwordField;

    @FindBy(id = "submit_btn")
    private WebElement submitButton;

    public LoginPO(WebDriver d) {
        super(d);
    }

    public WebDriver clickSubmit(){
        this.submitButton.click();
        return driver;
    }

    public void insertUsername(String username){
        this.usernameField.clear();
        this.usernameField.sendKeys(username);
    }

    public void insertPassword(String password){
        this.passwordField.clear();
        this.passwordField.sendKeys(password);
    }

    public void gotoLink() {
        driver.get("http://localhost:8080/login");
    }
}
