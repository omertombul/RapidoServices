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
    public boolean disponible = false;
    public EvaluationUtilisateur evaluation;
    //public Geolocalisation geolocalisation;

    public Utilisateur(){
    }
    //Pour l'inscription d'un nouvel utilisateur
    public Utilisateur(Identifiant identifiant, Profile profile, ArrayList<AbstraiteServices> listeServices, ArrayList<String> listeCompetences) throws MyException {
        traiterIdentifiant(identifiant);
        traiterProfile(profile);
        traiterListeServices(listeServices);
        traiterListeCompetences(listeCompetences);
    }

    public Utilisateur(Identifiant identifiant, Profile profile, ArrayList<AbstraiteServices> listeServices, ArrayList<String> listeCompetences, EvaluationUtilisateur evaluation) throws MyException {
        traiterIdentifiant(identifiant);
        traiterProfile(profile);
        traiterListeServices(listeServices);
        traiterListeCompetences(listeCompetences);
        traiterEvaluations(evaluation);
    }
//premier niveau d'abstraction
    private void traiterIdentifiant(Identifiant identifiant) throws MyException {
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

    private void traiterEvaluations(EvaluationUtilisateur evaluation) throws MyException {
        validerEvaluationPasNull(evaluation);
        affecterValeurEvaluation(evaluation);
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

    private void validerEvaluationPasNull(EvaluationUtilisateur evaluation) throws MyException {
        if (evaluation == null) {
            MyException e = new MyException(MESSAGE_EVALUATION_NULL);
            throw e;
        }
    }

    private void affecterValeurEvaluation(EvaluationUtilisateur evaluation) {
        this.evaluation = evaluation;
    }
//MÉTHODES PUBLIC
}
