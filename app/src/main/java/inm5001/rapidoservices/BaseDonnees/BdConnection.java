package inm5001.rapidoservices.baseDonnees;

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
        Connection conn = makeConnection();
        Statement stmt = makeStatement();
    }

    private Connection makeConnection() {

        /*
        // connect to Omer MySQL
        String url = "jdbc:mysql://localhost:8889/services";
        String usr = "root";
        String psw = "root";
         */
        // connect to UQAM MySQL
        String url = "jdbc:mysql://127.0.0.1/bd_ak791165?:3306";
        String usr = "ak791165";
        String psw = "uWoavCsH";

        // register MySQL Connector/J with the DriverManager
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Error Connecting.");
        }
        // once the driver is registered, make connection
        try {
            conn
                    = DriverManager.getConnection(url, usr, psw);
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
        System.out.println("Start Insert data into DB");
        try {
            System.out.println("    SQL string is: " + SQL);
            stmt.executeUpdate(SQL);
        } catch (SQLException ex) {
            System.out.println(ex + "    Error Putting new Data into DB");
        }
        System.out.println("End Insert data into DB");
    }

    public ResultSet readOrDeleteInDataBase() {
        System.out.println("Start get RS");
        try {
            System.out.println("SQL string is: " + SQL);
            rs = stmt.executeQuery(SQL);
        } catch (SQLException ex) {
            System.out.println(ex + "    Error Getting Data: rs");
        }
        System.out.println("End get RS");
        return rs;
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
