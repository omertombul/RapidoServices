package inm5001.rapidoservices.utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.service.Service;

import static inm5001.rapidoservices.utilisateur.ConstanteIdentifiant.MESSAGE_NOMUTILISATEUR_PAS_UNIQUE;
import static inm5001.rapidoservices.utilisateur.ConstanteUtilisateur.MESSAGE_IDENTIFIANT_NULL;
import static inm5001.rapidoservices.utilisateur.ConstanteUtilisateur.MESSAGE_PROFILE_NULL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UtilisateurTest {

    private Utilisateur utilisateur;
//attributs Utilisateur
    private Identifiant identifiant;
    private Profile profile;
    private ArrayList<Service> listeServices;
    private Service service;
    private ArrayList<String> listeCompetences;
    private String competence;
    //private boolean disponible;
    //private ArrayList<Evaluation> listeEvaluations;
    //private ArrayList<Evaluation> lisetEvaluationServicesGlobal;
    //private Evaluation evaluation;
    //private Geolocalisation geolocalisation;
//attributs Identifiant
    private String nomUtilisateur;
    private String motDePasse;
//attributs Profile
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private String adresseCourriel;
    private Boolean estValider;

    @Before
    public void setUp() throws MyException {
        listeServices = new ArrayList<>();
        service = new Service("service1@gmail.com");
        listeServices.add(service);
        service = new Service("service2@gmail.com");
        listeServices.add(service);
        service = null;
        listeCompetences = new ArrayList<>();
        listeCompetences.add("Plombier");
        listeCompetences.add("Électricien");
        competence = null;
        nomUtilisateur = "Francis";
        motDePasse = "Allo!234";
        nom = "Francis";
        prenom = "Bernier";
        numeroTelephone = "5145972143";
        adresseCourriel = "francis@hotmail.com";
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        profile = new Profile(nom, prenom, numeroTelephone, adresseCourriel);
        utilisateur = null;
        estValider = false;
    }

    @After
    public void tearDown() {
        identifiant = null;
        profile = null;
        listeServices = null;
        service = null;
        listeCompetences = null;
        competence = null;
        nom = null;
        prenom = null;
        numeroTelephone = null;
        adresseCourriel = null;
        utilisateur = null;
        motDePasse = null;
        estValider = null;
    }

    @Test
    public void UtilisateurPasNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        assertNotNull(utilisateur);
    }

    @Test
    public void UtilisateurIdentifiantPasNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        assertNotNull(utilisateur.identifiant);
    }

    @Test
    public void UtilisateurProfilePasNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        assertNotNull(utilisateur.profile);
    }

    @Test
    public void UtilisateurListeServicesPasNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        assertNotNull(utilisateur.listeServices);
    }
//IDENTIFIANT
    @Test
    public void ValiderIdentifiantPasNull() throws Exception {
        try {
            utilisateur = new Utilisateur(null, profile, listeServices, listeCompetences);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_IDENTIFIANT_NULL);
        }
        assertTrue(estValider);
    }

    @Test
    public void UtilisateurIdentifiantNomUtilisateur() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        String nomUtilisateur = utilisateur.identifiant.nomUtilisateur;
        assertEquals(nomUtilisateur, "Francis");
    }

    @Test
    public void UtilisateurIdentifiaMotDePasse() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        String motDePasse = utilisateur.identifiant.motDePasse;
        assertEquals(motDePasse, "Allo!234");
    }
//PROFILE
@Test
    public void ValiderProfilePasNull() throws Exception {
        try {
            utilisateur = new Utilisateur(identifiant, null, listeServices, listeCompetences);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_PROFILE_NULL);
        }
        assertTrue(estValider);
    }

    @Test
    public void UtilisateurProfileNom() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        String nom = utilisateur.profile.nom;
        assertEquals(nom, "FRANCIS");
    }

    @Test
    public void UtilisateurProfileNumeroTelephone() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        String numeroTelephone = utilisateur.profile.numeroTelephone;
        assertEquals(numeroTelephone, "5145972143");
    }

    @Test
    public void UtilisateurProfileAdresseCourriel() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        String adresseCourriel = utilisateur.profile.adresseCourriel;
        assertEquals(adresseCourriel, "francis@hotmail.com");
    }
//UTILISATEUR
    /*
    @Test
    public void UtilisateurProfileAdresseCourriel() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        String competence = utilisateur.listeCompetences.get(1);
        assertEquals(competence, "Électricien");
        //assertEquals(service, xxxx);
    }
    */
    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}