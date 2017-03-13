package inm5001.rapidoservices.interfaceGraphique;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import java.util.ArrayList;

        import inm5001.rapidoservices.MyException;
        import inm5001.rapidoservices.Orchestrateur;
        import inm5001.rapidoservices.R;
        import inm5001.rapidoservices.service.AbstraiteServices;
        import inm5001.rapidoservices.service.TypeServices;
        import inm5001.rapidoservices.utilisateur.Identifiant;
        import inm5001.rapidoservices.utilisateur.Profile;
        import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * @author Omer Tombul
 */


//import Orchestrateur.java;
public class InscriptionActivity extends Activity {
    Button sEnregistrer = null;
    String key_userName = "userName";
    EditText nom  = null;
    EditText prenom =  null;
    EditText noTelephonProfile =null;
    EditText adresseCourrielProfil = null;
    EditText nomUtilisteur = null;
    EditText motDePasse = null;
    Utilisateur user;
    Identifiant identifiant;
    Profile profile;
    ArrayList<TypeServices> listeServices;
    ArrayList<String> listeCompetences;
    AlertDialog.Builder dlgAlert  ;
    Orchestrateur orchestrateur;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        //Les champs a remplir
        sEnregistrer = (Button)findViewById(R.id.inscrire);
        nom  = (EditText)findViewById(R.id.nom);
        prenom =  (EditText)findViewById(R.id.prenoms);
        noTelephonProfile = (EditText)findViewById(R.id.noTelephone);
        adresseCourrielProfil = (EditText)findViewById(R.id.email);
        nomUtilisteur = (EditText)findViewById(R.id.username);
        motDePasse = (EditText)findViewById(R.id.password);

        //Dialog box pour erreur dans les champs
        dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setTitle("Erreur dans un des champs!");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);

        //Permissions de connection
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //listener sur le boutton s'enregistrer
        sEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    System.out.println("no tel "+ noTelephonProfile.getText().toString());

                    //Creation des Objets pour utilisateurs
                    identifiant = new Identifiant(nomUtilisteur.getText().toString(), motDePasse.getText().toString());
                    profile = new Profile(nom.getText().toString(), prenom.getText().toString(), noTelephonProfile.getText().toString(), adresseCourrielProfil.getText().toString());
                    user = new Utilisateur(identifiant,profile,listeServices,listeCompetences);

                    //appelle de la fonction qui creer l'utilisateur dans la BaseDonnee
                    orchestrateur = new Orchestrateur();
                    orchestrateur.creerUtilisateur(user);

                    //Creation du intent qui va appeler la page profile
                    Intent profile = new Intent(InscriptionActivity.this, ProfilActivity.class);
                    //ajout user name qui sera transferer dans la prochaine page qui est profile
                    profile.putExtra(key_userName,nomUtilisteur.getText().toString());
                    //lance la prochaine activite profile
                    startActivity(profile);

                }catch(MyException e){
                    dlgAlert.setMessage(e.getMessage());
                    dlgAlert.create().show();

                }
            }
        });

    }
}
