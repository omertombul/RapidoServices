package inm5001.rapidoservices.utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inm5001.rapidoservices.MyException;
import static inm5001.rapidoservices.utilisateur.ConstanteIdentifiant.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IdentifiantTest {

    private String nomUtilisateur;
    private String motDePasse;
    private Identifiant identifiant;

    @Before
    public void setUp() {
        nomUtilisateur = "blabka19";
        motDePasse = "Allo123!";
        identifiant = null;
    }

    @After
    public void tearDown() {
        nomUtilisateur = null;
        motDePasse = null;
        identifiant = null;
    }

    @Test
    public void IdentifiantPasNull() throws Exception {
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        assertNotNull(identifiant);
    }

    @Test
    public void IdentifiantNomUtilisateur() throws Exception {
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        String nomUtilisateur = identifiant.nomUtilisateur;
        assertEquals(nomUtilisateur, "blabka19");
    }

    @Test
    public void IdentifiantMotDePasse() throws Exception {
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        String motDePasse = identifiant.motDePasse;
        assertEquals(motDePasse, "Allo123!");
    }

    @Test
    public void IdentifiantNomUtilisateurPasUnique() {
        try {
            identifiant = new Identifiant("ilExiste", motDePasse);
        } catch (MyException e) {
            assertEquals(e.getMessage(), MESSAGE_NOMUTILISATEUR_PAS_UNIQUE);
        }
    }

    @Test
    public void IdentifiantNomUtilisateurHuitCaracteres() throws MyException {
        identifiant = new Identifiant("12345678", motDePasse);
        String nomUtilisateur = identifiant.nomUtilisateur;
        assertEquals(nomUtilisateur, "12345678");
    }

    @Test
    public void IdentifiantNomUtilisateurPlusQueHuitCaracteres() {
        try {
            identifiant = new Identifiant("123456789", motDePasse);
        } catch (MyException e) {
            assertEquals(e.getMessage(), MESSAGE_NOMUTILISATEUR_MAX_HUIT_MIN_TROIS_CARACTERES);
        }
    }

    @Test
    public void IdentifiantNomUtilisateurTroisCaracteres() throws MyException {
        identifiant = new Identifiant("123", motDePasse);
        String nomUtilisateur = identifiant.nomUtilisateur;
        assertEquals(nomUtilisateur, "123");
    }

    @Test
    public void IdentifiantNomUtilisateurMoinsDeTroisCaracteres() {
        try {
            identifiant = new Identifiant("12", motDePasse);
        } catch (MyException e) {
            assertEquals(e.getMessage(), MESSAGE_NOMUTILISATEUR_MAX_HUIT_MIN_TROIS_CARACTERES);
        }
    }

    @Test
    public void IdentifiantNomUtilisateurAvecEspace() throws MyException {
        try {
            identifiant = new Identifiant("12 3", motDePasse);
        } catch (MyException e) {
            assertEquals(e.getMessage(), MESSAGE_NOMUTILISATEUR_PAS_ESPACE);
        }
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}