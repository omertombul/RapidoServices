package inm5001.rapidoservices.utilisateur;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.utilisateur.ConstanteIdentifiant.*;
import inm5001.rapidoservices.utilisateur.APIDB_temporaire.BDIdentifiant;
/**
 * Created by Francis Bernier on 2016-10-10.
 */

public class Identifiant {

    public String nomUtilisateur;
    public String motDePasse;

    public Identifiant(String nomUtilisateur, String motDePasse) throws MyException {
        TraiterNomUtilisateur(nomUtilisateur);
        TraiterMotDePasse(motDePasse);
    }
//premier niveau d'abstraction
    private void TraiterNomUtilisateur(String nomUtilisateur) throws MyException {
        ValiderNomUtilisateurEstUnique(nomUtilisateur);
    }

    private void TraiterMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
//deuxième niveau d'abstraction
    private void ValiderNomUtilisateurEstUnique(String nomUtilisateur) throws MyException {
        if (BDIdentifiant.GetUtilisateur(nomUtilisateur) != null) {
            MyException e = new MyException(MESSAGE_NOMUTILISATEUR_PAS_UNIQUE);
            throw e;
        } else {
            this.nomUtilisateur = nomUtilisateur;
        }
    }
//troisième niveau d'abstraction
}
