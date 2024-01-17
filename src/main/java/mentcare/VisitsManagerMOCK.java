package mentcare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MOCK per simulare il recupero della lista delle visite nella "home page" dell'utente loggato.
 * **/
public class VisitsManagerMOCK {

    /**
     * Ho scelto di fare una MAP così per le visite possiamo creare una tabella con i vari elementi staccati, invece
     * di stringhe buttate lì, giusto per fare un po' di ordine. --A--
     */
    public List<Map<String, String>> getVisits(){
        List<Map<String, String>> lista = new ArrayList<>();
        //1
        Map<String, String> v1 = new HashMap<>();
        v1.put("day", "15 Febbraio 2024");
        v1.put("time", "15:30");
        v1.put("patient", "Mario Rossi");
        v1.put("room", "11B");
        lista.add(v1);
        //2
        Map<String, String> v2 = new HashMap<>();
        v2.put("day", "25 Febbraio 2024");
        v2.put("time", "11:00");
        v2.put("patient", "Mario Giordano");
        v2.put("room", "15A - PT");
        lista.add(v2);
        //3
        Map<String, String> v3 = new HashMap<>();
        v3.put("day", "3 Marzo 2024");
        v3.put("time", "10:30");
        v3.put("patient", "John Doe Sanchez");
        v3.put("room", "18A - P1");
        lista.add(v3);
        //4
        Map<String, String> v4 = new HashMap<>();
        v4.put("day", "14 Marzo 2024");
        v4.put("time", "17:00");
        v4.put("patient", "Ava Jenkins");
        v4.put("room", "15A - PT");
        lista.add(v4);
        //5
        Map<String, String> v5 = new HashMap<>();
        v5.put("day", "25 Marzo 2024");
        v5.put("time", "9:30");
        v5.put("patient", "Sam Sapiol");
        v5.put("room", "11B");
        lista.add(v5);

        return lista;
    }

}
