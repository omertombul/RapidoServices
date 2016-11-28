package inm5001.rapidoservices.interfaceGraphique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supservice);
        Intent intent = getIntent();
        final String us = intent.getStringExtra("userName");

        final String service = intent.getStringExtra("service");
        supprimer = (Button) findViewById(R.id.supprimerService);
        dispo = (ToggleButton) findViewById(R.id.toggleDispoService);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Orchestrateur o = new Orchestrateur();

                
                try {
                    user = o.recupererUtilisateur(us);
                    orchestrateur = o;
                    System.out.println("**************** nom :" +user.profile.nom);
                    nom = (TextView) findViewById(R.id.nomServ);
                    description = (TextView) findViewById(R.id.description);
                    mail = (TextView) findViewById(R.id.mail);
                    telephone = (TextView) findViewById(R.id.phone);
                    for (int i = 0; i < user.listeServices.size(); i++)
                    {


                        if (user.listeServices.get(i).getNomSservice().equals(service) ) {

                            nom.setText(user.listeServices.get(i).getNomSservice());
                            description.setText(user.listeServices.get(i).getDescription());
                            mail.setText(user.listeServices.get(i).getCourriel());
                            telephone.setText(user.listeServices.get(i).getNoTelephone());

                        }

                    }
                    //courriel = (TextView) findViewById(R.id.courrielProfil);
                    //telephone = (TextView) findViewById(R.id.telProfil);
                    //prenom = (TextView) findViewById(R.id.prenomProfil);
                    //prenom.setText(user.profile.prenom);
                    //courriel.setText(user.profile.adresseCourriel);
                    //telephone.setText(user.profile.numeroTelephone);
                    //toggle.setChecked(user.disponible);
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

                            orchestrateur.retirerOffreDeService(us, user.listeServices.get(i));
                        }
                    }

                    Intent profil = new Intent(AfficherSupprimerService.this, ProfilActivity.class);
                    profil.putExtra("userName",us);
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
                        orchestrateur.modifierDisponibiliteService(us, service, true);
                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                } else {

                    try{
                        orchestrateur.modifierDisponibiliteService(us, service, false);
                    }catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
    }
}
