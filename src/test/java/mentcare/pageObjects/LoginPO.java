package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPO extends PageObject{

    @FindBy(name = "firstname")
    private WebElement nameField;

    @FindBy(name = "lastname")
    private WebElement lastnameField;

    @FindBy(xpath = "//input[last()]")
    private WebElement submitbutton;

    public LoginPO(WebDriver d) {
        super(d);
    }

    public void clickSubmit(){
        this.submitbutton.click();
    }

    public void insertName(String name){
        this.nameField.clear();
        this.nameField.sendKeys(name);
    }

    public void insertLastName(String lastname){
        this.lastnameField.clear();
        this.lastnameField.sendKeys(lastname);
    }

}
