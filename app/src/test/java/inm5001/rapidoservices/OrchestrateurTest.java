package inm5001.rapidoservices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.utilisateur.Utilisateur;

import static inm5001.rapidoservices.ConstanteOrchetrateur.MESSAGE_MODE_TRI_INTROUVABLE;
import static inm5001.rapidoservices.ConstanteOrchetrateur.MESSAGE_MOT_DE_PASSE_INVALIDE;
import static inm5001.rapidoservices.ConstanteOrchetrateur.MESSAGE_UTILISATEUR_N_EXISTE_PAS;
import static inm5001.rapidoservices.ConstanteOrchetrateur.MESSAGE_NOMUTILISATEUR_PAS_UNIQUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrchestrateurTest {
    private Orchestrateur orchestrateur;
    private Utilisateur utilisateur;
    //attributs Utilisateur
    private Identifiant identifiant;
    private Profile profile;
    private ArrayList<AbstraiteServices> listeServices;
    private TypeServices service;
    private TypeServices service2;
    private TypeServices service3;
    private ArrayList<String> listeCompetences;
    private String competence;
    private boolean disponibleUtilisateur;
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

    @Before
    public void setUp() throws MyException {
        orchestrateur = new Orchestrateur();
        listeServices = new ArrayList<>();
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
        estValider = true;
        nomSservice = "Plombier";
        disponibleUtilisateur = false;
        disponibleService = false;
        ville = "Montreal";
        cote = 2;
        numeroTelephoneService ="514-444-4444";
        adresseCourrielService = "plomberie@plomberi.com";
        description = "Repare les tuyeaux";
        tauxHorraire = 14.50f;
        prixFixe = 50.00f;
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, cote,
                numeroTelephoneService, adresseCourrielService, description);
        service2 = new TypeServices(tauxHorraire, prixFixe, "Electricien", disponibleService, ville, cote,
                numeroTelephoneService, adresseCourrielService, description);
    }

    @After
    public void tearDown() throws MyException {
        orchestrateur = null;
        identifiant = null;
        profile = null;
        listeServices = null;
        listeCompetences = null;
        competence = null;
        nom = null;
        prenom = null;
        numeroTelephoneProfile = null;
        adresseCourrielProfile = null;
        utilisateur = null;
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
    public void validationLogin() throws MyException{
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
// et pas quand on vérifie si l'utilisateur existe. le résultat est le même, mais pourrait nous jouer des tour éventuellement
    @Test
    public void validationLoginUtilisateurExistePas() throws MyException{
        try {
            utilisateur = orchestrateur.validationLogin("bidon", motDePasse);
        } catch (Exception e) {
            estValider = !e.getMessage().equals(MESSAGE_UTILISATEUR_N_EXISTE_PAS);
        }
        assertFalse(estValider);
    }

    @Test
    public void validationLoginMotDePasseInvalide() throws MyException{
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
    public void supprimerCompte() throws MyException{
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
        orchestrateur.retirerOffreDeService(nomUtilisateur, service);
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
        assertEquals(orchestrateur.recupererUtilisateur(nomUtilisateur).listeServices.get(1).getNomSservice(), "Plombier");
        assertEquals(orchestrateur.recupererUtilisateur(nomUtilisateur).listeServices.get(0).getNomSservice(), "Electricien");
        orchestrateur.retirerOffreDeService(nomUtilisateur, service);
        orchestrateur.retirerOffreDeService(nomUtilisateur, service2);
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
            estValider = true;
        }
        assertTrue(estValider);
        orchestrateur.retirerOffreDeService(nomUtilisateur, service);
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
        assertEquals(orchestrateur.recupererUtilisateur(nomUtilisateur).listeCompetences.get(1), "Plombier");
        assertEquals(orchestrateur.recupererUtilisateur(nomUtilisateur).listeCompetences.get(0), "Electricien");
        orchestrateur.retirerOffreDeService(nomUtilisateur, service);
        orchestrateur.retirerOffreDeService(nomUtilisateur, service2);
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
        assertTrue(utilisateur.listeServices.get(0).isDisponible());

        orchestrateur.modifierDisponibiliteService(utilisateur.identifiant.nomUtilisateur, utilisateur.listeServices.get(0).getNomSservice(), false);
        utilisateur = orchestrateur.recupererUtilisateur(nomUtilisateur);
        assertFalse(utilisateur.listeServices.get(0).isDisponible());

        orchestrateur.retirerOffreDeService(nomUtilisateur, service);
        orchestrateur.supprimerCompte(nomUtilisateur);
    }

    @Test
    public void trierResultatRechercheTauxHorraire() throws MyException {
        service = new TypeServices(3, 1, "nomService1", disponibleService, "ville1", cote,
                "noTelephone1", adresseCourrielService, description);
        service2 = new TypeServices(1, 2, "nomService2", disponibleService, "ville2", cote,
                "noTelephone2", adresseCourrielService, description);
        service3 = new TypeServices(2, 3, "nomService3", disponibleService, "ville3", cote,
                "noTelephone3", adresseCourrielService, description);
        ArrayList<PaireNomUtilisateurEtTypeService> listePaire = new ArrayList<>();
        PaireNomUtilisateurEtTypeService pair1 = new PaireNomUtilisateurEtTypeService("nomUtilisateur1" , service);
        PaireNomUtilisateurEtTypeService pair2 = new PaireNomUtilisateurEtTypeService("nomUtilisateur2" , service2);
        PaireNomUtilisateurEtTypeService pair3 = new PaireNomUtilisateurEtTypeService("nomUtilisateur3" , service3);
        listePaire.add(pair1);
        listePaire.add(pair2);
        listePaire.add(pair3);

        listePaire = orchestrateur.trierResultatRecherche(listePaire, "tauxHorraire");
        assertTrue(listePaire.get(0).getService().getTauxHorraire() == 1);
        assertTrue(listePaire.get(1).getService().getTauxHorraire() == 2);
        assertTrue(listePaire.get(2).getService().getTauxHorraire() == 3);
    }

    @Test
    public void trierResultatRecherchePrixFixe() throws MyException {
        service = new TypeServices(1, 3, "nomService1", disponibleService, "ville1", cote,
                "noTelephone1", adresseCourrielService, description);
        service2 = new TypeServices(2, 1, "nomService2", disponibleService, "ville2", cote,
                "noTelephone2", adresseCourrielService, description);
        service3 = new TypeServices(3, 2, "nomService3", disponibleService, "ville3", cote,
                "noTelephone3", adresseCourrielService, description);
        ArrayList<PaireNomUtilisateurEtTypeService> listePaire = new ArrayList<>();
        PaireNomUtilisateurEtTypeService pair1 = new PaireNomUtilisateurEtTypeService("nomUtilisateur1" , service);
        PaireNomUtilisateurEtTypeService pair2 = new PaireNomUtilisateurEtTypeService("nomUtilisateur2" , service2);
        PaireNomUtilisateurEtTypeService pair3 = new PaireNomUtilisateurEtTypeService("nomUtilisateur3" , service3);
        listePaire.add(pair1);
        listePaire.add(pair2);
        listePaire.add(pair3);

        listePaire = orchestrateur.trierResultatRecherche(listePaire, "prixFixe");
        assertTrue(listePaire.get(0).getService().getPrixFixe() == 1);
        assertTrue(listePaire.get(1).getService().getPrixFixe() == 2);
        assertTrue(listePaire.get(2).getService().getPrixFixe() == 3);
    }

    @Test
    public void trierResultatRechercheNomService() throws MyException {
        service = new TypeServices(1, 1, "nomService3", disponibleService, "ville1", cote,
                "noTelephone1", adresseCourrielService, description);
        service2 = new TypeServices(2, 2, "nomService1", disponibleService, "ville2", cote,
                "noTelephone2", adresseCourrielService, description);
        service3 = new TypeServices(3, 3, "nomService2", disponibleService, "ville3", cote,
                "noTelephone3", adresseCourrielService, description);
        ArrayList<PaireNomUtilisateurEtTypeService> listePaire = new ArrayList<>();
        PaireNomUtilisateurEtTypeService pair1 = new PaireNomUtilisateurEtTypeService("nomUtilisateur1" , service);
        PaireNomUtilisateurEtTypeService pair2 = new PaireNomUtilisateurEtTypeService("nomUtilisateur2" , service2);
        PaireNomUtilisateurEtTypeService pair3 = new PaireNomUtilisateurEtTypeService("nomUtilisateur3" , service3);
        listePaire.add(pair1);
        listePaire.add(pair2);
        listePaire.add(pair3);

        listePaire = orchestrateur.trierResultatRecherche(listePaire, "nomService");
        assertTrue(listePaire.get(0).getService().getNomSservice() == "nomService1");
        assertTrue(listePaire.get(1).getService().getNomSservice() == "nomService2");
        assertTrue(listePaire.get(2).getService().getNomSservice() == "nomService3");
    }

    @Test
    public void trierResultatRechercheVille() throws MyException {
        service = new TypeServices(1, 1, "nomService1", disponibleService, "ville3", cote,
                "noTelephone1", adresseCourrielService, description);
        service2 = new TypeServices(2, 2, "nomService2", disponibleService, "ville1", cote,
                "noTelephone2", adresseCourrielService, description);
        service3 = new TypeServices(3, 3, "nomService3", disponibleService, "ville2", cote,
                "noTelephone3", adresseCourrielService, description);
        ArrayList<PaireNomUtilisateurEtTypeService> listePaire = new ArrayList<>();
        PaireNomUtilisateurEtTypeService pair1 = new PaireNomUtilisateurEtTypeService("nomUtilisateur1" , service);
        PaireNomUtilisateurEtTypeService pair2 = new PaireNomUtilisateurEtTypeService("nomUtilisateur2" , service2);
        PaireNomUtilisateurEtTypeService pair3 = new PaireNomUtilisateurEtTypeService("nomUtilisateur3" , service3);
        listePaire.add(pair1);
        listePaire.add(pair2);
        listePaire.add(pair3);

        listePaire = orchestrateur.trierResultatRecherche(listePaire, "ville");
        assertTrue(listePaire.get(0).getService().getVille() == "ville1");
        assertTrue(listePaire.get(1).getService().getVille() == "ville2");
        assertTrue(listePaire.get(2).getService().getVille() == "ville3");
    }

    @Test
    public void trierResultatRechercheNoTelephone() throws MyException {
        service = new TypeServices(1, 1, "nomService1", disponibleService, "ville1", cote,
                "noTelephone3", adresseCourrielService, description);
        service2 = new TypeServices(2, 2, "nomService2", disponibleService, "ville2", cote,
                "noTelephone1", adresseCourrielService, description);
        service3 = new TypeServices(3, 3, "nomService3", disponibleService, "ville3", cote,
                "noTelephone2", adresseCourrielService, description);
        ArrayList<PaireNomUtilisateurEtTypeService> listePaire = new ArrayList<>();
        PaireNomUtilisateurEtTypeService pair1 = new PaireNomUtilisateurEtTypeService("nomUtilisateur1" , service);
        PaireNomUtilisateurEtTypeService pair2 = new PaireNomUtilisateurEtTypeService("nomUtilisateur2" , service2);
        PaireNomUtilisateurEtTypeService pair3 = new PaireNomUtilisateurEtTypeService("nomUtilisateur3" , service3);
        listePaire.add(pair1);
        listePaire.add(pair2);
        listePaire.add(pair3);

        listePaire = orchestrateur.trierResultatRecherche(listePaire, "noTelephone");
        assertTrue(listePaire.get(0).getService().getNoTelephone() == "noTelephone1");
        assertTrue(listePaire.get(1).getService().getNoTelephone() == "noTelephone2");
        assertTrue(listePaire.get(2).getService().getNoTelephone() == "noTelephone3");
    }

    @Test
    public void trierResultatRechercheElse() throws MyException {
        ArrayList<PaireNomUtilisateurEtTypeService> listePaire = new ArrayList<>();
        PaireNomUtilisateurEtTypeService pair1 = new PaireNomUtilisateurEtTypeService("nomUtilisateur1" , service);
        listePaire.add(pair1);

        try {
            orchestrateur.trierResultatRecherche(listePaire, "bidon");
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