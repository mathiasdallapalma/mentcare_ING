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

        Map<String, Object> data = calc1(patients);

        String res= "{<br>" +
                ".   \"patients\":{<br>" +
                ". .     \"count\":" + data.get("count") + ",<br>" +
                ". .     \"maleCount\":" + data.get("maleCount") + ",<br>" +
                ". .     \"femaleCount\":" + data.get("femaleCount") + ",<br>" +
                ". .     \"avgAge\":" + data.get("avgAge") + ",<br>" +
                ". .     \"avgHeight\":" + data.get("avgHeight") + ",<br>" +
                ". .     \"avgWeight\":" + data.get("avgWeight") + ",<br>" +
                ".   }<br>"+
                "}<br>";

        return res;
    }

    public static Map<String,Object> calc1(Iterable<Patient> patients){

        Map<String, Object> res = new HashMap<>();
        float avgAge = 0f;
        int maleCount = 0;
        int femaleCount = 0;
        float avgHeight = 0f;
        float avgWeight = 0f;
        int count= 0;

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


        res.put("avgAge", avgAge);
        res.put("maleCount", maleCount);
        res.put("femaleCount", femaleCount);
        res.put("avgHeight", avgHeight);
        res.put("avgWeight", avgWeight);
        res.put("count", count);

        return res;
    }


    public static String generatePatientReport(Patient patient, List<Prescription> prescriptions, List<Evaluation> evaluations) {

        Map<String, Object> data = calc2(patient, prescriptions, evaluations);

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
                ". .  \"ListOfDrugsAssumed\":["+ data.get("mapDrugs") + "],<br>"+
                ". .  \"DrugMostAssumed\":["+ data.get("mostAssumedDrugs") + "],<br>"+
                "}<br>"+
                "evaluations: {<br>"+
                ". .  \"count\":" + evaluations.size() + ",<br>"+
                ". .  \"averageValue\":"+ data.get("averageValue") + ",<br>"+

                "}<br>";

        return res;
    }

    public static Map<String,Object> calc2(Patient patient, List<Prescription> prescriptions, List<Evaluation> evaluations){

        Map<String, Object> res = new HashMap<>();

        String mostAssumedDrugs="";
        HashMap<String, Integer> mapDrugs = new HashMap<>();
        float averageValue = 0f;

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



        if(!evaluations.isEmpty()) {

            for (Evaluation e : evaluations) {
                averageValue += e.getValue();
            }
            averageValue = averageValue / evaluations.size();
        }



        res.put("mostAssumedDrugs", mostAssumedDrugs);
        res.put("mapDrugs", mapDrugs);
        res.put("averageValue", averageValue);

        return res;
    }

}
