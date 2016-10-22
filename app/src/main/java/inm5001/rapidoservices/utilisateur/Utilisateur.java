package inm5001.rapidoservices.utilisateur;

import java.util.ArrayList;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.service.AbstraiteServices;

import static inm5001.rapidoservices.utilisateur.ConstanteUtilisateur.*;

/**
 * Created by Francis Bernier on 2016-10-10.
 */

public class Utilisateur {
    public Identifiant identifiant;
    public Profile profile;
    public ArrayList<AbstraiteServices> listeServices = new ArrayList<>();
    public ArrayList<String> listeCompetences = new ArrayList<>();
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

    public Utilisateur(Identifiant identifiant, Profile profile, ArrayList<AbstraiteServices> listeServices, ArrayList<String> listeCompetences) throws MyException {
        t(identifiant);
        traiterProfile(profile);
        traiterListeServices(listeServices);
        traiterListeCompetences(listeCompetences);
    }
//premier niveau d'abstraction
    private void t(Identifiant identifiant) throws MyException {
        validerIdentifiantPasNull(identifiant);
        affecterValeurIdentifiant(identifiant);
    }

    private void traiterProfile(Profile profile) throws MyException {
        validerProfilePasNull(profile);
        affecterValeurProfile(profile);
    }

    private void traiterListeServices(ArrayList<AbstraiteServices> listeServices) {
        if (listeServices != null) {
            affecterValeurListeService(listeServices);
        }
    }

    private void traiterListeCompetences(ArrayList<String> listeCompetences) {
        if (listeCompetences != null) {
            affecterValeurListeCompetences(listeCompetences);
        }
    }
//deuxième niveau d'abstraction
    private void validerIdentifiantPasNull(Identifiant identifiant) throws MyException {
        if (identifiant == null) {
            MyException e = new MyException(MESSAGE_IDENTIFIANT_NULL);
            throw e;
        }
    }

    private void affecterValeurIdentifiant(Identifiant identifiant) {
        this.identifiant = identifiant;
    }

    private void validerProfilePasNull(Profile profile) throws MyException {
        if (profile == null) {
            MyException e = new MyException(MESSAGE_PROFILE_NULL);
            throw e;
        }
    }

    private void affecterValeurProfile(Profile profile) {
        this.profile = profile;
    }

    private void affecterValeurListeService(ArrayList<AbstraiteServices> listeServices) {
        this.listeServices = listeServices;
    }

    private void affecterValeurListeCompetences(ArrayList<String> listeCompetences) {
        this.listeCompetences = listeCompetences;
    }
//MÉTHODES PUBLIC
}
