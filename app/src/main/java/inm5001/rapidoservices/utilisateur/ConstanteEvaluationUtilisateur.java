package inm5001.rapidoservices.utilisateur;

/**
 * Created by Francis Bernier on 2016-11-17.
 */

public interface ConstanteEvaluationUtilisateur {
    String MESSAGE_AUTRE_CHOIX = ", veillez recommencer.";

    String MESSAGE_COTEUTILISATEUR_NEGATIVE = "La cote d'un utilisateur ne peut pas être négative" + MESSAGE_AUTRE_CHOIX;

    String MESSAGE_COTETYPESERVICESMOYENNE_NEGATIVE = "La cote d'un service ne peut pas être négative" + MESSAGE_AUTRE_CHOIX;
}
