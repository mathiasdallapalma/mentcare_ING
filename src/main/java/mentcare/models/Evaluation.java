package mentcare.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String date;
    private String value;
    private String notes;
    private String motivation;
    private Long patientID;

    protected Evaluation() {}

    public Evaluation(String date, String value, String notes, String motivation, Long patientID) {
        this.date = date;
        this.value = value;
        this.notes = notes;
        this.motivation = motivation;
        this.patientID= patientID;
    }

    @Override
    public String toString() {
        return String.format(
                "Evaluation[id=%d, date='%s', value='%s', notes='%s', motivation='%s', patient_id='%d']",
                id, date, value, notes, motivation, patientID);
    }

    //TODO perchè in patient ritorna una mappa?
    public boolean selfCheck(){
        LocalDate date = LocalDate.parse(this.date);
        return date.isBefore(LocalDate.now());
    }

    /* Getters */
    public Long getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public String getValue() {
        return value;
    }
    public String getNotes() {
        return notes;
    }
    public String getMotivation() {
        return motivation;
    }
    public Long getPatient_id() {
        return patientID;
    }
}