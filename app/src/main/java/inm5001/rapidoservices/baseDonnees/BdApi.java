package inm5001.rapidoservices.baseDonnees;

import java.sql.ResultSet;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.recherche.RechercheACoter;
import inm5001.rapidoservices.recherche.RechercheServices;
import inm5001.rapidoservices.service.EvaluationService;
import inm5001.rapidoservices.utilisateur.EvaluationUtilisateur;
import inm5001.rapidoservices.utilisateur.Utilisateur;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.service.TypeServices;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Admin on 2016-10-22.
 */

public class BdApi {

    public BdApi() {
        //System.out.println("Start: Constructeur Api");
    }

    public void addUser(Utilisateur U) throws SQLException {
        String SQL = SQLaddUser(U);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
    }

    public Utilisateur getUser(String nomUtilisateur) throws MyException {
        Utilisateur U = new Utilisateur();

        String SQL = SQLgetUser(nomUtilisateur);
        BdConnection DB = new BdConnection(SQL);
        ResultSet RSutilisateur = DB.readFromDataBase();
        U = updateUtilisateurWithRSutilisateurData(U, RSutilisateur);
        DB.closeConnection();

        SQL = SQLgetServices(U);
        DB = new BdConnection(SQL);
        ResultSet RSservices = DB.readFromDataBase();
        U = updateUtilisateurWithRSservicesData(U, RSservices);
        DB.closeConnection();

        SQL = SQLgetCompetences(U);
        DB = new BdConnection(SQL);
        ResultSet RScompetences = DB.readFromDataBase();
        U = updateUtilisateurWithRScompetencesData(U, RScompetences);
        DB.closeConnection();

        return U;
    }

    public void deleteUser(String nomUtilisateur){
        String SQL = SQLdeleteUser(nomUtilisateur);
        BdConnection DB = new BdConnection(SQL);
        DB.deleteInDataBase();
        DB.closeConnection();
    }

    public void addServiceUser(String nomUtilisateur, TypeServices S) throws SQLException {
        String SQL = SQLaddServiceUser(nomUtilisateur, S);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
    }

    public void deleteService(String nomUtilisateur, String nomService){
        String SQL = SQLdeleteService(nomUtilisateur, nomService);
        BdConnection DB = new BdConnection(SQL);
        DB.deleteInDataBase();
        DB.closeConnection();
    }

    public void updateUserDisponibilite(String nomUtilisateur, String disponibilite)
            throws SQLException {
        String SQL = SQLchangeUserDisponibilite(nomUtilisateur, disponibilite);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
    }

    public void updateServiceDisponibilite(String nomUtilisateur, String nomService, String
            disponibilite) throws SQLException {
        String SQL = SQLchangeServiceDisponibilite(nomUtilisateur, nomService, disponibilite);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
    }

    public void addCompetenceUser(String nomUtilisateur, TypeServices s) throws SQLException {
        String SQL = SQLaddCompetence(nomUtilisateur, s);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
    }

    public void deleteCompetence(String nomUtilisateur, TypeServices s){
        String SQL = SQLdeleteCompetence(nomUtilisateur, s);
        BdConnection DB = new BdConnection(SQL);
        DB.deleteInDataBase();
        DB.closeConnection();
    }

    public ArrayList<RechercheServices> servicesSearch(TypeServices s, float coteUtilisateur,
                                                       float coteServicesMoyenne, float coteService){
        ArrayList<RechercheServices> UserAndServicesArray = new ArrayList<>();


        String SQL = SQLservicesSearch(s);
        BdConnection DB = new BdConnection(SQL);
        ResultSet RSservices = DB.readFromDataBase();
        UserAndServicesArray = updateUserAndSerivcesArrayWithRS(RSservices);
        DB.closeConnection();
        return UserAndServicesArray;
    }

    public void addIntoCoteService(String gradedUserId, String gradingUserId, String nomService,
                                   float grade) throws SQLException{
        String SQL = SQLaddIntoCoteService(gradedUserId, gradingUserId, nomService, grade);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
    }

    public void gradeService(String gradedUserId, String gradingUserId, String nomService,
                             float grade) throws SQLException{
        String SQL = SQLgradeService(gradedUserId, gradingUserId, nomService, grade);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
    }

    public ArrayList<RechercheACoter> getRechercheACoter(String nomUtilisateur){
        ArrayList<RechercheACoter> aCoterArray = new ArrayList<>();

        String SQL = SQLgetACoter(nomUtilisateur);
        BdConnection DB = new BdConnection(SQL);
        ResultSet RSaCoter = DB.readFromDataBase();
        aCoterArray = updateACoterWithRS(RSaCoter);
        DB.closeConnection();
        return aCoterArray;
    }

    //*************************************************************************
    // level 2 abstraction
    private String SQLaddUser(Utilisateur U) {
        byte diponibilite = (byte) (U.disponible?1:0);
        String SQL;
        String SQL_DEBUT = "INSERT INTO utilisateur VALUES('";
        String SQL_SEPARATEUR = "' ,'";
        String SQL_FIN = "');";
        SQL = SQL_DEBUT;
        SQL += U.identifiant.nomUtilisateur + SQL_SEPARATEUR;
        SQL += U.identifiant.motDePasse + SQL_SEPARATEUR;
        SQL += U.profile.nom + SQL_SEPARATEUR;
        SQL += U.profile.prenom + SQL_SEPARATEUR;
        SQL += U.profile.adresseCourriel + SQL_SEPARATEUR;
        SQL += diponibilite + SQL_SEPARATEUR;  // dispo
        SQL += U.evaluationUtilisateur.coteUtilisateur + SQL_SEPARATEUR;  // coteClient
        SQL += U.evaluationUtilisateur.nombreDEvaluationUtilisateur + SQL_SEPARATEUR;  // nbCoteClient
        SQL += U.evaluationUtilisateur.coteTypeServicesMoyenne + SQL_SEPARATEUR;  // coteFournisseur
        SQL += U.evaluationUtilisateur.nombreDEvaluationTypeServicesMoyenne + SQL_SEPARATEUR;  // nbCoteFournisseur
        SQL += "1" + SQL_SEPARATEUR;         // geo coordonnees
        SQL += U.profile.numeroTelephone + SQL_FIN;
//System.out.println("    String SQL addUser: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLgetUser(String nomUtilisateur) {
        String SQL;
        //String SQL_DEBUT = "SELECT * FROM utilisateur WHERE idUsager = '"; // need test before del
        String SQL_DEBUT = "SELECT idUsager, motDePasse, nom, prenom, courriel, disponibilite, " +
                "ifnull(coteClient, 0) as coteClient, nbCoteClient, ifnull(coteFournisseur, 0) " +
                "as coteFournisseur, nbCoteFournisseur, coordonnees, noTelephone " +
                "FROM utilisateur WHERE idUsager = '"; // 'ifnull' handles NULL as 0 from DB table
        String SQL_FIN = "';";
        SQL = SQL_DEBUT + nomUtilisateur + SQL_FIN;
//System.out.println("    String SQL getUser: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLgetServices(Utilisateur U) {
        String SQL;
        String SQL_DEBUT = "SELECT * FROM servicesDUsager WHERE idUsager = '";
        String SQL_FIN = "';";
        SQL = SQL_DEBUT + U.identifiant.nomUtilisateur + SQL_FIN;
//System.out.println("    String SQL getService: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLgetCompetences(Utilisateur U) {
        String SQL;
        String SQL_DEBUT = "SELECT * FROM competences WHERE idUsager = '";
        String SQL_FIN = "';";
        SQL = SQL_DEBUT + U.identifiant.nomUtilisateur + SQL_FIN;
//System.out.println("    String SQL getCompetencesUtilisateur: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLdeleteUser(String nomUtilisateur) {
        String SQL_DEBUT = "DELETE FROM utilisateur WHERE idUsager = '";
        String SQL_FIN = "';";
        String SQL = SQL_DEBUT + nomUtilisateur + SQL_FIN;
//System.out.println("Delete SQL is: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLaddServiceUser(String nomUtilisateur, TypeServices S) {
        byte diponibilite = (byte) (S.getDisponible()?1:0);

        String SQL;
        String SQL_DEBUT = "INSERT INTO servicesDUsager VALUES('";
        String SQL_SEPARATEUR = "' ,'";
        String SQL_FIN = "');";
        String SQL_DEBUT_COMPETENCE = " INSERT INTO competences VALUES('";
        SQL = SQL_DEBUT;
        SQL += nomUtilisateur + SQL_SEPARATEUR;
        SQL += S.getNomSservice() + SQL_SEPARATEUR;
        SQL += "0" + SQL_SEPARATEUR;              // duree
        SQL += diponibilite + SQL_SEPARATEUR;
        SQL += "0" + SQL_SEPARATEUR;          // reservation
        SQL += S.getPrixFixe() + SQL_SEPARATEUR;
        SQL += S.getTauxHorraire() + SQL_SEPARATEUR;
        SQL += S.getVille() + SQL_SEPARATEUR;
        SQL += S.getNoTelephone() + SQL_SEPARATEUR;
        SQL += S.getCourriel() + SQL_SEPARATEUR;
        SQL += S.getEvaluationService().coteService + SQL_SEPARATEUR;
        SQL += S.getEvaluationService().nombreDEvaluationService + SQL_SEPARATEUR;
        SQL += S.getDescription() + SQL_FIN;
System.out.println("    String SQL addService Usager: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLdeleteService(String nomUtilisateur, String nomService) {
        String SQL_DEBUT = "DELETE FROM servicesDUsager WHERE idUsager = '";
        String SQL_AND = "' AND nomService = '";
        String SQL_FIN = "';";
        String SQL = SQL_DEBUT + nomUtilisateur + SQL_AND + nomService + SQL_FIN;
//System.out.println("Delete **SERVICE** SQL is: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLchangeUserDisponibilite(String nomUtilisateur,
                                              String status) {
        String SQL_DEBUT = "UPDATE utilisateur SET disponibilite = ";
        String SQL_DEBUT_USR_ID = " where idUsager = '";
        String SQL_FIN = "';";
        String SQL = SQL_DEBUT + status + SQL_DEBUT_USR_ID + nomUtilisateur + SQL_FIN;
//System.out.println("UPDATE **disponibilite** SQL is: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLchangeServiceDisponibilite(String nomUtilisateur, String nomService,
                                                 String status) {
        String SQL_DEBUT = "UPDATE servicesDUsager SET disponibilite = ";
        String SQL_DEBUT_USR_ID = " where idUsager = '";
        String SQL_SEPARATEUR = "'";
        String SQL_NOM_SERVICE = " and nomService = '";
        String SQL_FIN = "';";
        String SQL = SQL_DEBUT + status + SQL_DEBUT_USR_ID + nomUtilisateur + SQL_SEPARATEUR +
                SQL_NOM_SERVICE + nomService + SQL_FIN;
//System.out.println("UPDATE **disponibilite** SQL is: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLaddCompetence(String nomUtilisateur, TypeServices s){
        String SQL;
        String SQL_DEBUT = "INSERT INTO competences VALUES('";
        String SQL_SEPARATEUR = "' ,'";
        String SQL_FIN = "');";
        SQL = SQL_DEBUT;
        SQL += s.getNomSservice() + SQL_SEPARATEUR;
        SQL += nomUtilisateur + SQL_FIN;
//System.out.println("add **Competence** SQL is: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLdeleteCompetence(String nomUtilisateur, TypeServices s){
        String SQL_DEBUT = "DELETE FROM competences WHERE idUsager = '";
        String SQL_AND = "' AND nomService = '";
        String SQL_FIN = "';";
        String SQL = SQL_DEBUT + nomUtilisateur + SQL_AND + s.getNomSservice() + SQL_FIN;
//System.out.println("Delete **COMPETENCE** SQL is: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLservicesSearch(TypeServices s){
        String SQL;
        String SQL_NOM_SERVICE = " and nomService = '" + s.getNomSservice() + "'";
        String SQL_PRIX_FIXE = " and prixFixe <= " + s.getPrixFixe();
        String SQL_PRIX_HORRAIRE = " and prixHorraire <= " + s.getTauxHorraire();
        String SQL_VILLE = " and ville = '" + s.getVille() + "'";
        String SQL_FIN = ";";

        // criteres de recherche possibles: nomService, disponibilite, prixFixe, prixHorraire, ville
        String SQL_DEBUT = "SELECT * FROM utilisateur u, servicesDUsager s " +
                "WHERE u.idUsager = s.idUsager and u.disponibilite = 1 and s.disponibilite = 1";
        if(s.getNomSservice() == "") {
            SQL_NOM_SERVICE = "";
        }
        if(s.getPrixFixe() == 0) {
            SQL_PRIX_FIXE = "";
        }
        if(s.getTauxHorraire() == 0) {
            SQL_PRIX_HORRAIRE = "";
        }
        if(s.getVille() == "") {
            SQL_VILLE = "";
        }

        SQL = SQL_DEBUT + SQL_NOM_SERVICE + SQL_PRIX_FIXE + SQL_PRIX_HORRAIRE +
                SQL_VILLE + SQL_FIN;
//System.out.println("    String SQL servicesSearch: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLaddIntoCoteService(String gradedUserId, String gradingUserId, String nomService,
                                         float grade){
        String SQL;
        String SQL_DEBUT = "INSERT INTO cotesServices VALUES('";
        String SQL_SEPARATEUR = "' ,'";
        String SQL_FIN_INSERT = "') ";
        String SQL_ON_DUPLICATE = "ON DUPLICATE gradedUserId=values(gradedUserId), " +
                "gradingUserId=values(gradingUserId), nomService=values(nomService), " +
                "cote=values(cote);";
        SQL = SQL_DEBUT;
        SQL += gradedUserId + SQL_SEPARATEUR;
        SQL += gradingUserId + SQL_SEPARATEUR;
        SQL += nomService + SQL_SEPARATEUR;
        SQL += grade + SQL_FIN_INSERT;
        SQL += SQL_ON_DUPLICATE;
//System.out.println(" **addIntoCoteService** SQL is: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLgradeService(String gradedUserId, String gradingUserId, String nomService,
                                   float grade){
// UPDATE cotesServices SET cote = '32' where gradedUserId = 'Bill' and gradingUserId = 'Mat' and nomService = 'Menuisier';
        String SQL;
        String SQL_DEBUT = "UPDATE cotesServices SET cote = '";
        String SQL_WHERE = "' where gradedUserId = '";
        String SQL_AND1 = "' and gradingUserId = '";
        String SQL_AND2 = "' and nomService = '";
        String SQL_FIN = "';";
        SQL = SQL_DEBUT;
        SQL += grade;
        SQL += SQL_WHERE;
        SQL += gradedUserId;
        SQL += SQL_AND1;
        SQL += gradingUserId;
        SQL += SQL_AND2;
        SQL += nomService;
        SQL += SQL_FIN;
//System.out.println(" **addIntoCoteService** SQL is: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLgetACoter(String nomUtilisateur){
        String SQL;
        String SQL_DEBUT = "SELECT * FROM cotesServices WHERE gradingUserId = '";
        String SQL_AND = "' and cote = 'NULL'";
        String SQL_FIN = ";";

        SQL = SQL_DEBUT + nomUtilisateur + SQL_AND + SQL_FIN;
//System.out.println("    String SQL getACoter: " + SQL); // shows SQL String
        return SQL;
    }

    //*************************************************************************
    // level 3 abstraction
    private Utilisateur updateUtilisateurWithRSutilisateurData(
            Utilisateur U, ResultSet RSutilisateur) {

        try {
            RSutilisateur.beforeFirst();
            while (RSutilisateur.next()) {
                EvaluationUtilisateur E = new EvaluationUtilisateur(
                        RSutilisateur.getFloat("coteClient"), RSutilisateur.getInt("nbCoteClient"),
                        RSutilisateur.getFloat("coteFournisseur"),
                        RSutilisateur.getInt("nbCoteFournisseur"));
                Identifiant I = new Identifiant(RSutilisateur.getString("idUsager"),
                        RSutilisateur.getString("motDePasse"));
                Profile P = new Profile(RSutilisateur.getString("nom"),
                        RSutilisateur.getString("prenom"), RSutilisateur.getString("noTelephone"),
                        RSutilisateur.getString("courriel"));
                ArrayList<TypeServices> listServices = new ArrayList<>();
                ArrayList<String> listeCompetences = new ArrayList<>();
                U = new Utilisateur(I, P, listServices, listeCompetences, E);
                U.disponible = RSutilisateur.getByte("disponibilite") != 0;
            }
        } catch (Exception ex) {
            System.out.println(ex + "Error updating User with RSutilisateur");
        }
        return U;
    }

    private Utilisateur updateUtilisateurWithRSservicesData(
            Utilisateur U, ResultSet RSservices) {
        try {
            RSservices.beforeFirst();
            while (RSservices.next()) {
//System.out.println("la cote retourne par le serveur est:" + RSservices.getFloat("cote") + ".");
                EvaluationService E = new EvaluationService(RSservices.getFloat("cote"),
                        RSservices.getInt("nbCote"));
//System.out.println(E.coteService);
                TypeServices S = new TypeServices(RSservices.getFloat("prixHorraire"),
                        RSservices.getFloat("prixFixe"), RSservices.getString("nomService"),
                        RSservices.getByte("disponibilite") != 0, RSservices.getString("ville"),
                        RSservices.getString("noTelephone"), RSservices.getString("courriel"),
                        RSservices.getString("description"), E);
                U.listeServices.add(S);
            }
        } catch (Exception ex) {
            System.out.println(ex + "Error Adding servicesList to user");
        }
        return U;
    }

    private Utilisateur updateUtilisateurWithRScompetencesData(
            Utilisateur U, ResultSet RScompetences) {
        try {
            RScompetences.beforeFirst();
            while (RScompetences.next()) {
                String competence = RScompetences.getString("nomService");
                U.listeCompetences.add(competence);
            }
        } catch (Exception ex) {
            System.out.println(ex + "Error Adding competencesList to user");
        }
        return U;
    }

    private ArrayList<RechercheServices> updateUserAndSerivcesArrayWithRS(
            ResultSet RSservices){
        ArrayList<RechercheServices> userAndServicesArray = new ArrayList<>();
        // userId and services is in the RSservices
        float tauxHorraire, prixFixe;
        String nomService, ville, noTelephone, courriel, description;
        boolean disponible;
        byte cote;

// float tauxHorraire, float prixFixe, String nomSservice, boolean disponible, String ville,
//  byte cote, String noTelephone, String courriel, String description
// code example
        //Commenté par Francis Bernier parce que j'ai changer les attributs de l'objet de type RechercheServices
        /* try {
            RSservices.beforeFirst();
            while (RSservices.next()) {
                TypeServices S = new TypeServices(RSservices.getFloat("prixHorraire"),
                        RSservices.getFloat("prixFixe"), RSservices.getString("nomService"),
                        RSservices.getBoolean("disponibilite"), RSservices.getString("ville"),
                        RSservices.getString("noTelephone"), RSservices.getString("courriel"),
                        RSservices.getString("description"));
                RechercheServices P = new RechercheServices(getUser(RSservices.getString("idUsager")), S);
                userAndServicesArray.add(P);
            }
        } catch (Exception ex) {
            System.out.println(ex + "Error updating serviceSearchArray with RSservices");
        }
        return userAndServicesArray;
    }*/
        //Francis Bernier: nouvelle fonction avec nouveau attributs
        try {
            RSservices.beforeFirst();
            while (RSservices.next()) {
                RechercheServices P = new RechercheServices(getUser(RSservices.getString("idUsager")),
                        RSservices.getString("nomService"));
                userAndServicesArray.add(P);
            }
        } catch (Exception ex) {
            System.out.println(ex + "Error updating serviceSearchArray with RSservices");
        }
        return userAndServicesArray;
    }

    private ArrayList<RechercheACoter> updateACoterWithRS(ResultSet RSaCoter){
        ArrayList<RechercheACoter> aCoterArray = new ArrayList<RechercheACoter>();

        try {
            RSaCoter.beforeFirst();
            while (RSaCoter.next()) {
                RechercheACoter R = new RechercheACoter(RSaCoter.getString("gradedUserId"),
                        RSaCoter.getString("nomService"));
                aCoterArray.add(R);
            }
        } catch (Exception ex) {
            System.out.println(ex + "Error updating aCoterSearchArray with RSaCoter");
        }
        return aCoterArray;
    }
}