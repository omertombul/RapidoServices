package inm5001.rapidoservices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

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
    private byte cote;
    private String numeroTelephoneService;
    private String adresseCourrielService;
    private String description;
    //attribut Plomberie
    private float tauxHorraire;
    private float prixFixe;
    //attributs evaluationUtilisateur
    public float coteUtilisateur;
    public int nombreDEvaluationUtilisateur;
    public float coteTypeServicesMoyenne;
    public int nombreDEvaluationTypeServicesMoyenne;
    private EvaluationUtilisateur evaluationUtilisateur1;
    private EvaluationUtilisateur evaluationUtilisateur2;
    private EvaluationUtilisateur evaluationUtilisateur3;
    //attributs evaluationService
    public float coteService;
    public int nombreDEvaluationService;
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
        evaluationUtilisateur1 = new EvaluationUtilisateur(50, nombreDEvaluationUtilisateur, 50,
                nombreDEvaluationTypeServicesMoyenne);
        evaluationUtilisateur2 = new EvaluationUtilisateur(90, nombreDEvaluationUtilisateur, 90,
                nombreDEvaluationTypeServicesMoyenne);
        evaluationUtilisateur3 = new EvaluationUtilisateur(100, nombreDEvaluationUtilisateur, 100,
                nombreDEvaluationTypeServicesMoyenne);
        //attributs EvaluationService
        coteService = 3.5f;
        nombreDEvaluationService = 210;
        evaluationService1 = new EvaluationService(50, nombreDEvaluationService);
        evaluationService2 = new EvaluationService(90, nombreDEvaluationService);
        evaluationService3 = new EvaluationService(100, nombreDEvaluationService);
        utilisateur = null;
        utilisateur1 = new Utilisateur(identifiant1, profile, listeServices, listeCompetences, evaluationUtilisateur1);
        utilisateur2 = new Utilisateur(identifiant2, profile, listeServices, listeCompetences, evaluationUtilisateur2);
        utilisateur3 = new Utilisateur(identifiant3, profile, listeServices, listeCompetences, evaluationUtilisateur3);
        estValider = true;
        nomSservice = "Plombier";
        disponibleUtilisateur = false;
        disponibleService = false;
        ville = "Montreal";
        cote = 2;
        numeroTelephoneService = "5144444444";
        adresseCourrielService = "plomberie@plomberi.com";
        description = "Repare les tuyeaux";
        tauxHorraire = 14.50f;
        prixFixe = 50.00f;
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, numeroTelephoneService,
                adresseCourrielService, description);
        service2 = new TypeServices(tauxHorraire, prixFixe, "Electricien", disponibleService, ville, numeroTelephoneService,
                adresseCourrielService, description);

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
        cote = 0;
        numeroTelephoneService = null;
        adresseCourrielService = null;
        description = null;
        tauxHorraire = 0;
        prixFixe = 0;
        service = null;
        service2 = null;
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
//L'objet utilisateur retourner par BdApi ne semble pas avoir les informations de L'évaluation service
    /*@Test
    public void evaluerService() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        orchestrateur.faireUneEvaluation(nomUtilisateur, "Client", "Plombier", 90);
        utilisateur = orchestrateur.recupererUtilisateur(nomUtilisateur);
        System.out.println("***************: " + utilisateur.listeServices.get(0).evaluationService.coteService);
        assertTrue(utilisateur.listeServices.get(0).evaluationService.coteService == 90);
    }
*/
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

        ArrayList<Recherche> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 0, "", "", 0, 0, 0);
        assertTrue(listeResultatsRecherche.size() == 3);
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

        ArrayList<Recherche> listeResultatsRecherche = orchestrateur.rechercheDeServices(2, 0, "", "", 0, 0, 0);
        assertTrue(listeResultatsRecherche.get(0).recupererService().getTauxHorraire() <= 2);
        assertTrue(listeResultatsRecherche.size() == 2);
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

        ArrayList<Recherche> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 2, "", "", 0, 0, 0);
        assertTrue(listeResultatsRecherche.get(0).recupererService().getPrixFixe() <= 2);
        assertTrue(listeResultatsRecherche.size() == 2);
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

        ArrayList<Recherche> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 2, "", "", 0, 0, 0);
        assertTrue(listeResultatsRecherche.get(0).recupererService().getPrixFixe() == 1);
        assertTrue(listeResultatsRecherche.size() == 1);
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

        ArrayList<Recherche> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 0, "nomService1", "", 0, 0, 0);
        assertEquals(listeResultatsRecherche.get(0).recupererService().getNomSservice(), "nomService1");
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

        ArrayList<Recherche> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 0, "nomService1", "", 0, 0, 0);
        assertTrue(listeResultatsRecherche.isEmpty());
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

        ArrayList<Recherche> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 0, "nomService1", "", 0, 0, 0);
        assertTrue(listeResultatsRecherche.isEmpty());
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

        ArrayList<Recherche> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 0, "", "villeC", 0, 0, 0);
        assertEquals(listeResultatsRecherche.get(0).recupererService().getVille(), "villeC");
        assertTrue(listeResultatsRecherche.size() == 1);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }
/*
    @Test
    public void rechercheDeServicesCoteUtilisateur() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur1,
                motDePasse, listeServices, listeCompetences);
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur2,
                motDePasse, listeServices, listeCompetences);
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur3,
                motDePasse, listeServices, listeCompetences);
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        orchestrateur.ajouterOffreDeService(nomUtilisateur1, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur1, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur1, service3);
        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur1, true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur1, service.getNomSservice(), true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur1, service2.getNomSservice(), true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur1, service3.getNomSservice(), true);

        orchestrateur.ajouterOffreDeService(nomUtilisateur2, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur2, service2);
        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur2, true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur2, service.getNomSservice(), true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur2, service2.getNomSservice(), true);

        orchestrateur.ajouterOffreDeService(nomUtilisateur3, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur3, service3);
        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur3, true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur3, service2.getNomSservice(), true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur3, service3.getNomSservice(), true);


        ArrayList<Recherche> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 0, "", "", 0, 90, 0);
        //assertTrue(listeResultatsRecherche.get(0).getUtilisateur().getEvaluationUtilisateur().coteUtilisateur == 90);
        assertTrue(listeResultatsRecherche.size() == 2);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesCoteServicesMoyenne() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur1,
                motDePasse, listeServices, listeCompetences, coteUtilisateur, evaluationUtilisateur1);
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur2,
                motDePasse, listeServices, listeCompetences, coteUtilisateur, evaluationUtilisateur2);
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur3,
                motDePasse, listeServices, listeCompetences, coteUtilisateur, evaluationUtilisateur3);
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description);
        orchestrateur.ajouterOffreDeService(nomUtilisateur1, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur1, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur1, service3);
        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur1, true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur1, service.getNomSservice(), true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur1, service2.getNomSservice(), true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur1, service3.getNomSservice(), true);

        orchestrateur.ajouterOffreDeService(nomUtilisateur2, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur2, service2);
        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur2, true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur2, service.getNomSservice(), true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur2, service2.getNomSservice(), true);

        orchestrateur.ajouterOffreDeService(nomUtilisateur3, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur3, service3);
        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur3, true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur3, service2.getNomSservice(), true);
        //orchestrateur.modifierDisponibiliteService(nomUtilisateur3, service3.getNomSservice(), true);


        ArrayList<Recherche> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 0, "", "", 0, 90, 0);
        //assertTrue(listeResultatsRecherche.get(0).getUtilisateur().getEvaluationUtilisateur().coteTypeServicesMoyenne == 90);
        assertTrue(listeResultatsRecherche.size() == 2);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void rechercheDeServicesCoteService() throws MyException, SQLException {
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, listeServices, listeCompetences);
        service = new TypeServices(3, 1, "nomService1", disponibleService, "villeA", "1234567890",
                adresseCourrielService, description, evaluationService1);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "villeB", "2234567890",
                adresseCourrielService, description, evaluationService1);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "villeC", "3234567890",
                adresseCourrielService, description, evaluationService1);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service2);
        orchestrateur.ajouterOffreDeService(nomUtilisateur, service3);

        orchestrateur.modifierDisponibiliteUsager(nomUtilisateur, true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service2.getNomSservice(), true);
        orchestrateur.modifierDisponibiliteService(nomUtilisateur, service3.getNomSservice(), true);

        ArrayList<Recherche> listeResultatsRecherche = orchestrateur.rechercheDeServices(0, 0, "", "", 0, 0, 90);
        //assertTrue(listeResultatsRecherche.get(0).recupererService().getEvaluationService() == 90);
        assertTrue(listeResultatsRecherche.size() == 2);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }
*/

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

        Recherche pair1 = new Recherche(utilisateur1, "nomService1");
        Recherche pair2 = new Recherche(utilisateur1, "nomService2");
        Recherche pair3 = new Recherche(utilisateur1, "nomService3");

        ArrayList<Recherche> listeResultatsRecherche = new ArrayList<>();
        listeResultatsRecherche.add(pair3);
        listeResultatsRecherche.add(pair1);
        listeResultatsRecherche.add(pair2);

        listeResultatsRecherche = orchestrateur.trierResultatRecherche(listeResultatsRecherche, "tauxHorraire");
        assertTrue(listeResultatsRecherche.get(0).recupererService().getTauxHorraire() == 1);
        assertTrue(listeResultatsRecherche.get(1).recupererService().getTauxHorraire() == 2);
        assertTrue(listeResultatsRecherche.get(2).recupererService().getTauxHorraire() == 3);
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

        Recherche pair1 = new Recherche(utilisateur1, "nomService1");
        Recherche pair2 = new Recherche(utilisateur1, "nomService2");
        Recherche pair3 = new Recherche(utilisateur1, "nomService3");

        ArrayList<Recherche> listeResultatsRecherche = new ArrayList<>();
        listeResultatsRecherche.add(pair3);
        listeResultatsRecherche.add(pair1);
        listeResultatsRecherche.add(pair2);

        listeResultatsRecherche = orchestrateur.trierResultatRecherche(listeResultatsRecherche, "prixFixe");
        assertTrue(listeResultatsRecherche.get(0).recupererService().getPrixFixe() == 1);
        assertTrue(listeResultatsRecherche.get(1).recupererService().getPrixFixe() == 2);
        assertTrue(listeResultatsRecherche.get(2).recupererService().getPrixFixe() == 3);
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

        Recherche pair1 = new Recherche(utilisateur1, "nomService1");
        Recherche pair2 = new Recherche(utilisateur1, "nomService2");
        Recherche pair3 = new Recherche(utilisateur1, "nomService3");

        ArrayList<Recherche> listeResultatsRecherche = new ArrayList<>();
        listeResultatsRecherche.add(pair3);
        listeResultatsRecherche.add(pair1);
        listeResultatsRecherche.add(pair2);

        listeResultatsRecherche = orchestrateur.trierResultatRecherche(listeResultatsRecherche, "nomService");
        assertEquals(listeResultatsRecherche.get(0).recupererService().getNomSservice(), "nomService1");
        assertEquals(listeResultatsRecherche.get(1).recupererService().getNomSservice(), "nomService2");
        assertEquals(listeResultatsRecherche.get(2).recupererService().getNomSservice(), "nomService3");
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

        Recherche pair1 = new Recherche(utilisateur1, "nomService1");
        Recherche pair2 = new Recherche(utilisateur1, "nomService2");
        Recherche pair3 = new Recherche(utilisateur1, "nomService3");

        ArrayList<Recherche> listeResultatsRecherche = new ArrayList<>();
        listeResultatsRecherche.add(pair3);
        listeResultatsRecherche.add(pair1);
        listeResultatsRecherche.add(pair2);

        listeResultatsRecherche = orchestrateur.trierResultatRecherche(listeResultatsRecherche, "ville");
        assertEquals(listeResultatsRecherche.get(0).recupererService().getVille(), "villeA");
        assertEquals(listeResultatsRecherche.get(1).recupererService().getVille(), "villeB");
        assertEquals(listeResultatsRecherche.get(2).recupererService().getVille(), "villeC");
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

        Recherche pair1 = new Recherche(utilisateur1, "nomService1");
        Recherche pair2 = new Recherche(utilisateur1, "nomService2");
        Recherche pair3 = new Recherche(utilisateur1, "nomService3");

        ArrayList<Recherche> listeResultatsRecherche = new ArrayList<>();
        listeResultatsRecherche.add(pair3);
        listeResultatsRecherche.add(pair1);
        listeResultatsRecherche.add(pair2);

        listeResultatsRecherche = orchestrateur.trierResultatRecherche(listeResultatsRecherche, "coteService");
        assertTrue(listeResultatsRecherche.get(0).recupererService().getEvaluationService().coteService == 50);
        assertTrue(listeResultatsRecherche.get(1).recupererService().getEvaluationService().coteService == 90);
        assertTrue(listeResultatsRecherche.get(2).recupererService().getEvaluationService().coteService == 100);
    }

    @Test
    public void trierResultatRechercheCoteUtilisateur() throws MyException {
        Recherche pair1 = new Recherche(utilisateur1, "nomService1");
        Recherche pair2 = new Recherche(utilisateur2, "nomService2");
        Recherche pair3 = new Recherche(utilisateur3, "nomService3");

        ArrayList<Recherche> listeResultatsRecherche = new ArrayList<>();
        listeResultatsRecherche.add(pair3);
        listeResultatsRecherche.add(pair1);
        listeResultatsRecherche.add(pair2);

        listeResultatsRecherche = orchestrateur.trierResultatRecherche(listeResultatsRecherche, "coteUtilisateur");
        assertTrue(listeResultatsRecherche.get(0).getUtilisateur().evaluationUtilisateur.coteUtilisateur == 50);
        assertTrue(listeResultatsRecherche.get(1).getUtilisateur().evaluationUtilisateur.coteUtilisateur == 90);
        assertTrue(listeResultatsRecherche.get(2).getUtilisateur().evaluationUtilisateur.coteUtilisateur == 100);
    }

    @Test
    public void trierResultatRechercheCoteServicesMoyenne() throws MyException {
        Recherche pair1 = new Recherche(utilisateur1, "nomService1");
        Recherche pair2 = new Recherche(utilisateur2, "nomService2");
        Recherche pair3 = new Recherche(utilisateur3, "nomService3");

        ArrayList<Recherche> listeResultatsRecherche = new ArrayList<>();
        listeResultatsRecherche.add(pair3);
        listeResultatsRecherche.add(pair1);
        listeResultatsRecherche.add(pair2);

        listeResultatsRecherche = orchestrateur.trierResultatRecherche(listeResultatsRecherche, "coteServiceMoyenne");
        assertTrue(listeResultatsRecherche.get(0).getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne == 50);
        assertTrue(listeResultatsRecherche.get(1).getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne == 90);
        assertTrue(listeResultatsRecherche.get(2).getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne == 100);
    }

    @Test
    public void trierResultatRechercheElse() throws MyException {
        Recherche pair1 = new Recherche(utilisateur1, "nomService1");
        Recherche pair2 = new Recherche(utilisateur2, "nomService2");

        ArrayList<Recherche> listeResultatsRecherche = new ArrayList<>();
        listeResultatsRecherche.add(pair1);
        listeResultatsRecherche.add(pair2);

        try {
            orchestrateur.trierResultatRecherche(listeResultatsRecherche, "bidon");
        } catch (Exception e) {
            estValider = !e.getMessage().equals(MESSAGE_MODE_TRI_INTROUVABLE);
        }
        assertFalse(estValider);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}
