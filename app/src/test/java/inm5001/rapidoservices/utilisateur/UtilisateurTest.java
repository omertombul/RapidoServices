package inm5001.rapidoservices.utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.Plomberie;

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
    private ArrayList<AbstraiteServices> listeServices;
    private AbstraiteServices service;
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
    private String numeroTelephoneProfile;
    private String adresseCourrielProfile;
    private Boolean estValider;
//attributs AbstraiteServices
    boolean disponible;
    String ville;
    byte cote;
    String numeroTelephoneService;
    String adresseCourrielService;
    String description;
//attribut Plomberie
    String nomSservice;
    float tauxHorraire;
    float prixFixe;

    @Before
    public void setUp() throws MyException {
        listeServices = new ArrayList<>();
        listeServices.add(new Plomberie(tauxHorraire, prixFixe, disponible, ville, cote,
                numeroTelephoneService, "service1@gmail.com", description));
        listeServices.add(new Plomberie(tauxHorraire, prixFixe, disponible, ville, cote,
                numeroTelephoneService, "service2@gmail.com", description));
        service = null;
        listeCompetences = new ArrayList<>();
        listeCompetences.add("Plombier");
        listeCompetences.add("Électricien");
        competence = null;
        nomUtilisateur = "Francis";
        motDePasse = "Allo!234";
        nom = "Francis";
        prenom = "Bernier";
        numeroTelephoneProfile = "5145972143";
        adresseCourrielProfile = "francis@hotmail.com";
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        profile = new Profile(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile);
        utilisateur = null;
        estValider = false;
        disponible = false;
        ville = "Montreal";
        cote = 2;
        numeroTelephoneService ="514-444-4444";
        adresseCourrielService = "plomberie@plomberi.com";
        description = "Repare les tuyeaux";
        tauxHorraire = 14.50f;
        prixFixe = 50.00f;
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
        numeroTelephoneProfile = null;
        adresseCourrielProfile = null;
        utilisateur = null;
        motDePasse = null;
        estValider = null;
        disponible = false;
        ville = null;
        cote = 0;
        numeroTelephoneService = null;
        adresseCourrielService = null;
        description = null;
        tauxHorraire = 0;
        prixFixe = 0;
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
        nomUtilisateur = utilisateur.identifiant.nomUtilisateur;
        assertEquals(nomUtilisateur, "Francis");
    }

    @Test
    public void UtilisateurIdentifiaMotDePasse() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        motDePasse = utilisateur.identifiant.motDePasse;
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
        nom = utilisateur.profile.nom;
        assertEquals(nom, "FRANCIS");
    }

    @Test
    public void UtilisateurProfileNumeroTelephone() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        numeroTelephoneProfile = utilisateur.profile.numeroTelephone;
        assertEquals(numeroTelephoneProfile, "5145972143");
    }

    @Test
    public void UtilisateurProfileAdresseCourriel() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        adresseCourrielProfile = utilisateur.profile.adresseCourriel;
        assertEquals(adresseCourrielProfile, "francis@hotmail.com");
    }
//LISTESERVICES
    @Test
    public void TraiterListeServices() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        service = utilisateur.listeServices.get(1);
        assertEquals(service.getCourriel(), "service2@gmail.com");
    }

    @Test
    public void TraiterListeServicesNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, null, listeCompetences);
        assertTrue((utilisateur.listeServices).isEmpty());
    }
//LISTECOMPETENCES
    @Test
    public void TraiterListeCompetences() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        competence = utilisateur.listeCompetences.get(1);
        assertEquals(competence, "Électricien");
    }

    @Test
    public void TraiterListeCompetencesNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, null);
        assertTrue((utilisateur.listeCompetences).isEmpty());
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}