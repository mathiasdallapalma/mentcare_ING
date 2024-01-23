package mentcare.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

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

    /*@FindBy(xpath = "//tr[td//text()[contains(., 'targetString')]]")
    @FindBys(xpath = "//tbody/tr/td")
    private WebElement table;
    */

    public boolean isCfPresent(String cf){
        /*
        try {
            WebElement tmp = driver.findElement(By.id(cf));
            return true;
        }catch (NoSuchElementException e){
            return false;
        }*/
        return driver.findElements(By.xpath("/tbody[1]/tr/td")).getLast().getText().contains(cf);
    }

    public String getTitle(){
        return title.getText();
    }

    /**
     * Si vuole andare alla pagina di un paziente specifico, l'input viene utilizzato per cercare nella tabella
     * il link con il nome e viene cliccato, reindirizzando la navigazione.
     */
    public WebDriver clickCF(String cf){
        //driver.findElement(By.id(cf)).click(); servirebbe il try-catch
        driver.findElements(By.xpath("/tbody[1]/tr/td[contains(text(),'"+cf+"')]")).get(0).click();
        return driver;
    }

    public void loadPage(){
        driver.get("http://localhost:8080/home");
    }

    public WebDriver clickAddPatient(){
        addpatientbutton.click();
        return driver;
    }

    public WebDriver clickReport() {
        reportbutton.click();
        return driver;
    }
}
