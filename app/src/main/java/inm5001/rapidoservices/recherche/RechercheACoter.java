package inm5001.rapidoservices.recherche;

/**
 * Created by Francis Bernier on 2016-11-23.
 */

public class RechercheACoter {
    String nomUtilisateurACoter;
    String nomService;

    public RechercheACoter(String nomUtilisateurACoter, String nomService){
        this.nomUtilisateurACoter = nomUtilisateurACoter;
        this.nomService = nomService;
    }

    public String getNomUtilisateurACoter(){
        return nomUtilisateurACoter;
    }

    public String getNomService(){
        return nomService;
    }
    public String toString(){
        return " " + nomUtilisateurACoter +" " +nomService;
    }
}
