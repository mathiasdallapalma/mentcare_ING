package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPO extends PageObject{
    public ErrorPO(WebDriver d) {
        super(d);
    }

    @FindBy(id = "error_text")
    private WebElement error_text;

    private String getErrorMessage(){
        return error_text.getText();
    }

}
