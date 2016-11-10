package inm5001.rapidoservices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import inm5001.rapidoservices.baseDonnees.BdApi;
import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.Utilisateur;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.service.AbstraiteServices;

import static inm5001.rapidoservices.ConstanteOrchetrateur.*;

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
    private String disponibleUtilisateur;
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
    private String disponibleService;
    private String ville;
    private byte cote;
    private String numeroTelephoneService;
    private String adresseCourrielService;
    private String description;
    private float tauxHorraire;
    private float prixFixe;
//attributs BdApi
    private static BdApi bd = new BdApi();

    public void creerUtilisateur(Utilisateur utilisateur) throws MyException {
        try {
            bd.addUser(utilisateur);
        } catch (Exception ex) {
            MyException e = new MyException(MESSAGE_NOMUTILISATEUR_PAS_UNIQUE);
            throw e;
        }
    }

    public void creerUtilisateur(String nom, String prenom, String numeroTelephoneProfile, String adresseCourrielProfile,
                                                 String nomUtilisateur, String motDePasse, ArrayList<AbstraiteServices> listeServices, ArrayList<String> listeCompetences) throws MyException {
        profile = new Profile(nom, prenom, numeroTelephoneProfile, adresseCourrielProfile);
        identifiant = new Identifiant(nomUtilisateur, motDePasse);
        utilisateur = new Utilisateur(identifiant, profile, listeServices, listeCompetences);
        try {
            bd.addUser(utilisateur);
        } catch (Exception eBd) {
            MyException e = new MyException(MESSAGE_NOMUTILISATEUR_PAS_UNIQUE);
            throw e;
        }
    }

    public Utilisateur recupererUtilisateur(String nomUtilisateur) throws MyException {
        return bd.getUser(nomUtilisateur);
    }

    public Utilisateur validationLogin(String nomUtilisateur, String motDePasse) throws MyException {
        try {
            utilisateur =  bd.getUser(nomUtilisateur);
        } catch (Exception eBd) {
            MyException e = new MyException(MESSAGE_UTILISATEUR_N_EXISTE_PAS);
            throw e;
        }

        if (!utilisateur.identifiant.motDePasse.equals(motDePasse)) {
            MyException e = new MyException(MESSAGE_MOT_DE_PASSE_INVALIDE);
            throw e;
        }
        return utilisateur;
    }

    public void supprimerCompte(String nomUtilisateur) throws MyException {
        bd.deleteUser(nomUtilisateur);
    }

    public void ajouterOffreDeService(String nomUtilisateur, TypeServices service) throws SQLException {
        bd.addServiceUser(nomUtilisateur, service);
        bd.addCompetenceUser(nomUtilisateur, service);
    }

    public void retirerOffreDeService(String nomUtilisateur, TypeServices service) throws MyException {
        bd.deleteService(nomUtilisateur, service.getNomSservice());
        bd.deleteCompetence(nomUtilisateur, service);
    }

    public void modifierDisponibiliteUsager(String nomUtilisateur, boolean disponible) throws MyException, SQLException {
        if (disponible) {
            bd.updateUserDisponibilite(nomUtilisateur, "1");
        } else {
            bd.updateUserDisponibilite(nomUtilisateur, "0");
        }
    }

    public void modifierDisponibiliteService(String nomUtilisateur, String nomService, boolean disponible) throws MyException, SQLException {
        if (disponible) {
            bd.updateServiceDisponibilite(nomUtilisateur, nomService, "1");
        } else {
            bd.updateServiceDisponibilite(nomUtilisateur, nomService, "0");
        }
    }
/*
    public ArrayList<PaireNomUtilisateurEtTypeService> rechercheDeServices(float tauxHorraire, float prixFixe, String nomSservice,
                                                            boolean disponible, String ville, byte cote, String noTelephone,
                                                            String courriel, String description) throws MyException, SQLException {
        TypeServices service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, cote, noTelephone,
                                                        courriel, description);
        ArrayList<PaireNomUtilisateurEtTypeService> listePaires = bd.searchForServices(service);
        return listePaires;
    }
*/
    public ArrayList<PaireNomUtilisateurEtTypeService> trierResultatRecherche(ArrayList<PaireNomUtilisateurEtTypeService> listeServices, String trierPar) throws MyException {
        if (trierPar == "tauxHorraire") {
            Collections.sort(listeServices, new TypeServices.TrierParTauxHorraire());
        } else if (trierPar == "prixFixe") {
            Collections.sort(listeServices, new TypeServices.TrierParPrixFixe());
        } else if (trierPar == "nomService") {
            Collections.sort(listeServices, new TypeServices.TrierParNomService());
        } else if (trierPar == "ville") {
            Collections.sort(listeServices, new TypeServices.TrierParVille());
        } else if (trierPar == "noTelephone") {
            Collections.sort(listeServices, new TypeServices.TrierParNoTelephone());
        } else {
            MyException e = new MyException(MESSAGE_MODE_TRI_INTROUVABLE);
            throw e;
        }

        return listeServices;
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
