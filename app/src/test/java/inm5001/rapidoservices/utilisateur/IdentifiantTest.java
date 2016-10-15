package inm5001.rapidoservices.utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inm5001.rapidoservices.MyException;
import static inm5001.rapidoservices.utilisateur.ConstanteIdentifiant.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class IdentifiantTest {

    private String nomUtilisateur;
    private String motDePasse;
    private Identifiant identifiant;

    @Before
    public void setUp() {
        nomUtilisateur = "blabka19";
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
        assertEquals(nomUtilisateur, "blabka19");
        assertEquals(motDePasse, "Allo123");
    }

    @Test
    public void Identifiant3() {
        try {
            identifiant = new Identifiant("existe", motDePasse);
        } catch (MyException e) {
            assertEquals(e.getMessage(), MESSAGE_NOMUTILISATEUR_PAS_UNIQUE);
        }
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}