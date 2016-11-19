package inm5001.rapidoservices.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.utilisateur.EvaluationUtilisateur;
import inm5001.rapidoservices.utilisateur.Profile;

import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_COURRIEL_FORMAT_VALIDE;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_DESCRIPTION_MAX_DEUXCENTCINQUANTESIX_CARACTERES;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_NOMSERVICE_CARACTERE_SPECIAL;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_NOMSERVICE_MAX_QUINZE_CARACTERES;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_NOMSERVICE_NULL;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_NOTELEPHONE_DIX_CHIFFRE;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_NOTELEPHONE_SEULEMENT_CHIFFRE;
import static inm5001.rapidoservices.service.ConstanteAbstraiteServices.MESSAGE_VILLE_MAX_QUARANTE_CARACTERES;
import static inm5001.rapidoservices.utilisateur.ConstanteProfile.MESSAGE_ADRESSECOURRIEL_FORMAT_VALIDE;
import static inm5001.rapidoservices.utilisateur.ConstanteProfile.MESSAGE_ADRESSECOURRIEL_NULL;
import static inm5001.rapidoservices.utilisateur.ConstanteProfile.MESSAGE_NOM_CARACTERE_SPECIAL;
import static inm5001.rapidoservices.utilisateur.ConstanteProfile.MESSAGE_NUMEROTELEPHONE_DIX_CHIFFRE;
import static inm5001.rapidoservices.utilisateur.ConstanteProfile.MESSAGE_NUMEROTELEPHONE_SEULEMENT_CHIFFRE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AbstraiteServicesTest {

    TypeServices service;
    //attributs AbstraiteServices
    private boolean disponible;
    private String ville;
    private String noTelephone;
    private String courriel;
    private String description;
    //attribut TypeServices
    String nomSservice;
    private float tauxHorraire;
    private float prixFixe;
    boolean estValider;
    //attributs EvaluationService
    private float coteService;
    private int nombreDEvaluationService;
    private EvaluationService evaluationService;

    @Before
    public void setUp() throws MyException {
        nomSservice = "Plomberie";
        disponible = false;
        ville = "Montreal";
        noTelephone = "5144444444";
        courriel = "plomberie@plomberi.com";
        description = "Repare les tuyeaux";
        tauxHorraire = 14.50f;
        prixFixe = 50.00f;
        estValider = false;
        //attributs EvaluationService
        coteService = 3.5f;
        nombreDEvaluationService = 210;
        evaluationService = new EvaluationService(coteService, nombreDEvaluationService);
    }

    @After
    public void tearDown() {
        nomSservice = null;
        disponible = false;
        ville = null;
        noTelephone = null;
        courriel = null;
        description = null;
        tauxHorraire = 0;
        prixFixe = 0;
        estValider = false;
        //attributs EvaluationService
        coteService = 0;
        nombreDEvaluationService = 0;
        evaluationService = null;
    }

    @Test
    public void AbstraiteServicesPasNull() throws Exception {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        assertNotNull(service);
    }

    @Test
    public void AbstraiteServicesNomSservice() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        assertEquals(service.getNomSservice(), nomSservice);
    }

    @Test
    public void AbstraiteServicesDisponible() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        assertFalse(service.getDisponible());
    }

    @Test
    public void AbstraiteServicesVille() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        assertEquals(service.getVille(), ville);
    }

    @Test
    public void AbstraiteServicesCote() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description, evaluationService);
        assertTrue(service.getEvaluationService().coteService == coteService);
    }

    @Test
    public void AbstraiteServicesNoTelephone() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        assertEquals(service.getNoTelephone(), noTelephone);
    }

    @Test
    public void AbstraiteServicesCourriel() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        assertEquals(service.getCourriel(), courriel);
    }

    @Test
    public void AbstraiteServicesDescription() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        assertEquals(service.getDescription(), description);
    }

    @Test
    public void AbstraiteServicesEvaluationService() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description, evaluationService);
        assertTrue(service.getEvaluationService().coteService == evaluationService.coteService);
        assertTrue(service.getEvaluationService().nombreDEvaluationService == nombreDEvaluationService);
    }
//tauxHorraire
    @Test
    public void validerNomServicePasNull() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, null, disponible, ville, noTelephone, courriel, description);
        } catch (Exception e) {
            estValider = e.getMessage().equals(MESSAGE_NOMSERVICE_NULL);
        }
        assertTrue(estValider);
    }
/*
    @Test
    public void validerNomServiceSansCaratereSpecial() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, "plom!ier", disponible, ville, cote,
                    noTelephone, courriel, description);
        } catch (Exception e) {
            estValider = e.getMessage().equals(MESSAGE_NOMSERVICE_CARACTERE_SPECIAL);
        }
        assertTrue(estValider);
    }

    @Test
    public void validerNomServiceSansCaratereSpecialExeption1() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, "plombi-er", disponible, ville, cote,
                    noTelephone, courriel, description);
        } catch (Exception e) {
            estValider = e.getMessage().equals(MESSAGE_NOMSERVICE_CARACTERE_SPECIAL);
        }
        assertFalse(estValider);
    }

    @Test
    public void validerNomServiceSansCaratereSpecialException2() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, "plom bier", disponible, ville, cote,
                    noTelephone, courriel, description);
        } catch (Exception e) {
            estValider = e.getMessage().equals(MESSAGE_NOMSERVICE_CARACTERE_SPECIAL);
        }
        assertFalse(estValider);
    }
*/
    @Test
    public void validerNomSserviceMaxQuinzeCaracteres15() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, "abcdefghijklmno", disponible, ville, noTelephone, courriel, description);
        } catch (Exception e){
            estValider = e.getMessage().equals(MESSAGE_NOMSERVICE_MAX_QUINZE_CARACTERES);
        }
        assertFalse(estValider);
    }

    @Test
    public void validerNomSserviceMaxQuinzeCaracteres16() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, "abcdefghijklmnop", disponible, ville, noTelephone, courriel, description);
        } catch (Exception e){
            estValider = e.getMessage().equals(MESSAGE_NOMSERVICE_MAX_QUINZE_CARACTERES);
        }
        assertTrue(estValider);
    }

    @Test
    public void convertirEnMajusculeNomSservice() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, "ab1c-", disponible, ville, noTelephone, courriel, description);
        assertEquals(service.getNomSservice(), "ab1c-");
    }
//ville
    @Test
    public void validerVilleMaxQuaranteCaracteres40() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, "abcdefghijklmnopqrstuvwxyzabcdefghijklmn",
                    noTelephone, courriel, description);
        } catch (Exception e) {
            estValider = e.getMessage().equals(MESSAGE_VILLE_MAX_QUARANTE_CARACTERES);
        }
        assertFalse(estValider);
    }

    @Test
    public void validerVilleMaxQuaranteCaracteres41() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, "abcdefghijklmnopqrstuvwxyzabcdefghijklmno",
                    noTelephone, courriel, description);
        } catch (Exception e) {
            estValider = e.getMessage().equals(MESSAGE_VILLE_MAX_QUARANTE_CARACTERES);
        }
        assertTrue(estValider);
    }
//NUMÉRO DE TÉLÉPHONE
    @Test
    public void TraiterNumeroTelephoneNull() throws Exception {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, null, courriel, description);
        String noTelephone = service.getNoTelephone();
        assertEquals(noTelephone, "");
    }

    @Test
    public void TraiterNumeroTelephoneVide() throws Exception {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, "", courriel, description);
        String noTelephone = service.getNoTelephone();
        assertEquals(noTelephone, "");
    }

    @Test
    public void ValiderNumeroTelephoneSeulementChiffre1() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, "514 5972143", courriel, description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOTELEPHONE_SEULEMENT_CHIFFRE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderNumeroTelephoneSeulementChiffre2() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, "514-5972143", courriel, description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOTELEPHONE_SEULEMENT_CHIFFRE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderNumeroTelephoneSeulementChiffre3() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, "5i45972143", courriel, description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOTELEPHONE_SEULEMENT_CHIFFRE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderNumeroTelephoneDixChiffre9() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, "514597214", courriel, description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOTELEPHONE_DIX_CHIFFRE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderNumeroTelephoneDixChiffre11() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, "51459721433", courriel, description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_NOTELEPHONE_DIX_CHIFFRE);
        }
        assertTrue(estValider);
    }
//ADRESSE COURRIEL
    @Test
    public void ValiderAdresseCourrielNull() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, null, description);
        String courriel = service.getCourriel();
        assertEquals(courriel, "");
    }

    @Test
    public void ValiderAdresseCourrielVidel() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, "", description);
        String courriel = service.getCourriel();
        assertEquals(courriel, "");
    }

    @Test
    public void ValiderAdresseCourrielFormatValide1() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, "@hotmail.com", description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_COURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderAdresseCourrielFormatValide2() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, "francis@.com", description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_COURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderAdresseCourrielFormatValide3() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone,
                    "francishotmail.com", description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_COURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderAdresseCourrielFormatValide4() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone,
                    "francis@hotmailcom", description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_COURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderAdresseCourrielFormatValide5() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone,
                    "francis@hotmail.", description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_COURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderAdresseCourrielFormatValide6() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone,
                    "francis.bernier@hotmail.", description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_COURRIEL_FORMAT_VALIDE);
        }
        assertTrue(estValider);
    }

    @Test
    public void ValiderAdresseCourrielFormatValide7() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone,
                    "francis1@hotmail.com", description);
        } catch (MyException e) {
            estValider = e.getMessage().equals(MESSAGE_COURRIEL_FORMAT_VALIDE);
        }
        assertFalse(estValider);
    }
//description
    @Test
    public void ValiderDescriptionNull() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, null);
        String description = service.getDescription();
        assertEquals(description, "");
    }

    @Test
    public void ValiderDescriptionVide() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, "");
        String description = service.getDescription();
        assertEquals(description, "");
    }

    @Test
    public void validerDescriptionMaxDeuxCentCinquanteSixCaracteres256() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel,
                    "abcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuv");
        } catch (Exception e) {
            estValider = e.getMessage().equals(MESSAGE_DESCRIPTION_MAX_DEUXCENTCINQUANTESIX_CARACTERES);
        }
        assertFalse(estValider);
    }

    @Test
    public void validerDescriptionMaxDeuxCentCinquanteSixCaracteres257() {
        try {
            service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel,
                    "abcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvwxyzabcdefghijklnmopqrstuvw");
        } catch (Exception e) {
            estValider = e.getMessage().equals(MESSAGE_DESCRIPTION_MAX_DEUXCENTCINQUANTESIX_CARACTERES);
        }
        assertTrue(estValider);
    }

    @Test
    public void setNomSservice() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        service.setNomSservice("testtest");
        assertEquals(service.getNomSservice(), "testtest");
    }

    @Test
    public void setDisponible() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        service.setDisponible(true);
        assertEquals(service.getDisponible(), true);
    }

    @Test
    public void setVille() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        service.setVille("test");
        assertEquals(service.getVille(), "test");
    }

    @Test
    public void setNoTelephone() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        service.setNoTelephone("1111111111");
        assertEquals(service.getNoTelephone(), "1111111111");
    }

    @Test
    public void setCourriel() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        service.setCourriel("111@tt.tv");
        assertEquals(service.getCourriel(), "111@tt.tv");
    }

    @Test
    public void setDescription() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description);
        service.setDescription("abcd");
        assertEquals(service.getDescription(), "abcd");
    }

    @Test
    public void setEvaluationService() throws MyException {
        service = new TypeServices(tauxHorraire, prixFixe, nomSservice, disponible, ville, noTelephone, courriel, description, evaluationService);
        assertTrue(service.getEvaluationService().coteService == 3.5);
        service.setEvaluationService(new EvaluationService(2, nombreDEvaluationService));
        assertTrue(service.getEvaluationService().coteService == 2);
    }

    @Test
    public void fauxPositif() throws Exception {
        assertTrue(false);
    }
}