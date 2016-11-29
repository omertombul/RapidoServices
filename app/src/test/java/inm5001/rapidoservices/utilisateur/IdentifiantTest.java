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
    private Boolean estValider;

    @Before
    public void setUp() {
        nomUtilisateur = "blabka19";
        motDePasse = "Allo123!";
        identifiant = null;
        estValider = false;
    }

    @After
    public void tearDown() {
        nomUtilisateur = null;
        motDePasse = null;
        identifiant = null;
        estValider = null;
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
        assertEquals(nomUtilisateur, "BLABKA19");
    }

    @Test
    public void IdentifiantMotDePasse() throws Exception {
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        String motDePasse = identifiant.motDePasse;
        assertEquals(motDePasse, "Allo123!");
    }

//NOM UTILISATEUR
    @Test
    public void ValiderNomUtilisateurPasNull() {
        try {
            identifiant = new Identifiant(null, motDePasse);
        } catch (MyException e) {
            estValider =  e.getMessage().equals(MESSAGE_NOMUTILISATEUR_NULL);
        }
        assertTrue(estValider);
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
            estValider =  e.getMessage().equals(MESSAGE_NOMUTILISATEUR_MAX_HUIT_MIN_TROIS_CARACTERES);
        }
        assertTrue(estValider);
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
            estValider = e.getMessage().equals(MESSAGE_NOMUTILISATEUR_MAX_HUIT_MIN_TROIS_CARACTERES);
        }
        assertTrue(estValider);
    }

    @Test
    public void IdentifiantNomUtilisateurAvecEspace() throws MyException {
        try {
            identifiant = new Identifiant("12 3", motDePasse);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOMUTILISATEUR_PAS_ESPACE);
        }
        assertTrue(estValider);
    }
//MOT DE PASSE
    @Test
    public void ValiderMotDePassePasNull() {
        try {
            identifiant = new Identifiant(nomUtilisateur, null);
        } catch (MyException e) {
            estValider =  e.getMessage().equals(MESSAGE_MOTDEPASSE_NULL);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderMotDePasseMinHuitCaractere() throws MyException {
        try {
            identifiant = new Identifiant(nomUtilisateur, "Allo!23");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_MOTDEPASSE_HUIT_CARACTERES);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderMotDePasseContientMajuscule() throws MyException {
        try {
            identifiant = new Identifiant(nomUtilisateur, "allo!234");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_MOTDEPASSE_MAJUSCULE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderMotDePasseContientMinuscule() throws MyException {
        try {
            identifiant = new Identifiant(nomUtilisateur, "ALLO!234");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_MOTDEPASSE_MINUSCULE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderMotDePasseContientCaractereSpecial() throws MyException {
        try {
            identifiant = new Identifiant(nomUtilisateur, "Allo1234");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_MOTDEPASSE_CARACTERE_SPECIAL);
        }
        assertTrue(estValider);
    }
//MÉTHODES PUBLIC

    @Test
    public void validationMotDePasse() throws MyException {
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        motDePasse = identifiant.validationMotDePasse("Allo456!");
        assertEquals(motDePasse, "Allo456!");
    }

    @Test
    public void validationMotDePasseValiderUneDesErreur() throws MyException {
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        try {
            identifiant.validationMotDePasse("Allo4567");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_MOTDEPASSE_CARACTERE_SPECIAL);
        }
        assertTrue(estValider);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}