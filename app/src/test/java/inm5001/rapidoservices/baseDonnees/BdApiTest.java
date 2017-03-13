package inm5001.rapidoservices.baseDonnees;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.utilisateur.Utilisateur;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Admin on 2016-10-22.
 */

public class BdApiTest {
    private BdApi bd;
    private Utilisateur utilisateur;
    //attributs Utilisateur
    private Identifiant identifiant;
    private Profile profile;
    private ArrayList<TypeServices> listeServices;
    private ArrayList<String> listeCompetences;
    //attributs Identifiant
    private String nomUtilisateur;
    private String motDePasse;
    //attributs Profile
    private String nom;
    private String prenom;
    private String numeroTelephoneProfile;
    private String adresseCourrielProfile;
    //attributs AbstraiteServices
    private boolean disponibleService;
    private String ville;
    private String numeroTelephoneService;
    private String description;
    //attribut Plomberie
    private float tauxHorraire;
    private float prixFixe;

    @Before
    public void setUp() throws MyException {
        bd = new BdApi();
        listeServices = new ArrayList<>();
        listeCompetences = new ArrayList<>();
        nomUtilisateur = "iduser1";
        motDePasse = "Allo!234";
        nom = "Francis";
        prenom = "Bernier";
        numeroTelephoneProfile = "5145972143";
        adresseCourrielProfile = "francis@hotmail.com";
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        profile = new Profile(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile);
        utilisateur = null;
        disponibleService = false;
        ville = "Montreal";
        numeroTelephoneService ="5144444444";
        description = "Repare les tuyeaux";
        tauxHorraire = 14.50f;
        prixFixe = 50.00f;
        listeServices.add(new TypeServices(tauxHorraire, prixFixe, nomUtilisateur, disponibleService, ville, numeroTelephoneService,
                "service1@gmail.com", description));
        listeServices.add(new TypeServices(tauxHorraire, prixFixe, nomUtilisateur, disponibleService, ville, numeroTelephoneService,
                "service2@gmail.com", description));
        listeCompetences.add("Plombier");
        listeCompetences.add("Electricien");
    }

    @After
    public void tearDown() {
        bd = null;
        identifiant = null;
        profile = null;
        listeServices = null;
        listeCompetences = null;
        nom = null;
        prenom = null;
        numeroTelephoneProfile = null;
        adresseCourrielProfile = null;
        utilisateur = null;
        motDePasse = null;
        disponibleService = false;
        ville = null;
        numeroTelephoneService = null;
        description = null;
        tauxHorraire = 0;
        prixFixe = 0;
    }

    @Test
    public void addUser() throws Exception {
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        assertNotNull(utilisateur);
        bd.addUser(utilisateur);
        bd.deleteUser(nomUtilisateur);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}
