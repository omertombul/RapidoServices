package inm5001.rapidoservices.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AbstraiteServicesTest {

        String nomSservice;
        byte disponible;
        String ville;
        byte cote;
        String noTelephone;
        String courriel;
        String description;
        float tauxHorraire;
        float prixFixe;

        @Before
        public void setUp() {
            nomSservice = "Plomberie";
            disponible = 0;
            ville = "Montreal";
            cote = 2;
            noTelephone ="514-444-4444";
            courriel = "plomberie@plomberi.com";
            description = "Repare les tuyeaux";
            tauxHorraire = 14.50f;
            prixFixe = 50.00f;
        }

        @After
        public void tearDown() {
            nomSservice = null;
            disponible = 0;
            ville = null;
            cote = 0;
            noTelephone = null;
            courriel = null;
            description = null;
            tauxHorraire = 0;
            prixFixe = 0;
        }

    @Test
    public void UtilisateurPasNull() throws Exception {
        AbstraiteServices plombier = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, cote, noTelephone, courriel, description );
        assertNotNull(plombier);
    }

    @Test
    public void getNomSservice() throws Exception {
        AbstraiteServices service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, cote, noTelephone, courriel, description);
        nomSservice = service.getNomSservice();
        assertEquals(nomSservice, "Plomberie");
    }

    @Test
    public void getDescription() throws Exception {
        AbstraiteServices service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, cote, noTelephone, courriel, description);
        description = service.getDescription();
        assertEquals(description, "Repare les tuyeaux");
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}