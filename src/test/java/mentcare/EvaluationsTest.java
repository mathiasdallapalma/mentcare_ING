package mentcare;




/*---*/


import mentcare.pageObjects.AddEvaluationPO;
import mentcare.pageObjects.ErrorPO;
import mentcare.pageObjects.PatientViewPO;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class EvaluationsTest extends SystemTest {

    @Test
    public void addEvaluationOK() {
        PatientViewPO patientPO = new PatientViewPO(driver);
        patientPO.loadWithID(1);

        AddEvaluationPO addEvaluationPO = patientPO.clickAddEvaluation();

        addEvaluationPO.insertDate("2024-01-01");
        addEvaluationPO.insertValue("1");//TODO rimmpiazzare con un enum
        addEvaluationPO.insertNotes("notes");
        addEvaluationPO.insertMotivation("motivation");


        patientPO=new PatientViewPO(addEvaluationPO.clickSubmit());

        assertEquals("New evaluation expected", "2024-01-01,1,notes,motivation", patientPO.getLastEvaluation_toString());
    }
    @Test
    public void addEvaluationFAIL() {
        PatientViewPO patientPO = new PatientViewPO(driver);
        patientPO.loadWithID(1);

        AddEvaluationPO addEvaluationPO = patientPO.clickAddEvaluation();

        addEvaluationPO.insertDate("2025-01-01"); //data futura
        addEvaluationPO.insertValue("1");//TODO rimmpiazzare con un enum
        addEvaluationPO.insertNotes("notes");
        addEvaluationPO.insertMotivation("motivation");
        ErrorPO errorPO=new ErrorPO(addEvaluationPO.clickSubmit());

        assertEquals("Error expected", "Failed to insert Evaluation: future date", errorPO.getErrorMessage());


    }



}