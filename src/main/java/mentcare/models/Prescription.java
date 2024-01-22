package mentcare.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String drugs;
    private Integer quantity;
    private String notes;
    private Long patientID;

    protected Prescription() {}

    public Prescription(String drugs, Integer quantity, String notes, Long patientID) {
        this.drugs = drugs;
        this.quantity = quantity;
        this.notes = notes;
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return String.format(
                "Prescription[id=%d, drugs='%s', qantity='%d', notes='%s', patientID='%d']",
                id, drugs, quantity, notes, patientID);
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

    public Long getpatientID() {
        return patientID;
    }

    public String selfCheck(List<String> allergies){
        return allergies.contains(this.drugs)? "Il paziente è allergico al medicinale ["+this.drugs+"] !" : "";
    }

}
