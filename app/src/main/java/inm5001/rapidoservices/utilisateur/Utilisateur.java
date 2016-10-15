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
    //public ArrayList<Evaluation> listeEvaluations;
    //public ArrayList<Evaluation> lisetEvaluationServicesGlobal;
    //public Geolocalisation geolocalisation;

    /*
    public Utilisateur(int disponibilite, Identifiant identifiant, Profile profile, ArrayList<Service> listeServices, ArrayList<String> listeCompetences, ArrayList<Evaluation> listeEvaluations, ArrayList<Evaluation> lisetEvaluationServicesGlobal, Geolocalisation geolocalisation) {
        this.disponibilite = disponibilite;
        this.identifiant = identifiant;
        this.profile = profile;
        this.listeServices = listeServices;
        this.listeCompetences = listeCompetences;
        this.listeEvaluations = listeEvaluations;
        this.lisetEvaluationServicesGlobal = lisetEvaluationServicesGlobal;
        this.geolocalisation = geolocalisation;
    }
    */

    public Utilisateur(Identifiant identifiant, Profile profile, ArrayList<Service> listeServices, ArrayList<String> listeCompetences) {
        this.identifiant = identifiant;
        this.profile = profile;
        this.listeServices = listeServices;
        this.listeCompetences = listeCompetences;
    }
}
