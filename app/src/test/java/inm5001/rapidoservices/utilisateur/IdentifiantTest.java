package inm5001.rapidoservices.utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IdentifiantTest {

    private String nomUtilisateur;
    private String motDePasse;
    private Identifiant identifiant;

    @Before
    public void setUp() {
        nomUtilisateur = "Francis";
        motDePasse = "Allo123";
        identifiant = null;
    }

    @After
    public void tearDown() {
        nomUtilisateur = null;
        motDePasse = null;
        identifiant = null;
    }

    @Test
    public void Identifiant1() throws Exception {
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        assertNotNull(identifiant);
    }

    @Test
    public void Identifiant2() throws Exception {
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        String nomUtilisateur = identifiant.nomUtilisateur;
        String motDePasse = identifiant.motDePasse;
        assertEquals(nomUtilisateur, "Francis");
        assertEquals(motDePasse, "Allo123");
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}