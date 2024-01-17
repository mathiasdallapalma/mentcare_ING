package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddPatientPO extends PageObject{
    public AddPatientPO(WebDriver d) {
        super(d);
    }

    @FindBy(id = "boxfirstname")
    private WebElement boxfirstname;

    @FindBy(id = "boxlastname")
    private WebElement boxlastname;

    @FindBy(id = "boxweight")
    private WebElement boxweight;

    @FindBy(id = "boxheight")
    private WebElement boxheight;

    @FindBy(id = "boxage")
    private WebElement boxage;

    @FindBy(id = "boxbirthdate")
    private WebElement boxbirthdate;

    @FindBy(id = "boxsex")
    private WebElement boxsex;

    @FindBy(id = "boxphonenumber")
    private WebElement boxphonenumber;

    @FindBy(id = "boxemail")
    private WebElement boxemail;

    @FindBy(id = "boxaddress")
    private WebElement boxaddress;

    @FindBy(id = "boxallergies")
    private WebElement boxallergies;

    @FindBy(id = "submitbutton")
    private WebElement submitbutton;

    @FindBy(id = "cancelbutton")
    private WebElement cancelbutton;

    public void addFirstname(String in){
        this.boxfirstname.clear();
        this.boxfirstname.sendKeys(in);
    }

    public void addLastname(String in){
        this.boxlastname.clear();
        this.boxlastname.sendKeys(in);
    }

    public void addWeight(String in){
        this.boxweight.clear();
        this.boxweight.sendKeys(in);
    }

    public void addHeight(String in){
        this.boxheight.clear();
        this.boxheight.sendKeys(in);
    }

    public void addAge(Integer in){
        this.boxage.clear();
        this.boxage.sendKeys(in.toString());
    }

    public void addBirthdate(String in){
        this.boxbirthdate.clear();
        this.boxbirthdate.sendKeys(in);
    }

    public void addSex(String in){
        this.boxsex.clear();
        this.boxsex.sendKeys(in);
    }

    public void addPhonenumber(String in){
        this.boxphonenumber.clear();
        this.boxphonenumber.sendKeys(in);
    }

    public void addEmail(String in){
        this.boxemail.clear();
        this.boxemail.sendKeys(in);
    }

    public void addAddress(String in){
        this.boxaddress.clear();
        this.boxaddress.sendKeys(in);
    }

    public void addAllergies(String in){
        this.boxallergies.clear();
        this.boxallergies.sendKeys(in);
    }

    public void clickSubmit(){
        this.submitbutton.click();
    }

    public void clickCancel(){
        this.cancelbutton.click();
    }

}
