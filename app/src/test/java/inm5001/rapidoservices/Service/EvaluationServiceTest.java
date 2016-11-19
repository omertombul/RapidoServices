package inm5001.rapidoservices.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.service.ConstanteEvaluationService.MESSAGE_COTESERVICE_ENTREZEROETCENT;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Admin on 2016-11-17.
 */

public class EvaluationServiceTest {
    public float coteService;
    public int nombreDEvaluationService;
    private EvaluationService evaluationService;
    private Boolean estValider;

    @Before
    public void setUp() {
        coteService = 3.5f;
        nombreDEvaluationService = 210;
        EvaluationService evaluationService = null;
        estValider = false;
    }

    @After
    public void tearDown() {
        coteService = 0;
        nombreDEvaluationService = 0;
        evaluationService = null;
        estValider = null;
    }

    @Test
    public void EvaluationServicePasNull() throws Exception {
        evaluationService = new EvaluationService(coteService, nombreDEvaluationService);
        assertNotNull(evaluationService);
    }

    @Test
    public void validerValeurCoteServiceEntreZeroEtCent1() throws Exception {
        try {
            evaluationService = new EvaluationService(-0.1f, nombreDEvaluationService);
        } catch (MyException e) {
            estValider =  e.getMessage().equals(MESSAGE_COTESERVICE_ENTREZEROETCENT);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerValeurCoteServiceEntreZeroEtCent2() throws Exception {
        try {
            evaluationService = new EvaluationService(100.1f, nombreDEvaluationService);
        } catch (MyException e) {
            estValider =  e.getMessage().equals(MESSAGE_COTESERVICE_ENTREZEROETCENT);
        }
        assertTrue(estValider);
    }

    @Test
    public void affecterValeurCoteService() throws Exception {
        evaluationService = new EvaluationService(coteService, nombreDEvaluationService);
        assertTrue(evaluationService.coteService == 3.5);
    }

    @Test
    public void affecterValeurNombreDEvaluationService() throws Exception {
        evaluationService = new EvaluationService(coteService, nombreDEvaluationService);
        assertTrue(evaluationService.nombreDEvaluationService == 210);
    }

    @Test
    public void validationCoteService() throws Exception {
        try {
            evaluationService = new EvaluationService(coteService, nombreDEvaluationService);
        } catch (MyException e) {
            throw e;
        } try {
            evaluationService.validationCoteService(-1);
        } catch (Exception e) {
            estValider =  e.getMessage().equals(MESSAGE_COTESERVICE_ENTREZEROETCENT);
        }
        assertTrue(estValider);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}
