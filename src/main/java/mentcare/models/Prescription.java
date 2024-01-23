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

    public String selfCheck(List<String> allergies){
        String errMsg = "";
        if(this.drugs.isEmpty() || this.quantity == null){
            return "Alcuni valori non sono stati inseriti!";
        }
        if(allergies.contains(this.drugs)){
            errMsg = errMsg.concat("Il paziente è allergico al medicinale ["+this.drugs+"] !<br>");
        }
        if(this.getQuantity() < 0 || this.getQuantity() > 100){
            errMsg = errMsg.concat("La quantità inserita dovrebbe essere maggiore di 0 e minore di 100 mg !<br>");
        }
        return errMsg;
    }

}
