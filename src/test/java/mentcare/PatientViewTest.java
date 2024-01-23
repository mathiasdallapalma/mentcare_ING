package mentcare;




/*---*/


import mentcare.pageObjects.ErrorPO;
import mentcare.pageObjects.HomePO;
import mentcare.pageObjects.ReportPO;
import mentcare.pageObjects.PatientViewPO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PatientViewTest extends SystemTest {

    @Test
    public void patientViewFromHomeOK() { //TODO dopo il merge perchè serve quello che sta facendo ale oggi
        HomePO homePO = new HomePO(driver);
        homePO.loadPage();

        PatientViewPO patientViewPO =new PatientViewPO( homePO.clickCF("BBB"));

        assertEquals("TitlePage", "PatientView", patientViewPO.getTitle());
        assertEquals("Patient expected", "John Doe", patientViewPO.getName());
    }

    @Test
    public void patientViewFromID() {

        PatientViewPO patientViewPO = new PatientViewPO(driver);

        patientViewPO=new PatientViewPO(patientViewPO.loadWithID(1));
        assertEquals("TitlePage", "PatientView", patientViewPO.getTitle());
        assertEquals("Patient expected", "John Doe", patientViewPO.getName());
    }

    @Test
    public void patientViewFromIDFAIL() {
        PatientViewPO patientViewPO = new PatientViewPO(driver);

        ErrorPO errorPO =new ErrorPO(patientViewPO.loadWithID(999));
        assertEquals("TitlePage", "Paziente non trovato", errorPO.getTitle());
        assertEquals("Error message", "Il paziente con id [999] non è stato trovato", errorPO.getErrorMessage());
    }

    @Test
    public void generatePatientReport() { 
        PatientViewPO patientViewPO = new PatientViewPO(driver);
        patientViewPO.loadWithID(1);

        ReportPO reportPO = patientViewPO.clickReport();
        assertEquals("Report expected", "REPORT", reportPO.getTitle());
    }
}