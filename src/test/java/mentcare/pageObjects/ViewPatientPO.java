package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;

public class ViewPatientPO extends PageObject{

    @FindBy(id = "name")
    private WebElement nameText;

    @FindBy(id = "add_evaluation_btn")
    private WebElement addEvaluationButton;

    @FindBy(id = "add_prescription_btn")
    private WebElement addPrescriptionButton;

    @FindBy(XPath = "//table[@id='evaluations_table']/tbody/tr[first()]")
    private WebElement lastEvaluationRow;

    public ViewPatientPO(WebDriver d) {
        super(d);
    }

    public AddEvaluationPO clickAddEvaluation(){
        this.addEvaluationButton.click();
        return new AddEvaluationPO(driver);
    }
    public AddPrescriptionPO clickAddPrescription(){
        this.addPrescriptionButton.click();
        return new AddPrescritionPO(driver);
    }

    public String getNameText(){
        return this.nameText.getText();
    }

    public void loadPage() {
        driver.get("http://localhost:8080/patient/1");
    }

    public String getLastEvaluation_toString() {
        return this.lastEvaluationRow.getText();
    }
}
