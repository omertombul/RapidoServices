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
        ValiderNomUtilisateurSansEspace(nomUtilisateur);
        ValiderNomUtilisateurEntreHuitEtTroisCaracteres(nomUtilisateur);
        ValiderNomUtilisateurEstUnique(nomUtilisateur);
        AffecterValeurNomUtilisateur(nomUtilisateur);
    }

    private void TraiterMotDePasse(String motDePasse) throws MyException {
        ValiderMotDePasseMinHuitCaractere(motDePasse);
        ValiderMotDePasseContientMajuscule(motDePasse);
        ValiderMotDePasseContientMinuscule(motDePasse);
        ValiderMotDePasseContientCaractereSpecial(motDePasse);
        AffecterValeurMotDePasse(motDePasse);
    }
//deuxième niveau d'abstraction
    private void ValiderNomUtilisateurSansEspace(String nomUtilisateur) throws MyException {
        if (nomUtilisateur.indexOf(' ') >= 0) {
            MyException e = new MyException(MESSAGE_NOMUTILISATEUR_PAS_ESPACE);
            throw e;
        }
    }

    private void ValiderNomUtilisateurEntreHuitEtTroisCaracteres(String nomUtilisateur) throws MyException {
        if (nomUtilisateur.length() > 8 || nomUtilisateur.length() < 3) {
            MyException e = new MyException(MESSAGE_NOMUTILISATEUR_MAX_HUIT_MIN_TROIS_CARACTERES);
            throw e;
        }
    }

    private void ValiderNomUtilisateurEstUnique(String nomUtilisateur) throws MyException {
        if (BDIdentifiant.GetUtilisateur(nomUtilisateur) != null) {
            MyException e = new MyException(MESSAGE_NOMUTILISATEUR_PAS_UNIQUE);
            throw e;
        }
    }

    private void AffecterValeurNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    private void ValiderMotDePasseMinHuitCaractere(String motDePasse) throws MyException {
        if (motDePasse.length() < 8) {
            MyException e = new MyException(MESSAGE_MOTDEPASSE_HUIT_CARACTERES);
            throw e;
        }
    }

    private void ValiderMotDePasseContientMajuscule(String motDepasse) throws MyException {
        if (motDepasse.equals(motDepasse.toLowerCase())) {
            MyException e = new MyException(MESSAGE_MOTDEPASSE_MAJUSCULE);
            throw e;
        }
    }

    private void ValiderMotDePasseContientMinuscule(String motDepasse) throws MyException {
        if (motDepasse.equals(motDepasse.toUpperCase())) {
            MyException e = new MyException(MESSAGE_MOTDEPASSE_MINUSCULE);
            throw e;
        }
    }

    private void ValiderMotDePasseContientCaractereSpecial(String motDepasse) throws MyException {
        if (motDepasse.matches("[A-Za-z0-9 ]*")) {
            MyException e = new MyException(MESSAGE_MOTDEPASSE_CARACTERE_SPECIAL);
            throw e;
        }
    }

    private void AffecterValeurMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
