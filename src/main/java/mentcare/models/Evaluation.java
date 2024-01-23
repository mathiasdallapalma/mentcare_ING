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
    private Integer value; //è tipo il voto che il dottore da al paziente in quella visita
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

    public String selfCheck(){
        String error= "";
        if(this.date.isEmpty()){
            error= error.concat("La data non può essere vuota<br>");

        }else{
            LocalDate date = LocalDate.parse(this.date);
            if(date.isAfter(LocalDate.now()))
            {
                error= error.concat("La data non può essere nel futuro<br>");
            }
        }

        if(value==null||value<0 || value>50)
        {
            error= error.concat("\nIl valore deve essere compreso tra 0 e 50<br>");
        }
        if(this.motivation.isEmpty()){
            error= error.concat("\nLa motivazione non può essere vuota<br>");
        }
        return error;
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