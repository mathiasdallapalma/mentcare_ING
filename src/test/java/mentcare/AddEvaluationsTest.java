package mentcare;




/*---*/


import mentcare.pageObjects.AddEvaluationPO;
import mentcare.pageObjects.ErrorPO;
import mentcare.pageObjects.PatientViewPO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AddEvaluationsTest extends SystemTest {

    @Test
    public void addEvaluationOK() {
        PatientViewPO patientPO = new PatientViewPO(driver);
        patientPO.loadWithID(1);

        AddEvaluationPO addEvaluationPO = patientPO.clickAddEvaluation();

        addEvaluationPO.insertDate("2024-01-01");
        addEvaluationPO.insertValue("1");
        addEvaluationPO.insertMotivation("motivation");
        addEvaluationPO.insertNotes("notes");


        patientPO=new PatientViewPO(addEvaluationPO.clickSubmit());

        assertEquals("New evaluation expected", "2024-01-01 1 motivation notes", patientPO.getLastEvaluation_toString());
    }
    @Test
    public void addEvaluationFAIL() {
        PatientViewPO patientPO = new PatientViewPO(driver);
        patientPO.loadWithID(1);

        AddEvaluationPO addEvaluationPO = patientPO.clickAddEvaluation();

        addEvaluationPO.insertDate("2025-01-01"); //data futura
        addEvaluationPO.insertValue("1");
        addEvaluationPO.insertMotivation("motivation");
        addEvaluationPO.insertNotes("notes");

        ErrorPO errorPO=new ErrorPO(addEvaluationPO.clickSubmit());

        assertEquals("Error expected", "Aggiunta valutazione fallita", errorPO.getTitle());
        assertEquals("Error expected", "La data non può essere nel futuro", errorPO.getErrorMessage());
    }

    @Test
    public void addEvaluationNoValues(){
        PatientViewPO patientPO = new PatientViewPO(driver);
        patientPO.loadWithID(1);

        AddEvaluationPO addEvaluationPO = patientPO.clickAddEvaluation();

        ErrorPO errorPO=new ErrorPO(addEvaluationPO.clickSubmit());

        assertEquals("Error expected", "Aggiunta valutazione fallita", errorPO.getTitle());
        assertEquals("Error expected", "La data non può essere vuota\n" +
                "Il valore deve essere compreso tra 0 e 50\n" +
                "La motivazione non può essere vuota", errorPO.getErrorMessage());
    }



}