package mentcare.utils;

/**
 * MOCK per simulare il login dell'utente
 * **/
public class VerificaLoginMOCK {

    private String user = "matias00";
    private String pasw = "pass";

    public boolean effettuaLogin(String username, String pass){
        return username.equals(getUser()) && pass.equals(getPasw());
    }

    //Questi 2 get li possiamo mettere nella pagina di login per far vedere che se si inseriscono sbagliati non
    // logga, potrebbe essere un'idea stupida ma la ho scritta per non scordarla --A--
    public String getUser(){
        return this.user;
    }
    public String getPasw(){
        return this.pasw;
    }
}
