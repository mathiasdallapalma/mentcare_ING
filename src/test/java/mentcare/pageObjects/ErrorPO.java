package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPO extends PageObject{
    public ErrorPO(WebDriver d) {
        super(d);
    }

    @FindBy(id = "error_title")
    private WebElement error_title;

    @FindBy(id = "error_message")
    private WebElement error_message;

    public String getErrorMessage(){
        return error_message.getText();
    }

    public String getTitle() {
        return error_title.getText();
    }
}
