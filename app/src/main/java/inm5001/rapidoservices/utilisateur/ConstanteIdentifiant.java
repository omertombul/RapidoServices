package inm5001.rapidoservices.utilisateur;

/**
 * Created by Francis Bernier on 2016-10-14.
 */

public interface ConstanteIdentifiant {
    String MESSAGE_AUTRE_CHOIX = ", veillez faire un autre choix.";

    String MESSAGE_NOMUTILISATEUR = "Le nom d'utilisateur ";
    String MESSAGE_NOMUTILISATEUR_NULL = MESSAGE_NOMUTILISATEUR + "est oubligatoire.";
    String MESSAGE_NOMUTILISATEUR_PAS_ESPACE = MESSAGE_NOMUTILISATEUR + "ne doit pas contenir d'espace" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_NOMUTILISATEUR_MAX_HUIT_MIN_TROIS_CARACTERES = MESSAGE_NOMUTILISATEUR + "ne doit pas contenir plus de huit caractères" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_NOMUTILISATEUR_PAS_UNIQUE = MESSAGE_NOMUTILISATEUR + "existe déjà" + MESSAGE_AUTRE_CHOIX;

    String MESSAGE_MOTDEPASSE = "Le mot de passe doit contenir au minimum ";
    String MESSAGE_MOTDEPASSE_NULL = "Le mot de passe est oubligatoire.";
    String MESSAGE_MOTDEPASSE_HUIT_CARACTERES = MESSAGE_MOTDEPASSE + "huit caractère" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_MOTDEPASSE_MAJUSCULE = MESSAGE_MOTDEPASSE + "une majuscule" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_MOTDEPASSE_MINUSCULE = MESSAGE_MOTDEPASSE + "une minuscule" + MESSAGE_AUTRE_CHOIX;
    String MESSAGE_MOTDEPASSE_CARACTERE_SPECIAL = MESSAGE_MOTDEPASSE + "un caractère spécial" + MESSAGE_AUTRE_CHOIX;
}
