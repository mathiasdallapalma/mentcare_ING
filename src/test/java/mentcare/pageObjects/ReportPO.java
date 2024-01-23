package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReportPO extends PageObject{

    @FindBy(id = "title")
    private WebElement title;

    public ReportPO(WebDriver d) {
        super(d);
    }

    public String getTitle(){
        return title.getText();
    }


}
