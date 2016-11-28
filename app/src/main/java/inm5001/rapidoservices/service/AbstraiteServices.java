package inm5001.rapidoservices.service;

/**
 * @author omer tombul et Francis Bernier
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import inm5001.rapidoservices.MyException;

import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_EVALUATIONSERVICE_NULL;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_NOMSERVICE_CARACTERE_SPECIAL;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_NOMSERVICE_NULL;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_COURRIEL_FORMAT_VALIDE;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_NOMSERVICE_MAX_QUINZE_CARACTERES;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_VILLE_MAX_QUARANTE_CARACTERES;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_COURRIEL_MAX_DEUXCENTCINQUANTESIX_CARACTERES;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_DESCRIPTION_MAX_DEUXCENTCINQUANTESIX_CARACTERES;
import static inm5001.rapidoservices.utilisateur.ConstanteProfile.MESSAGE_NUMEROTELEPHONE_FORMAT_VALIDE;
import static inm5001.rapidoservices.utilisateur.ConstanteProfile.patternCourriel;
import static inm5001.rapidoservices.utilisateur.ConstanteProfile.patternNumeroTelephone;

public abstract class AbstraiteServices {
	private String nomSservice;
 	private boolean disponible;
 	private String ville = "";
 	private String noTelephone = "";
 	private String courriel = "";
 	private String description = "";
    public EvaluationService evaluationService = new EvaluationService(0, 0);

    public AbstraiteServices( String nomSservice, boolean disponible, String ville, String noTelephone,
                              String courriel, String description ) throws MyException {
        traiterNomService(nomSservice);
        traiterDisponible(disponible);
        traiterVille(ville);
        traiterNoTelephone(noTelephone);
        traiterCourriel(courriel);
        traiterDescription(description);
    }

    public AbstraiteServices( String nomSservice, boolean disponible, String ville, String noTelephone,
                              String courriel, String description, EvaluationService evaluationService ) throws MyException {
        traiterNomService(nomSservice);
        traiterDisponible(disponible);
        traiterVille(ville);
        traiterNoTelephone(noTelephone);
        traiterCourriel(courriel);
        traiterDescription(description);
        traiterEvaluationService(evaluationService);
    }

    public AbstraiteServices( String nomSservice, String ville ) throws MyException {
        traiterNomService(nomSservice);
        traiterVille(ville);
    }

    public AbstraiteServices(String nomSservice)throws MyException{
            traiterNomService(nomSservice);
    }

//premier niveau d'abstraction
    private void traiterNomService(String nomSservice) throws MyException {
        validerNomServicePasNull(nomSservice);
        //validerNomServiceSansCaratereSpecial(nomSservice);
        validerNomSserviceMaxQuinzeCaracteres(nomSservice);
        //nomSservice = convertirEnMajuscule(nomSservice);
        affecterValeurNomService(nomSservice);
    }

    private void traiterDisponible(boolean disponible) throws MyException {
        affecterValeurDisponible(disponible);
    }

    private void traiterVille(String ville) throws MyException {
        validerVilleMaxQuaranteCaracteres(ville);
        affecterValeurVille(ville);
    }

    private void traiterNoTelephone(String noTelephone) throws MyException {
        if (noTelephone != null && !noTelephone.equals("")) {
            validerNumeroTelephone(noTelephone);
            noTelephone = formaterNumeroTelephone(noTelephone);
            affecterValeurNoTelephone(noTelephone);
        }
    }

    private void traiterCourriel(String courriel) throws MyException {
        if (courriel != null && !courriel.equals("")) {
            validerCourrielFormatValide(courriel);
            validerCourrielMaxDeuxCentCinquanteSixCaracteres(courriel);
            affecterValeurCourriel(courriel);
        }
    }

    private void traiterDescription(String description) throws MyException {
        if (description != null && !description.equals("")) {
            validerDescriptionMaxDeuxCentCinquanteSixCaracteres(description);
            affecterValeurDescription(description);
        }
    }

    private void traiterEvaluationService(EvaluationService evaluationService) throws MyException {
        validerEvaluationServicePasNull(evaluationService);
        affecterValeurEvaluationService(evaluationService);
    }
//deuxième niveau d'abstraction
    private void validerNomServicePasNull(String nomSservice) throws MyException {
        if (nomSservice == null) {
            MyException e = new MyException(MESSAGE_NOMSERVICE_NULL);
            throw e;
        }
    }

    private void validerEvaluationServicePasNull(EvaluationService evaluationService) throws MyException {
        if (evaluationService == null) {
            MyException e = new MyException(MESSAGE_EVALUATIONSERVICE_NULL);
            throw e;
        }
    }

    private void affecterValeurEvaluationService(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }
//problème avec REGEX car ne suport pas les caractères spéciaux
    private void validerNomServiceSansCaratereSpecial(String nomSservice) throws MyException {
        if (!nomSservice.matches("[A-Za-z0-9 -]*")) {
            MyException e = new MyException(MESSAGE_NOMSERVICE_CARACTERE_SPECIAL);
            throw e;
        }
    }

    private void validerNomSserviceMaxQuinzeCaracteres(String nomSservice) throws MyException {
        if (nomSservice.length() > 15) {
            MyException e = new MyException(MESSAGE_NOMSERVICE_MAX_QUINZE_CARACTERES);
            throw e;
        }
    }

    private void affecterValeurNomService(String nomSservice) {
        this.nomSservice = nomSservice;
    }

    private void affecterValeurDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    private void validerVilleMaxQuaranteCaracteres(String ville) throws MyException {
        if (ville.length() > 40) {
            MyException e = new MyException(MESSAGE_VILLE_MAX_QUARANTE_CARACTERES);
            throw e;
        }
    }

    private void affecterValeurVille(String ville) {
        this.ville = ville;
    }

    private void validerNumeroTelephone(String noTelephone) throws MyException {
        Pattern pattern = Pattern.compile(patternNumeroTelephone);

        Matcher matcher = pattern.matcher(noTelephone);
        if (!matcher.matches()) {
            MyException e = new MyException(MESSAGE_NUMEROTELEPHONE_FORMAT_VALIDE);
            throw e;
        }
    }

    private String formaterNumeroTelephone (String noTelephone) {
        Pattern pattern = Pattern.compile(patternNumeroTelephone);
        Matcher matcher = pattern.matcher(noTelephone);
        return matcher.replaceFirst("($1) $2-$3");
    }

    private void affecterValeurNoTelephone(String noTelephone) {
        this.noTelephone = noTelephone;
    }

    private void validerCourrielFormatValide(String courriel) throws MyException {
        Pattern pattern = Pattern.compile(patternCourriel);
        Matcher matcher = pattern.matcher(courriel);

        if (!matcher.matches()) {
            MyException e = new MyException(MESSAGE_COURRIEL_FORMAT_VALIDE);
            throw e;
        }
    }

    private void validerCourrielMaxDeuxCentCinquanteSixCaracteres(String courriel) throws MyException {
        if (courriel.length() > 256) {
            MyException e = new MyException(MESSAGE_COURRIEL_MAX_DEUXCENTCINQUANTESIX_CARACTERES);
            throw e;
        }
    }

    private void affecterValeurCourriel(String courriel) {
        this.courriel = courriel;
    }

    private void validerDescriptionMaxDeuxCentCinquanteSixCaracteres(String description) throws MyException {
        if (description.length() > 256) {
            MyException e = new MyException(MESSAGE_DESCRIPTION_MAX_DEUXCENTCINQUANTESIX_CARACTERES);
            throw e;
        }
    }

    private void affecterValeurDescription(String description) {
        this.description = description;
    }
//MÉTHODES GLOBAL
    private String convertirEnMajuscule(String uneChaine) {
        return uneChaine.toUpperCase();
    }

//MÉTHODES PUBLIC
    public String getNomSservice() {
        return nomSservice;
    }

    public void setNomSservice( String nomSservice ) throws MyException {
        traiterNomService(nomSservice);
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible( boolean disponible ) throws MyException {
        traiterDisponible(disponible);
    }

    public String getVille() {
        return ville;
    }

    public void setVille( String ville ) throws MyException {
        traiterVille(ville);
    }

    public String getNoTelephone() {
        return noTelephone;
    }

    public void setNoTelephone( String noTelephone ) throws MyException {
        traiterNoTelephone(noTelephone);
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel( String courriel ) throws MyException {
        traiterCourriel(courriel);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) throws MyException {
        traiterDescription(description);
    }

    public EvaluationService getEvaluationService() {
        return evaluationService;
    }

    public void setEvaluationService( EvaluationService evaluationService ) throws MyException {
        traiterEvaluationService(evaluationService);
    }
}