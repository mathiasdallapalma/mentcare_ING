package mentcare;

import mentcare.pageObjects.AddPrescriptionPO;
import mentcare.pageObjects.ErrorPO;
import mentcare.pageObjects.PatientViewPO;
import org.junit.Assert;
import org.junit.Test;

public class AddPrescriptionTest extends SystemTest {

    @Test
    public void addPrescriptionCheck() {
        PatientViewPO paginaPaziente = new PatientViewPO(driver);
        paginaPaziente.loadWithID(1);
        AddPrescriptionPO addPrescription = paginaPaziente.clickAddPrescription();

        String drug = "tachipirina";
        addPrescription.addDrug(drug);
        addPrescription.addQuantity(2);
        addPrescription.addNote("dopo i pasti, per 3 giorni");

        PatientViewPO ret = new PatientViewPO(addPrescription.clickSubmit());
        Assert.assertEquals("Dovevo essere nella pagina del paziente 1", "patientview", ret.getTitle().toLowerCase());
        Assert.assertTrue(ret.getLastPrescription_toString().contains(drug));
    }

    @Test
    public void addPrescriptionNoValues() {
        PatientViewPO paginaPaziente = new PatientViewPO(driver);
        paginaPaziente.loadWithID(1);
        AddPrescriptionPO addPrescription = paginaPaziente.clickAddPrescription();

        ErrorPO ret = new ErrorPO(addPrescription.clickSubmit());
        Assert.assertTrue(ret.getTitle().toLowerCase().contains("aggiunta prescrizione fallita"));
    }

    @Test
    public void addPrescriptionErrorAllergy() {
        PatientViewPO paginaPaziente = new PatientViewPO(driver);
        paginaPaziente.loadWithID(1);
        AddPrescriptionPO addPrescription = paginaPaziente.clickAddPrescription();

        addPrescription.addDrug("lattice");
        addPrescription.addQuantity(2);
        addPrescription.addNote("dopo i pasti, per 3 giorni");

        PatientViewPO ret = new PatientViewPO(addPrescription.clickSubmit());
        Assert.assertTrue("Dovrei essere nella pagina di errore per allergie",
                ret.getTitle().toLowerCase().contains("allergico al medicinale"));
    }
}
