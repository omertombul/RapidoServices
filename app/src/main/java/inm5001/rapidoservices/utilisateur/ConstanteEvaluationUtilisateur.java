package inm5001.rapidoservices.utilisateur;

/**
 * Created by Francis Bernier on 2016-11-17.
 */

public interface ConstanteEvaluationUtilisateur {
    String MESSAGE_AUTRE_CHOIX = ", veillez recommencer.";

    String MESSAGE_COTEUTILISATEUR_ENTREZEROETCINQ = "La cote d'un utilisateur doit être un chiffre entre 0 et 5" + MESSAGE_AUTRE_CHOIX;

    String MESSAGE_COTETYPESERVICESMOYENNE_ENTREZEROETCINQ = "La cote d'un service doit être un chiffre entre 0 et 5" + MESSAGE_AUTRE_CHOIX;
}
