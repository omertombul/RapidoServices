package inm5001.rapidoservices.interfaceGraphique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.recherche.RechercheServices;
import inm5001.rapidoservices.service.EvaluationService;
import inm5001.rapidoservices.utilisateur.Utilisateur;


/**
 * Created by joy-reybabagbeto on 16-11-18.
 * and Omer Tombul
 */

public class ServiceRechercherAvtivity extends Activity {

    TextView nomService = null;
    TextView villeService = null;
    TextView tauxHorraire = null;
    TextView description = null;
    TextView noTel = null;
    TextView idFournisseur = null;
    TextView courriel = null;
    Button accepter = null;
    Button cancel = null;
    RatingBar ratingStars = null;
    ArrayList<String> resultat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicerechercher);


        //recuper les champs recherche de l'activite RecherActivity
        Intent intent = getIntent();
        final String nomS = intent.getStringExtra("nomService");
        String villeS = intent.getStringExtra("villeService");
        String tauxH = intent.getStringExtra("taux");
        String descrip = intent.getStringExtra("description");
        Float evalFloat = intent.getFloatExtra("rating",0.0f);
        final String userNameService = intent.getStringExtra("userNameService");
        final String userName = intent.getStringExtra("userName");


        nomService = (TextView) findViewById(R.id.textViewAfficheNomTrouver);
        nomService.setText(nomS);
        villeService = (TextView) findViewById(R.id.textViewAfficheVilleTrouver);
        villeService.setText(villeS);
        tauxHorraire = (TextView) findViewById(R.id.textViewAfficheTauxTrouver);
        tauxHorraire.setText(tauxH);
        description = (TextView) findViewById(R.id.textViewAfficheDescriTrouver);
        description.setText(descrip);



        accepter = (Button)findViewById(R.id.buttonRateTrouver);
        ratingStars = (RatingBar)findViewById(R.id.ratingBarTrouver);
        ratingStars.setRating(evalFloat);

        cancel = (Button) findViewById(R.id.buttonCancelTrouver);





        accepter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Orchestrateur o = new Orchestrateur();
                        Utilisateur u;

                        try {
                            u = o.recupererUtilisateur(userNameService);

                            noTel = (TextView) findViewById(R.id.noTel);
                            courriel = (TextView) findViewById(R.id.courrier);
                            idFournisseur = (TextView) findViewById(R.id.userServiceRechercer);

                            RechercheServices rService = new RechercheServices(u,nomS);
                            resultat = o.accepterUnFournisseurDeService(rService, userName);
                            noTel.setText("Telephone : " + resultat.get(0));
                            idFournisseur.setText("Fournisseur : "+ userNameService);
                            courriel.setText("Courriel : " + resultat.get(1));
                            accepter.setVisibility(View.GONE);

                        }catch(SQLException e){
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            System.out.println(e.getMessage());
                        }catch(MyException e){
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            System.out.println(e.getMessage());
                        }


                    }
                });



            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profilActivity = new Intent(ServiceRechercherAvtivity.this, ProfilActivity.class);
                profilActivity.putExtra("userName",userName);
                startActivity(profilActivity);
            }
        });




    }
}
