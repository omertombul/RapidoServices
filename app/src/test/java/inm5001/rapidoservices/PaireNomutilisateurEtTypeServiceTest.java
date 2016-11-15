package inm5001.rapidoservices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inm5001.rapidoservices.service.TypeServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Admin on 2016-11-13.
 */

public class PaireNomutilisateurEtTypeServiceTest {

    //attributs Identifiant
    private String nomUtilisateur;
    //attributs AbstraiteServices
    private String nomSservice;
    private boolean disponibleService;
    private String ville;
    private byte cote;
    private String numeroTelephoneService;
    private String adresseCourrielService;
    private String description;
    //attribut TypeServices
    private float tauxHorraire;
    private float prixFixe;
    private TypeServices service;
    private TypeServices service2;

    @Before
    public void setUp() throws MyException {
        nomUtilisateur = "Francis1";
        nomSservice = "Plombier";
        disponibleService = false;
        ville = "Montreal";
        cote = 2;
        numeroTelephoneService ="5144444444";
        adresseCourrielService = "plomberie@plomberi.com";
        description = "Repare les tuyeaux";
        tauxHorraire = 14.50f;
        prixFixe = 50.00f;
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, cote,
                numeroTelephoneService, adresseCourrielService, description);
    }

    @After
    public void tearDown() throws MyException {
        nomUtilisateur = null;
        nomSservice = null;
        disponibleService = false;
        ville = null;
        cote = 0;
        numeroTelephoneService = null;
        adresseCourrielService = null;
        description = null;
        tauxHorraire = 0f;
        prixFixe = 0f;
        service = null;
    }

    @Test
    public void PaireNomUtilisateurEtTypeServicePasNull() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, cote,
                numeroTelephoneService, adresseCourrielService, description);
        Recherche pair1 = new Recherche(nomUtilisateur , service);

        assertNotNull(pair1);
    }

    @Test
    public void PaireNomUtilisateurEtTypeServiceNomUtilisateur() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, cote,
                numeroTelephoneService, adresseCourrielService, description);
        Recherche pair1 = new Recherche(nomUtilisateur , service);

        assertEquals(pair1.getNomUtilisateur(), nomUtilisateur);
    }

    @Test
    public void PaireNomUtilisateurEtTypeServiceServicePasNull() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, cote,
                numeroTelephoneService, adresseCourrielService, description);
        Recherche pair1 = new Recherche(nomUtilisateur , service);

        assertNotNull(pair1.getService());
    }

    @Test
    public void PaireNomUtilisateurEtTypeServiceServiceValiderParametres() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, cote,
                numeroTelephoneService, adresseCourrielService, description);
        Recherche pair1 = new Recherche(nomUtilisateur , service);

        assertTrue(pair1.getService().getTauxHorraire() == tauxHorraire);
        assertEquals(pair1.getService().getNomSservice(), "PLOMBIER");
    }
}
