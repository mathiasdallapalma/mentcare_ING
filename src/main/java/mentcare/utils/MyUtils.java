package mentcare.utils;

import mentcare.models.Evaluation;
import mentcare.models.Patient;
import mentcare.models.Prescription;

import java.util.*;

public class MyUtils {


    public static List<String> createAllergiesList(String all){
        return Arrays.asList(all.split(","));
    }

    public static String generatePatientReport(Patient patient){
        //TODO
        return "";
    }

    public static String generateGeneralReport(Iterable<Patient> patients){
        //TODO
        return "";
    }

    public static String generateReport(Patient patient, List<Prescription> prescriptions, List<Evaluation> evaluations) {
        //TODO

        HashMap<String, Integer> mapDrugs=new HashMap<>();
        for (Prescription p:prescriptions) {
            String[] drugs=p.getDrugs().split(",");
            for (String d:drugs) {
                if(mapDrugs.containsKey(d)){
                    mapDrugs.put(d,mapDrugs.get(d)+1);
                }
                else{
                    mapDrugs.put(d,1);
                }
            }
        }


        String mostAssumedDrugs=mapDrugs.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

        Float averageValue=0f;

        for (Evaluation e:evaluations) {
            averageValue+=e.getValue();
        }
        averageValue=averageValue/evaluations.size();

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



        //TODO evaluations and prescriptions
    }


}
