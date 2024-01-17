package mentcare.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Evaluation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String date;
    private String value;
    private String notes;
    private String motivation;
    private Long patient_id;

    protected Evaluation() {}

    public Evaluation(String date, String value, String notes, String motivation, Long patient_id) {
        this.date = date;
        this.value = value;
        this.notes = notes;
        this.motivation = motivation;
        this.patient_id = patient_id;
    }

    @Override
    public String toString() {
        return String.format(
                "Evaluation[id=%d, date='%s', value='%s', notes='%s', motivation='%s', patient_id='%d']",
                id, date, value, notes, motivation, patient_id);
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
        return patient_id;
    }
}