package inm5001.rapidoservices.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inm5001.rapidoservices.service.Plomberie;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AbstraiteServicesTest {

        String nomSservice;
        boolean disponible;
        String ville;
        byte cote;
        String noTelephone;
        String courriel;
        String description;
        float tauxHorraire;
        float prixFixe;

        @Before
        public void setUp() {
            disponible = false;
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
            disponible = false;
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
        AbstraiteServices plombier = new Plomberie(tauxHorraire, prixFixe, disponible,
                                                        ville, cote, noTelephone, courriel, description );
        assertNotNull(plombier);
    }

    @Test
    public void getNomSservice() throws Exception {
        Plomberie service = new Plomberie(tauxHorraire, prixFixe, disponible,
                                        ville, cote, noTelephone, courriel, description);
        nomSservice = service.getNomSservice();
        assertEquals(nomSservice, "Plomberie");
    }

    @Test
    public void getDescription() throws Exception {
        Plomberie service = new Plomberie(tauxHorraire, prixFixe, disponible,
                ville, cote, noTelephone, courriel, description);
        description = service.getDescription();
        assertEquals(description, "Repare les tuyeaux");
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}