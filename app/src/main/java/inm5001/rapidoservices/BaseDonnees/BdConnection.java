package inm5001.rapidoservices.BaseDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BdConnection {

    // class Vars
    Connection conn = null;
    Statement stmt = null;
    String SQL = null;
    ResultSet rs = null;

    /**
     * constructeur
     */
    public BdConnection(String SQL) {
        this.SQL = SQL;

        conn = makeConnection();

        if(conn == null){
            System.out.println("REtour de connection *************** ");
        }
        stmt = makeStatement();
    }

    private Connection makeConnection() {

        // connect to Server MySQL
        String url = "jdbc:mysql://138.197.137.28:3306/inm5001?useSSL=false";
        String usr = "inm5001";
        String psw = "inm5001";

        // connect to UQAM MySQL
      /*  String url = "jdbc:mysql://127.0.0.1/bd_ak791165?:3306";
        String usr = "ak791165";
        String psw = "uWoavCsH";
*/
        // register MySQL Connector/J with the DriverManager
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Error Connecting.");
        }
        // once the driver is registered, make connection
        try {
            conn = DriverManager.getConnection(url, usr, psw);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        System.out.println("Made Connection");
        return conn;
    }

    private Statement makeStatement() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex + "Error making DB statement");
        }
        return stmt;
    }

    public void insertToDB() {
        try {
            stmt.executeUpdate(SQL);
        } catch (SQLException ex) {
            System.out.println(ex + "    Error Putting new Data into DB");
        }
    }

    public ResultSet readFromDataBase() {
        try {
            rs = stmt.executeQuery(SQL);
        } catch (SQLException ex) {
            System.out.println(ex + "    Error Getting Data: rs");
        }
        return rs;
    }

    public void deleteInDataBase() {
        try {
            stmt.executeUpdate(SQL);
        } catch (SQLException ex) {
            System.out.println(ex + "    Error Deleting From DATAbase");
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex + "Error Closing Connection");
        }
        System.out.println("Connection closed");
    }
}