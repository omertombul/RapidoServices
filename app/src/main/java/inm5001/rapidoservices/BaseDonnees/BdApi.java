package inm5001.rapidoservices.baseDonnees;

import java.sql.ResultSet;

import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by Admin on 2016-10-22.
 */
//aa
public class BdApi {

    public BdApi() {
        //System.out.println("Start: Constructeur Api");
    }

    public void addUser(Utilisateur U) {
        System.out.println("Debut addUser()");
        String SQL = SQLaddUser(U);
        BdConnection DB = new BdConnection(SQL);
        DB.insertToDB();
        DB.closeConnection();
    }

    public void getUser(Utilisateur U) {
        System.out.println("Debut construction String SQL: get User");
        String SQL = SQLgetUser(U);
        BdConnection DB = new BdConnection(SQL);
        ResultSet RSutilisateur = DB.readFromDataBase();
        updateUtilisateurWithRSutilisateurData(U, RSutilisateur);
        DB.closeConnection();

        SQL = SQLgetServices(U);
        DB = new BdConnection(SQL);
        ResultSet RSservices = DB.readFromDataBase();
        updateUtilisateurWithRSservicesData(U, RSservices);
        DB.closeConnection();

        SQL = SQLgetCompetences(U);
        DB = new BdConnection(SQL);
        ResultSet RScompetences = DB.readFromDataBase();
        updateUtilisateurWithRScompetencesData(U, RScompetences);
        DB.closeConnection();

        updateUtilisateurWithRScompetencesData(U, RScompetences);
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
        System.out.println("    String SQL addUser: " + SQL);
        return SQL;
    }

    private String SQLgetUser(Utilisateur U) {
        String SQL;
        String SQL_DEBUT = "SELECT * FROM utilisateur WHERE idUsager = ";
        String SQL_FIN = ";";
        SQL = SQL_DEBUT + U.identifiant.nomUtilisateur + SQL_FIN;
        System.out.println("    String SQL getUser: " + SQL);
        return SQL;
    }

    private String SQLgetServices(Utilisateur U) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String SQLgetCompetences(Utilisateur U) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Utilisateur updateUtilisateurWithRS(
            Utilisateur U, ResultSet RSutilisateur, ResultSet RSservices,
            ResultSet RScompetences) {
        updateUtilisateurWithRSutilisateurData(U, RSutilisateur);
        //updateUtilisateurWithRSservicesData(U, RSservices);
        //updateUtilisateurWithRScompetencesData(U, RScompetences);
        return U;
    }

    //*************************************************************************
    // level 3 abstraction
    private Utilisateur updateUtilisateurWithRSutilisateurData(
            Utilisateur U, ResultSet RSutilisateur) {
        try {
            RSutilisateur.beforeFirst();
            while (RSutilisateur.next()) {
                U.identifiant.motDePasse = RSutilisateur.getString("motDePasse");
                U.profile.nom = RSutilisateur.getString("nom");
                U.profile.prenom = RSutilisateur.getString("prenom");
                U.profile.numeroTelephone = RSutilisateur.getString("noTelephone");
                U.profile.adresseCourriel = RSutilisateur.getString("courriel");
            }
        } catch (Exception ex) {
            System.out.println(ex + "Error updating User with RSutilisateur");
        }
        return U;
    }

    private void updateUtilisateurWithRSservicesData(
            Utilisateur U, ResultSet RSservices) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // TODO implement this function: need to know how service ArrayList workds
    }

    private void updateUtilisateurWithRScompetencesData(
            Utilisateur U, ResultSet RScompetences) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // TODO implement this function: need to know how competence ArrayList workds
    }
}