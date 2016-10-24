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

    String nom;
    String prenom;
    String noTelephonProfile;
    String adresseCourrielProfil;
    String nomUtilisteur;
    String motDePasse;
    Utilisateur user;
    Identifiant identifiant;
    Profile profile;
    ArrayList<AbstraiteServices> listeServices;
    ArrayList<String> listeCompetences;
    AlertDialog adb = new AlertDialog.Builder(this).create();



   // username = (EditText)findViewById(R.id.editText);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        sEnregistrer = (Button)findViewById(R.id.inscrire);

        sEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination

                try {
                    nom = findViewById(R.id.nom).toString();
                    prenom = findViewById(R.id.prenoms).toString();
                    noTelephonProfile = findViewById(R.id.noTelephone).toString();
                    adresseCourrielProfil = findViewById(R.id.email).toString();
                    nomUtilisteur = findViewById(R.id.username).toString();
                    motDePasse = findViewById(R.id.password).toString();
                    identifiant = new Identifiant(nomUtilisteur, motDePasse);
                    profile = new Profile(nom, prenom, noTelephonProfile, adresseCourrielProfil);
                    user = new Utilisateur(identifiant,profile,listeServices,listeCompetences);
                }catch(MyException e){
                    adb.setTitle("Alert Error");
                    adb.setMessage(e.getMessage());
                    adb.show();
                }
                //Orchestrateur or = new Orchestrateur();
                //or.creerUtilisateur();

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
