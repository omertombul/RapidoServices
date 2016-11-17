package inm5001.rapidoservices.utilisateur;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.utilisateur.ConstanteEvaluationUtilisateur.MESSAGE_COTETYPESERVICESMOYENNE_NEGATIVE;
import static inm5001.rapidoservices.utilisateur.ConstanteEvaluationUtilisateur.MESSAGE_COTEUTILISATEUR_NEGATIVE;

/**
 * Created by Francis Bernier on 2016-11-17.
 */

public class EvaluationUtilisateur {
    public float coteUtilisateur;
    public int nombreDEvaluationUtilisateur;
    public float coteTypeServicesMoyenne;
    public int nombreDEvaluationTypeServicesMoyenne;

    public EvaluationUtilisateur(){

    }

    public EvaluationUtilisateur(float coteUtilisateur, int nombreDEvaluationUtilisateur,
                                 float coteTypeServicesMoyenne, int nombreDEvaluationTypeServicesMoyenne) throws MyException {
        traiterCoteUtilisateur(coteUtilisateur);
        traiterNombreDEvaluationUtilisateur(nombreDEvaluationUtilisateur);
        traiterCoteTypeServicesMoyenne(coteTypeServicesMoyenne);
        traiterNombreDEvaluationTypeServicesMoyenne(nombreDEvaluationTypeServicesMoyenne);
    }

//premier niveau d'abstraction
    private void traiterCoteUtilisateur(float coteUtilisateur) throws MyException {
        validerValeurCoteUtilisateurPasNegatif(coteUtilisateur);
        affecterValeurCoteUtilisateur(coteUtilisateur);
    }

    private void traiterNombreDEvaluationUtilisateur(int nombreDEvaluationUtilisateur) {
        affecterValeurNombreDEvaluationUtilisateur(nombreDEvaluationUtilisateur);
    }

    private void traiterCoteTypeServicesMoyenne(float coteTypeServicesMoyenne) throws MyException {
        validerValeurCoteTypeServicesMoyennePasNegatif(coteTypeServicesMoyenne);
        affecterValeurCoteTypeServicesMoyenne(coteTypeServicesMoyenne);
    }

    private void traiterNombreDEvaluationTypeServicesMoyenne(int nombreDEvaluationTypeServicesMoyenne) {
        affecterValeurNombreDEvaluationTypeServicesMoyenne(nombreDEvaluationTypeServicesMoyenne);
    }

//deuxième niveau d'abstraction
    private void validerValeurCoteUtilisateurPasNegatif(float coteUtilisateur) throws MyException {
        if (coteUtilisateur < 0) {
            MyException e = new MyException(MESSAGE_COTEUTILISATEUR_NEGATIVE);
            throw e;
        }
    }

    private void affecterValeurCoteUtilisateur(float coteUtilisateur) {
        this.coteUtilisateur = coteUtilisateur;
    }

    private void affecterValeurNombreDEvaluationUtilisateur(int nombreDEvaluationUtilisateur) {
        this.nombreDEvaluationUtilisateur = nombreDEvaluationUtilisateur;
    }

    private void validerValeurCoteTypeServicesMoyennePasNegatif(float evaluationTypeServicesMoyenne) throws MyException {
        if (evaluationTypeServicesMoyenne < 0) {
            MyException e = new MyException(MESSAGE_COTETYPESERVICESMOYENNE_NEGATIVE);
            throw e;
        }
    }

    private void affecterValeurCoteTypeServicesMoyenne(float coteTypeServicesMoyenne) {
        this.coteTypeServicesMoyenne = coteTypeServicesMoyenne;
    }

    private void affecterValeurNombreDEvaluationTypeServicesMoyenne(int nombreDEvaluationTypeServicesMoyenne) {
        this.nombreDEvaluationTypeServicesMoyenne = nombreDEvaluationTypeServicesMoyenne;
    }
//MÉTHODES PUBLIC
    public float validationCoteUtilisateur(float coteUtilisateur) throws MyException {
        traiterCoteUtilisateur(coteUtilisateur);
        return this.coteUtilisateur;
    }
}
