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

    //TODO risolvi i \n non caricati dalla pagina
    public String selfCheck(){
        String error= "";
        if(this.date.isEmpty()){
            error= error.concat("\nLa data non può essere vuota\n");

        }else{
            LocalDate date = LocalDate.parse(this.date);
            if(date.isAfter(LocalDate.now()))
            {
                error= error.concat("\nLa data non può essere nel passato\n");
            }
        }

        if(this.value<0 || this.value>50)
        {
            error= error.concat("\nIl valore deve essere compreso tra 0 e 50\n");
        }
        if(this.motivation.isEmpty()){
            error= error.concat("\nLa motivazione non può essere vuota\n");
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