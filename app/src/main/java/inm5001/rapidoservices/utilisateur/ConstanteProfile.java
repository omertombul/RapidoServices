package inm5001.rapidoservices.utilisateur;

/**
 * Created by Francis Bernier on 2016-10-14.
 */

public interface ConstanteProfile {
    String MESSAGE_AUTRE_CHOIX = ", veillez recommencer.";

    String MESSAGE_NOM = "Le nom ne doit pas contenir ";
    String MESSAGE_NOM_SANS_CHIFFRE = MESSAGE_NOM + "de chiffre" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_NOM_CARACTERE_SPECIAL = MESSAGE_NOM + "de caractère spécial" + MESSAGE_AUTRE_CHOIX;

    String MESSAGE_PRENOM = "Le prenom ne doit pas contenir ";
    String MESSAGE_PRENOM_SANS_CHIFFRE = MESSAGE_PRENOM + "de chiffre" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_PRENOM_CARACTERE_SPECIAL = MESSAGE_PRENOM + "de caractère spécial" + MESSAGE_AUTRE_CHOIX;

    String MESSAGE_NUMEROTELEPHONE = "Le numéro de téléphone ";
    String MESSAGE_NUMEROTELEPHONE_SEULEMENT_CHIFFRE = MESSAGE_NUMEROTELEPHONE + "ne doit être constitué que de chiffres" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_NUMEROTELEPHONE_DIX_CHIFFRE = MESSAGE_NUMEROTELEPHONE + "doit être constitué de 10 chiffres" + MESSAGE_AUTRE_CHOIX;

    String MESSAGE_ADRESSECOURRIEL_NULL = "L'adresse courriel est obligatoire.";
    String MESSAGE_ADRESSECOURRIEL_FORMAT_VALIDE = "Ceci n'est pas une adresse de courriel valide" + MESSAGE_AUTRE_CHOIX;

    String patternCourriel = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
}
