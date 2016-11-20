package inm5001.rapidoservices.interfaceGraphique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import inm5001.rapidoservices.R;
import inm5001.rapidoservices.Recherche;


/**
 * Created by joy-reybabagbeto on 16-11-18.
 */

public class ServiceRechercherAvtivity extends Activity {

    TextView nomService = null;
    TextView villeService = null;
    TextView tauxHorraire = null;
    TextView description = null;
    TextView titreTrouver = null;
    Button rate = null;
    Button ratingStars = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicerechercher);


        //recuper objet recherche de l'activite RecherActivity
        Intent intent = getIntent();
        String nomS = intent.getStringExtra("nomService");
        String villeS = intent.getStringExtra("villeService");
        String tauxH = intent.getStringExtra("taux");
        String descrip = intent.getStringExtra("description");
        String userName = intent.getStringExtra("userName");
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











    }
}
