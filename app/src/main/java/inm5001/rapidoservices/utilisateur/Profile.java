package inm5001.rapidoservices.utilisateur;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.utilisateur.ConstanteProfile.*;

/**
 * Created by Francis Bernier on 2016-10-10.
 */

public class Profile {

    public String nom = "";
    public String prenom = "";
    public String numeroTelephone = "";
    public String adresseCourriel;

    public Profile(String nom, String prenom, String numeroTelephone, String adresseCourriel) throws MyException {
        traiterNom(nom);
        traiterPrenom(prenom);
        traiterNumeroTelephone(numeroTelephone);
        traiterAdresseCourriel(adresseCourriel);
    }
//premier niveau d'abstraction
    private void traiterNom(String nom) throws MyException {
        if (nom != null && !nom.equals("")) {
            validerNomSansChiffre(nom);
            validerNomSansCaratereSpecial(nom);
            nom = convertirEnMajuscule(nom);
            affecterValeurNom(nom);
        }
    }

    private void traiterPrenom(String prenom) throws MyException {
        if (prenom != null && !prenom.equals("")) {
            validerPrenomSansChiffre(prenom);
            validerPrenomSansCaratereSpecial(prenom);
            prenom = convertirEnMajuscule(prenom);
            affecterValeurPrenom(prenom);
        }
    }

    private void traiterNumeroTelephone(String numeroTelephone) throws MyException {
        if (numeroTelephone != null && !numeroTelephone.equals("")) {
            validerNumeroTelephone(numeroTelephone);
            numeroTelephone = formaterNumeroTelephone(numeroTelephone);
            affecterValeurNumeroTelephone(numeroTelephone);
        }
    }

    private void traiterAdresseCourriel(String adresseCourriel) throws MyException {
        validerAdresseCourrielPasNull(adresseCourriel);
        validerAdresseCourrielFormatValide(adresseCourriel);
        affecterValeurAdresseCourriel(adresseCourriel);
    }
//deuxième niveau d'abstraction
    private void validerNomSansChiffre(String nom) throws MyException {
        if (Pattern.compile("[0-9]+").matcher(nom).find()) {
            MyException e = new MyException(MESSAGE_NOM_SANS_CHIFFRE);
            throw e;
        }
    }

    private void validerNomSansCaratereSpecial(String nom) throws MyException {
        if (!nom.matches("[A-Za-z0-9 -]*")) {
            MyException e = new MyException(MESSAGE_NOM_CARACTERE_SPECIAL);
            throw e;
        }
    }

    private void affecterValeurNom(String nom) {
        this.nom = nom;
    }

    private void validerPrenomSansChiffre(String prenom) throws MyException {
        if (Pattern.compile("[0-9]+").matcher(prenom).find()) {
            MyException e = new MyException(MESSAGE_PRENOM_SANS_CHIFFRE);
            throw e;
        }
    }

    private void validerPrenomSansCaratereSpecial(String prenom) throws MyException {
        if (!prenom.matches("[A-Za-z0-9 -]*")) {
            MyException e = new MyException(MESSAGE_PRENOM_CARACTERE_SPECIAL);
            throw e;
        }
    }

    private void affecterValeurPrenom(String prenom) {
        this.prenom = prenom;
    }

    private void validerNumeroTelephone(String numeroTelephone) throws MyException {
        Pattern pattern = Pattern.compile(PATTERN_NUMERO_TELEPHONE);

        Matcher matcher = pattern.matcher(numeroTelephone);
        if (!matcher.matches()) {
            MyException e = new MyException(MESSAGE_NUMEROTELEPHONE_FORMAT_VALIDE);
            throw e;
        }
    }

    private String formaterNumeroTelephone (String numeroTelephone) {
        Pattern pattern = Pattern.compile(PATTERN_NUMERO_TELEPHONE);
        Matcher matcher = pattern.matcher(numeroTelephone);
        return matcher.replaceFirst("($1) $2-$3");
    }

    private void affecterValeurNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    private void validerAdresseCourrielPasNull(String adresseCourriel) throws MyException {
        if (adresseCourriel == null) {
            MyException e = new MyException(MESSAGE_ADRESSECOURRIEL_NULL);
            throw e;
        }
    }

    private void validerAdresseCourrielFormatValide(String adresseCourriel) throws MyException {
        Pattern pattern = Pattern.compile(PATTERN_ADRESSE_COURRIEL);
        Matcher matcher = pattern.matcher(adresseCourriel);

        if (!matcher.matches()) {
            MyException e = new MyException(MESSAGE_ADRESSECOURRIEL_FORMAT_VALIDE);
            throw e;
        }
    }

    private void affecterValeurAdresseCourriel(String adresseCourriel) {
        this.adresseCourriel = adresseCourriel;
    }
//MÉTHODES GLOBAL
    private String convertirEnMajuscule(String uneChaine) {
        return uneChaine.toUpperCase();
    }
}
