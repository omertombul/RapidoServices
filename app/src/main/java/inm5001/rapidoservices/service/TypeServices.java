package inm5001.rapidoservices.service;

import java.util.Comparator;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Recherche;

import static inm5001.rapidoservices.service.ConstanteTypeServices.MESSAGE_TAUXHORRAIRE_NEGATIF;
import static inm5001.rapidoservices.service.ConstanteTypeServices.MESSAGE_PRIXFIXE_NEGATIF;

/**
 * @author omer et Francis Bernier
 */

public class TypeServices extends AbstraiteServices {

    private float tauxHorraire;
    private float prixFixe;

    public TypeServices(String nomSservice)throws MyException{
        super(nomSservice);
    }

    public TypeServices(float tauxHorraire, float prixFixe, String nomSservice, String ville) throws MyException {
        super( nomSservice, ville );
        traiterTauxHorraire(tauxHorraire);
        traiterPrixFixe(prixFixe);
    }

    public TypeServices(float tauxHorraire, float prixFixe, String nomSservice, boolean disponible, String ville,
                        String noTelephone, String courriel, String description) throws MyException {
        super( nomSservice, disponible, ville, noTelephone, courriel, description );
        traiterTauxHorraire(tauxHorraire);
        traiterPrixFixe(prixFixe);
    }

    public TypeServices(float tauxHorraire, float prixFixe, String nomSservice, boolean disponible, String ville,
                        String noTelephone, String courriel, String description, EvaluationService evaluationService) throws MyException {
        super( nomSservice, disponible, ville, noTelephone, courriel, description, evaluationService );
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

    public void setTauxHorraire( float tauxHorraire ) throws MyException {
        traiterTauxHorraire(tauxHorraire);
    }

    public float getPrixFixe() {
        return prixFixe;
    }

    public void setPrixFixe( float prixFixe ) throws MyException {
        traiterPrixFixe(prixFixe);
    }


}
