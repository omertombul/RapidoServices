package inm5001.rapidoservices.service;

import java.util.Comparator;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.PaireNomUtilisateurEtTypeService;

import static inm5001.rapidoservices.service.ConstanteTypeServices.MESSAGE_TAUXHORRAIRE_NEGATIF;
import static inm5001.rapidoservices.service.ConstanteTypeServices.MESSAGE_PRIXFIXE_NEGATIF;

/**
 * @author omer et Francis Bernier
 */

public class TypeServices extends AbstraiteServices implements Comparable<PaireNomUtilisateurEtTypeService> {

    private float tauxHorraire;
    private float prixFixe;

    public TypeServices(float tauxHorraire, float prixFixe, String nomSservice, boolean disponible, String ville, byte cote, String noTelephone, String courriel, String description) throws MyException {
        super( nomSservice, disponible, ville, cote, noTelephone, courriel, description );
        traiterTauxHorraire(tauxHorraire);
        traiterPrixFixe(prixFixe);
    }
//premier niveau d'abstraction
    private void traiterTauxHorraire(float tauxHorraire) throws MyException {
        validerTauxHorrairePasNegatif(tauxHorraire);
        affecterValeurTauxHorraire(tauxHorraire);
    }

    private void traiterPrixFixe(float prixFixe) throws MyException {
        validerPrixFixePasNegatif(prixFixe);
        affecterValeurPrixFixe(prixFixe);
    }

 //deuxième niveau d'abstraction
    private void validerTauxHorrairePasNegatif(float tauxHorraire) throws MyException {
        if (tauxHorraire < 0) {
            MyException e = new MyException(MESSAGE_TAUXHORRAIRE_NEGATIF);
            throw e;
        }
    }

    private void validerPrixFixePasNegatif(float prixFixe) throws MyException {
        if (prixFixe < 0) {
            MyException e = new MyException(MESSAGE_PRIXFIXE_NEGATIF);
            throw e;
        }
    }

    private void affecterValeurTauxHorraire(float tauxHorraire) {
        this.tauxHorraire = tauxHorraire;
    }

    private void affecterValeurPrixFixe(float prixFixe) {
        this.prixFixe = prixFixe;
    }
 //MÉTHODES PUBLIC
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
