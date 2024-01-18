package mentcare;




/*---*/


import mentcare.pageObjects.HomePO;
import mentcare.pageObjects.ReportPO;
import mentcare.pageObjects.PatientViewPO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PatientViewTest extends SystemTest {

    @Test
    public void patientViewFromHomeOK() { //TODO
        HomePO homePO = new HomePO(driver);
        homePO.loadPage();

        PatientViewPO patientViewPO = homePO.clickName("Gianfranco");

        assertEquals("Patient expected", "Gianfranco", patientViewPO.getName());
    }

    @Test
    public void patientViewFromID() {

        PatientViewPO patientViewPO = new PatientViewPO(driver);
        patientViewPO.loadWithID(1);
        assertEquals("Patient expected", "Gianfranco", patientViewPO.getName());
    }

    @Test
    public void patientViewFromIDFAIL() {
        PatientViewPO patientViewPO = new PatientViewPO(driver);
        patientViewPO.loadWithID(19000);
        assertTrue("Expected error while loading patient:19000", patientViewPO.isError(19000));
    }

    @Test
    public void generatePatientReport() { //TODO
        PatientViewPO patientViewPO = new PatientViewPO(driver);
        patientViewPO.loadWithID(1);

        ReportPO reportPO = patientViewPO.clickReport();
        assertEquals("Report expected", "Gianfranco", patientViewPO.getName());

    }



}