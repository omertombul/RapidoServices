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

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.service.EvaluationService;


/**
 * Created by joy-reybabagbeto on 16-11-18.
 * and Omer Tombul
 */

public class ServiceRechercherAvtivity extends Activity {

    TextView nomService = null;
    TextView villeService = null;
    TextView tauxHorraire = null;
    TextView description = null;
    TextView titreTrouver = null;
    Button rate = null;
    Button cancel = null;
    RatingBar ratingStars = null;
    EvaluationService eval = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicerechercher);


        //recuper objet recherche de l'activite RecherActivity
        Intent intent = getIntent();
        final String nomS = intent.getStringExtra("nomService");
        String villeS = intent.getStringExtra("villeService");
        String tauxH = intent.getStringExtra("taux");
        String descrip = intent.getStringExtra("description");
        final String userNameService = intent.getStringExtra("userNameService");
        final String userName = intent.getStringExtra("userName");

        final Orchestrateur orc = new Orchestrateur();

        nomService = (TextView) findViewById(R.id.textViewAfficheNomTrouver);
        nomService.setText(nomS);
        villeService = (TextView) findViewById(R.id.textViewAfficheVilleTrouver);
        villeService.setText(villeS);
        tauxHorraire = (TextView) findViewById(R.id.textViewAfficheTauxTrouver);
        tauxHorraire.setText(tauxH);
        description = (TextView) findViewById(R.id.textViewAfficheDescriTrouver);
        description.setText(descrip);
        titreTrouver = (TextView) findViewById(R.id.textViewTitleActiviteTrouver);
        titreTrouver.setText(nomS);

        rate = (Button)findViewById(R.id.buttonRateTrouver);
        ratingStars = (RatingBar)findViewById(R.id.ratingBarTrouver);
        eval = new EvaluationService();
        cancel = (Button) findViewById(R.id.buttonCancelTrouver);





        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            orc.faireUneEvaluation(userNameService, userName, nomS, ratingStars.getRating());
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
                startActivity(profilActivity);
            }
        });




    }
}
