package inm5001.rapidoservices.utilisateur;

import inm5001.rapidoservices.MyException;
import static inm5001.rapidoservices.utilisateur.ConstanteProfile.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
        assertEquals(nom, "FRANCIS");
    }

    @Test
    public void ProfilePreNom() throws Exception {
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel);
        String prenom = profile.prenom;
        assertEquals(prenom, "BERNIER");
    }

    @Test
    public void ProfileNumeroTelephone() throws Exception {
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel);
        String numeroTelephone = profile.numeroTelephone;
        assertEquals(numeroTelephone, "(514) 597-2143");
    }

    @Test
    public void ProfileAdresseCourriel() throws Exception {
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel);
        String adresseCourriel = profile.adresseCourriel;
        assertEquals(adresseCourriel, "francis@hotmail.com");
    }
//NOM
    @Test
    public void traiterNomNull() throws MyException {
        profile = new Profile(null, prenom, numeroTelephone, adresseCourriel);
        String nom = profile.nom;
        assertEquals(nom, "");
    }

    @Test
    public void traiterNomVide() throws MyException {
        profile = new Profile("", prenom, numeroTelephone, adresseCourriel);
        String nom = profile.nom;
        assertEquals(nom, "");
    }

    @Test
    public void validerNomSansChiffre() {
        try {
            profile = new Profile("Franc1s", prenom, numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOM_SANS_CHIFFRE);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerNomSansCaratereSpecial1() {
        try {
            profile = new Profile("Franc!s", prenom, numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOM_CARACTERE_SPECIAL);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerNomSansCaratereSpecial2() {
        try {
            profile = new Profile("Francis B", prenom, numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOM_CARACTERE_SPECIAL);
        }
        assertFalse(estValider);
    }

    @Test
    public void validerNomSansCaratereSpecia3() {
        try {
            profile = new Profile("Francis-B", prenom, numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOM_CARACTERE_SPECIAL);
        }
        assertFalse(estValider);
    }
//PRENOM
    @Test
    public void traiterPrenomNull() throws Exception {
        profile = new Profile(nom, null, numeroTelephone, adresseCourriel);
        String prenom = profile.prenom;
        assertEquals(prenom, "");
    }

    @Test
    public void traiterPrenomVide() throws Exception {
        profile = new Profile(nom, "", numeroTelephone, adresseCourriel);
        String prenom = profile.prenom;
        assertEquals(prenom, "");
    }

    @Test
    public void validerPreomSansChiffre() {
        try {
            profile = new Profile(nom, "Bern1er", numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_PRENOM_SANS_CHIFFRE);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerPrenomSansCaratereSpecial1() {
        try {
            profile = new Profile(nom, "Bern!er", numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_PRENOM_CARACTERE_SPECIAL);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerPrenomSansCaratereSpecial2() {
        try {
            profile = new Profile(nom, "F Bernier", numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_PRENOM_CARACTERE_SPECIAL);
        }
        assertFalse(estValider);
    }

    @Test
    public void validerPrenomSansCaratereSpecia3() {
        try {
            profile = new Profile(nom, "B-Francis", numeroTelephone, adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_PRENOM_CARACTERE_SPECIAL);
        }
        assertFalse(estValider);
    }
//NUMÉRO DE TÉLÉPHONE
    @Test
    public void traiterNumeroTelephoneNull() throws Exception {
        profile = new Profile(nom, prenom, null, adresseCourriel);
        String numeroTelephone = profile.numeroTelephone;
        assertEquals(numeroTelephone, "");
    }

    @Test
    public void traiterNumeroTelephoneVide() throws Exception {
        profile = new Profile(nom, prenom, "", adresseCourriel);
        String numeroTelephone = profile.numeroTelephone;
        assertEquals(numeroTelephone, "");
    }

    @Test
    public void validerNumeroTelephoneValides() {
        estValider = true;

        try {
            profile = new Profile(nom, prenom, "1234567890", adresseCourriel);
            profile = new Profile(nom, prenom, "123 4567890", adresseCourriel);
            profile = new Profile(nom, prenom, "123 456 7890", adresseCourriel);
            profile = new Profile(nom, prenom, "123-456-7890", adresseCourriel);
            profile = new Profile(nom, prenom, "123-4567890", adresseCourriel);
            profile = new Profile(nom, prenom, "123456-7890", adresseCourriel);
            profile = new Profile(nom, prenom, "123-456 7890", adresseCourriel);
            profile = new Profile(nom, prenom, "123 456-7890", adresseCourriel);
            profile = new Profile(nom, prenom, "123.456.7890", adresseCourriel);
            profile = new Profile(nom, prenom, "123456.7890", adresseCourriel);
            profile = new Profile(nom, prenom, "123.4567890", adresseCourriel);
            profile = new Profile(nom, prenom, "123.456 7890", adresseCourriel);
            profile = new Profile(nom, prenom, "123 456.7890", adresseCourriel);
            profile = new Profile(nom, prenom, "(123) 456 7890", adresseCourriel);
            profile = new Profile(nom, prenom, "(123)456 7890", adresseCourriel);
            profile = new Profile(nom, prenom, "(123) 4567890", adresseCourriel);
            profile = new Profile(nom, prenom, "(123)4567890", adresseCourriel);
            profile = new Profile(nom, prenom, "(123)-456-7890", adresseCourriel);
            profile = new Profile(nom, prenom, "(123) 456-7890", adresseCourriel);
            profile = new Profile(nom, prenom, "(123)-456 7890", adresseCourriel);
            profile = new Profile(nom, prenom, "(123).456.7890", adresseCourriel);
            profile = new Profile(nom, prenom, "(123) 456.7890", adresseCourriel);
            profile = new Profile(nom, prenom, "(123).456 7890", adresseCourriel);
            profile = new Profile(nom, prenom, "(123).456-7890", adresseCourriel);
        } catch (MyException e) {
            estValider = false;
        }
        assertTrue(estValider);
    }

    @Test
    public void validerNumeroTelephoneInvalide9Chiffres() {
        try {
            profile = new Profile(nom, prenom, "123456789", adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NUMEROTELEPHONE_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerNumeroTelephoneInvalide11Chiffres() {
        try {
            profile = new Profile(nom, prenom, "12345678900", adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NUMEROTELEPHONE_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerNumeroTelephoneInvalideChar() {
        try {
            profile = new Profile(nom, prenom, "123x4567890", adresseCourriel);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NUMEROTELEPHONE_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void formaterNumeroTelephone1() throws MyException {
        profile = new Profile(nom, prenom, "1234567890", adresseCourriel);

        assertEquals(profile.numeroTelephone, "(123) 456-7890");
    }

    @Test
    public void formaterNumeroTelephone2() throws MyException {
        profile = new Profile(nom, prenom, "(123)4567890", adresseCourriel);

        assertEquals(profile.numeroTelephone, "(123) 456-7890");
    }

    @Test
    public void formaterNumeroTelephone3() throws MyException {
        profile = new Profile(nom, prenom, "123-4567890", adresseCourriel);

        assertEquals(profile.numeroTelephone, "(123) 456-7890");
    }

    @Test
    public void formaterNumeroTelephone4() throws MyException {
        profile = new Profile(nom, prenom, "123.4567890", adresseCourriel);

        assertEquals(profile.numeroTelephone, "(123) 456-7890");
    }
//ADRESSE COURRIEL
    @Test
    public void validerAdresseCourrielPasNull() {
        try {
            profile = new Profile(nom, prenom, numeroTelephone, null);
        } catch (MyException e) {
            estValider =  e.getMessage().equals(MESSAGE_ADRESSECOURRIEL_NULL);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerAdresseCourrielFormatValide1() {
        try {
            profile = new Profile(nom, prenom, numeroTelephone, "@hotmail.com");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_ADRESSECOURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerAdresseCourrielFormatValide2() {
        try {
            profile = new Profile(nom, prenom, numeroTelephone, "francis@.com");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_ADRESSECOURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerAdresseCourrielFormatValide3() {
        try {
            profile = new Profile(nom, prenom, numeroTelephone, "francishotmail.com");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_ADRESSECOURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerAdresseCourrielFormatValide4() {
        try {
            profile = new Profile(nom, prenom, numeroTelephone, "francis@hotmailcom");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_ADRESSECOURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerAdresseCourrielFormatValide5() {
        try {
            profile = new Profile(nom, prenom, numeroTelephone, "francis@hotmail.");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_ADRESSECOURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerAdresseCourrielFormatValide6() {
        try {
            profile = new Profile(nom, prenom, numeroTelephone, "francis.bernier@hotmail.");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_ADRESSECOURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerAdresseCourrielFormatValide7() {
        try {
            profile = new Profile(nom, prenom, numeroTelephone, "francis1@hotmail.com");
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_ADRESSECOURRIEL_FORMAT_VALIDE);
        }
        assertFalse(estValider);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}