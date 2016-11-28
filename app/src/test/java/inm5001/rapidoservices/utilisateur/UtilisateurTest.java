package inm5001.rapidoservices.utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.EvaluationService;
import inm5001.rapidoservices.service.TypeServices;

import static inm5001.rapidoservices.utilisateur.ConstanteUtilisateur.MESSAGE_IDENTIFIANT_NULL;
import static inm5001.rapidoservices.utilisateur.ConstanteUtilisateur.MESSAGE_PROFILE_NULL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UtilisateurTest {

    private Utilisateur utilisateur;
//attributs Utilisateur
    private Identifiant identifiant;
    private Profile profile;
    private ArrayList<TypeServices> listeServices;
    private TypeServices service;
    private ArrayList<String> listeCompetences;
    private String competence;
    private boolean disponibleUtilisateur;
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
    private boolean disponibleService;
    private String ville;
    private byte cote;
    private String numeroTelephoneService;
    private String adresseCourrielService;
    private String description;
//attribut Plomberie
    private String nomSservice;
    private float tauxHorraire;
    private float prixFixe;
//attributs EvaluationUtilisateur
    private float coteUtilisateur;
    private int nombreDEvaluationUtilisateur;
    private float coteTypeServicesMoyenne;
    private int nombreDEvaluationTypeServicesMoyenne;
    private EvaluationUtilisateur evaluationUtilisateur;
//attributs EvaluationService
    private float coteService;
    private int nombreDEvaluationService;
    private EvaluationService evaluationService;

    @Before
    public void setUp() throws MyException {
        listeServices = new ArrayList<>();
        service = null;
        listeCompetences = new ArrayList<>();
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
        disponibleUtilisateur = false;
        disponibleService = false;
        ville = "Montreal";
        cote = 2;
        numeroTelephoneService ="5144444444";
        adresseCourrielService = "plomberie@plomberi.com";
        description = "Repare les tuyeaux";
        tauxHorraire = 14.50f;
        prixFixe = 50.00f;
        listeServices.add(new TypeServices(tauxHorraire, prixFixe, nomUtilisateur, disponibleService, ville, numeroTelephoneService,
                "service1@gmail.com", description));
        listeServices.add(new TypeServices(tauxHorraire, prixFixe, nomUtilisateur, disponibleService, ville, numeroTelephoneService,
                "service2@gmail.com", description));
        listeCompetences.add("Plombier");
        listeCompetences.add("Electricien");
        //attributs EvaluationUtilisateur
        coteUtilisateur = 3.5f;
        nombreDEvaluationUtilisateur = 210;
        coteTypeServicesMoyenne = 4.5f;
        nombreDEvaluationTypeServicesMoyenne = 1000;
        evaluationUtilisateur = new EvaluationUtilisateur(coteUtilisateur, nombreDEvaluationUtilisateur, coteTypeServicesMoyenne,
                                                            nombreDEvaluationTypeServicesMoyenne);
        //attributs EvaluationService
        coteService = 3.5f;
        nombreDEvaluationService = 210;
        evaluationService = new EvaluationService(coteService, nombreDEvaluationService);
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
        disponibleUtilisateur = false;
        disponibleService = false;
        ville = null;
        cote = 0;
        numeroTelephoneService = null;
        adresseCourrielService = null;
        description = null;
        tauxHorraire = 0;
        prixFixe = 0;
        //attributs EvaluationUtilisateur
        coteUtilisateur = 0;
        nombreDEvaluationUtilisateur = 0;
        coteTypeServicesMoyenne = 0;
        nombreDEvaluationTypeServicesMoyenne = 0;
        evaluationUtilisateur = null;
        //attributs EvaluationService
        coteService = 0;
        nombreDEvaluationService = 0;
        evaluationService = null;
    }

    @Test
    public void UtilisateurPasNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        assertNotNull(utilisateur);
    }

    @Test
    public void UtilisateurIdentifiantPasNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        assertNotNull(utilisateur.identifiant);
    }

    @Test
    public void UtilisateurProfilePasNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        assertNotNull(utilisateur.profile);
    }

    @Test
    public void UtilisateurListeServicesPasNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        assertNotNull(utilisateur.listeServices);
    }

    @Test
    public void UtilisateurEvaluationPasNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        assertNotNull(utilisateur.evaluationUtilisateur);
    }
//IDENTIFIANT
    @Test
    public void ValiderIdentifiantPasNull() throws Exception {
        try {
            utilisateur = new Utilisateur(null, profile, listeServices, listeCompetences, evaluationUtilisateur);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_IDENTIFIANT_NULL);
        }
        assertTrue(estValider);
    }

    @Test
    public void UtilisateurIdentifiantNomUtilisateur() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        nomUtilisateur = utilisateur.identifiant.nomUtilisateur;
        assertEquals(nomUtilisateur, "FRANCIS");
    }

    @Test
    public void UtilisateurIdentifiaMotDePasse() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        motDePasse = utilisateur.identifiant.motDePasse;
        assertEquals(motDePasse, "Allo!234");
    }

    @Test
    public void UtilisateurDisponible() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        disponibleUtilisateur = utilisateur.disponible;
        assertFalse(disponibleUtilisateur);
    }
//PROFILE
@Test
    public void ValiderProfilePasNull() throws Exception {
        try {
            utilisateur = new Utilisateur(identifiant, null, listeServices, listeCompetences, evaluationUtilisateur);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_PROFILE_NULL);
        }
        assertTrue(estValider);
    }

    @Test
    public void UtilisateurProfileNom() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        nom = utilisateur.profile.nom;
        assertEquals(nom, "FRANCIS");
    }

    @Test
    public void UtilisateurProfileNumeroTelephone() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        numeroTelephoneProfile = utilisateur.profile.numeroTelephone;
        assertEquals(numeroTelephoneProfile, "(514) 597-2143");
    }

    @Test
    public void UtilisateurProfileAdresseCourriel() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        adresseCourrielProfile = utilisateur.profile.adresseCourriel;
        assertEquals(adresseCourrielProfile, "francis@hotmail.com");
    }
//LISTESERVICES
    @Test
    public void TraiterListeServices() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        service = utilisateur.listeServices.get(1);
        assertEquals(service.getCourriel(), "service2@gmail.com");
    }

    @Test
    public void TraiterListeServicesNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, null, listeCompetences, evaluationUtilisateur);
        assertTrue((utilisateur.listeServices).isEmpty());
    }
//LISTECOMPETENCES
    @Test
    public void TraiterListeCompetences() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        competence = utilisateur.listeCompetences.get(1);
        assertEquals(competence, "Electricien");
    }

    @Test
    public void TraiterListeCompetencesNull() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, null, evaluationUtilisateur);
        assertTrue((utilisateur.listeCompetences).isEmpty());
    }
//EVALUATIONUTILISATEUR
    @Test
    public void traiterEvaluationUtilisateurCoteUtilisateur() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        coteUtilisateur = utilisateur.evaluationUtilisateur.coteUtilisateur;
        assertTrue(coteUtilisateur == 3.5);
    }

    @Test
    public void traiterEvaluationUtilisateurNombreDEvaluationUtilisateur() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        nombreDEvaluationUtilisateur = utilisateur.evaluationUtilisateur.nombreDEvaluationUtilisateur;
        assertTrue(nombreDEvaluationUtilisateur == 210);
    }

    @Test
    public void traiterEvaluationUtilisateurCoteTypeServicesMoyenne() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        coteTypeServicesMoyenne = utilisateur.evaluationUtilisateur.coteTypeServicesMoyenne;
        assertTrue(coteTypeServicesMoyenne == 4.5);
    }

    @Test
    public void traiterEvaluationUtilisateurNombreDEvaluationTypeServicesMoyenne() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
        nombreDEvaluationTypeServicesMoyenne = utilisateur.evaluationUtilisateur.nombreDEvaluationTypeServicesMoyenne;
        assertTrue(nombreDEvaluationTypeServicesMoyenne == 1000);
    }

//EVALUATIONSERVICE
    @Test
    public void traiterEvaluationServiceCoteService() throws Exception {
        service = new TypeServices(tauxHorraire, prixFixe, nomUtilisateur, disponibleService, ville, numeroTelephoneService,
                adresseCourrielService, description, evaluationService);
        ArrayList<TypeServices> listeServices2 = new ArrayList<>();
        listeServices2.add(service);
        utilisateur = new Utilisateur(identifiant, profile, listeServices2, listeCompetences, evaluationUtilisateur);
        coteService = utilisateur.listeServices.get(0).getEvaluationService().coteService;
        assertTrue(coteService == 3.5);
    }

    @Test
    public void traiterEvaluationServiceNombreDEvaluationService() throws Exception {
        service = new TypeServices(tauxHorraire, prixFixe, nomUtilisateur, disponibleService, ville, numeroTelephoneService,
                adresseCourrielService, description, evaluationService);
        ArrayList<TypeServices> listeServices2 = new ArrayList<>();
        listeServices2.add(service);
        utilisateur = new Utilisateur(identifiant, profile, listeServices2, listeCompetences, evaluationUtilisateur);
        nombreDEvaluationService = utilisateur.listeServices.get(0).getEvaluationService().nombreDEvaluationService;
        assertTrue(nombreDEvaluationService == 210);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}