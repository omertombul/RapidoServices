package inm5001.rapidoservices;

import java.util.ArrayList;

import inm5001.rapidoservices.service.Plomberie;
import inm5001.rapidoservices.utilisateur.Utilisateur;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.service.AbstraiteServices;

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
/*
    public static Utilisateur creerUtilisateur(String nom, String prenom, String numeroTelephoneProfile, String adresseCourrielProfile,
                                                 String nomUtilisateur, String motDePasse, Identifiant identifiant, Profile profile,
                                                 ArrayList<AbstraiteServices> listeServices, ArrayList<String> listeCompetences) throws MyException {
        profile = new Profile(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile);
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        enregistrerNouvelUtilisateur(new Utilisateur(identifiant, profile, listeServices, listeCompetences));
        return null;
    }

    public void supprimerCompte(String nomUtilisateur) throws MyException {
        supprimerUtilisateur(nomUtilisateur);
    }

    public void modifierMotDePasse(String nomUtilisateur, String motDePasse) throws MyException {
        utilisateur = recupererUtilisateur(nomUtilisateur);
        utilisateur.identifiant.validationMotDePasse(motDePasse);
        remplacerMotDePasse(nomUtilisateur, motDePasseNouveau);
    }

    public void ajouterOffreDeService(String nomUtilisateur, Plomberie service) throws MyException {
        ajouterServiceUtilisateur(nomUtilisateur, service);
        ajouterCompetenceUtilisateur(nomUtilisateur, service.getNomSservice());
    }

    public void retirerOffreDeService(String nomUtilisateur, Plomberie service) throws MyException {
        retirerServiceUtilisateur(nomUtilisateur, service);
        retirerCompetenceUtilisateur(nomUtilisateur, service.getNomSservice());
    }

    public void remplacerNomProfile(String nomUtilisateur, String nom) throws MyException throws MyException {
        profile = recupererUtilisateur(nomUtilisateur).profile;
        profile = new Profile (nom, profile.prenom, profile.numeroTelephone, profile.adresseCourriel);
        remplacerProfile(nomUtilisateur, profile);
    }

    public void remplacerPrenomProfile(String nomUtilisateur, String prenom) throws MyException throws MyException {
        profile = recupererUtilisateur(nomUtilisateur).profile;
        profile = new Profile (profile.nom, prenom, profile.numeroTelephone, profile.adresseCourriel);
        remplacerProfile(nomUtilisateur, profile);
    }

    public void remplacerNumeroTelephoneProfile(String nomUtilisateur, String numeroTelephone) throws MyException {
        profile = recupererUtilisateur(nomUtilisateur).profile;
        profile = new Profile (profile.nom, profile.prenom, numeroTelephone, profile.adresseCourriel);
        remplacerProfile(nomUtilisateur, profile);
    }

    public void remplacerNomProfile(String nomUtilisateur, String adresseCourriel) throws MyException {
        profile = recupererUtilisateur(nomUtilisateur).profile;
        profile = new Profile (profile.nom, profile.prenom, profile.numeroTelephone, adresseCourriel);
        remplacerProfile(nomUtilisateur, profile);
    }

    public void changerMotDePasse(String nomUtilisateur, String motDePasse) throws MyException {
        identifiant = recupererUtilisateur(nomUtilisateur).identifiant;
        remplacerProfile(identifiant.nomUtilisateur, motDePasse);
    }
    */
}
