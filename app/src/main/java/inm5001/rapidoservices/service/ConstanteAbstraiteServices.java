package inm5001.rapidoservices.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Francis Bernier on 2016-10-14.
 */

public interface ConstanteAbstraiteServices {
    String MESSAGE_AUTRE_CHOIX = ", veillez recommencer.";

    String MESSAGE_NOMSERVICE = "Le nom de service ne doit pas ";
    String MESSAGE_NOMSERVICE_MAX_QUINZE_CARACTERES = MESSAGE_NOMSERVICE + "pas contenir plus de quinze caractères" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_NOMSERVICE_NULL = MESSAGE_NOMSERVICE + "être null" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_NOMSERVICE_CARACTERE_SPECIAL = MESSAGE_NOMSERVICE + "contenir de caractère spécial" + MESSAGE_AUTRE_CHOIX;

    String MESSAGE_VILLE_MAX_QUARANTE_CARACTERES = "La ville ne doit pas contenir plus de quarante caractères" + MESSAGE_AUTRE_CHOIX;

    String MESSAGE_NOTELEPHONE = "Le numéro de téléphone ";
    String MESSAGE_NOTELEPHONE_SEULEMENT_CHIFFRE = MESSAGE_NOTELEPHONE + "ne doit être constitué que de chiffres" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_NOTELEPHONE_DIX_CHIFFRE = MESSAGE_NOTELEPHONE + "doit être constitué de 10 chiffres" + MESSAGE_AUTRE_CHOIX;

    String MESSAGE_COURRIEL_FORMAT_VALIDE = "Ceci n'est pas une adresse de courriel valide" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_COURRIEL_MAX_DEUXCENTCINQUANTESIX_CARACTERES = "L'adresse courriel ne doit pas contenir plus de deux cent cinquante-six caractères" + MESSAGE_AUTRE_CHOIX;

    String MESSAGE_DESCRIPTION_MAX_DEUXCENTCINQUANTESIX_CARACTERES = "La description ne doit pas contenir plus de deux cent cinquante-six caractères" + MESSAGE_AUTRE_CHOIX;

    String patternCourriel = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    String[] tabVilleAjout = new String []{"Montéal", "Québec", "Laval", "Gatineau", "Longueuil", "Sherbrooke", "Saguenay", "Lévis",
            "Trois-Rivières", "Terrebonne", "Saint-Jean-sur-Richelieu", "Brossard", "Repentigny", "Drummondville",
            "Saint-Jérôme", "Granby", "Blainville", "Saint-Hyacinthe", "Dollard-Des Ormeaux", "Rimouski", "Shawinigan",
            "Mirabel", "Châteauguay", "Mascouche", "Victoriaville", "Saint-Eustache", "Rouyn-Noranda", "Boucherville",
            "Salaberry-de-Valleyfield", "Vaudreuil-Dorion", "Sorel-Tracy", "Côte-Saint-Luc", "Val-d'Or", "Saint-Georges",
            "Pointe-Claire", "Alma", "Sainte-Julie", "Chambly", "Boisbriand", "Saint-Constant", "Saint-Bruno-de-Montarville",
            "Sainte-Thérèse", "Magog", "Thetford Mines", "Sept-Îles", "La Prairie", "Saint-Lambert", "Belœil", "L'Assomption",
            "Baie-Comeau"};
    List<String> listeVilleAjout = (Arrays.asList(tabVilleAjout));

    String[] tabNomServicesAjout = new String []{"Plombier", "Électricien", "Menuisier"};
    List<String> listeNomServiceAjout = (Arrays.asList(tabNomServicesAjout));

    String[] tabVilleRecherche = new String []{"", "Montéal", "Québec", "Laval", "Gatineau", "Longueuil", "Sherbrooke", "Saguenay", "Lévis",
            "Trois-Rivières", "Terrebonne", "Saint-Jean-sur-Richelieu", "Brossard", "Repentigny", "Drummondville",
            "Saint-Jérôme", "Granby", "Blainville", "Saint-Hyacinthe", "Dollard-Des Ormeaux", "Rimouski", "Shawinigan",
            "Mirabel", "Châteauguay", "Mascouche", "Victoriaville", "Saint-Eustache", "Rouyn-Noranda", "Boucherville",
            "Salaberry-de-Valleyfield", "Vaudreuil-Dorion", "Sorel-Tracy", "Côte-Saint-Luc", "Val-d'Or", "Saint-Georges",
            "Pointe-Claire", "Alma", "Sainte-Julie", "Chambly", "Boisbriand", "Saint-Constant", "Saint-Bruno-de-Montarville",
            "Sainte-Thérèse", "Magog", "Thetford Mines", "Sept-Îles", "La Prairie", "Saint-Lambert", "Belœil", "L'Assomption",
            "Baie-Comeau"};
    List<String> listeVilleRecherche = (Arrays.asList(tabVilleRecherche));

    String[] tabNomServicesRecherche = new String []{"", "Plombier", "Électricien", "Menuisier"};
    List<String> listeNomServiceRecherche = (Arrays.asList(tabNomServicesRecherche));
}

