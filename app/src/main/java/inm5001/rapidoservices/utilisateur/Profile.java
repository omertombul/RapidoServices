package inm5001.rapidoservices.utilisateur;

import java.util.ArrayList;

/**
 * Created by Francis Bernier on 2016-10-10.
 */

public class Profile {

    public String nom;
    public String prenom;
    public String numeroTelephone;
    public String adresseCourriel;
    public ArrayList<String> habiletes;

    public Profile(String nom, String prenom, String numeroTelephone, String adresseCourriel, ArrayList<String> habiletes) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTelephone =numeroTelephone;
        this.adresseCourriel = adresseCourriel;
        this.habiletes = habiletes;
    }
}
