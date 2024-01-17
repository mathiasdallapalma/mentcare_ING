package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePO extends PageObject{

    public HomePO(WebDriver d) {
        super(d);
    }

    @FindBy(id = "reportbutton")
    private WebElement reportbutton;

    @FindBy(id = "addpatientbutton")
    private WebElement addpatientbutton;

    //@FindBy(xpath = "//tr[td//text()[contains(., 'targetString')]]")
    @FindBy(xpath = "//tr/td")
    private WebElement table;

    public WebElement getTable(){
        return table;
    }


}
