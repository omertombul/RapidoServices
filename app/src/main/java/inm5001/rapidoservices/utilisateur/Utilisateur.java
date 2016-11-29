package inm5001.rapidoservices.utilisateur;

import java.util.ArrayList;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.TypeServices;

import static inm5001.rapidoservices.utilisateur.ConstanteUtilisateur.*;

/**
 * Created by Francis Bernier on 2016-10-10.
 */

public class Utilisateur {
    public Identifiant identifiant;
    public Profile profile;
    public ArrayList<TypeServices> listeServices = new ArrayList<>();
    public ArrayList<String> listeCompetences = new ArrayList<>();
    public boolean disponible = false;
    public EvaluationUtilisateur evaluationUtilisateur = new EvaluationUtilisateur(0, 0, 0, 0);
    //public Geolocalisation geolocalisation;

    public Utilisateur() throws MyException {
    }
    //Pour l'inscription d'un nouvel utilisateur
    public Utilisateur(Identifiant identifiant, Profile profile, ArrayList<TypeServices> listeServices,
                       ArrayList<String> listeCompetences) throws MyException {
        traiterIdentifiant(identifiant);
        traiterProfile(profile);
        traiterListeServices(listeServices);
        traiterListeCompetences(listeCompetences);
    }

    public Utilisateur(Identifiant identifiant, Profile profile, ArrayList<TypeServices> listeServices,
                       ArrayList<String> listeCompetences, EvaluationUtilisateur evaluationUtilisateur) throws MyException {
        traiterIdentifiant(identifiant);
        traiterProfile(profile);
        traiterListeServices(listeServices);
        traiterListeCompetences(listeCompetences);
        traiterEvaluationUtilisateur(evaluationUtilisateur);
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

    private void traiterListeServices(ArrayList<TypeServices> listeServices) {
        if (listeServices != null) {
            affecterValeurListeService(listeServices);
        }
    }

    private void traiterListeCompetences(ArrayList<String> listeCompetences) {
        if (listeCompetences != null) {
            affecterValeurListeCompetences(listeCompetences);
        }
    }

    private void traiterEvaluationUtilisateur(EvaluationUtilisateur evaluationUtilisateur) throws MyException {
        validerEvaluationUtilisateurPasNull(evaluationUtilisateur);
        affecterValeurEvaluationUtilisateur(evaluationUtilisateur);
    }
//deuxième niveau d'abstraction
    private void validerIdentifiantPasNull(Identifiant identifiant) throws MyException {
        if (identifiant == null) {
            throw new MyException(MESSAGE_IDENTIFIANT_NULL);
        }
    }

    private void affecterValeurIdentifiant(Identifiant identifiant) {
        this.identifiant = identifiant;
    }

    private void validerProfilePasNull(Profile profile) throws MyException {
        if (profile == null) {
            throw new MyException(MESSAGE_PROFILE_NULL);
        }
    }

    private void affecterValeurProfile(Profile profile) {
        this.profile = profile;
    }

    private void affecterValeurListeService(ArrayList<TypeServices> listeServices) {
        this.listeServices = listeServices;
    }

    private void affecterValeurListeCompetences(ArrayList<String> listeCompetences) {
        this.listeCompetences = listeCompetences;
    }

    private void validerEvaluationUtilisateurPasNull(EvaluationUtilisateur evaluationUtilisateur) throws MyException {
        if (evaluationUtilisateur == null) {
            throw new MyException(MESSAGE_EVALUATIONUTILISATEUR_NULL);
        }
    }

    private void affecterValeurEvaluationUtilisateur(EvaluationUtilisateur evaluationUtilisateur) {
        this.evaluationUtilisateur = evaluationUtilisateur;
    }
}
