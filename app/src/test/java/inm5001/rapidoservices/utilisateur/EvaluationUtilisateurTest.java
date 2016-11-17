package inm5001.rapidoservices.utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.utilisateur.ConstanteEvaluationUtilisateur.MESSAGE_COTETYPESERVICESMOYENNE_NEGATIVE;
import static inm5001.rapidoservices.utilisateur.ConstanteEvaluationUtilisateur.MESSAGE_COTEUTILISATEUR_NEGATIVE;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Francis Bernier on 2016-11-17.
 */

public class EvaluationUtilisateurTest {
    public float coteUtilisateur;
    public int nombreDEvaluationUtilisateur;
    public float coteTypeServicesMoyenne;
    public int nombreDEvaluationTypeServicesMoyenne;
    private EvaluationUtilisateur evaluationUtilisateur;
    private Boolean estValider;

    @Before
    public void setUp() {
        coteUtilisateur = 3.5f;
        nombreDEvaluationUtilisateur = 210;
        coteTypeServicesMoyenne = 4.5f;
        nombreDEvaluationTypeServicesMoyenne = 1000;
        EvaluationUtilisateur evaluationUtilisateur = null;
        estValider = false;
    }

    @After
    public void tearDown() {
        coteUtilisateur = 0;
        nombreDEvaluationUtilisateur = 0;
        coteTypeServicesMoyenne = 0;
        nombreDEvaluationTypeServicesMoyenne = 0;
        evaluationUtilisateur = null;
        estValider = null;
    }

    @Test
    public void EvaluationUtilisateurPasNull() throws Exception {
        evaluationUtilisateur = new EvaluationUtilisateur(coteUtilisateur, nombreDEvaluationUtilisateur, coteTypeServicesMoyenne,
                                                nombreDEvaluationTypeServicesMoyenne);
        assertNotNull(evaluationUtilisateur);
    }

    @Test
    public void validerValeurCoteUtilisateurPasNegatif() throws Exception {
        try {
            evaluationUtilisateur = new EvaluationUtilisateur(-1, nombreDEvaluationUtilisateur, coteTypeServicesMoyenne,
                                                    nombreDEvaluationTypeServicesMoyenne);
        } catch (MyException e) {
            estValider =  e.getMessage().equals(MESSAGE_COTEUTILISATEUR_NEGATIVE);
        }
        assertTrue(estValider);
    }

    @Test
    public void affecterValeurCoteUtilisateur() throws Exception {
        evaluationUtilisateur = new EvaluationUtilisateur(coteUtilisateur, nombreDEvaluationUtilisateur, coteTypeServicesMoyenne,
                nombreDEvaluationTypeServicesMoyenne);
        assertTrue(evaluationUtilisateur.coteUtilisateur == 3.5);
    }

    @Test
    public void affecterValeurNombreDEvaluationUtilisateur() throws Exception {
        evaluationUtilisateur = new EvaluationUtilisateur(coteUtilisateur, nombreDEvaluationUtilisateur, coteTypeServicesMoyenne,
                nombreDEvaluationTypeServicesMoyenne);
        assertTrue(evaluationUtilisateur.nombreDEvaluationUtilisateur == 210);
    }

    @Test
    public void validerValeurCoteTypeServicesMoyennePasNegatif() throws Exception {
        try {
            evaluationUtilisateur = new EvaluationUtilisateur(coteUtilisateur, nombreDEvaluationUtilisateur, -1, nombreDEvaluationTypeServicesMoyenne);
        } catch (MyException e) {
            estValider =  e.getMessage().equals(MESSAGE_COTETYPESERVICESMOYENNE_NEGATIVE);
        }
        assertTrue(estValider);
    }

    @Test
    public void affecterValeurCoteTypeServicesMoyenne() throws Exception {
        evaluationUtilisateur = new EvaluationUtilisateur(coteUtilisateur, nombreDEvaluationUtilisateur, coteTypeServicesMoyenne,
                nombreDEvaluationTypeServicesMoyenne);
        assertTrue(evaluationUtilisateur.coteTypeServicesMoyenne == 4.5);
    }

    @Test
    public void affecterValeurNombreDEvaluationTypeServicesMoyenne() throws Exception {
        evaluationUtilisateur = new EvaluationUtilisateur(coteUtilisateur, nombreDEvaluationUtilisateur, coteTypeServicesMoyenne,
                nombreDEvaluationTypeServicesMoyenne);
        assertTrue(evaluationUtilisateur.nombreDEvaluationTypeServicesMoyenne == 1000);
    }

    @Test
    public void validationCoteUtilisateur() throws Exception {
        try {
            evaluationUtilisateur = new EvaluationUtilisateur(coteUtilisateur, nombreDEvaluationUtilisateur, coteTypeServicesMoyenne,
                                    nombreDEvaluationTypeServicesMoyenne);
        } catch (MyException e) {
            throw e;
        } try {
            evaluationUtilisateur.validationCoteUtilisateur(-1);
        } catch (Exception e) {
            estValider =  e.getMessage().equals(MESSAGE_COTEUTILISATEUR_NEGATIVE);
        }
        assertTrue(estValider);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}
