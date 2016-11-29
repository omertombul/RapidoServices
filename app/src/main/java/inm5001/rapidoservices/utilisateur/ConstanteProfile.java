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

    String MESSAGE_NUMEROTELEPHONE_FORMAT_VALIDE = "Ceci n'est pas un format de numéro de téléphone valide" + MESSAGE_AUTRE_CHOIX;
    String PATTERN_NUMERO_TELEPHONE = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";

    String MESSAGE_ADRESSECOURRIEL_NULL = "L'adresse courriel est obligatoire.";
    String MESSAGE_ADRESSECOURRIEL_FORMAT_VALIDE = "Ceci n'est pas une adresse de courriel valide" + MESSAGE_AUTRE_CHOIX;
    String PATTERN_ADRESSE_COURRIEL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
}