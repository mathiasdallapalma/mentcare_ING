package mentcare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddEvaluationPO extends PageObject{


    private String date;
    private String value;
    private String notes;
    private String motivation;

    @FindBy(id = "date_input")
    private WebElement dateField;

    @FindBy(id = "value_input")
    private WebElement valueField;

    @FindBy(id = "notes_input")
    private WebElement notesField;

    @FindBy(id = "motivation_input")
    private WebElement motivationField;

    @FindBy(id = "submit_btn")
    private WebElement submitButton;

    public AddEvaluationPO(WebDriver d) {
        super(d);
    }

    public ViewPatientPO clickSubmit(){
        this.submitButton.click();
        return new ViewPatientPO(driver);
    }

    public void insertDate(String date){
        this.dateField.clear();
        this.dateField.sendKeys(date);
    }

    public void insertValue(String value){
        this.valueField.clear();
        this.valueField.sendKeys(value);
    }

    public void insertNotes(String notes){
        this.notesField.clear();
        this.notesField.sendKeys(notes);
    }

    public void insertMotivation(String motivation){
        this.motivationField.clear();
        this.motivationField.sendKeys(motivation);
    }

}
