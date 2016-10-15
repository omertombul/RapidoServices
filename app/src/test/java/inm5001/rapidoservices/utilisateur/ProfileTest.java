package inm5001.rapidoservices.utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import inm5001.rapidoservices.MyException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProfileTest {

    private String nom;
    private String prenom;
    private String numeroTelephone;
    private String adresseCourriel;
    private ArrayList<String> habiletes;
    private String habilete = null;
    private Identifiant identifiant;
    private Profile profile;

    @Before
    public void setUp() throws MyException {
        nom = "Francis";
        prenom = "Bernier";
        numeroTelephone = "(514) 597-2143";
        adresseCourriel = "francis@hotmail.com";
        habiletes = new ArrayList<>();
        habiletes.add("Plombier");
        habiletes.add("Électricien");
        habilete = null;
        identifiant = new Identifiant("Francis", "Allo123");
        profile = null;
    }

    @After
    public void tearDown() {
        nom = null;
        prenom = null;
        numeroTelephone = null;
        adresseCourriel = null;
        habiletes = null;
        habilete = null;
        identifiant = null;
        profile = null;
    }

    @Test
    public void Profile1() throws Exception {
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel, habiletes);
        assertNotNull(profile);
    }

    @Test
    public void Profile2() throws Exception {
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel, habiletes);
        String nom = profile.nom;
        String prenom = profile.prenom;
        String numeroTelephone = profile.numeroTelephone;
        String adresseCourriel = profile.adresseCourriel;
        String habilete = profile.habiletes.get(1);
        assertEquals(nom, "Francis");
        assertEquals(prenom, "Bernier");
        assertEquals(numeroTelephone, "(514) 597-2143");
        assertEquals(adresseCourriel, "francis@hotmail.com");
        assertEquals(habilete, "Électricien");
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}