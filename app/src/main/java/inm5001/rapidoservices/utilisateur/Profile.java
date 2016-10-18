package inm5001.rapidoservices.utilisateur;

import java.util.regex.Pattern;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.utilisateur.ConstanteIdentifiant.MESSAGE_NOMUTILISATEUR_NULL;
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
        TraiterNom(nom);
        TraiterPrenom(prenom);
        TraiterNumeroTelephone(numeroTelephone);
        TraiterAdresseCourriel(adresseCourriel);
    }
//premier niveau d'abstraction
    private void TraiterNom(String nom) throws MyException {
        if (nom != null) {
            ValiderNomSansChiffre(nom);
            ValiderNomSansCaratereSpecial(nom);
            nom = ConvertirEnMajuscule(nom);
            SetNom(nom);
        }
    }

    private void TraiterPrenom(String prenom) throws MyException {
        if (prenom != null) {
            ValiderPrenomSansChiffre(prenom);
            ValiderPrenomSansCaratereSpecial(prenom);
            prenom = ConvertirEnMajuscule(prenom);
            SetPrenom(prenom);
        }
    }

    private void TraiterNumeroTelephone(String numeroTelephone) throws MyException {
        if (numeroTelephone != null) {
            ValiderNumeroTelephoneSeulementChiffre(numeroTelephone);
            ValiderNumeroTelephoneDixChiffre(numeroTelephone);
            SetNumeroTelephone(numeroTelephone);
        }
    }

    private void TraiterAdresseCourriel(String adresseCourriel) throws MyException {
        ValiderAdresseCourrielPasNull(adresseCourriel);
        ValiderAdresseCourrielArobase(adresseCourriel);
        SetAdresseCourriel(adresseCourriel);
    }
//deuxième niveau d'abstraction
    private void ValiderNomSansChiffre(String nom) throws MyException {
        if (Pattern.compile("[0-9]+").matcher(nom).find()) {
            MyException e = new MyException(MESSAGE_NOM_SANS_CHIFFRE);
            throw e;
        }
    }

    private void ValiderNomSansCaratereSpecial(String nom) throws MyException {
        if (!nom.matches("[A-Za-z0-9 -]*")) {
            MyException e = new MyException(MESSAGE_NOM_CARACTERE_SPECIAL);
            throw e;
        }
    }

    private void ValiderPrenomSansChiffre(String prenom) throws MyException {
        if (Pattern.compile("[0-9]+").matcher(prenom).find()) {
            MyException e = new MyException(MESSAGE_PRENOM_SANS_CHIFFRE);
            throw e;
        }
    }

    private void ValiderPrenomSansCaratereSpecial(String prenom) throws MyException {
        if (!prenom.matches("[A-Za-z0-9 -]*")) {
            MyException e = new MyException(MESSAGE_PRENOM_CARACTERE_SPECIAL);
            throw e;
        }
    }

    private void ValiderNumeroTelephoneSeulementChiffre(String numeroTelephone) throws MyException {
        if (Pattern.compile("[^0-9]+").matcher(numeroTelephone).find()) {
            MyException e = new MyException(MESSAGE_NUMEROTELEPHONE_SEULEMENT_CHIFFRE);
            throw e;
        }
    }

    private void ValiderNumeroTelephoneDixChiffre(String numeroTelephone) throws MyException {
        if (numeroTelephone.length() != 10) {
            MyException e = new MyException(MESSAGE_NUMEROTELEPHONE_DIX_CHIFFRE);
            throw e;
        }
    }

    private void ValiderAdresseCourrielPasNull(String adresseCourriel) throws MyException {
        if (adresseCourriel == null) {
            MyException e = new MyException(MESSAGE_ADRESSECOURRIEL_NULL);
            throw e;
        }
    }

    private void ValiderAdresseCourrielArobase(String adresseCourriel) throws MyException {
        if (!Pattern.compile("[@]+").matcher(adresseCourriel).find()) {
            MyException e = new MyException(MESSAGE_ADRESSECOURRIEL_AROBASE);
            throw e;
        }
    }
//MÉTHODES GLOBAL
    private String ConvertirEnMajuscule(String uneChaine) {
        return uneChaine.toUpperCase();
    }
//MÉTHODES PUBLIC
    private void SetNom(String nom) {
        this.nom = nom;
    }

    private void SetPrenom(String prenom) {
        this.prenom = prenom;
    }

    private void SetNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    private void SetAdresseCourriel(String adresseCourriel) {
        this.adresseCourriel = adresseCourriel;
    }
}
