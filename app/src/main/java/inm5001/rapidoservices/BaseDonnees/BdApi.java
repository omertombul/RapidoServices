package inm5001.rapidoservices.baseDonnees;

import java.sql.ResultSet;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Recherche;
import inm5001.rapidoservices.service.EvaluationService;
import inm5001.rapidoservices.utilisateur.EvaluationUtilisateur;
import inm5001.rapidoservices.utilisateur.Utilisateur;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.service.AbstraiteServices;
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

    public ArrayList<Recherche> servicesSearch(TypeServices s, float coteUtilisateur,
                                               float coteServicesMoyenne, float coteService){
        ArrayList<Recherche> UserAndServicesArray = new ArrayList<>();


        String SQL = SQLservicesSearch(s);
        BdConnection DB = new BdConnection(SQL);
        ResultSet RSservices = DB.readFromDataBase();
        UserAndServicesArray = updateUserAndSerivcesArrayWithRS(RSservices);
        DB.closeConnection();
        return UserAndServicesArray;
    }

    public void gradeService(String gradedUserId, String gradingUserId, String nomService,
                             float grade) throws SQLException{
        String SQL = SQLgradeService(gradedUserId, gradingUserId, nomService, grade);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
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
        SQL += "1" + SQL_SEPARATEUR;  // eval
        SQL += "1" + SQL_SEPARATEUR;         // geo coordonnees
        SQL += U.profile.numeroTelephone + SQL_FIN;
//System.out.println("    String SQL addUser: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLgetUser(String nomUtilisateur) {
        String SQL;
        String SQL_DEBUT = "SELECT * FROM utilisateur WHERE idUsager = '";
        String SQL_FIN = "';";
        SQL = SQL_DEBUT + nomUtilisateur + SQL_FIN;
//System.out.println("    String SQL getUser: " + SQL); // shows SQL String
        return SQL;
    }

    private String SQLgetServices(Utilisateur U) {
        String SQL;
        String SQL_DEBUT = "SELECT * FROM servicesDUsager WHERE idUsager = '";
        String SQL_FIN = "';";
//SQL = SQL_DEBUT + "Francis" + SQL_FIN; //U.identifiant.nomUtilisateur + SQL_FIN; // shows SQL String
        SQL = SQL_DEBUT + U.identifiant.nomUtilisateur + SQL_FIN;
        System.out.println("    String SQL getServiceUtilisateur: " + SQL);
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
        System.out.println("********************************: " + S.getEvaluationService().coteService);
        SQL += S.getEvaluationService().coteService + SQL_SEPARATEUR;
        SQL += S.getDescription() + SQL_FIN;
// System.out.println("    String SQL addService Usager: " + SQL); // shows SQL String
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

    private String SQLgradeService(String gradedUserId, String gradingUserId, String nomService,
                                   float grade){
        String SQL;
        String SQL_DEBUT = "INSERT INTO cotesServices VALUES('";
        String SQL_SEPARATEUR = "' ,'";
        String SQL_FIN = "');";
        SQL = SQL_DEBUT;
        SQL += gradedUserId + SQL_SEPARATEUR;
        SQL += gradingUserId + SQL_SEPARATEUR;
        SQL += nomService + SQL_SEPARATEUR;
        SQL += grade + SQL_FIN;
//System.out.println(" **gradeService** SQL is: " + SQL); // shows SQL String
        return SQL;
    }

    //*************************************************************************
    // level 3 abstraction
    private Utilisateur updateUtilisateurWithRSutilisateurData(
            Utilisateur U, ResultSet RSutilisateur) {

        try {
            RSutilisateur.beforeFirst();
            while (RSutilisateur.next()) {
                Identifiant I = new Identifiant(RSutilisateur.getString("idUsager"),
                        RSutilisateur.getString("motDePasse"));
                Profile P = new Profile(RSutilisateur.getString("nom"),
                        RSutilisateur.getString("prenom"), RSutilisateur.getString("noTelephone"),
                        RSutilisateur.getString("courriel"));
                ArrayList<TypeServices> listServices = new ArrayList<>();
                ArrayList<String> listeCompetences = new ArrayList<>();
                U = new Utilisateur(I,P,listServices,listeCompetences);
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
                TypeServices S = new TypeServices(RSservices.getFloat("prixHorraire"),
                        RSservices.getFloat("prixFixe"), RSservices.getString("nomService"),
                        RSservices.getByte("disponibilite") != 0, RSservices.getString("ville"),
                        RSservices.getString("noTelephone"), RSservices.getString("courriel"),
                        RSservices.getString("description"));
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

    private ArrayList<Recherche> updateUserAndSerivcesArrayWithRS(
            ResultSet RSservices){
        ArrayList<Recherche> userAndServicesArray = new ArrayList<>();
        // userId and services is in the RSservices
        float tauxHorraire, prixFixe;
        String nomService, ville, noTelephone, courriel, description;
        boolean disponible;
        byte cote;

// float tauxHorraire, float prixFixe, String nomSservice, boolean disponible, String ville,
//  byte cote, String noTelephone, String courriel, String description
// code example
       //Commenté par Francis Bernier parce que j'ai changer les attributs de l'objet de type Recherche
        /* try {
            RSservices.beforeFirst();
            while (RSservices.next()) {
                TypeServices S = new TypeServices(RSservices.getFloat("prixHorraire"),
                        RSservices.getFloat("prixFixe"), RSservices.getString("nomService"),
                        RSservices.getBoolean("disponibilite"), RSservices.getString("ville"),
                        RSservices.getString("noTelephone"), RSservices.getString("courriel"),
                        RSservices.getString("description"));
                Recherche P = new Recherche(getUser(RSservices.getString("idUsager")), S);
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
                Recherche P = new Recherche(getUser(RSservices.getString("idUsager")), RSservices.getString("nomService"));
                userAndServicesArray.add(P);
            }
        } catch (Exception ex) {
            System.out.println(ex + "Error updating serviceSearchArray with RSservices");
        }
        return userAndServicesArray;
    }
}