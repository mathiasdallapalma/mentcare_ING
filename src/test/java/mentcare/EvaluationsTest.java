package mentcare;




/*---*/


import mentcare.SystemTest;
import mentcare.pageObjects.AddEvaluationPO;
import mentcare.pageObjects.ViewPatientPO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class EvaluationsTest extends SystemTest {

    @Test
    public void addEvaluation() {
        ViewPatientPO patientPO = new ViewPatientPO(driver);
        patientPO.loadPage();

        AddEvaluationPO addEvaluationPO = patientPO.clickAddEvaluation();

        addEvaluationPO.insertDate("2024-01-01");
        addEvaluationPO.insertValue("1");//TODO rimmpiazzare con un enum
        addEvaluationPO.insertNotes("notes");
        addEvaluationPO.insertMotivation("motivation");
        patientPO=addEvaluationPO.clickSubmit();

        assertEquals("New evaluation expected", "2018-01-01,1,notes,motivation", patientPO.getLastEvaluation_toString());
    }
    @Test
    public void addEvaluationFAIL() {
        ViewPatientPO patientPO = new ViewPatientPO(driver);
        patientPO.loadPage();

        AddEvaluationPO addEvaluationPO = patientPO.clickAddEvaluation();

        addEvaluationPO.insertDate("2025-01-01"); //data futura
        addEvaluationPO.insertValue("1");//TODO rimmpiazzare con un enum
        addEvaluationPO.insertNotes("notes");
        addEvaluationPO.insertMotivation("motivation");
        patientPO=addEvaluationPO.clickSubmit();

        assertEquals("Error expected", "Error", patientPO.getStatus());
        // TODO come facciamo a capire che è un errore? io direi che senza complicarci la vita
        // con i pop up possiamo reindirizzare ad una pagina con scritto il messaggio di errore

    }



}