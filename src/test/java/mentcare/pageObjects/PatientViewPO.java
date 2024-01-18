package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PatientViewPO extends PageObject{

    @FindBy(id = "name")
    private WebElement nameText;

    @FindBy(id = "add_evaluation_btn")
    private WebElement addEvaluationButton;

    @FindBy(id = "add_prescription_btn")
    private WebElement addPrescriptionButton;

    @FindBy(id = "report_btn")
    private WebElement reportButton;

    @FindBy(xpath = "//table[@id='evaluations_table']/tbody/tr[first()]")
    private WebElement lastEvaluationRow;

    public PatientViewPO(WebDriver d) {
        super(d);
    }

    public AddEvaluationPO clickAddEvaluation(){
        this.addEvaluationButton.click();
        return new AddEvaluationPO(driver);
    }
    public AddPrescriptionPO clickAddPrescription(){
        this.addPrescriptionButton.click();
        return new AddPrescriptionPO(driver);
    }

    public String getNameText(){
        return this.nameText.getText();
    }

    public String getLastEvaluation_toString() {
        return this.lastEvaluationRow.getText();
    }

    public String getName() {
        return nameText.getText();
    }

    public void loadWithID(int i)  {
        driver.get("http://localhost:8080/patient/"+i);
    }

    public boolean isError(int i) {
        if (!driver.getCurrentUrl().equals("http://localhost:8080/patient/"+i)) {
            return true;
        }
        return false;
    }

    public ReportPO clickReport() {
        this.reportButton.click();
        return new ReportPO(driver);
    }
}
