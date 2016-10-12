package inm5001.rapidoservices.utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import inm5001.rapidoservices.service.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UtilisateurTest {

    //attributs Utilisateur
    private Utilisateur utilisateur;
    //private int disponibilite;
    private Identifiant identifiant;
    private Profile profile;
    private ArrayList<Service> listeServices;
    private Service service;
    private ArrayList<String> listeCompetences;
    private String competence;
    //private ArrayList<Evaluation> listeEvaluations;
    //private ArrayList<Evaluation> lisetEvaluationServicesGlobal;
    //private Geolocalisation geolocalisation;
    //attributs Identifiant
    private String nomUtilisateur;
    private String motDePasse;
    //attributs Profile
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private String adresseCourriel;
    private ArrayList<String> habiletes;
    private String habilete;

    @Before
    public void setUp() {
        listeServices = new ArrayList<>();
        //listeServices.add(xxxx);
        //listeServices.add(xxxx);
        //service = null;
        listeCompetences = new ArrayList<>();
        listeCompetences.add("Plombier");
        listeCompetences.add("Électricien");
        competence = null;
        nom = "Francis";
        prenom = "Bernier";
        numeroTelephone = "(514) 597-2143";
        adresseCourriel = "francis@hotmail.com";
        habiletes = new ArrayList<>();
        habiletes.add("Plombier");
        habiletes.add("Électricien");
        habilete = null;
        nomUtilisateur = "Francis";
        motDePasse = "Allo123";
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel, habiletes);
        utilisateur = null;
    }

    @After
    public void tearDown() {
        identifiant = null;
        profile = null;
        listeServices = null;
        listeCompetences = null;
        listeCompetences = null;
        competence = null;
        nom = null;
        prenom = null;
        numeroTelephone = null;
        adresseCourriel = null;
        habiletes = null;
        habilete = null;
        utilisateur = null;
        motDePasse = null;
    }

    @Test
    public void Utilisateur1() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        assertNotNull(utilisateur);
    }

    @Test
    public void Utilisateur2() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        assertNotNull(utilisateur.profile);
    }

    @Test
    public void Utilisateur3() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        assertNotNull(utilisateur.identifiant);
    }

    @Test
    public void UtilisateurX() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        //partie Identifiant
        String nomUtilisateur = utilisateur.identifiant.nomUtilisateur;
        String motDePasse = utilisateur.identifiant.motDePasse;
        assertEquals(nomUtilisateur, "Francis");
        assertEquals(motDePasse, "Allo123");
        //partie Profile
        String nom = utilisateur.profile.nom;
        String prenom = utilisateur.profile.prenom;
        String numeroTelephone = utilisateur.profile.numeroTelephone;
        String adresseCourriel = utilisateur.profile.adresseCourriel;
        String habilete = utilisateur.profile.habiletes.get(1);
        assertEquals(nom, "Francis");
        assertEquals(prenom, "Bernier");
        assertEquals(numeroTelephone, "(514) 597-2143");
        assertEquals(adresseCourriel, "francis@hotmail.com");
        assertEquals(habilete, "Électricien");
        //partie Utilisateur
        //Service service = utilisateur.listeServices.get(1);
        String competence = utilisateur.listeCompetences.get(1);
        assertEquals(competence, "Électricien");
        //assertEquals(service, xxxx);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}