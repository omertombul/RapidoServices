package inm5001.rapidoservices.interfaceGraphique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by joy-reybabagbeto on 16-11-15.
 */

public class AfficherSupprimerService extends Activity {

    TextView nom = null;
    TextView taux = null;
    TextView  email = null;
    TextView telephone = null;
    TextView description = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supservice);

        Intent intent = getIntent();
        final String us = intent.getStringExtra("userName");
        final String se = intent.getStringExtra("electricien");



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Orchestrateur o = new Orchestrateur();
                Utilisateur u;
                try {
                    u = o.recupererUtilisateur(us);
                    System.out.println("**************** nom :" +u.profile.nom);
                    email = (TextView) findViewById(R.id.mail);
                    telephone = (TextView) findViewById(R.id.phone);
                    nom = (TextView) findViewById(R.id.nomServ);
                    taux = (TextView) findViewById(R.id.taux);
                    description = (TextView) findViewById(R.id.description);

                    for (int i = 0; i < u.listeServices.size(); i++) {

                        if (se.equals(u.listeServices.get(i))) {
                            nom.setText(u.listeServices.get(i).getNomSservice());

                            telephone.setText(u.listeServices.get(i).getNoTelephone());
                            email.setText(u.listeServices.get(i).getCourriel());
                            description.setText(u.listeServices.get(i).getDescription());

                        }
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

    }
}
