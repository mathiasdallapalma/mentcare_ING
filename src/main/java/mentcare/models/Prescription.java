package mentcare.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Prescription {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String drugs;
    private Integer quantity;
    private String notes;
    private Long patient_id;

    protected Prescription() {}

    public Prescription(String drugs, Integer quantity, String notes, Long patient_id) {
        this.drugs = drugs;
        this.quantity = quantity;
        this.notes = notes;
        this.patient_id = patient_id;
    }

    @Override
    public String toString() {
        return String.format(
                "Prescription[id=%d, drugs='%s', qantity='%d', notes='%s', patient_id='%d']",
                id, drugs, quantity, notes, patient_id);
    }
    
    public String getDrugs() {
        return drugs;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getNotes() {
        return notes;
    }

    public Long getPatient_id() {
        return patient_id;
    }

}
