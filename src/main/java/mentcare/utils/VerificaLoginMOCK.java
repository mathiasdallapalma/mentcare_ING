package mentcare.utils;

/**
 * MOCK per simulare il login dell'utente
 * **/
public class VerificaLoginMOCK {

    private String username = "admin";
    private String password = "admin";

    public boolean checkLogin(String u, String p){
        return u.equals(username) && p.equals(password);
    }

    //Questi 2 get li possiamo mettere nella pagina di login per far vedere che se si inseriscono sbagliati non
    // logga, potrebbe essere un'idea stupida ma la ho scritta per non scordarla --A--
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
}
