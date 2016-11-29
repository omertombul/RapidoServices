package inm5001.rapidoservices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import inm5001.rapidoservices.recherche.RechercheServices;
import inm5001.rapidoservices.service.EvaluationService;
import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.EvaluationUtilisateur;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.utilisateur.Utilisateur;

import static inm5001.rapidoservices.ConstanteOrchetrateur.MESSAGE_MOT_DE_PASSE_INVALIDE;
import static inm5001.rapidoservices.ConstanteOrchetrateur.MESSAGE_SERVICE_EXISTANT;
import static inm5001.rapidoservices.ConstanteOrchetrateur.MESSAGE_UTILISATEUR_N_EXISTE_PAS;
import static inm5001.rapidoservices.ConstanteOrchetrateur.MESSAGE_NOMUTILISATEUR_PAS_UNIQUE;
import static inm5001.rapidoservices.ConstanteRecherche.MESSAGE_MODE_TRI_INTROUVABLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrchestrateurTest {
    private Orchestrateur orchestrateur;
    private Utilisateur utilisateur;
    private Utilisateur utilisateur1;
    private Utilisateur utilisateur2;
    private Utilisateur utilisateur3;
    //attributs Utilisateur
    private Identifiant identifiant;
    private Identifiant identifiant1;
    private Identifiant identifiant2;
    private Identifiant identifiant3;
    private Profile profile;
    private ArrayList<TypeServices> listeServices;
    private TypeServices service;
    private TypeServices service2;
    private TypeServices service3;
    private ArrayList<String> listeCompetences;
    private String competence;
    private boolean disponibleUtilisateur;
    //private Geolocalisation geolocalisation;
//attributs Identifiant
    private String nomUtilisateur;
    private String nomUtilisateur1;
    private String nomUtilisateur2;
    private String nomUtilisateur3;
    private String motDePasse;
    //attributs Profile
    private String nom;
    private String prenom;
    private String numeroTelephoneProfile;
    private String adresseCourrielProfile;
    private Boolean estValider;
    //attributs AbstraiteServices
    private String nomSservice;
    private boolean disponibleService;
    private String ville;
    private String numeroTelephoneService;
    private String adresseCourrielService;
    private String description;
    //attribut Plomberie
    private float tauxHorraire;
    private float prixFixe;
    //attributs evaluationUtilisateur
    private float coteUtilisateur;
    private int nombreDEvaluationUtilisateur;
    private float coteTypeServicesMoyenne;
    private int nombreDEvaluationTypeServicesMoyenne;
    private EvaluationUtilisateur evaluationUtilisateur1;
    private EvaluationUtilisateur evaluationUtilisateur2;
    private EvaluationUtilisateur evaluationUtilisateur3;
    //attributs evaluationService
    private float coteService;
    private int nombreDEvaluationService;
    private EvaluationService evaluationService1;
    private EvaluationService evaluationService2;
    private EvaluationService evaluationService3;

    @Before
    public void setUp() throws MyException {
        orchestrateur = new Orchestrateur();
        listeServices = new ArrayList<>();
        listeCompetences = new ArrayList<>();
        competence = null;
        nomUtilisateur = "Francis";
        nomUtilisateur1 = "IdUser1";
        nomUtilisateur2 = "IdUser2";
        nomUtilisateur2 = "IdUser3";
        motDePasse = "Allo!234";
        nom = "Francis";
        prenom = "Bernier";
        numeroTelephoneProfile = "5145972143";
        adresseCourrielProfile = "francis@hotmail.com";
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        identifiant1 = new Identifiant("IdUser1", motDePasse);
        identifiant2 = new Identifiant("IdUser2", motDePasse);
        identifiant3 = new Identifiant("IdUser3", motDePasse);
        profile = new Profile(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile);
        //attributs EvaluationUtilisateur
        coteUtilisateur = 3.5f;
        nombreDEvaluationUtilisateur = 210;
        coteTypeServicesMoyenne = 4.5f;
        nombreDEvaluationTypeServicesMoyenne = 1000;
        evaluationUtilisateur1 = new EvaluationUtilisateur(1, nombreDEvaluationUtilisateur, 1, nombreDEvaluationTypeServicesMoyenne);
        evaluationUtilisateur2 = new EvaluationUtilisateur(3, nombreDEvaluationUtilisateur, 3, nombreDEvaluationTypeServicesMoyenne);
        evaluationUtilisateur3 = new EvaluationUtilisateur(5, nombreDEvaluationUtilisateur, 5, nombreDEvaluationTypeServicesMoyenne);
        //attributs EvaluationService
        coteService = 3.5f;
        nombreDEvaluationService = 210;
        evaluationService1 = new EvaluationService(1, nombreDEvaluationService);
        evaluationService2 = new EvaluationService(3, nombreDEvaluationService);
        evaluationService3 = new EvaluationService(5, nombreDEvaluationService);
        utilisateur = null;
        utilisateur1 = new Utilisateur(identifiant1, profile, listeServices, listeCompetences, evaluationUtilisateur1);
        utilisateur2 = new Utilisateur(identifiant2, profile, listeServices, listeCompetences, evaluationUtilisateur2);
        utilisateur3 = new Utilisateur(identifiant3, profile, listeServices, listeCompetences, evaluationUtilisateur3);
        estValider = true;
        nomSservice = "Plombier";
        disponibleUtilisateur = false;
        disponibleService = false;
        ville = "Montreal";
        numeroTelephoneService = "5144444444";
        adresseCourrielService = "plomberie@plomberi.com";
        description = "Repare les tuyeaux";
        tauxHorraire = 14.50f;
        prixFixe = 50.00f;
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, numeroTelephoneService,
                adresseCourrielService, description, evaluationService1);
        service2 = new TypeServices(tauxHorraire, prixFixe, "Electricien", disponibleService, ville, numeroTelephoneService,
                adresseCourrielService, description, evaluationService2);
        service3 = new TypeServices(tauxHorraire, prixFixe, "Mixer", disponibleService, ville, numeroTelephoneService,
                adresseCourrielService, description, evaluationService3);
    }

    @After
    public void tearDown() throws MyException {
        orchestrateur = null;
        identifiant = null;
        identifiant1 = null;
        identifiant2 = null;
        identifiant3 = null;
        profile = null;
        listeServices = null;
        listeCompetences = null;
        competence = null;
        nom = null;
        prenom = null;
        numeroTelephoneProfile = null;
        adresseCourrielProfile = null;
        //attributs EvaluationUtilisateur
        coteUtilisateur = 0f;
        nombreDEvaluationUtilisateur = 0;
        coteTypeServicesMoyenne = 0f;
        nombreDEvaluationTypeServicesMoyenne = 0;
        evaluationUtilisateur1 = null;
        evaluationUtilisateur2 = null;
        evaluationUtilisateur3 = null;
        evaluationService1 = null;
        evaluationService2 = null;
        evaluationService3 = null;
        utilisateur = null;
        utilisateur1 = null;
        utilisateur2 = null;
        utilisateur3 = null;
        motDePasse = null;
        estValider = null;
        nomSservice = null;
        disponibleUtilisateur = false;
        disponibleService = false;
        ville = null;
        numeroTelephoneService = null;
        adresseCourrielService = null;
        description = null;
        tauxHorraire = 0;
        prixFixe = 0;
        service = null;
        service2 = null;
        service3 = null;
    }

    @Test
    public void creerUtilisateur() throws MyException {
        try {
            orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                    motDePasse, listeServices, listeCompetences);
        } catch (Exception e) {
            estValider = false;
        }
        assertTrue(estValider);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void creerUtilisateurExiste() throws MyException {
        estValider = false;
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        try {
            orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                    motDePasse, listeServices, listeCompetences);
        } catch (Exception e) {
            estValider = e.getMessage().equals(MESSAGE_NOMUTILISATEUR_PAS_UNIQUE);
        }
        assertTrue(estValider);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void creerUtilisateurExistePas() throws MyException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        try {
            orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, "Francis2",
                    motDePasse, listeServices, listeCompetences);
        } catch (Exception e) {
            estValider = !e.getMessage().equals(MESSAGE_NOMUTILISATEUR_PAS_UNIQUE);
        }
        assertTrue(estValider);
        orchestrateur.supprimerCompte(nomUtilisateur);
        orchestrateur.supprimerCompte("Francis2");
    }

    @Test
    public void validationLogin() throws MyException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        try {
            utilisateur = orchestrateur.validationLogin(nomUtilisateur, motDePasse);
        } catch (Exception e) {
            estValider = false;
        }
        assertTrue(estValider);
        assertEquals(utilisateur.identifiant.nomUtilisateur, "FRANCIS");
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

//semble avoir un problème côté BdApi...l'exception est lancer dans l'étape de gestion de la listeServices
// et pas quand on vérifie si l'utilisateur existe. le résultat est le même, mais pourrait nous jouer un tour éventuellement.
    @Test
    public void validationLoginUtilisateurExistePas() throws MyException {
        try {
            utilisateur = orchestrateur.validationLogin("bidon", motDePasse);
        } catch (Exception e) {
            estValider = !e.getMessage().equals(MESSAGE_UTILISATEUR_N_EXISTE_PAS);
        }
        assertFalse(estValider);
    }

    @Test
    public void validationLoginMotDePasseInvalide() throws MyException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        try {
            utilisateur = orchestrateur.validationLogin(nomUtilisateur, "bidon");
        } catch (Exception e) {
            estValider = !e.getMessage().equals(MESSAGE_MOT_DE_PASSE_INVALIDE);
        }
        assertFalse(estValider);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void supprimerCompte() throws MyException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        try {
            orchestrateur.supprimerCompte(nomUtilisateur);
        } catch (Exception e) {
            estValider = false;
        }
        assertTrue(estValider);

        estValider = false;
        try {
            orchestrateur.recupererUtilisateur(nomUtilisateur);
        } catch (Exception e) {
            estValider = true;
        }
        assertTrue(estValider);
    }

    @Test
    public void ajouterOffreDeService() throws MyException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        try {
            orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        } catch (Exception e) {
            estValider = false;
        }
        assertTrue(estValider);
        assertEquals(orchestrateur.recupererUtilisateur(nomUtilisateur).listeServices.get(0).getNomSservice(), "Plombier");
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void ajouterOffreDeServiceDeuxServices() throws MyException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        try {
            orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
            orchestrateur.ajouterOffreDeService(nomUtilisateur, service2);
        } catch (Exception e) {
            estValider = false;
        }
        assertTrue(estValider);
        assertEquals(orchestrateur.recupererUtilisateur(nomUtilisateur).listeServices.get(1).getNomSservice(), nomSservice);
        assertEquals(orchestrateur.recupererUtilisateur(nomUtilisateur).listeServices.get(0).getNomSservice(), "Electricien");
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void ajouterOffreDeServiceDeuxMemeService() throws MyException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        try {
            orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
            orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        } catch (Exception e) {
            estValider = e.getMessage().equals(MESSAGE_SERVICE_EXISTANT);
        }
        assertTrue(estValider);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void ajouterOffreDeServiceDeuxCompetences() throws MyException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        try {
            orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
            orchestrateur.ajouterOffreDeService(nomUtilisateur, service2);
        } catch (Exception e) {
            estValider = false;
        }
        assertTrue(estValider);
        assertEquals(orchestrateur.recupererUtilisateur(nomUtilisateur).listeCompetences.get(1), nomSservice);
        assertEquals(orchestrateur.recupererUtilisateur(nomUtilisateur).listeCompetences.get(0), "Electricien");
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void retirerOffreDeService() throws MyException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        try {
            orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
            orchestrateur.retirerOffreDeService(nomUtilisateur, service);
        } catch (Exception e) {
            estValider = false;
        }
        if (estValider) {
            estValider = false;
            try {
                orchestrateur.recupererUtilisateur(nomUtilisateur).listeServices.get(0);
            } catch (Exception e) {
                estValider = true;
            }
        }
        assertTrue(estValider);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void modifierDisponibiliteUsager() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        utilisateur = orchestrateur.recupererUtilisateur(nomUtilisateur);
        assertFalse(utilisateur.disponible);

        orchestrateur.modifierDisponibiliteUsager(utilisateur.identifiant.nomUtilisateur, true);
        utilisateur = orchestrateur.recupererUtilisateur(nomUtilisateur);
        assertTrue(utilisateur.disponible);

        orchestrateur.modifierDisponibiliteUsager(utilisateur.identifiant.nomUtilisateur, false);
        utilisateur = orchestrateur.recupererUtilisateur(nomUtilisateur);
        assertFalse(utilisateur.disponible);

        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void modifierDisponibiliteService() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        utilisateur = orchestrateur.recupererUtilisateur(nomUtilisateur);

        orchestrateur.modifierDisponibiliteService(utilisateur.identifiant.nomUtilisateur, utilisateur.listeServices.get(0).getNomSservice(), true);
        utilisateur = orchestrateur.recupererUtilisateur(nomUtilisateur);
        assertTrue(utilisateur.listeServices.get(0).getDisponible());

        orchestrateur.modifierDisponibiliteService(utilisateur.identifiant.nomUtilisateur, utilisateur.listeServices.get(0).getNomSservice(), false);
        utilisateur = orchestrateur.recupererUtilisateur(nomUtilisateur);
        assertFalse(utilisateur.listeServices.get(0).getDisponible());

        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesPasDeCritere() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service2.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service3.getNomSservice(), true);

        ArrayList<RechercheServices> listeResultatsRechercheServices = orchestrateur.rechercheDeServices(0, 0, "", "", 0, 0, 0);
        assertTrue(listeResultatsRechercheServices.size() == 3);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesTauxHorraire() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service2.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service3.getNomSservice(), true);

        ArrayList<RechercheServices> listeResultatsRechercheServices = orchestrateur.rechercheDeServices(2, 0, "", "", 0, 0, 0);
        assertTrue(listeResultatsRechercheServices.get(0).recupererService().getTauxHorraire() <= 2);
        assertTrue(listeResultatsRechercheServices.size() == 2);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesPrixFix() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service2.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service3.getNomSservice(), true);

        ArrayList<RechercheServices> listeResultatsRechercheServices = orchestrateur.rechercheDeServices(0, 2, "", "", 0, 0, 0);
        assertTrue(listeResultatsRechercheServices.get(0).recupererService().getPrixFixe() <= 2);
        assertTrue(listeResultatsRechercheServices.size() == 2);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesPrixFixServicesNonDisponible() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service2.getNomSservice(), false);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service3.getNomSservice(), true);

        ArrayList<RechercheServices> listeResultatsRechercheServices = orchestrateur.rechercheDeServices(0, 2, "", "", 0, 0, 0);
        assertTrue(listeResultatsRechercheServices.get(0).recupererService().getPrixFixe() == 1);
        assertTrue(listeResultatsRechercheServices.size() == 1);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesNomServiceEtServiceDisponible() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service2.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service3.getNomSservice(), true);

        ArrayList<RechercheServices> listeResultatsRechercheServices = orchestrateur.rechercheDeServices(0, 0, "nomService1", "", 0, 0, 0);
        assertEquals(listeResultatsRechercheServices.get(0).recupererService().getNomSservice(), "nomService1");
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesNomServiceEtServiceNonDisponible() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service.getNomSservice(), false);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service2.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service3.getNomSservice(), true);

        ArrayList<RechercheServices> listeResultatsRechercheServices = orchestrateur.rechercheDeServices(0, 0, "nomService1", "", 0, 0, 0);
        assertTrue(listeResultatsRechercheServices.isEmpty());
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesNomServiceEtUsagerNonDisponible() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur, false);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service2.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service3.getNomSservice(), true);

        ArrayList<RechercheServices> listeResultatsRechercheServices = orchestrateur.rechercheDeServices(0, 0, "nomService1", "", 0, 0, 0);
        assertTrue(listeResultatsRechercheServices.isEmpty());
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesVille() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service2.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service3.getNomSservice(), true);

        ArrayList<RechercheServices> listeResultatsRechercheServices = orchestrateur.rechercheDeServices(0, 0, "", "villeC", 0, 0, 0);
        assertEquals(listeResultatsRechercheServices.get(0).recupererService().getVille(), "villeC");
        assertTrue(listeResultatsRechercheServices.size() == 1);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesCoteUtilisateur() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        orchestrateur.creerUtilisateur(utilisateur3);

        orchestrateur.ajouterOffreDeService(utilisateur1.identifiant.nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(utilisateur2.identifiant.nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(utilisateur3.identifiant.nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(utilisateur1.identifiant.nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteUsager(utilisateur2.identifiant.nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteUsager(utilisateur3.identifiant.nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteService(utilisateur1.identifiant.nomUtilisateur, service.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(utilisateur2.identifiant.nomUtilisateur, service2.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(utilisateur3.identifiant.nomUtilisateur, service3.getNomSservice(), true);

        utilisateur1 = orchestrateur.recupererUtilisateur(utilisateur1.identifiant.nomUtilisateur);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, utilisateur1.listeServices.get(0).getNomSservice());
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur);
        orchestrateur.faireUneEvaluation(utilisateur2.identifiant.nomUtilisateur, utilisateur1.identifiant.nomUtilisateur, "Client", 1);

        utilisateur2 = orchestrateur.recupererUtilisateur(utilisateur2.identifiant.nomUtilisateur);
        rechercheServices = new RechercheServices(utilisateur2, utilisateur2.listeServices.get(0).getNomSservice());
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.faireUneEvaluation(utilisateur1.identifiant.nomUtilisateur, utilisateur2.identifiant.nomUtilisateur, "Client", 3);

        utilisateur3 = orchestrateur.recupererUtilisateur(utilisateur3.identifiant.nomUtilisateur);
        rechercheServices = new RechercheServices(utilisateur3, utilisateur3.listeServices.get(0).getNomSservice());
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.faireUneEvaluation(utilisateur1.identifiant.nomUtilisateur, utilisateur3.identifiant.nomUtilisateur, "Client", 5);

        ArrayList<RechercheServices> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 0, "", "", 3, 0, 0);
        assertTrue(listeResultatsRecherche.size() == 1);
        assertTrue(listeResultatsRecherche.get(0).getUtilisateur().evaluationUtilisateur.coteUtilisateur == 4);

        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur3.identifiant.nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesCoteServicesMoyenne() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        orchestrateur.creerUtilisateur(utilisateur3);

        orchestrateur.ajouterOffreDeService(utilisateur1.identifiant.nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(utilisateur2.identifiant.nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(utilisateur3.identifiant.nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(utilisateur1.identifiant.nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteUsager(utilisateur2.identifiant.nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteUsager(utilisateur3.identifiant.nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteService(utilisateur1.identifiant.nomUtilisateur, service.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(utilisateur2.identifiant.nomUtilisateur, service2.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(utilisateur3.identifiant.nomUtilisateur, service3.getNomSservice(), true);

        utilisateur1 = orchestrateur.recupererUtilisateur(utilisateur1.identifiant.nomUtilisateur);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, utilisateur1.listeServices.get(0).getNomSservice());
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur);
        orchestrateur.faireUneEvaluation(utilisateur1.identifiant.nomUtilisateur, utilisateur2.identifiant.nomUtilisateur, utilisateur1.listeServices.get(0).getNomSservice(), 1);

        utilisateur1 = orchestrateur.recupererUtilisateur(utilisateur1.identifiant.nomUtilisateur);
        rechercheServices = new RechercheServices(utilisateur1, utilisateur1.listeServices.get(0).getNomSservice());
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur3.identifiant.nomUtilisateur);
        orchestrateur.faireUneEvaluation(utilisateur1.identifiant.nomUtilisateur, utilisateur3.identifiant.nomUtilisateur, utilisateur1.listeServices.get(0).getNomSservice(), 2);

        utilisateur2 = orchestrateur.recupererUtilisateur(utilisateur2.identifiant.nomUtilisateur);
        rechercheServices = new RechercheServices(utilisateur2, utilisateur2.listeServices.get(0).getNomSservice());
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.faireUneEvaluation(utilisateur2.identifiant.nomUtilisateur, utilisateur1.identifiant.nomUtilisateur, utilisateur2.listeServices.get(0).getNomSservice(), 3);

        utilisateur3 = orchestrateur.recupererUtilisateur(utilisateur3.identifiant.nomUtilisateur);
        rechercheServices = new RechercheServices(utilisateur3, utilisateur3.listeServices.get(0).getNomSservice());
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.faireUneEvaluation(utilisateur3.identifiant.nomUtilisateur, utilisateur1.identifiant.nomUtilisateur, utilisateur3.listeServices.get(0).getNomSservice(), 5);

        ArrayList<RechercheServices> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 0, "", "", 0, 1.5f, 0);
        assertTrue(listeResultatsRecherche.size() == 3);
        assertTrue(listeResultatsRecherche.get(0).getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne == 1.5);
        assertTrue(listeResultatsRecherche.get(1).getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne == 3);
        assertTrue(listeResultatsRecherche.get(2).getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne == 5);

        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur3.identifiant.nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesCoteService() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        orchestrateur.creerUtilisateur(utilisateur3);

        orchestrateur.ajouterOffreDeService(utilisateur1.identifiant.nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(utilisateur2.identifiant.nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(utilisateur3.identifiant.nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(utilisateur1.identifiant.nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteUsager(utilisateur2.identifiant.nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteUsager(utilisateur3.identifiant.nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteService(utilisateur1.identifiant.nomUtilisateur, service.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(utilisateur2.identifiant.nomUtilisateur, service2.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(utilisateur3.identifiant.nomUtilisateur, service3.getNomSservice(), true);

        utilisateur1 = orchestrateur.recupererUtilisateur(utilisateur1.identifiant.nomUtilisateur);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, utilisateur1.listeServices.get(0).getNomSservice());
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur);
        orchestrateur.faireUneEvaluation(utilisateur1.identifiant.nomUtilisateur, utilisateur2.identifiant.nomUtilisateur, utilisateur1.listeServices.get(0).getNomSservice(), 1);

        utilisateur2 = orchestrateur.recupererUtilisateur(utilisateur2.identifiant.nomUtilisateur);
        rechercheServices = new RechercheServices(utilisateur2, utilisateur2.listeServices.get(0).getNomSservice());
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.faireUneEvaluation(utilisateur2.identifiant.nomUtilisateur, utilisateur1.identifiant.nomUtilisateur, utilisateur2.listeServices.get(0).getNomSservice(), 3);

        utilisateur3 = orchestrateur.recupererUtilisateur(utilisateur3.identifiant.nomUtilisateur);
        rechercheServices = new RechercheServices(utilisateur3, utilisateur3.listeServices.get(0).getNomSservice());
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.faireUneEvaluation(utilisateur3.identifiant.nomUtilisateur, utilisateur1.identifiant.nomUtilisateur, utilisateur3.listeServices.get(0).getNomSservice(), 5);

        ArrayList<RechercheServices> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 0, "", "", 0, 0, 3);
        assertTrue(listeResultatsRecherche.size() == 2);
        assertTrue(listeResultatsRecherche.get(0).getUtilisateur().listeServices.get(0).evaluationService.coteService == 3);
        assertTrue(listeResultatsRecherche.get(1).getUtilisateur().listeServices.get(0).evaluationService.coteService == 5);

        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur3.identifiant.nomUtilisateur);
    }

    @Test
    public void trierResultatRechercheTauxHorraire() throws MyException {
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        utilisateur1.listeServices.add(service);
        utilisateur1.listeServices.add(service2);
        utilisateur1.listeServices.add(service3);

        RechercheServices pair1 = new RechercheServices(utilisateur1, "nomService1");
        RechercheServices pair2 = new RechercheServices(utilisateur1, "nomService2");
        RechercheServices pair3 = new RechercheServices(utilisateur1, "nomService3");

        ArrayList<RechercheServices> listeResultatsRechercheServices = new ArrayList<>();
        listeResultatsRechercheServices.add(pair3);
        listeResultatsRechercheServices.add(pair1);
        listeResultatsRechercheServices.add(pair2);

        listeResultatsRechercheServices = orchestrateur.trierResultatRecherche(listeResultatsRechercheServices, "tauxHorraire");
        assertTrue(listeResultatsRechercheServices.get(0).recupererService().getTauxHorraire() == 1);
        assertTrue(listeResultatsRechercheServices.get(1).recupererService().getTauxHorraire() == 2);
        assertTrue(listeResultatsRechercheServices.get(2).recupererService().getTauxHorraire() == 3);
    }

    @Test
    public void trierResultatRecherchePrixFixe() throws MyException {
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        utilisateur1.listeServices.add(service);
        utilisateur1.listeServices.add(service2);
        utilisateur1.listeServices.add(service3);

        RechercheServices pair1 = new RechercheServices(utilisateur1, "nomService1");
        RechercheServices pair2 = new RechercheServices(utilisateur1, "nomService2");
        RechercheServices pair3 = new RechercheServices(utilisateur1, "nomService3");

        ArrayList<RechercheServices> listeResultatsRechercheServices = new ArrayList<>();
        listeResultatsRechercheServices.add(pair3);
        listeResultatsRechercheServices.add(pair1);
        listeResultatsRechercheServices.add(pair2);

        listeResultatsRechercheServices = orchestrateur.trierResultatRecherche(listeResultatsRechercheServices, "prixFixe");
        assertTrue(listeResultatsRechercheServices.get(0).recupererService().getPrixFixe() == 1);
        assertTrue(listeResultatsRechercheServices.get(1).recupererService().getPrixFixe() == 2);
        assertTrue(listeResultatsRechercheServices.get(2).recupererService().getPrixFixe() == 3);
    }

    @Test
    public void trierResultatRechercheNomService() throws MyException {
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        utilisateur1.listeServices.add(service);
        utilisateur1.listeServices.add(service2);
        utilisateur1.listeServices.add(service3);

        RechercheServices pair1 = new RechercheServices(utilisateur1, "nomService1");
        RechercheServices pair2 = new RechercheServices(utilisateur1, "nomService2");
        RechercheServices pair3 = new RechercheServices(utilisateur1, "nomService3");

        ArrayList<RechercheServices> listeResultatsRechercheServices = new ArrayList<>();
        listeResultatsRechercheServices.add(pair3);
        listeResultatsRechercheServices.add(pair1);
        listeResultatsRechercheServices.add(pair2);

        listeResultatsRechercheServices = orchestrateur.trierResultatRecherche(listeResultatsRechercheServices, "nomService");
        assertEquals(listeResultatsRechercheServices.get(0).recupererService().getNomSservice(), "nomService1");
        assertEquals(listeResultatsRechercheServices.get(1).recupererService().getNomSservice(), "nomService2");
        assertEquals(listeResultatsRechercheServices.get(2).recupererService().getNomSservice(), "nomService3");
    }

    @Test
    public void trierResultatRechercheVille() throws MyException {
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        utilisateur1.listeServices.add(service);
        utilisateur1.listeServices.add(service2);
        utilisateur1.listeServices.add(service3);

        RechercheServices pair1 = new RechercheServices(utilisateur1, "nomService1");
        RechercheServices pair2 = new RechercheServices(utilisateur1, "nomService2");
        RechercheServices pair3 = new RechercheServices(utilisateur1, "nomService3");

        ArrayList<RechercheServices> listeResultatsRechercheServices = new ArrayList<>();
        listeResultatsRechercheServices.add(pair3);
        listeResultatsRechercheServices.add(pair1);
        listeResultatsRechercheServices.add(pair2);

        listeResultatsRechercheServices = orchestrateur.trierResultatRecherche(listeResultatsRechercheServices, "ville");
        assertEquals(listeResultatsRechercheServices.get(0).recupererService().getVille(), "villeA");
        assertEquals(listeResultatsRechercheServices.get(1).recupererService().getVille(), "villeB");
        assertEquals(listeResultatsRechercheServices.get(2).recupererService().getVille(), "villeC");
    }

    @Test
    public void trierResultatRechercheCoteService() throws MyException {
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description, evaluationService1);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description, evaluationService2);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description, evaluationService3);
        utilisateur1.listeServices.add(service);
        utilisateur1.listeServices.add(service2);
        utilisateur1.listeServices.add(service3);

        RechercheServices pair1 = new RechercheServices(utilisateur1, "nomService1");
        RechercheServices pair2 = new RechercheServices(utilisateur1, "nomService2");
        RechercheServices pair3 = new RechercheServices(utilisateur1, "nomService3");

        ArrayList<RechercheServices> listeResultatsRechercheServices = new ArrayList<>();
        listeResultatsRechercheServices.add(pair3);
        listeResultatsRechercheServices.add(pair1);
        listeResultatsRechercheServices.add(pair2);

        listeResultatsRechercheServices = orchestrateur.trierResultatRecherche(listeResultatsRechercheServices, "coteService");
        assertTrue(listeResultatsRechercheServices.get(0).recupererService().getEvaluationService().coteService == 1);
        assertTrue(listeResultatsRechercheServices.get(1).recupererService().getEvaluationService().coteService == 3);
        assertTrue(listeResultatsRechercheServices.get(2).recupererService().getEvaluationService().coteService == 5);
    }

    @Test
    public void trierResultatRechercheCoteUtilisateur() throws MyException {
        RechercheServices pair1 = new RechercheServices(utilisateur1, "nomService1");
        RechercheServices pair2 = new RechercheServices(utilisateur2, "nomService2");
        RechercheServices pair3 = new RechercheServices(utilisateur3, "nomService3");

        ArrayList<RechercheServices> listeResultatsRechercheServices = new ArrayList<>();
        listeResultatsRechercheServices.add(pair3);
        listeResultatsRechercheServices.add(pair1);
        listeResultatsRechercheServices.add(pair2);

        listeResultatsRechercheServices = orchestrateur.trierResultatRecherche(listeResultatsRechercheServices, "coteUtilisateur");
        assertTrue(listeResultatsRechercheServices.get(0).getUtilisateur().evaluationUtilisateur.coteUtilisateur == 1);
        assertTrue(listeResultatsRechercheServices.get(1).getUtilisateur().evaluationUtilisateur.coteUtilisateur == 3);
        assertTrue(listeResultatsRechercheServices.get(2).getUtilisateur().evaluationUtilisateur.coteUtilisateur == 5);
    }

    @Test
    public void trierResultatRechercheCoteServicesMoyenne() throws MyException {
        RechercheServices pair1 = new RechercheServices(utilisateur1, "nomService1");
        RechercheServices pair2 = new RechercheServices(utilisateur2, "nomService2");
        RechercheServices pair3 = new RechercheServices(utilisateur3, "nomService3");

        ArrayList<RechercheServices> listeResultatsRechercheServices = new ArrayList<>();
        listeResultatsRechercheServices.add(pair3);
        listeResultatsRechercheServices.add(pair1);
        listeResultatsRechercheServices.add(pair2);

        listeResultatsRechercheServices = orchestrateur.trierResultatRecherche(listeResultatsRechercheServices, "coteServiceMoyenne");
        assertTrue(listeResultatsRechercheServices.get(0).getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne == 1);
        assertTrue(listeResultatsRechercheServices.get(1).getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne == 3);
        assertTrue(listeResultatsRechercheServices.get(2).getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne == 5);
    }

    @Test
    public void trierResultatRechercheElse() throws MyException {
        RechercheServices pair1 = new RechercheServices(utilisateur1, "nomService1");
        RechercheServices pair2 = new RechercheServices(utilisateur2, "nomService2");

        ArrayList<RechercheServices> listeResultatsRechercheServices = new ArrayList<>();
        listeResultatsRechercheServices.add(pair1);
        listeResultatsRechercheServices.add(pair2);

        try {
            orchestrateur.trierResultatRecherche(listeResultatsRechercheServices, "bidon");
        } catch (Exception e) {
            estValider = !e.getMessage().equals(MESSAGE_MODE_TRI_INTROUVABLE);
        }
        assertFalse(estValider);
    }

    @Test
    public void accepterUnFournisseurDeServiceNoTelephoneService() throws SQLException, MyException {
        utilisateur1.listeServices.add(service);
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, "Plombier");

        assertEquals(orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur).get(0), "(514) 444-4444");
        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
    }

    @Test
    public void accepterUnFournisseurDeServiceCourrielService() throws SQLException, MyException {
        utilisateur1.listeServices.add(service);
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, "Plombier");

        assertEquals(orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur).get(1), "plomberie@plomberi.com");
        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
    }

    @Test
    public void accepterUnFournisseurDeServiceNoTelephoneUtilisateur() throws MyException, SQLException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, "", adresseCourrielService, description);
        utilisateur1.listeServices.add(service);
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, "Plombier");

        assertEquals(orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur).get(0), "(514) 597-2143");
        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
    }

    @Test
    public void accepterUnFournisseurDeServiceCourrielUtilisateur() throws MyException, SQLException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, numeroTelephoneService, "", description);
        utilisateur1.listeServices.add(service);
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, "Plombier");

        assertEquals(orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur).get(1), "francis@hotmail.com");
        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
    }

    @Test
    public void accepterUnFournisseurDeServiceCreationDeLignesCoteServiceService() throws MyException, SQLException {
        utilisateur1.listeServices.add(service);
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, "Plombier");
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur);
        assertTrue(orchestrateur.obtenirMesEvaluationsADonner(utilisateur1.identifiant.nomUtilisateur).size() == 1);
        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
    }

    @Test
    public void accepterUnFournisseurDeServiceCreationDeLignesCoteServiceClient() throws MyException, SQLException {
        utilisateur1.listeServices.add(service);
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, "Plombier");
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur);

        assertTrue(orchestrateur.obtenirMesEvaluationsADonner(utilisateur2.identifiant.nomUtilisateur).size() == 1);
        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
    }

    @Test
    public void obtenirMesEvaluationsADonner() throws MyException, SQLException {
        utilisateur1.listeServices.add(service);
        utilisateur1.listeServices.add(service2);
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);

        RechercheServices rechercheServices = new RechercheServices(utilisateur1, "Plombier");
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur);

        rechercheServices = new RechercheServices(utilisateur1, "Electricien");
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur);

        assertTrue(orchestrateur.obtenirMesEvaluationsADonner(utilisateur2.identifiant.nomUtilisateur).size() == 2);
        assertTrue(orchestrateur.obtenirMesEvaluationsADonner(utilisateur1.identifiant.nomUtilisateur).size() == 1);
        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
    }

    @Test
    public void faireUneEvaluationCoteUtilisateur() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        orchestrateur.ajouterOffreDeService(utilisateur1.identifiant.nomUtilisateur, service);
        utilisateur1 = orchestrateur.recupererUtilisateur(utilisateur1.identifiant.nomUtilisateur);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, service.getNomSservice());
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur);

        orchestrateur.faireUneEvaluation(utilisateur2.identifiant.nomUtilisateur, utilisateur1.identifiant.nomUtilisateur, "Client", 4.1f);

        utilisateur1 = orchestrateur.recupererUtilisateur(utilisateur1.identifiant.nomUtilisateur);
        utilisateur2 = orchestrateur.recupererUtilisateur(utilisateur2.identifiant.nomUtilisateur);
        System.out.println("***************coteUtilisateur: " + utilisateur2.evaluationUtilisateur.coteUtilisateur);
        assertTrue(utilisateur2.evaluationUtilisateur.coteUtilisateur == 4.1f);
        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
    }

    @Test
    public void faireUneEvaluationCoteServiceMoyenne() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        orchestrateur.creerUtilisateur(utilisateur3);
        orchestrateur.ajouterOffreDeService(utilisateur1.identifiant.nomUtilisateur, service);
        utilisateur1 = orchestrateur.recupererUtilisateur(utilisateur1.identifiant.nomUtilisateur);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, service.getNomSservice());

        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur);
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur3.identifiant.nomUtilisateur);

        orchestrateur.faireUneEvaluation(utilisateur1.identifiant.nomUtilisateur, utilisateur2.identifiant.nomUtilisateur, service.getNomSservice(), 1.2f);
        orchestrateur.faireUneEvaluation(utilisateur1.identifiant.nomUtilisateur, utilisateur3.identifiant.nomUtilisateur, service.getNomSservice(), 5f);

        utilisateur1 = orchestrateur.recupererUtilisateur(utilisateur1.identifiant.nomUtilisateur);
        assertTrue(utilisateur1.evaluationUtilisateur.coteTypeServicesMoyenne == 3.1f);
        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur3.identifiant.nomUtilisateur);
    }

    @Test
    public void faireUneEvaluationCoteService() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(utilisateur1);
        orchestrateur.creerUtilisateur(utilisateur2);
        orchestrateur.ajouterOffreDeService(utilisateur1.identifiant.nomUtilisateur, service);
        utilisateur1 = orchestrateur.recupererUtilisateur(utilisateur1.identifiant.nomUtilisateur);
        RechercheServices rechercheServices = new RechercheServices(utilisateur1, service.getNomSservice());
//erreur au niveau BD, ajoute un compte dans nmCote et nmCoteFournisseur, mais devrait pas...hypothese...doit compter la deuxième ligne (Client)
        orchestrateur.accepterUnFournisseurDeService(rechercheServices, utilisateur2.identifiant.nomUtilisateur);

        orchestrateur.faireUneEvaluation(utilisateur1.identifiant.nomUtilisateur, utilisateur2.identifiant.nomUtilisateur, service.getNomSservice(), 3.5f);
        orchestrateur.faireUneEvaluation(utilisateur1.identifiant.nomUtilisateur, utilisateur2.identifiant.nomUtilisateur, service.getNomSservice(), 3.7f);

        utilisateur1 = orchestrateur.recupererUtilisateur(utilisateur1.identifiant.nomUtilisateur);
        utilisateur2 = orchestrateur.recupererUtilisateur(utilisateur2.identifiant.nomUtilisateur);
        System.out.println("***************coteUtilisateur: " + utilisateur2.evaluationUtilisateur.coteUtilisateur);
        System.out.println("***************coteServiceMoyenne: " + utilisateur1.evaluationUtilisateur.coteTypeServicesMoyenne);
        System.out.println("***************coteService: " + utilisateur1.listeServices.get(0).evaluationService.coteService);
        //assertTrue(utilisateur.listeServices.get(0).evaluationService.coteService == 3.5);
        orchestrateur.supprimerCompte(utilisateur1.identifiant.nomUtilisateur);
        orchestrateur.supprimerCompte(utilisateur2.identifiant.nomUtilisateur);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}
