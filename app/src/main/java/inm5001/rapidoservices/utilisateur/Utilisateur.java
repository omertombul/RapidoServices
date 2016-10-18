package inm5001.rapidoservices.utilisateur;

import java.util.ArrayList;

import inm5001.rapidoservices.service.Service;

/**
 * Created by Francis Bernier on 2016-10-10.
 */

public class Utilisateur {
    public Identifiant identifiant;
    public Profile profile;
    public ArrayList<Service> listeServices;
    public ArrayList<String> listeCompetences;
    //public boolean disponible;
    //public ArrayList<Evaluation> listeEvaluations;
    //public ArrayList<Evaluation> lisetEvaluationServicesGlobal;
    //public Geolocalisation geolocalisation;

    /*
    public Utilisateur(Identifiant identifiant, Profile profile, ArrayList<Service> listeServices, ArrayList<String> listeCompetences, boolean disponible, ArrayList<Evaluation> listeEvaluations, ArrayList<Evaluation> lisetEvaluationServicesGlobal, Geolocalisation geolocalisation) {
        this.identifiant = identifiant;
        this.profile = profile;
        this.listeServices = listeServices;
        this.listeCompetences = listeCompetences;
        this.disponible = disponible;
        this.listeEvaluations = listeEvaluations;
        this.lisetEvaluationsServicesGlobal = lisetEvaluationServicesGlobal;
        this.geolocalisation = geolocalisation;
    }
    */

    public Utilisateur(Identifiant identifiant, Profile profile, ArrayList<Service> listeServices, ArrayList<String> listeCompetences) {
        TraiterIdentifiant(identifiant);
        TraiterProfile(profile);
        TraiterListeServices(listeServices);
        TraiterListeCompetences(listeCompetences);
    }
//premier niveau d'abstraction
    private void TraiterIdentifiant(Identifiant identifiant) {
        this.identifiant = identifiant;
    }

    private void TraiterProfile(Profile profile) {
        this.profile = profile;
    }

    private void TraiterListeServices(ArrayList<Service> listeServices) {
        this.listeServices = listeServices;
    }

    private void TraiterListeCompetences(ArrayList<String> listeCompetences) {
        this.listeCompetences = listeCompetences;
    }
//deuxième niveau d'abstraction
}
