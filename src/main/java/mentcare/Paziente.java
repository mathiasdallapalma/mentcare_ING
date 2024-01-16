package mentcare;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paziente {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    //TODO

    protected Paziente() {}

    public Paziente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        //TODO
    }

    @Override
    public String toString() {
        return String.format(
                //TODO
                "Paziente[id=%d, Nome='%s', Cognome='%s']",
                id, nome, cognome);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

}