package inm5001.rapidoservices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.Utilisateur;

import static inm5001.rapidoservices.ConstanteRecherche.MESSAGE_MODE_TRI_INTROUVABLE;

/**
 * Created by Francis Bernier on 2016-11-10.
 */

public class Recherche implements Comparable<Recherche> {
    private Utilisateur utilisateur;
    private String nomService;

    public Recherche(Utilisateur utilisateur, String nomService){
        this.utilisateur = utilisateur;
        this.nomService = nomService;
    }

    private static class TrierParTauxHorraire implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.recupererService().getTauxHorraire() > pair2.recupererService().getTauxHorraire() ? 1 : (pair1.recupererService().getTauxHorraire() < pair2.recupererService().getTauxHorraire() ? -1 : 0);
        }
    }

    private static class TrierParPrixFixe implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.recupererService().getPrixFixe() > pair2.recupererService().getPrixFixe() ? 1 : (pair1.recupererService().getPrixFixe() < pair2.recupererService().getPrixFixe() ? -1 : 0);
        }
    }

    private static class TrierParNomService implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.recupererService().getNomSservice().compareTo(pair2.recupererService().getNomSservice());
        }
    }

    private static class TrierParVille implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.recupererService().getVille().compareTo(pair2.recupererService().getVille());
        }
    }

    private static class TrierParCoteService implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.recupererService().getEvaluationService().coteService > pair2.recupererService().getEvaluationService().coteService ? 1 :
                    (pair1.recupererService().getEvaluationService().coteService < pair2.recupererService().getEvaluationService().coteService ? -1 : 0);
        }
    }

    private static class TrierParCoteUtilisateur implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.getUtilisateur().getEvaluationUtilisateur().coteUtilisateur > pair2.getUtilisateur().getEvaluationUtilisateur().coteUtilisateur ? 1 :
                    (pair1.getUtilisateur().getEvaluationUtilisateur().coteUtilisateur < pair2.getUtilisateur().getEvaluationUtilisateur().coteUtilisateur ? -1 : 0);
        }
    }

    private static class TrierParCoteServicesMoyenne implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.getUtilisateur().getEvaluationUtilisateur().coteTypeServicesMoyenne > pair2.getUtilisateur().getEvaluationUtilisateur().coteTypeServicesMoyenne ? 1 :
                    (pair1.getUtilisateur().getEvaluationUtilisateur().coteTypeServicesMoyenne < pair2.getUtilisateur().getEvaluationUtilisateur().coteTypeServicesMoyenne ? -1 : 0);
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

    public static ArrayList<Recherche> trierListeRecherche(ArrayList<Recherche> listeResultatRecherche, String valeurDeTri) throws MyException {
        switch (valeurDeTri) {
            case "tauxHorraire":
                Collections.sort(listeResultatRecherche, new Recherche.TrierParTauxHorraire());
                break;
            case "prixFixe":
                Collections.sort(listeResultatRecherche, new Recherche.TrierParPrixFixe());
                break;
            case "nomService":
                Collections.sort(listeResultatRecherche, new Recherche.TrierParNomService());
                break;
            case "ville":
                Collections.sort(listeResultatRecherche, new Recherche.TrierParVille());
                break;
            case "coteUtilisateur":
                Collections.sort(listeResultatRecherche, new Recherche.TrierParCoteUtilisateur());
                break;
            case "coteServiceMoyenne":
                Collections.sort(listeResultatRecherche, new Recherche.TrierParCoteServicesMoyenne());
                break;
            case "coteService":
                Collections.sort(listeResultatRecherche, new Recherche.TrierParCoteService());
                break;
            default:
                MyException e = new MyException(MESSAGE_MODE_TRI_INTROUVABLE);
                throw e;
        }

        return listeResultatRecherche;
    }

    public Utilisateur getUtilisateur(){ return utilisateur; }
    public String getNomService(){ return nomService; }
    //public void setUtilisateur(Utilisateur utilisateur){ this.utilisateur = utilisateur; }
    //public void setService(String nomService){ this.nomService = nomService; }

    //Pas implémenté, mais obligatoire pour le [implements Comparable<Recherche>]
    @Override
    public int compareTo(Recherche o) {
        return 0;
    }
}
