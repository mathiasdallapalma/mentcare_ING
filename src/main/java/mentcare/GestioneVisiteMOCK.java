package mentcare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MOCK per simulare il recupero della lista delle visite nella "home page" dell'utente loggato.
 * **/
public class GestioneVisiteMOCK {

    /**
     * Ho scelto di fare una MAP così per le visite possiamo creare una tabella con i vari elementi staccati, invece
     * di stringhe buttate lì, giusto per fare un po' di ordine. --A--
     */
    public List<Map<String, String>> getVisite(){
        List<Map<String, String>> lista = new ArrayList<>();
        //1
        Map<String, String> v1 = new HashMap<>();
        v1.put("giorno", "15 Febbraio 2024");
        v1.put("orario", "15:30");
        v1.put("paziente", "Mario Rossi");
        v1.put("stanza", "11B");
        lista.add(v1);
        //2
        Map<String, String> v2 = new HashMap<>();
        v2.put("giorno", "25 Febbraio 2024");
        v2.put("orario", "11:00");
        v2.put("paziente", "Mario Giordano");
        v2.put("stanza", "15A - PT");
        lista.add(v2);
        //3
        Map<String, String> v3 = new HashMap<>();
        v3.put("giorno", "3 Marzo 2024");
        v3.put("orario", "10:30");
        v3.put("paziente", "John Doe Sanchez");
        v3.put("stanza", "18A - P1");
        lista.add(v3);
        //4
        Map<String, String> v4 = new HashMap<>();
        v4.put("giorno", "14 Marzo 2024");
        v4.put("orario", "17:00");
        v4.put("paziente", "Ava Jenkins");
        v4.put("stanza", "15A - PT");
        lista.add(v4);
        //5
        Map<String, String> v5 = new HashMap<>();
        v5.put("giorno", "25 Marzo 2024");
        v5.put("orario", "9:30");
        v5.put("paziente", "Sam Sapiol");
        v5.put("stanza", "11B");
        lista.add(v5);

        return lista;
    }

}
