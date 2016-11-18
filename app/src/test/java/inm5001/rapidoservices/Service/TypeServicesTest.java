package inm5001.rapidoservices.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.service.ConstanteTypeServices.MESSAGE_PRIXFIXE_NEGATIF;
import static inm5001.rapidoservices.service.ConstanteTypeServices.MESSAGE_TAUXHORRAIRE_NEGATIF;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Francis Bernier on 2016-11-12.
 */

public class TypeServicesTest {

    TypeServices service;
    //attributs AbstraiteServices
    private boolean disponibleService;
    private String ville;
    private byte cote;
    private String numeroTelephoneService;
    private String adresseCourrielService;
    private String description;
    //attribut TypeServices
    String nomSservice;
    private float tauxHorraire;
    private float prixFixe;
    boolean estValider;
    //attributs EvaluationService
    private float coteService;
    private int nombreDEvaluationService;
    private EvaluationService evaluationService;

    @Before
    public void setUp() throws MyException {
        disponibleService = false;
        ville = "Montreal";
        cote = 2;
        numeroTelephoneService ="5144444444";
        adresseCourrielService = "plomberie@plomberi.com";
        description = "Repare les tuyeaux";
        nomSservice = "Plombier";
        tauxHorraire = 0f;
        prixFixe = 0f;
        estValider = false;
        //attributs EvaluationService
        coteService = 3.5f;
        nombreDEvaluationService = 210;
        evaluationService = new EvaluationService(coteService, nombreDEvaluationService);
    }

    @After
    public void tearDown() {
        service = null;
        disponibleService = false;
        ville = null;
        cote = 0;
        numeroTelephoneService = null;
        adresseCourrielService = null;
        description = null;
        tauxHorraire = 0;
        prixFixe = 0;
        estValider = false;
        //attributs EvaluationService
        coteService = 0;
        nombreDEvaluationService = 0;
        evaluationService = null;
    }

    @Test
    public void TypeServicesConstructeur1() throws MyException {
        estValider = true;
        try {
            service = new TypeServices(nomSservice);
        } catch (MyException e) {
            estValider = false;
        }
        assertTrue(estValider);
        assertNotNull(service);
        assertEquals(service.getNomSservice(), nomSservice);
    }

    @Test
    public void TypeServicesConstructeur2() throws MyException {
        estValider = true;
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, ville);
        } catch (MyException e) {
            estValider = false;
        }
        assertTrue(estValider);
        assertNotNull(service);
        assertTrue(service.getTauxHorraire() == tauxHorraire);
    }

    @Test
    public void TypeServicesConstructeur2TauxHorraireNull() throws MyException {
        estValider = true;
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, ville);
        } catch (MyException e) {
            estValider = false;
        }
        assertTrue(estValider);
        assertNotNull(service);
        assertTrue(service.getTauxHorraire() == tauxHorraire);
    }

    @Test
    public void TypeServicesConstructeur3() throws MyException {
        estValider = true;
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, numeroTelephoneService,
                    adresseCourrielService, description);
        } catch (MyException e) {
            estValider = false;
        }
        assertTrue(estValider);
        assertNotNull(service);
    }

    @Test
    public void TypeServicesConstructeur4() throws MyException {
        estValider = true;
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, numeroTelephoneService,
                    adresseCourrielService, description, evaluationService);
        } catch (MyException e) {
            estValider = false;
        }
        assertTrue(estValider);
        assertNotNull(service);
        assertTrue(service.getEvaluationService().coteService == coteService);
    }

    @Test
    public void TypeServicesTauxHorrairePasNegatif() throws MyException {
        try {
            service = new TypeServices(-1, prixFixe, nomSservice, disponibleService, ville, numeroTelephoneService,
                    adresseCourrielService, description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_TAUXHORRAIRE_NEGATIF);
        }
        assertTrue(estValider);
    }

    @Test
    public void TypeServicesPrixFixePasNegatif() throws MyException {
        try {
            service = new TypeServices(tauxHorraire, -1, nomSservice, disponibleService, ville, numeroTelephoneService,
                    adresseCourrielService, description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_PRIXFIXE_NEGATIF);
        }
        assertTrue(estValider);
    }

    @Test
    public void setTauxHorraire() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, numeroTelephoneService,
                adresseCourrielService, description);
        service.setTauxHorraire(11);
        tauxHorraire = service.getTauxHorraire();
        assertTrue(tauxHorraire == 11);
    }

    @Test
    public void setPrixFixe() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, numeroTelephoneService,
                adresseCourrielService, description);
        service.setPrixFixe(11);
        prixFixe = service.getPrixFixe();
        assertTrue(prixFixe == 11);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}