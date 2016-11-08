package inm5001.rapidoservices;

/**
 * Created by Francis Bernier on 2016-10-14.
 */

public interface ConstanteOrchetrateur {
    String MESSAGE_RECOMMENCER = ", veillez recommencer.";

    String MESSAGE_UTILISATEUR_N_EXISTE_PAS = "L'utilisateur demandé n'existe pas" + MESSAGE_RECOMMENCER;

    String MESSAGE_MOT_DE_PASSE_INVALIDE = "Le mot de passe est invalide" + MESSAGE_RECOMMENCER;

    String MESSAGE_NOMUTILISATEUR_PAS_UNIQUE = "Le nom d'utilisateur existe déjà, veillez faire un autre choix.";
}

