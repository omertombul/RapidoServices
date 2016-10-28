package inm5001.rapidoservices;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;

        import java.util.ArrayList;

        import inm5001.rapidoservices.BaseDonnees.BdApi;
        import inm5001.rapidoservices.service.AbstraiteServices;
        import inm5001.rapidoservices.service.TypeServices;
        import inm5001.rapidoservices.utilisateur.Identifiant;
        import inm5001.rapidoservices.utilisateur.Profile;
        import inm5001.rapidoservices.utilisateur.Utilisateur;

//import Orchestrateur.java;
public class InscriptionActivity extends Activity {
    Button sEnregistrer = null;
    String key_prenom = "prenom";
    String key_nom = "nom";
    String key_userName = "username";
    String key_email = "email";
    EditText nom  = null;
    EditText prenom =  null;
    EditText noTelephonProfile =null;
    EditText adresseCourrielProfil = null;
    EditText nomUtilisteur = null;
    EditText motDePasse = null;
    Utilisateur user;
    Identifiant identifiant;
    Profile profile;
    ArrayList<AbstraiteServices> listeServices;
    ArrayList<String> listeCompetences;
    AlertDialog.Builder dlgAlert  ;
    Orchestrateur orchestrateur;

    Connection conn = null;
    Statement stmt = null;
    String SQL = null;
    ResultSet rs = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        sEnregistrer = (Button)findViewById(R.id.inscrire);
        nom  = (EditText)findViewById(R.id.nom);
        prenom =  (EditText)findViewById(R.id.prenoms);
        noTelephonProfile = (EditText)findViewById(R.id.noTelephone);
        adresseCourrielProfil = (EditText)findViewById(R.id.email);
        nomUtilisteur = (EditText)findViewById(R.id.username);
        motDePasse = (EditText)findViewById(R.id.password);
        dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Erreur dans un des champs");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        sEnregistrer.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination

                try {
                    identifiant = new Identifiant(nomUtilisteur.getText().toString(), motDePasse.getText().toString());
                    profile = new Profile(nom.getText().toString(), prenom.getText().toString(), noTelephonProfile.getText().toString(), adresseCourrielProfil.getText().toString());
                    user = new Utilisateur(identifiant,profile,listeServices,listeCompetences);
                    orchestrateur = new Orchestrateur();
                    System.out.println(user.identifiant.nomUtilisateur);
                    orchestrateur.creerUtilisateur(user);
                    //BdApi bd = new BdApi();
                    //bd.addUser(user);
                   // testBd("INSERT INTO utilisateur VALUES('Name' ,'Allo123!' ,'NAME' ,'NAME' ,'a@a.com' ,'1' ,'1' ,'1' ,'5142321212');");
                    Intent troisiemeActivite = new Intent(InscriptionActivity.this, ProfilActivity.class);
                    System.out.println(nomUtilisteur.getText().toString());
                    troisiemeActivite.putExtra(key_userName,nomUtilisteur.getText().toString());
                    troisiemeActivite.putExtra(key_email,adresseCourrielProfil.getText().toString());
                    troisiemeActivite.putExtra(key_nom, nom.getText().toString());
                    troisiemeActivite.putExtra(key_prenom, prenom.getText().toString());
                    startActivity(troisiemeActivite);

                }catch(MyException e){

                    dlgAlert.setTitle(e.getMessage());
                    dlgAlert.create().show();
                    System.out.println(e);
                }

                System.out.println("Apres le try");


            }
        });
    }


    public void testBd(String sq){
            SQL = sq;
            conn = makeConnection();
            stmt = makeStatement();

    }

    private Connection makeConnection() {

        // connect to Omer MySQL
        String url = "jdbc:mysql://localhost:8889/service";
        String usr = "root";
        String psw = "root";

        // connect to UQAM MySQL
        //String url = "jdbc:mysql://127.0.0.1/bd_ak791165?:3306";
        //String usr = "ak791165";
        //String psw = "uWoavCsH";

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
