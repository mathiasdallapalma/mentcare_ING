package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject{

    protected WebDriver driver;

    public PageObject(WebDriver d){
        this.driver = d;
        PageFactory.initElements(d, this);
    }

    public WebDriver getDriver(){
        return this.driver;
    }


}
