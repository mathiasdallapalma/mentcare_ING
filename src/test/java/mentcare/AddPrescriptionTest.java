package mentcare;

import mentcare.pageObjects.AddPrescriptionPO;
import mentcare.pageObjects.ErrorPO;
import mentcare.pageObjects.PatientViewPO;
import org.junit.Assert;
import org.junit.Test;

public class AddPrescriptionTest extends SystemTest{

    @Test
    public void addPrescriptionCheck(){
        PatientViewPO paginaPaziente = new PatientViewPO(driver);
        paginaPaziente.loadWithID(1);
        AddPrescriptionPO addPrescription = paginaPaziente.clickAddPrescription();

        addPrescription.addDrug("tachipirina");
        addPrescription.addQuantity(2);
        addPrescription.addNote("dopo i pasti, per 3 giorni");

        PatientViewPO ret = new PatientViewPO(addPrescription.clickSubmit());


        //TODO verificare che la prescrizione sia stata inserita nella tabella della pagina paziente, manca il metodo
    }

    @Test
    public void addPrescriptionFail(){
        PatientViewPO paginaPaziente = new PatientViewPO(driver);
        paginaPaziente.loadWithID(1);
        AddPrescriptionPO addPrescription = paginaPaziente.clickAddPrescription();

        ErrorPO ret = new ErrorPO(addPrescription.clickSubmit());
        Assert.assertTrue(ret.getErrorMessage().toLowerCase().contains("Username o password errati"));
    }

    //TODO test per allergie
}
