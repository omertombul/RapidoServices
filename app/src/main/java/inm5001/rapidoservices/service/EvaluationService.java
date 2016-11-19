package inm5001.rapidoservices.service;

import java.util.Comparator;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Recherche;

import static inm5001.rapidoservices.service.ConstanteEvaluationService.MESSAGE_COTESERVICE_ENTREZEROETCINQ;

/**
 * Created by Francis Bernier on 2016-11-17.
 */

public class EvaluationService {
    public float coteService;
    public int nombreDEvaluationService;

    public EvaluationService(){

    }

    public EvaluationService(float coteService, int nombreDEvaluationService) throws MyException {
        traiterCoteService(coteService);
        traiterNombreDEvaluationService(nombreDEvaluationService);
    }

    //premier niveau d'abstraction
    private void traiterCoteService(float coteService) throws MyException {
        validerValeurCoteServiceEntreZeroEtCinq(coteService);
        affecterValeurCoteService(coteService);
    }

    private void traiterNombreDEvaluationService(int nombreDEvaluationService) {
        affecterValeurNombreDEvaluationService(nombreDEvaluationService);
    }

    //deuxième niveau d'abstraction
    private void validerValeurCoteServiceEntreZeroEtCinq(float coteService) throws MyException {
        if (coteService < 0 || coteService > 5) {
            MyException e = new MyException(MESSAGE_COTESERVICE_ENTREZEROETCINQ);
            throw e;
        }
    }

    private void affecterValeurCoteService(float coteService) {
        this.coteService = coteService;
    }

    private void affecterValeurNombreDEvaluationService(int nombreDEvaluationService) {
        this.nombreDEvaluationService = nombreDEvaluationService;
    }
//MÉTHODES PUBLIC
    public float validationCoteService(float coteService) throws MyException {
        traiterCoteService(coteService);
        return this.coteService;
    }
}
