package inm5001.rapidoservices.baseDonnees;

import java.sql.ResultSet;

import inm5001.rapidoservices.utilisateur.Utilisateur;
import inm5001.rapidoservices.utilisateur.Identifiant;
import inm5001.rapidoservices.utilisateur.Profile;
import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.TypeServices;
import java.util.ArrayList;

/**
 * Created by Admin on 2016-10-22.
 */
//
public class BdApi {

    public BdApi() {
        //System.out.println("Start: Constructeur Api");
    }

    public void addUser(Utilisateur U) {
        String SQL = SQLaddUser(U);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
    }

    public Utilisateur getUser(String nomUtilisateur) {
        Utilisateur U = new Utilisateur();//null;
        System.out.println("Debut construction String SQL: get User");

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

    public void addServiceUser(String nomUtilisateur, TypeServices S){
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

    public void updateUserDisponibilite(String nomUtilisateur, String disponibilite){
        String SQL = SQLchangeUserDisponibilite(nomUtilisateur, disponibilite);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
    }

    public void updateServiceDisponibilite(String nomUtilisateur, String nomService, String
            disponibilite){
        String SQL = SQLchangeServiceDisponibilite(nomUtilisateur, nomService,disponibilite);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
    }

    public void addCompetenceUser(String nomUtilisateur, TypeServices s){
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

    //*************************************************************************
    // level 2 abstraction
    private String SQLaddUser(Utilisateur U) {
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
        SQL += "1" + SQL_SEPARATEUR;  // dispo
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
        String SQL;
        String SQL_DEBUT = "INSERT INTO servicesDUsager VALUES('";
        String SQL_SEPARATEUR = "' ,'";
        String SQL_FIN = "');";
        String SQL_DEBUT_COMPETENCE = " INSERT INTO competences VALUES('";
        SQL = SQL_DEBUT;
        SQL += nomUtilisateur + SQL_SEPARATEUR;
        SQL += S.getNomSservice() + SQL_SEPARATEUR;
        SQL += "0" + SQL_SEPARATEUR;              // duree
        SQL += S.isDisponible() + SQL_SEPARATEUR;
        SQL += "0" + SQL_SEPARATEUR;          // reservation
        SQL += S.getPrixFixe() + SQL_SEPARATEUR;
        SQL += S.getTauxHorraire() + SQL_SEPARATEUR;
        SQL += S.getVille() + SQL_SEPARATEUR;
        SQL += S.getNoTelephone() + SQL_SEPARATEUR;
        SQL += S.getCourriel() + SQL_SEPARATEUR;
        SQL += S.getCote() + SQL_SEPARATEUR;
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
        String SQL_SEPARATEUR = "' ,'";
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
                ArrayList<AbstraiteServices> listServices = new ArrayList<>();
                ArrayList<String> listeCompetences = new ArrayList<>();
                U = new Utilisateur(I,P,listServices,listeCompetences);
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
                AbstraiteServices S = new TypeServices(RSservices.getFloat("prixHorraire"),
                        RSservices.getFloat("prixFixe"), RSservices.getString("nomService"),
                        RSservices.getByte("disponibilite"), RSservices.getString("ville"),
                        RSservices.getByte ("cote"), RSservices.getString("noTelephone"),
                        RSservices.getString("courriel"), RSservices.getString("description"));
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
}
