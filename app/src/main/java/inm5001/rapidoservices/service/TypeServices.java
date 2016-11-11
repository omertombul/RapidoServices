/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inm5001.rapidoservices.service;

import java.util.Comparator;

import inm5001.rapidoservices.PaireNomUtilisateurEtTypeService;

/**
 *
 * @author omer
 */
public class TypeServices extends AbstraiteServices implements Comparable<PaireNomUtilisateurEtTypeService> {
        
    private float tauxHorraire;
    private float prixFixe;

    public TypeServices(float tauxHorraire, float prixFixe, String nomSservice, boolean disponible, String ville, byte cote, String noTelephone, String courriel, String description ) {
        super( nomSservice, disponible, ville, cote, noTelephone, courriel, description );
        this.tauxHorraire = tauxHorraire;
        this.prixFixe = prixFixe;
    }

    public float getTauxHorraire() {
        return tauxHorraire;
    }

    public void setTauxHorraire( float tauxHorraire ) {
        this.tauxHorraire = tauxHorraire;
    }

    public float getPrixFixe() {
        return prixFixe;
    }

    public void setPrixFixe( float prixFixe ) {
        this.prixFixe = prixFixe;
    }

    public static class TrierParTauxHorraire implements Comparator<PaireNomUtilisateurEtTypeService> {

        @Override
        public int compare(PaireNomUtilisateurEtTypeService pair1, PaireNomUtilisateurEtTypeService pair2) {
            return pair1.getService().getTauxHorraire() > pair2.getService().getTauxHorraire() ? 1 : (pair1.getService().getTauxHorraire() < pair2.getService().getTauxHorraire() ? -1 : 0);
        }
    }

    public static class TrierParPrixFixe implements Comparator<PaireNomUtilisateurEtTypeService> {

        @Override
        public int compare(PaireNomUtilisateurEtTypeService pair1, PaireNomUtilisateurEtTypeService pair2) {
            return pair1.getService().getPrixFixe() > pair2.getService().getPrixFixe() ? 1 : (pair1.getService().getPrixFixe() < pair2.getService().getPrixFixe() ? -1 : 0);
        }
    }

    public static class TrierParNomService implements Comparator<PaireNomUtilisateurEtTypeService> {

        @Override
        public int compare(PaireNomUtilisateurEtTypeService pair1, PaireNomUtilisateurEtTypeService pair2) {
            return pair1.getService().getNomSservice().compareTo(pair2.getService().getNomSservice());
        }
    }

    public static class TrierParVille implements Comparator<PaireNomUtilisateurEtTypeService> {

        @Override
        public int compare(PaireNomUtilisateurEtTypeService pair1, PaireNomUtilisateurEtTypeService pair2) {
            return pair1.getService().getVille().compareTo(pair2.getService().getVille());
        }
    }

    //Pas implémenté, mais obligatoire pour le [implements Comparable<TypeServices>]
    @Override
    public int compareTo(PaireNomUtilisateurEtTypeService o) {
        return 0;
    }
}
