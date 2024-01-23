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

    @FindBy(id= "title")
    private WebElement title;


    //@FindBy(xpath = "//tr[td//text()[contains(., 'targetString')]]")
    @FindBy(xpath = "//tr/td[last()]")
    private WebElement table;

    public String getTableLastRow(){
        return table.getText();
    }

    public String getTitle(){
        System.out.println(title);
        return title.getText();

    }

    /**
     * Si vuole andare alla pagina di un paziente specifico, l'input viene utilizzato per cercare nella tabella
     * il link con il nome e viene cliccato, reindirizzando la navigazione.
     */
    public WebDriver clickName(String nameToClick){
        //TODO
        addpatientbutton.click();
        return driver;
    }

    public void loadPage(){
        driver.get("localhost:8080/home");
    }

    public WebDriver clickAddPatient(){
        addpatientbutton.click();
        return driver;
    }

    public boolean isError() {
        return !driver.getCurrentUrl().equals("http://localhost:8080/home");
    }

    public WebDriver clickReport() {
        reportbutton.click();
        return driver;
    }
}
