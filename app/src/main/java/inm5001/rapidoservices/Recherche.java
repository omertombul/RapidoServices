package inm5001.rapidoservices;

import java.util.Comparator;

import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.Utilisateur;

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

    public Utilisateur getUtilisateur(){ return utilisateur; }
    public String getNomService(){ return nomService; }
    public void setUtilisateur(Utilisateur utilisateur){ this.utilisateur = utilisateur; }
    public void setService(String service){ this.nomService = nomService; }

    public static class TrierParTauxHorraire implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.recupererService().getTauxHorraire() > pair2.recupererService().getTauxHorraire() ? 1 : (pair1.recupererService().getTauxHorraire() < pair2.recupererService().getTauxHorraire() ? -1 : 0);
        }
    }

    public static class TrierParPrixFixe implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.recupererService().getPrixFixe() > pair2.recupererService().getPrixFixe() ? 1 : (pair1.recupererService().getPrixFixe() < pair2.recupererService().getPrixFixe() ? -1 : 0);
        }
    }

    public static class TrierParNomService implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.recupererService().getNomSservice().compareTo(pair2.recupererService().getNomSservice());
        }
    }

    public static class TrierParVille implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.recupererService().getVille().compareTo(pair2.recupererService().getVille());
        }
    }

    public static class TrierParCoteService implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.recupererService().getEvaluationService().coteService > pair2.recupererService().getEvaluationService().coteService ? 1 :
                    (pair1.recupererService().getEvaluationService().coteService < pair2.recupererService().getEvaluationService().coteService ? -1 : 0);
        }
    }

    public static class TrierParCoteUtilisateur implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.getUtilisateur().getEvaluationUtilisateur().coteUtilisateur > pair2.getUtilisateur().getEvaluationUtilisateur().coteUtilisateur ? 1 :
                    (pair1.getUtilisateur().getEvaluationUtilisateur().coteUtilisateur < pair2.getUtilisateur().getEvaluationUtilisateur().coteUtilisateur ? -1 : 0);
        }
    }

    public static class TrierParCoteServicesMoyenne implements Comparator<Recherche> {

        @Override
        public int compare(Recherche pair1, Recherche pair2) {
            return pair1.getUtilisateur().getEvaluationUtilisateur().coteTypeServicesMoyenne > pair2.getUtilisateur().getEvaluationUtilisateur().coteTypeServicesMoyenne ? 1 :
                    (pair1.getUtilisateur().getEvaluationUtilisateur().coteTypeServicesMoyenne < pair2.getUtilisateur().getEvaluationUtilisateur().coteTypeServicesMoyenne ? -1 : 0);
        }
    }

    //Pas implémenté, mais obligatoire pour le [implements Comparable<TypeServices>]
    @Override
    public int compareTo(Recherche o) {
        return 0;
    }
}
