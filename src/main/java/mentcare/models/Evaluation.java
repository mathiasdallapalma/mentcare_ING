package mentcare.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String date;
    private Integer value;
    private String notes;
    private String motivation;
    private Long patientID;

    protected Evaluation() {}

    public Evaluation(String date, Integer value, String notes, String motivation, Long patientID) {
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

    /* Getters */
    public Long getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public Integer getValue() {
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