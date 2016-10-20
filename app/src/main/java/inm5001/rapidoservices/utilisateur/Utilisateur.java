package inm5001.rapidoservices.utilisateur;

import java.util.ArrayList;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.service.Service;

import static inm5001.rapidoservices.utilisateur.ConstanteUtilisateur.*;

/**
 * Created by Francis Bernier on 2016-10-10.
 */

public class Utilisateur {
    public Identifiant identifiant;
    public Profile profile;
    public ArrayList<Service> listeServices = new ArrayList<Service>();
    public ArrayList<String> listeCompetences = new ArrayList<String>();
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

    public Utilisateur(Identifiant identifiant, Profile profile, ArrayList<Service> listeServices, ArrayList<String> listeCompetences) throws MyException {
        TraiterIdentifiant(identifiant);
        TraiterProfile(profile);
        TraiterListeServices(listeServices);
        TraiterListeCompetences(listeCompetences);
    }
//premier niveau d'abstraction
    private void TraiterIdentifiant(Identifiant identifiant) throws MyException {
        ValiderIdentifiantPasNull(identifiant);
        AffecterValeurIdentifiant(identifiant);
    }

    private void TraiterProfile(Profile profile) throws MyException {
        ValiderProfilePasNull(profile);
        AffecterValeurProfile(profile);
    }

    private void TraiterListeServices(ArrayList<Service> listeServices) {
        if (listeServices != null) {
            AffecterValeurListeService(listeServices);
        }
    }

    private void TraiterListeCompetences(ArrayList<String> listeCompetences) {
        if (listeCompetences != null) {
            AffecterValeurListeCompetences(listeCompetences);
        }
    }
//deuxième niveau d'abstraction
    private void ValiderIdentifiantPasNull(Identifiant identifiant) throws MyException {
        if (identifiant == null) {
            MyException e = new MyException(MESSAGE_IDENTIFIANT_NULL);
            throw e;
        }
    }

    private void AffecterValeurIdentifiant(Identifiant identifiant) {
        this.identifiant = identifiant;
    }

    private void ValiderProfilePasNull(Profile profile) throws MyException {
        if (profile == null) {
            MyException e = new MyException(MESSAGE_PROFILE_NULL);
            throw e;
        }
    }

    private void AffecterValeurProfile(Profile profile) {
        this.profile = profile;
    }

    private void AffecterValeurListeService(ArrayList<Service> listeServices) {
        this.listeServices = listeServices;
    }

    private void AffecterValeurListeCompetences(ArrayList<String> listeCompetences) {
        this.listeCompetences = listeCompetences;
    }
//MÉTHODES PUBLIC
}
