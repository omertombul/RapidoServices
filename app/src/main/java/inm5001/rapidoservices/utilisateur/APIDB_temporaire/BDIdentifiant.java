package inm5001.rapidoservices.utilisateur.APIDB_temporaire;

import java.util.ArrayList;

import inm5001.rapidoservices.service.Service;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by Admin on 2016-10-14.
 */

public class BDIdentifiant {
    public static String GetUtilisateur(String nomUtilisateur) {
        if (nomUtilisateur == "existe") {
            return "nomUtilisateurExiste";
        } else {
            return null;
        }
    }
}
