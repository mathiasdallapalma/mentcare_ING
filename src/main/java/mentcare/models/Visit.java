package mentcare.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String day;
    private String time;
    private String room;
    private String patient;

    protected Visit() {}

    public Visit(String day, String time, String toom, String patient) {
        this.day = day;
        this.time = time;
        this.room = toom;
        this.patient = patient;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getRoom() {
        return room;
    }

    public String getPatient() {
        return patient;
    }
}
