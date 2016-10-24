package inm5001.rapidoservices;

import java.util.ArrayList;

import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.Utilisateur;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.BaseDonnees.BdApi;

/**
 * Created by Francis Bernier on 2016-10-21.
 */

public class Orchestrateur {

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
    String nomSservice;
    boolean disponible;
    String ville;
    byte cote;
    String numeroTelephoneService;
    String adresseCourrielService;
    String description;
    float tauxHorraire;
    float prixFixe;
//attributs BdApi
    private static BdApi bd = new BdApi();

    public void creerUtilisateur(String nom, String prenom, String numeroTelephoneProfile, String adresseCourrielProfile,
                                                 String nomUtilisateur, String motDePasse, Identifiant identifiant, Profile profile,
                                                 ArrayList<AbstraiteServices> listeServices, ArrayList<String> listeCompetences) throws MyException {
        profile = new Profile(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile);
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        bd.addUser(utilisateur);
    }

    public Utilisateur recupererUtilisateur(String nomUtilisateur) throws MyException {
        return bd.getUser(nomUtilisateur);
    }

    public void supprimerCompte(String nomUtilisateur) throws MyException {
        bd.deleteUser(nomUtilisateur);
    }

    public void ajouterOffreDeService(String nomUtilisateur, TypeServices service) throws MyException {
        bd.addServiceUser(nomUtilisateur, service);
    }

    public void retirerOffreDeService(String nomUtilisateur, AbstraiteServices service) throws MyException {
        bd.deleteService(nomUtilisateur, service.getNomSservice());
    }
    /*
    public void modifierMotDePasse(String nomUtilisateur, String motDePasse) throws MyException {
        utilisateur = bd.getUser(nomUtilisateur);
        utilisateur.identifiant.validationMotDePasse(motDePasse);
        bd.setPassword(nomUtilisateur, motDePasse);
    }

    public void remplacerNomProfile(String nomUtilisateur, String nom) throws MyException throws MyException {
        profile = bd.getUser(nomUtilisateur).profile;
        profile = new Profile (nom, profile.prenom, profile.numeroTelephone, profile.adresseCourriel);
        bd.replaceProfile(nomUtilisateur, profile);
    }

    public void remplacerPrenomProfile(String nomUtilisateur, String prenom) throws MyException throws MyException {
        profile = bd.getUser(nomUtilisateur).profile;
        profile = new Profile (profile.nom, prenom, profile.numeroTelephone, profile.adresseCourriel);
        bd.replaceProfile(nomUtilisateur, profile);
    }

    public void remplacerNumeroTelephoneProfile(String nomUtilisateur, String numeroTelephone) throws MyException {
        profile = bd.getUser(nomUtilisateur).profile;
        profile = new Profile (profile.nom, profile.prenom, numeroTelephone, profile.adresseCourriel);
        bd.replaceProfile(nomUtilisateur, profile);
    }

    public void remplacerAdresseCourrielProfile(String nomUtilisateur, String adresseCourriel) throws MyException {
        profile = bd.getUser(nomUtilisateur).profile;
        profile = new Profile (profile.nom, profile.prenom, profile.numeroTelephone, adresseCourriel);
        replaceProfile(nomUtilisateur, profile);
    }
    */
}
