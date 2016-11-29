package inm5001.rapidoservices.recherche;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.Utilisateur;

import static inm5001.rapidoservices.ConstanteRecherche.MESSAGE_MODE_TRI_INTROUVABLE;

/**
 * Created by Francis Bernier on 2016-11-10.
 */

public class RechercheServices implements Comparable<RechercheServices> {
    private Utilisateur utilisateur;
    private String nomService;

    public RechercheServices(Utilisateur utilisateur, String nomService){
        this.utilisateur = utilisateur;
        this.nomService = nomService;
    }

    private static class TrierParTauxHorraire implements Comparator<RechercheServices> {

        @Override
        public int compare(RechercheServices pair1, RechercheServices pair2) {
            return pair1.recupererService().getTauxHorraire() > pair2.recupererService().getTauxHorraire() ? 1 : (pair1.recupererService().getTauxHorraire() < pair2.recupererService().getTauxHorraire() ? -1 : 0);
        }
    }

    private static class TrierParPrixFixe implements Comparator<RechercheServices> {

        @Override
        public int compare(RechercheServices pair1, RechercheServices pair2) {
            return pair1.recupererService().getPrixFixe() > pair2.recupererService().getPrixFixe() ? 1 : (pair1.recupererService().getPrixFixe() < pair2.recupererService().getPrixFixe() ? -1 : 0);
        }
    }

    private static class TrierParNomService implements Comparator<RechercheServices> {

        @Override
        public int compare(RechercheServices pair1, RechercheServices pair2) {
            return pair1.recupererService().getNomSservice().compareTo(pair2.recupererService().getNomSservice());
        }
    }

    private static class TrierParVille implements Comparator<RechercheServices> {

        @Override
        public int compare(RechercheServices pair1, RechercheServices pair2) {
            return pair1.recupererService().getVille().compareTo(pair2.recupererService().getVille());
        }
    }

    private static class TrierParCoteService implements Comparator<RechercheServices> {

        @Override
        public int compare(RechercheServices pair1, RechercheServices pair2) {
            return pair1.recupererService().getEvaluationService().coteService > pair2.recupererService().getEvaluationService().coteService ? 1 :
                    (pair1.recupererService().getEvaluationService().coteService < pair2.recupererService().getEvaluationService().coteService ? -1 : 0);
        }
    }

    private static class TrierParCoteUtilisateur implements Comparator<RechercheServices> {

        @Override
        public int compare(RechercheServices pair1, RechercheServices pair2) {
            return pair1.getUtilisateur().evaluationUtilisateur.coteUtilisateur > pair2.getUtilisateur().evaluationUtilisateur.coteUtilisateur ? 1 :
                    (pair1.getUtilisateur().evaluationUtilisateur.coteUtilisateur < pair2.getUtilisateur().evaluationUtilisateur.coteUtilisateur ? -1 : 0);
        }
    }

    private static class TrierParCoteServicesMoyenne implements Comparator<RechercheServices> {

        @Override
        public int compare(RechercheServices pair1, RechercheServices pair2) {
            return pair1.getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne > pair2.getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne ? 1 :
                    (pair1.getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne < pair2.getUtilisateur().evaluationUtilisateur.coteTypeServicesMoyenne ? -1 : 0);
        }
    }
//MÉTHODES PUBLIC
    public TypeServices recupererService() {
        int i = this.getUtilisateur().listeServices.size();
        int y = 0;
        String nomServiceTemp = this.getUtilisateur().listeServices.get(y).getNomSservice();
        String nomServiceAttendu = this.getNomService();

        while (!nomServiceAttendu.equals(nomServiceTemp) && y < i) {
            y = y + 1;
            nomServiceTemp = this.getUtilisateur().listeServices.get(y).getNomSservice();
        }

        return this.getUtilisateur().listeServices.get(y);
    }

    public static ArrayList<RechercheServices> trierListeRecherche(ArrayList<RechercheServices> listeResultatRechercheServices, String valeurDeTri) throws MyException {
        switch (valeurDeTri) {
            case "tauxHorraire":
                Collections.sort(listeResultatRechercheServices, new RechercheServices.TrierParTauxHorraire());
                break;
            case "prixFixe":
                Collections.sort(listeResultatRechercheServices, new RechercheServices.TrierParPrixFixe());
                break;
            case "nomService":
                Collections.sort(listeResultatRechercheServices, new RechercheServices.TrierParNomService());
                break;
            case "ville":
                Collections.sort(listeResultatRechercheServices, new RechercheServices.TrierParVille());
                break;
            case "coteUtilisateur":
                Collections.sort(listeResultatRechercheServices, new RechercheServices.TrierParCoteUtilisateur());
                break;
            case "coteServiceMoyenne":
                Collections.sort(listeResultatRechercheServices, new RechercheServices.TrierParCoteServicesMoyenne());
                break;
            case "coteService":
                Collections.sort(listeResultatRechercheServices, new RechercheServices.TrierParCoteService());
                break;
            default:
                throw new MyException(MESSAGE_MODE_TRI_INTROUVABLE);
        }

        return listeResultatRechercheServices;
    }

    public Utilisateur getUtilisateur(){ return utilisateur; }
    public String getNomService(){ return nomService; }

    //Pas implémenté, mais obligatoire pour le [implements Comparable<RechercheServices>]
    @Override
    public int compareTo(RechercheServices o) {
        return 0;
    }
}
