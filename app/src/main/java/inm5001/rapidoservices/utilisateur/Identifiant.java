package inm5001.rapidoservices.utilisateur;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.utilisateur.ConstanteIdentifiant.*;
import inm5001.rapidoservices.Orchestrateur;

/**
 * Created by Francis Bernier on 2016-10-10.
 */

public class Identifiant {

    public String nomUtilisateur;
    public String motDePasse;

    public Identifiant(String nomUtilisateur, String motDePasse) throws MyException {
        traiterNomUtilisateur(nomUtilisateur);
        traiterMotDePasse(motDePasse);
    }
//premier niveau d'abstraction
    private void traiterNomUtilisateur(String nomUtilisateur) throws MyException {
        validerNomUtilisateurPasNull(nomUtilisateur);
        validerNomUtilisateurSansEspace(nomUtilisateur);
        validerNomUtilisateurEntreHuitEtTroisCaracteres(nomUtilisateur);
        nomUtilisateur = convertirEnMajuscule(nomUtilisateur);
        //validerNomUtilisateurEstUnique(nomUtilisateur);
        affecterValeurNomUtilisateur(nomUtilisateur);
    }

    private void traiterMotDePasse(String motDePasse) throws MyException {
        validerMotDePassePasNull(motDePasse);
        validerMotDePasseMinHuitCaractere(motDePasse);
        validerMotDePasseContientMajuscule(motDePasse);
        validerMotDePasseContientMinuscule(motDePasse);
        validerMotDePasseContientCaractereSpecial(motDePasse);
        affecterValeurMotDePasse(motDePasse);
    }
//deuxième niveau d'abstraction
    private void validerNomUtilisateurPasNull(String nomUtilisateur) throws MyException {
        if (nomUtilisateur == null) {
            MyException e = new MyException(MESSAGE_NOMUTILISATEUR_NULL);
            throw e;
        }
    }

    private void validerNomUtilisateurSansEspace(String nomUtilisateur) throws MyException {
        if (nomUtilisateur.indexOf(' ') >= 0) {
            MyException e = new MyException(MESSAGE_NOMUTILISATEUR_PAS_ESPACE);
            throw e;
        }
    }

    private void validerNomUtilisateurEntreHuitEtTroisCaracteres(String nomUtilisateur) throws MyException {
        if (nomUtilisateur.length() > 8 || nomUtilisateur.length() < 3) {
            MyException e = new MyException(MESSAGE_NOMUTILISATEUR_MAX_HUIT_MIN_TROIS_CARACTERES);
            throw e;
        }
    }
/*
    private void validerNomUtilisateurEstUnique(String nomUtilisateur) throws MyException {
        Orchestrateur orchestrateur = new Orchestrateur();

        if (orchestrateur.nomUtilisateurExisteDansBd(nomUtilisateur)) {
            MyException e = new MyException(MESSAGE_NOMUTILISATEUR_PAS_UNIQUE);
            throw e;
        }
    }
*/
    private void affecterValeurNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    private void validerMotDePassePasNull(String motDePasse) throws MyException {
        if (motDePasse == null) {
            MyException e = new MyException(MESSAGE_MOTDEPASSE_NULL);
            throw e;
        }
    }

    private void validerMotDePasseMinHuitCaractere(String motDePasse) throws MyException {
        if (motDePasse.length() < 8) {
            MyException e = new MyException(MESSAGE_MOTDEPASSE_HUIT_CARACTERES);
            throw e;
        }
    }

    private void validerMotDePasseContientMajuscule(String motDepasse) throws MyException {
        if (motDepasse.equals(motDepasse.toLowerCase())) {
            MyException e = new MyException(MESSAGE_MOTDEPASSE_MAJUSCULE);
            throw e;
        }
    }

    private void validerMotDePasseContientMinuscule(String motDepasse) throws MyException {
        if (motDepasse.equals(motDepasse.toUpperCase())) {
            MyException e = new MyException(MESSAGE_MOTDEPASSE_MINUSCULE);
            throw e;
        }
    }

    private void validerMotDePasseContientCaractereSpecial(String motDepasse) throws MyException {
        if (motDepasse.matches("[A-Za-z0-9 ]*")) {
            MyException e = new MyException(MESSAGE_MOTDEPASSE_CARACTERE_SPECIAL);
            throw e;
        }
    }

    private void affecterValeurMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
//MÉTHODES GLOBAL
    private String convertirEnMajuscule(String uneChaine) {
        return uneChaine.toUpperCase();
    }
//MÉTHODES PUBLIC
    public String validationMotDePasse(String motDePasse) throws MyException {
        traiterMotDePasse(motDePasse);
        return this.motDePasse;
    }
}
