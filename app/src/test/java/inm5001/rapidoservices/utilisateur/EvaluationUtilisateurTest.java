package inm5001.rapidoservices.utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.utilisateur.ConstanteEvaluationUtilisateur.MESSAGE_EVALUATIONTYPESERVICESMOYENNE_NEGATIVE;
import static inm5001.rapidoservices.utilisateur.ConstanteEvaluationUtilisateur.MESSAGE_EVALUATIONUTILISATEUR_NEGATIVE;
import static inm5001.rapidoservices.utilisateur.ConstanteIdentifiant.MESSAGE_NOMUTILISATEUR_NULL;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Francis Bernier on 2016-11-17.
 */

public class EvaluationUtilisateurTest {
    public float evaluationUtilisateur;
    public int nombreDEvaluationUtilisateur;
    public float evaluationTypeServicesMoyenne;
    public int nombreDEvaluationTypeServicesMoyenne;
    private EvaluationUtilisateur evaluation;
    private Boolean estValider;

    @Before
    public void setUp() {
        evaluationUtilisateur = 3.5f;
        nombreDEvaluationUtilisateur = 210;
        evaluationTypeServicesMoyenne = 4.5f;
        nombreDEvaluationTypeServicesMoyenne = 1000;
        EvaluationUtilisateur evaluation = null;
        estValider = false;
    }

    @After
    public void tearDown() {
        evaluationUtilisateur = 0;
        nombreDEvaluationUtilisateur = 0;
        evaluationTypeServicesMoyenne = 0;
        nombreDEvaluationTypeServicesMoyenne = 0;
        evaluation = null;
        estValider = null;
    }

    @Test
    public void EvaluationPasNull() throws Exception {
        evaluation = new EvaluationUtilisateur(evaluationUtilisateur, nombreDEvaluationUtilisateur, evaluationTypeServicesMoyenne,
                                                nombreDEvaluationTypeServicesMoyenne);
        assertNotNull(evaluation);
    }

    @Test
    public void validerValeurEvaluationPasNegatif() throws Exception {
        try {
            evaluation = new EvaluationUtilisateur(-1, nombreDEvaluationUtilisateur, evaluationTypeServicesMoyenne,
                                                    nombreDEvaluationTypeServicesMoyenne);
        } catch (MyException e) {
            estValider =  e.getMessage().equals(MESSAGE_EVALUATIONUTILISATEUR_NEGATIVE);
        }
        assertTrue(estValider);
    }

    @Test
    public void affecterValeurEvaluation() throws Exception {
        evaluation = new EvaluationUtilisateur(evaluationUtilisateur, nombreDEvaluationUtilisateur, evaluationTypeServicesMoyenne,
                nombreDEvaluationTypeServicesMoyenne);
        assertTrue(evaluation.evaluationUtilisateur == 3.5);
    }

    @Test
    public void affecterValeurNombreDEvaluationUtilisateur() throws Exception {
        evaluation = new EvaluationUtilisateur(evaluationUtilisateur, nombreDEvaluationUtilisateur, evaluationTypeServicesMoyenne,
                nombreDEvaluationTypeServicesMoyenne);
        assertTrue(evaluation.nombreDEvaluationUtilisateur == 210);
    }

    @Test
    public void validerValeurEvaluationTypeServicesMoyennePasNegatif() throws Exception {
        try {
            evaluation = new EvaluationUtilisateur(evaluationUtilisateur, nombreDEvaluationUtilisateur, -1, nombreDEvaluationTypeServicesMoyenne);
        } catch (MyException e) {
            estValider =  e.getMessage().equals(MESSAGE_EVALUATIONTYPESERVICESMOYENNE_NEGATIVE);
        }
        assertTrue(estValider);
    }

    @Test
    public void affecterValeurEvaluationTypeServicesMoyenne() throws Exception {
        evaluation = new EvaluationUtilisateur(evaluationUtilisateur, nombreDEvaluationUtilisateur, evaluationTypeServicesMoyenne,
                nombreDEvaluationTypeServicesMoyenne);
        assertTrue(evaluation.evaluationTypeServicesMoyenne == 4.5);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}
