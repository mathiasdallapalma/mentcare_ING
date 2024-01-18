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

    @FindBy(id = "title")
    private WebElement title;

    //@FindBy(xpath = "//tr[td//text()[contains(., 'targetString')]]")
    @FindBy(xpath = "//tr/td[last()]")
    private WebElement table;

    public String getTableLastRow(){
        return table.getText();
    }

    public String getTitle(){
        return title.getText();
    }

    /**
     * Si vuole andare alla pagina di un paziente specifico, l'input viene utilizzato per cercare nella tabella
     * il link con il nome e viene cliccato, reindirizzando la navigazione.
     */
    public PatientViewPO clickName(String nameToClick){
        //TODO
        addpatientbutton.click();
        return new PatientViewPO(driver);
    }

    public void loadPage(){
        driver.get("localhost:8080/home");
    }

    public AddPatientPO clickAddPatient(){
        addpatientbutton.click();
        return new AddPatientPO(driver);
    }

    public boolean isError() {
        return !driver.getCurrentUrl().equals("http://localhost:8080/home");
    }

}
