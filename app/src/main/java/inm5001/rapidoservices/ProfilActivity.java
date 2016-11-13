package inm5001.rapidoservices;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.content.Intent;

        import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by joy-reybabagbeto on 16-10-24.
 */

public class ProfilActivity extends Activity {
    TextView nom = null;
    TextView prenom = null;
    TextView courriel = null;
    TextView telephone = null;
    Button ajouter = null;
    Button rechercher = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ajouter = (Button)findViewById(R.id.ajouter);
        rechercher = (Button)findViewById(R.id.rechercher);

        //recuper le userName et password de la page precedente
        Intent intent = getIntent();
        final String userName = intent.getStringExtra("userName");
        String pass = intent.getStringExtra("password");


        // lance un thread qui recupere l'info de l'utilisateur de la bd
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Orchestrateur o = new Orchestrateur();
                Utilisateur u ;
                try {
                    u = o.recupererUtilisateur(userName);
                    courriel = (TextView)findViewById(R.id.courrielProfil);
                    telephone = (TextView)findViewById(R.id.telProfil);
                    nom = (TextView)findViewById(R.id.nomProfil);
                    prenom = (TextView)findViewById(R.id.prenomProfil);
                    nom.setText(u.profile.nom);
                    prenom.setText(u.profile.prenom);
                    courriel.setText(u.profile.adresseCourriel);
                    telephone.setText(u.profile.numeroTelephone);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        });



        //listner sur le boutton ajouter
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ajouterService = new Intent(ProfilActivity.this, AjouterServiceActivity.class);
                System.out.println("Username dans profile Activity " + userName);
                ajouterService.putExtra("userName",userName);
                startActivity(ajouterService);


            }
        });

        //listner sur le boutton rechercher
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rechercherService = new Intent(ProfilActivity.this, RechercheActivity.class);
                startActivity(rechercherService);
            }
        });





    }
}
