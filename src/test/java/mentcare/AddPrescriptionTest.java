package mentcare;

import mentcare.pageObjects.AddPrescriptionPO;
import mentcare.pageObjects.ErrorPO;
import mentcare.pageObjects.PatientViewPO;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddPrescriptionTest extends SystemTest {

    @Test
    public void addPrescriptionCheck() {
        PatientViewPO paginaPaziente = new PatientViewPO(driver);
        paginaPaziente.loadWithID(1);

        AddPrescriptionPO addPrescription = paginaPaziente.clickAddPrescription();

        addPrescription.addDrug("tachipirina");
        addPrescription.addQuantity(2);
        addPrescription.addNote("note");

        paginaPaziente = new PatientViewPO(addPrescription.clickSubmit());

        assertEquals("New evaluation expected", "tachipirina 2 note", paginaPaziente.getLastPrescription_toString());
    }

    @Test
    public void addPrescriptionNoValues() {
        PatientViewPO paginaPaziente = new PatientViewPO(driver);
        paginaPaziente.loadWithID(1);
        AddPrescriptionPO addPrescription = paginaPaziente.clickAddPrescription();

        ErrorPO ret = new ErrorPO(addPrescription.clickSubmit());

        assertEquals("Error expected", "Inserimento prescrizione non riuscito", ret.getTitle());
        assertEquals("Error expected", "Alcuni valori non sono stati inseriti!", ret.getErrorMessage()); //TODO
    }

    @Test
    public void addPrescriptionErrorAllergy() {
        PatientViewPO paginaPaziente = new PatientViewPO(driver);
        paginaPaziente.loadWithID(1);
        AddPrescriptionPO addPrescription = paginaPaziente.clickAddPrescription();

        addPrescription.addDrug("lattice");
        addPrescription.addQuantity(2);
        addPrescription.addNote("dopo i pasti, per 3 giorni");

        ErrorPO ret = new ErrorPO(addPrescription.clickSubmit());

        assertEquals("Error expected", "Inserimento prescrizione non riuscito", ret.getTitle());
        assertEquals("Error expected", "Il paziente è allergico al medicinale \"lattice\" !", ret.getErrorMessage());
    }
}
