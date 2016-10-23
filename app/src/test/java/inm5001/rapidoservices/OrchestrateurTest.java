package inm5001.rapidoservices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.sql.SQLException;
import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.utilisateur.Utilisateur;

import static inm5001.rapidoservices.utilisateur.ConstanteUtilisateur.MESSAGE_PROFILE_NULL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class OrchestrateurTest {
    Orchestrateur orchestrateur;
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
        orchestrateur = new Orchestrateur();
        listeServices = new ArrayList<>();
        listeServices.add(new TypeServices(tauxHorraire, prixFixe, nomUtilisateur, disponible, ville, cote,
                numeroTelephoneService, "service1@gmail.com", description));
        listeServices.add(new TypeServices(tauxHorraire, prixFixe, nomUtilisateur, disponible, ville, cote,
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
        estValider = true;
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
        orchestrateur = null;
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
    public void creerUtilisateur() {
        try {
            orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                                            motDePasse, identifiant, profile, listeServices, listeCompetences);
            } catch (Exception e) {
                //System.out.println("OMER :" + e.getClass().getSimpleName());
                estValider = false;
            }
        assertTrue(estValider);
    }

    @Test
    public void recupererUtilisateur() throws MyException{
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                                        motDePasse, identifiant, profile, listeServices, listeCompetences);
        try {
            utilisateur = orchestrateur.recupererUtilisateur(nomUtilisateur);
        } catch (Exception e) {
            //System.out.println("OMER :" + e.getClass().getSimpleName());
            estValider = false;
        }
        assertTrue(estValider);
        assertEquals(utilisateur.identifiant.nomUtilisateur, "Francis");
    }

    @Test
    public void supprimerCompte() throws MyException{
        orchestrateur.creerUtilisateur(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile, nomUtilisateur,
                motDePasse, identifiant, profile, listeServices, listeCompetences);
        try {
            orchestrateur.supprimerCompte(nomUtilisateur);
        } catch (Exception e) {
            //System.out.println("OMER :" + e.getClass().getSimpleName());
            estValider = false;
        }
        assertTrue(estValider);
        assertNull(orchestrateur.recupererUtilisateur(nomUtilisateur));
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}