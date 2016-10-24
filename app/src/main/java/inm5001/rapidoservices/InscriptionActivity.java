package inm5001.rapidoservices;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;


        import java.util.ArrayList;

        import inm5001.rapidoservices.service.AbstraiteServices;
        import inm5001.rapidoservices.service.TypeServices;
        import inm5001.rapidoservices.utilisateur.Identifiant;
        import inm5001.rapidoservices.utilisateur.Profile;
        import inm5001.rapidoservices.utilisateur.Utilisateur;

//import Orchestrateur.java;
public class InscriptionActivity extends Activity {
    Button sEnregistrer = null;

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

   // AlertDialog adb = new AlertDialog.Builder(this).create();



   // username = (EditText)findViewById(R.id.editText);




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
        dlgAlert.setTitle("App Title");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

        sEnregistrer.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination

                try {
                    identifiant = new Identifiant(nomUtilisteur.getText().toString(), motDePasse.getText().toString());
                    profile = new Profile(nom.getText().toString(), prenom.getText().toString(), noTelephonProfile.getText().toString(), adresseCourrielProfil.getText().toString());
                    user = new Utilisateur(identifiant,profile,listeServices,listeCompetences);

                }catch(MyException e){
                  //  adb.setTitle("Alert Error");
                   // adb.setMessage(e.getMessage());
                   // adb.show();

                    System.out.println(e);
                }
                //Orchestrateur or = new Orchestrateur();
                //or.creerUtilisateur();
                System.out.println("Apres le try");

                Intent troisiemeActivite = new Intent(InscriptionActivity.this, ProfilActivity.class);

                // On rajoute un extra
                //secondeActivite.putExtra(AGE, 31);

                // Puis on lance l'intent !
                //
                startActivity(troisiemeActivite);
            }
        });
    }
}
