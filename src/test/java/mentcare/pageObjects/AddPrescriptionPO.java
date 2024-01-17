package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddPrescriptionPO extends PageObject{
    public AddPrescriptionPO(WebDriver d) {
        super(d);
    }

    @FindBy(id = "boxdrug1")
    private WebElement boxdrug1;

    @FindBy(id = "boxquantity1")
    private WebElement boxquantity1;

    @FindBy(id = "boxnote1")
    private WebElement boxnote1;

    @FindBy(id = "submitbutton")
    private WebElement submitbutton;

    @FindBy(id = "cancelbutton")
    private WebElement cancelbutton;


    public void adddrug(String in){
        this.boxdrug1.clear();
        this.boxdrug1.sendKeys(in);
    }

    public void addquantity(Integer in){
        this.boxquantity1.clear();
        this.boxquantity1.sendKeys(in.toString());
    }

    public void addnote(String in){
        this.boxnote1.clear();
        this.boxnote1.sendKeys(in);
    }

    public void clickSubmit(){
        this.submitbutton.click();
    }

    public void clickCancel(){
        this.cancelbutton.click();
    }
}
