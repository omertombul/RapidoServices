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

        import inm5001.rapidoservices.baseDonnees.BdApi;
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

                    Intent troisiemeActivite = new Intent(InscriptionActivity.this, ProfilActivity.class);

                    troisiemeActivite.putExtra(key_userName,nomUtilisteur.getText().toString());
                    troisiemeActivite.putExtra(key_email,adresseCourrielProfil.getText().toString());
                    troisiemeActivite.putExtra(key_nom, nom.getText().toString());
                    troisiemeActivite.putExtra(key_prenom, prenom.getText().toString());
                    startActivity(troisiemeActivite);

                }catch(MyException e){

                    dlgAlert.setTitle(e.getMessage());
                    dlgAlert.create().show();
                   
                }

                System.out.println("Apres le try");


            }
        });
    }
}
