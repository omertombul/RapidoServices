package inm5001.rapidoservices.service;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.service.ConstanteEvaluationService.MESSAGE_COTESERVICE_NEGATIVE;

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
        validerValeurCoteServicePasNegatif(coteService);
        affecterValeurCoteService(coteService);
    }

    private void traiterNombreDEvaluationService(int nombreDEvaluationService) {
        affecterValeurNombreDEvaluationService(nombreDEvaluationService);
    }

    //deuxième niveau d'abstraction
    private void validerValeurCoteServicePasNegatif(float coteService) throws MyException {
        if (coteService < 0) {
            MyException e = new MyException(MESSAGE_COTESERVICE_NEGATIVE);
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
