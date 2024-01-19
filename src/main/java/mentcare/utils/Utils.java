package mentcare.utils;

import mentcare.models.Patient;

import java.util.Arrays;
import java.util.List;

public class Utils {


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

    public static boolean validateLogin(String username, String password){
        return (username.equals("admin") && password.equals("admin"));

    }

}
