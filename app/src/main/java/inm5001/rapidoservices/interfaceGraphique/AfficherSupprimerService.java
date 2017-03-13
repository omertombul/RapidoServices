package inm5001.rapidoservices.interfaceGraphique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.sql.SQLException;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by joy-reybabagbeto on 16-11-15.
 */

public class AfficherSupprimerService extends Activity {

    TextView nom = null;
    TextView description = null;
    TextView taux = null;
    TextView mail = null;
    TextView telephone = null;
    Button supprimer = null;
    ToggleButton dispo = null;
    Orchestrateur orchestrateur = null;
    Utilisateur user;
    RatingBar rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supservice);

        //recupere info de l'activite precedente
        Intent intent = getIntent();
        final String uName = intent.getStringExtra("userName");
        final String service = intent.getStringExtra("service");


        supprimer = (Button) findViewById(R.id.supprimerService);





        runOnUiThread(new Runnable() {
            @Override
            public void run() {
              orchestrateur = new Orchestrateur();

                
                try {

                    user = orchestrateur.recupererUtilisateur(uName);


                  System.out.println("**************** nom :" + user.profile.nom);
                    nom = (TextView) findViewById(R.id.nomServ);
                    description = (TextView) findViewById(R.id.description);
                    mail = (TextView) findViewById(R.id.mail);
                    taux = (TextView) findViewById(R.id.taux);
                    telephone = (TextView) findViewById(R.id.phone);
                    rate = (RatingBar) findViewById(R.id.ratingBarServiceOffert);
                    dispo = (ToggleButton) findViewById(R.id.toggleDispoService);
                    String tauxH = "0.0";


                    for (int i = 0; i < user.listeServices.size(); i++)
                    {


                        if (user.listeServices.get(i).getNomSservice().equals(service) ) {


                            tauxH = Float.toString(user.listeServices.get(i).getTauxHorraire());
                            nom.setText(user.listeServices.get(i).getNomSservice());
                            taux.setText(tauxH);
                            description.setText(user.listeServices.get(i).getDescription());
                            dispo.setChecked(user.listeServices.get(i).getDisponible());
                            mail.setText(user.listeServices.get(i).getCourriel());
                            telephone.setText(user.listeServices.get(i).getNoTelephone());
                            rate.setRating(user.listeServices.get(i).evaluationService.coteService);




                        }

                    }

                } catch (Exception e) {
                    System.out.println("Dans le try catch du for ******");
                    System.out.println(e.getMessage());
                }
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


                try {
                    for (int i = 0; i < user.listeServices.size(); i++)
                    {

                        if (user.listeServices.get(i).getNomSservice().equals(service) ){

                            orchestrateur.retirerOffreDeService(uName, user.listeServices.get(i));
                        }
                    }

                    Intent profil = new Intent(AfficherSupprimerService.this, ProfilActivity.class);
                    profil.putExtra("userName",uName);
                    startActivity(profil);
                }catch(MyException e){
                    System.out.println(e.getMessage());
                }
            }
        });

        dispo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    try {
                        // The toggle is enabled
                        orchestrateur.modifierDisponibiliteService(uName, service, true);
                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                } else {

                    try{
                        orchestrateur.modifierDisponibiliteService(uName, service, false);
                    }catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
    }
}
