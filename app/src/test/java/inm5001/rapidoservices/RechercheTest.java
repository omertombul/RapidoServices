package inm5001.rapidoservices;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import inm5001.rapidoservices.recherche.RechercheServices;
import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.EvaluationUtilisateur;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.utilisateur.Utilisateur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Francis Bernier on 2016-11-13.
 */

public class RechercheTest {

    //attributs Utilisateur
    private Identifiant identifiant;
    private Profile profile;
    private ArrayList<TypeServices> listeServices;
    private ArrayList<String> listeCompetences;
    private String competence;
    private String disponibleUtilisateur;
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
    //attribut TypeServices
    private float tauxHorraire;
    private float prixFixe;
    private TypeServices service;
    private TypeServices service2;
    private Utilisateur utilisateur;
    //attributs evaluationUtilisateur
    public float coteUtilisateur;
    public int nombreDEvaluationUtilisateur;
    public float coteTypeServicesMoyenne;
    public int nombreDEvaluationTypeServicesMoyenne;
    private EvaluationUtilisateur evaluationUtilisateur;

    @Before
    public void setUp() throws MyException {
        listeServices = new ArrayList<>();
        listeCompetences = new ArrayList<>();
        competence = null;
        nomUtilisateur = "Francis";
        motDePasse = "Allo!234";
        nom = "Francis";
        prenom = "Bernier";
        numeroTelephoneProfile = "5145972143";
        adresseCourrielProfile = "francis@hotmail.com";
        profile = new Profile(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile);
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        //attributs EvaluationUtilisateur
        coteUtilisateur = 3.5f;
        nombreDEvaluationUtilisateur = 210;
        coteTypeServicesMoyenne = 4.5f;
        nombreDEvaluationTypeServicesMoyenne = 1000;
        evaluationUtilisateur = new EvaluationUtilisateur(coteUtilisateur, nombreDEvaluationUtilisateur, coteTypeServicesMoyenne,
                                                            nombreDEvaluationTypeServicesMoyenne);
        //attributs service
        nomSservice = "Plombier";
        disponibleService = false;
        ville = "Montreal";
        cote = 2;
        numeroTelephoneService ="5144444444";
        adresseCourrielService = "plomberie@plomberi.com";
        description = "Repare les tuyeaux";
        tauxHorraire = 14.50f;
        prixFixe = 50.00f;
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponibleService, ville, numeroTelephoneService,
                adresseCourrielService, description);
        service2 = new TypeServices(tauxHorraire, prixFixe, "Electricien", disponibleService, ville, numeroTelephoneService,
                adresseCourrielService, description);
        listeServices.add(service);
        listeServices.add(service2);
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences, evaluationUtilisateur);
    }

    @After
    public void tearDown() throws MyException {
        listeServices = null;
        listeCompetences = null;
        competence = null;
        nomUtilisateur = null;
        motDePasse = null;
        nom = null;
        prenom = null;
        numeroTelephoneProfile = null;
        adresseCourrielProfile = null;
        profile = null;
        identifiant = null;
        //attributs EvaluationUtilisateur
        coteUtilisateur = 0f;
        nombreDEvaluationUtilisateur = 0;
        coteTypeServicesMoyenne = 0f;
        nombreDEvaluationTypeServicesMoyenne = 0;
        evaluationUtilisateur = null;
        utilisateur = null;
        //attributs service
        nomSservice = null;
        disponibleService = false;
        ville = null;
        numeroTelephoneService = null;
        adresseCourrielService = null;
        description = null;
        tauxHorraire = 0f;
        prixFixe = 0f;
        service = null;
    }

    @Test
    public void RecherchePasNull() throws MyException {
        RechercheServices pair1 = new RechercheServices(utilisateur , nomSservice);

        assertNotNull(pair1);
    }

    @Test
    public void RechercheNomUtilisateurPasNull() throws MyException {
        RechercheServices pair1 = new RechercheServices(utilisateur , nomSservice);

        assertNotNull(pair1.getUtilisateur());
    }

    @Test
    public void RechercheUtilisateurNomUtilisateur() throws MyException {
        RechercheServices pair1 = new RechercheServices(utilisateur , nomSservice);

        assertEquals(pair1.getUtilisateur().identifiant.nomUtilisateur, "FRANCIS");
    }

    @Test
    public void RechercheServicePasNull() throws MyException {
        RechercheServices pair1 = new RechercheServices(utilisateur , nomSservice);

        assertNotNull(pair1);
    }

    @Test
    public void RechercheServiceValiderParametres() throws MyException {
        RechercheServices pair1 = new RechercheServices(utilisateur , "Plombier");
        assertTrue(pair1.recupererService().getTauxHorraire() == tauxHorraire);
        assertEquals(pair1.recupererService().getNomSservice(), "Plombier");
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}
