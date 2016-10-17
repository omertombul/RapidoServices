package inm5001.rapidoservices.utilisateur;

import java.util.regex.Pattern;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.utilisateur.ConstanteProfile.*;

/**
 * Created by Francis Bernier on 2016-10-10.
 */

public class Profile {

    public String nom;
    public String prenom;
    public String numeroTelephone;
    public String adresseCourriel;

    public Profile(String nom, String prenom, String numeroTelephone, String adresseCourriel) throws MyException {
        TraiterNom(nom);
        TraiterPrenom(prenom);
        TraiterNumeroTelephone(numeroTelephone);
        TraiterAdresseCourriel(adresseCourriel);
    }
//premier niveau d'abstraction
    private void TraiterNom(String nom) throws MyException {
        ValiderNomSansChiffre(nom);
        ValiderNomSansCaratereSpecial(nom);
        SetNom(nom);
    }

    private void TraiterPrenom(String prenom) throws MyException {
        ValiderPrenomSansChiffre(prenom);
        ValiderPrenomSansCaratereSpecial(prenom);
        SetPrenom(prenom);
    }

    private void TraiterNumeroTelephone(String numeroTelephone) throws MyException {
        ValiderNumeroTelephoneSeulementChiffre(numeroTelephone);
        ValiderNumeroTelephoneDixChiffre(numeroTelephone);
        SetNumeroTelephone(numeroTelephone);
    }

    private void TraiterAdresseCourriel(String adresseCourriel) throws MyException {
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

    private void ValiderAdresseCourrielArobase(String adresseCourriel) throws MyException {
        if (!Pattern.compile("[@]+").matcher(adresseCourriel).find()) {
            MyException e = new MyException(MESSAGE_ADRESSE_COURRIEL_AROBASE);
            throw e;
        }
    }
//méthodes public
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
