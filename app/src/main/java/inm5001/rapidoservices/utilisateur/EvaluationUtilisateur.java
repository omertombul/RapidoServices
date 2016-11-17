package inm5001.rapidoservices.utilisateur;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.utilisateur.ConstanteEvaluationUtilisateur.MESSAGE_EVALUATIONTYPESERVICESMOYENNE_NEGATIVE;
import static inm5001.rapidoservices.utilisateur.ConstanteEvaluationUtilisateur.MESSAGE_EVALUATIONUTILISATEUR_NEGATIVE;

/**
 * Created by Francis Bernier on 2016-11-17.
 */

public class EvaluationUtilisateur {
    public float evaluationUtilisateur;
    public int nombreDEvaluationUtilisateur;
    public float evaluationTypeServicesMoyenne;
    public int nombreDEvaluationTypeServicesMoyenne;

    public EvaluationUtilisateur(){

    }

    public EvaluationUtilisateur(float evaluationUtilisateur, int nombreDEvaluationUtilisateur,
                                 float evaluationTypeServicesMoyenne, int nombreDEvaluationTypeServicesMoyenne) throws MyException {
        traiterEvaluationUtilisateur(evaluationUtilisateur);
        traiterNombreDEvaluationUtilisateur(nombreDEvaluationUtilisateur);
        traiterEvaluationTypeServicesMoyenne(evaluationTypeServicesMoyenne);
        traiterNombreDEvaluationTypeServicesMoyenne(nombreDEvaluationTypeServicesMoyenne);
    }

//premier niveau d'abstraction
    private void traiterEvaluationUtilisateur(float evaluationUtilisateur) throws MyException {
        validerValeurEvaluationPasNegatif(evaluationUtilisateur);
        affecterValeurEvaluation(evaluationUtilisateur);
    }

    private void traiterNombreDEvaluationUtilisateur(int nombreDEvaluationUtilisateur) {
        affecterValeurNombreDEvaluationUtilisateur(nombreDEvaluationUtilisateur);
    }

    private void traiterEvaluationTypeServicesMoyenne(float evaluationTypeServicesMoyenne) throws MyException {
        validerValeurEvaluationTypeServicesMoyennePasNegatif(evaluationTypeServicesMoyenne);
        affecterValeurEvaluationTypeServicesMoyenne(evaluationTypeServicesMoyenne);
    }

    private void traiterNombreDEvaluationTypeServicesMoyenne(int nombreDEvaluationTypeServicesMoyenne) {
        affecterValeurNombreDEvaluationTypeServicesMoyenne(nombreDEvaluationTypeServicesMoyenne);
    }

//deuxième niveau d'abstraction
    private void validerValeurEvaluationPasNegatif(float evaluationUtilisateur) throws MyException {
        if (evaluationUtilisateur < 0) {
            MyException e = new MyException(MESSAGE_EVALUATIONUTILISATEUR_NEGATIVE);
            throw e;
        }
    }

    private void affecterValeurEvaluation(float evaluationUtilisateur) {
        this.evaluationUtilisateur = evaluationUtilisateur;
    }

    private void affecterValeurNombreDEvaluationUtilisateur(int nombreDEvaluationUtilisateur) {
        this.nombreDEvaluationUtilisateur = nombreDEvaluationUtilisateur;
    }

    private void validerValeurEvaluationTypeServicesMoyennePasNegatif(float evaluationTypeServicesMoyenne) throws MyException {
        if (evaluationTypeServicesMoyenne < 0) {
            MyException e = new MyException(MESSAGE_EVALUATIONTYPESERVICESMOYENNE_NEGATIVE);
            throw e;
        }
    }

    private void affecterValeurEvaluationTypeServicesMoyenne(float evaluationTypeServicesMoyenne) {
        this.evaluationTypeServicesMoyenne = evaluationTypeServicesMoyenne;
    }

    private void affecterValeurNombreDEvaluationTypeServicesMoyenne(int nombreDEvaluationTypeServicesMoyenne) {
        this.nombreDEvaluationTypeServicesMoyenne = nombreDEvaluationTypeServicesMoyenne;
    }
}
