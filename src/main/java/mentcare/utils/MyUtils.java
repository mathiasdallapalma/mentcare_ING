package mentcare.utils;

import mentcare.models.Evaluation;
import mentcare.models.Patient;
import mentcare.models.Prescription;

import java.util.*;

public class MyUtils {


    public static List<String> createAllergiesList(String all){
        return Arrays.asList(all.split(","));
    }


    public static String generateGeneralReport(Iterable<Patient> patients){
        Float avgAge = 0f;
        Integer maleCount = 0;
        Integer femaleCount = 0;
        Float avgHeight = 0f;
        Float avgWeight = 0f;
        Integer count= 0;

        for(Patient p : patients){
            avgAge+= p.getAge();
            maleCount+= p.getSex().equals("maschio") ? 1 : 0;
            femaleCount+= p.getSex().equals("femmina") ? 1 : 0;
            avgHeight+= p.getHeight();
            avgWeight+= p.getWeight();
            count++;
        }
        avgAge= avgAge/count;
        avgHeight = avgHeight/count;
        avgWeight = avgWeight/count;

        String res= "{<br>" +
                ".   \"patients\":{<br>" +
                ". .     \"count\":" + count + ",<br>" +
                ". .     \"maleCount\":\"" + maleCount + "\",<br>" +
                ". .     \"femaleCount\":" + femaleCount + ",<br>" +
                ". .     \"avgAge\":" + avgAge + ",<br>" +
                ". .     \"avgHeight\":" + avgHeight + ",<br>" +
                ". .     \"avgWeight\":" + avgWeight + ",<br>" +
                ".   }<br>"+
                "}<br>";

        return res;
    }


    public static String generatePatientReport(Patient patient, List<Prescription> prescriptions, List<Evaluation> evaluations) {

        String mostAssumedDrugs="";
        HashMap<String, Integer> mapDrugs = new HashMap<>();

        if(!prescriptions.isEmpty()) {
            for (Prescription p : prescriptions) {
                String[] drugs = p.getDrugs().split(",");
                for (String d : drugs) {
                    if (mapDrugs.containsKey(d)) {
                        mapDrugs.put(d, mapDrugs.get(d) + 1);
                    } else {
                        mapDrugs.put(d, 1);
                    }
                }
            }

            mostAssumedDrugs = Collections.max(mapDrugs.entrySet(), Map.Entry.comparingByValue()).getKey();
        }

        Float averageValue = 0f;

        if(!evaluations.isEmpty()) {

            for (Evaluation e : evaluations) {
                averageValue += e.getValue();
            }
            averageValue = averageValue / evaluations.size();
        }

        String res= "{<br>" +
                ".   \"patient\":{<br>" +
                ". .     \"id\":" + patient.getId() + ",<br>" +
                ". .     \"name\":\"" + patient.getFirstname() + "\",<br>" +
                ". .     \"surname\":\"" + patient.getLastname() + "\",<br>" +
                ". .     \"age\":" + patient.getAge() + ",<br>" +
                ". .     \"height\":" + patient.getHeight() + ",<br>" +
                ". .     \"weight\":" + patient.getWeight() + ",<br>" +
                ". .     \"birthDate\":\"" + patient.getBirthdate() + "\",<br>" +
                ". .     \"sex\":\"" + patient.getSex() + "\",<br>" +
                ". .     \"fiscalCode\":\"" + patient.getCf() + "\",<br>" +
                ". .     \"email\":\"" + patient.getEmail() + "\",<br>" +
                ". .     \"address\":\"" + patient.getAddress() + "\",<br>" +
                ". .     \"allergies\":\"" + patient.getAllergies() + "\",<br>" +
                ".   }<br>"+
                "}<br>"+
                "prescriptions: {<br>"+
                ". .  \"count\":" + prescriptions.size() + ",<br>"+
                ". .  \"ListOfDrugsAssumed\":["+ mapDrugs + "],<br>"+
                ". .  \"DrugMostAssumed\":["+ mostAssumedDrugs + "],<br>"+
                "}<br>"+
                "evaluations: {<br>"+
                ". .  \"count\":" + evaluations.size() + ",<br>"+
                ". .  \"averageValue\":"+ averageValue+ ",<br>"+

                "}<br>";

        return res;
    }


    public static boolean validateLogin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
