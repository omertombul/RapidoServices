package inm5001.rapidoservices.utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.utilisateur.ConstanteProfile.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ProfileTest {

    private String nom;
    private String prenom;
    private String numeroTelephone;
    private String adresseCourriel;
    private Profile profile;
    private Boolean estValider;

    @Before
    public void setUp() throws MyException {
        nom = "Francis";
        prenom = "Bernier";
        numeroTelephone = "5145972143";
        adresseCourriel = "francis@hotmail.com";
        profile = null;
        estValider = false;
    }

    @After
    public void tearDown() {
        nom = null;
        prenom = null;
        numeroTelephone = null;
        adresseCourriel = null;
        profile = null;
        estValider = null;
    }

    @Test
    public void ProfilePasNull() throws Exception {
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel);
        assertNotNull(profile);
    }

    @Test
    public void ProfileNom() throws Exception {
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel);
        String nom = profile.nom;
        assertEquals(nom, "Francis");
    }

    @Test
    public void ProfilePreNom() throws Exception {
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel);
        String prenom = profile.prenom;
        assertEquals(prenom, "Bernier");
    }

    @Test
    public void ProfileNumeroTelephone() throws Exception {
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel);
        String numeroTelephone = profile.numeroTelephone;
        assertEquals(numeroTelephone, "5145972143");
    }

    @Test
    public void ProfileAdresseCourriel() throws Exception {
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel);
        String adresseCourriel = profile.adresseCourriel;
        assertEquals(adresseCourriel, "francis@hotmail.com");
    }
//NOM
    @Test
    public void TraiterNomNull() throws MyException {
        profile = new Profile(null, prenom, numeroTelephone, adresseCourriel);
        String nom = profile.nom;
        assertEquals(nom, "");
    }

    @Test
    public void ValiderNomSansChiffre() {
        try {
            profile = new Profile("Franc1s", prenom, numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOM_SANS_CHIFFRE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderNomSansCaratereSpecial1() {
        try {
            profile = new Profile("Franc!s", prenom, numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOM_CARACTERE_SPECIAL);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderNomSansCaratereSpecial2() {
        try {
            profile = new Profile("Francis B", prenom, numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOM_CARACTERE_SPECIAL);
        }
        assertFalse(estValider);
    }

    @Test
    public void ValiderNomSansCaratereSpecia3() {
        try {
            profile = new Profile("Francis-B", prenom, numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOM_CARACTERE_SPECIAL);
        }
        assertFalse(estValider);
    }
//PRENOM
    @Test
    public void TraiterPrenomNull() throws Exception {
        profile = new Profile(nom, null, numeroTelephone, adresseCourriel);
        String prenom = profile.prenom;
        assertEquals(prenom, "");
    }

    @Test
    public void ValiderPreomSansChiffre() {
        try {
            profile = new Profile(nom, "Bern1er", numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_PRENOM_SANS_CHIFFRE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderPrenomSansCaratereSpecial1() {
        try {
            profile = new Profile(nom, "Bern!er", numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_PRENOM_CARACTERE_SPECIAL);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderPrenomSansCaratereSpecial2() {
        try {
            profile = new Profile(nom, "F Bernier", numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_PRENOM_CARACTERE_SPECIAL);
        }
        assertFalse(estValider);
    }

    @Test
    public void ValiderPrenomSansCaratereSpecia3() {
        try {
            profile = new Profile(nom, "B-Francis", numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_PRENOM_CARACTERE_SPECIAL);
        }
        assertFalse(estValider);
    }
//NUMÉRO DE TÉLÉPHONE
    @Test
    public void TraiterNumeroTelephoneNull() throws Exception {
        profile = new Profile(nom, prenom, null, adresseCourriel);
        String numeroTelephone = profile.numeroTelephone;
        assertEquals(numeroTelephone, "");
    }

    @Test
    public void ValiderNumeroTelephoneSeulementChiffre1() {
        try {
            profile = new Profile(nom, prenom, "514 5972143", adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NUMEROTELEPHONE_SEULEMENT_CHIFFRE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderNumeroTelephoneSeulementChiffre2() {
        try {
            profile = new Profile(nom, prenom, "514-5972143", adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NUMEROTELEPHONE_SEULEMENT_CHIFFRE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderNumeroTelephoneSeulementChiffre3() {
        try {
            profile = new Profile(nom, prenom, "5i45972143", adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NUMEROTELEPHONE_SEULEMENT_CHIFFRE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderNumeroTelephoneDixChiffre() {
        try {
            profile = new Profile(nom, prenom, "514597214", adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NUMEROTELEPHONE_DIX_CHIFFRE);
        }
        assertTrue(estValider);
    }
//ADRESSE COURRIEL
    @Test
    public void ValiderAdresseCourrielPasNull() {
        try {
            profile = new Profile(nom, prenom, numeroTelephone, null);
        } catch (MyException e) {
            estValider =  e.getMessage().equals(MESSAGE_ADRESSECOURRIEL_NULL);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderAdresseCourrielArobase() {
        try {
            profile = new Profile(nom, prenom, numeroTelephone, "francishotmail.com");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_ADRESSECOURRIEL_AROBASE);
        }
        assertTrue(estValider);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}