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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supservice);
        Intent intent = getIntent();
        final String us = intent.getStringExtra("userName");
        final String se = intent.getStringExtra("Électricien");
        final String plomb = intent.getStringExtra("Plombier");
        final String menu = intent.getStringExtra("Menuisier");
        supprimer = (Button) findViewById(R.id.supprimerService);
        dispo = (ToggleButton) findViewById(R.id.toggleDispoService);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Orchestrateur o = new Orchestrateur();
                orchestrateur = o;
                Utilisateur u;
                try {
                    u = o.recupererUtilisateur(us);
                    System.out.println("**************** nom :" +u.profile.nom);

                    for (int i = 0; i < u.listeServices.size(); i++)
                    {
                        nom = (TextView) findViewById(R.id.nomServ);
                        description = (TextView) findViewById(R.id.description);
                        mail = (TextView) findViewById(R.id.mail);
                        telephone = (TextView) findViewById(R.id.phone);
                        if (u.listeServices.get(i).getNomSservice().equals(se) ){

                            nom.setText(u.listeServices.get(i).getNomSservice());
                            description.setText(u.listeServices.get(i).getDescription());
                            mail.setText(u.listeServices.get(i).getCourriel());
                            telephone.setText(u.listeServices.get(i).getNoTelephone());

                        }else if (u.listeServices.get(i).getNomSservice().equals(plomb)) {
                            nom.setText(u.listeServices.get(i).getNomSservice());
                            description.setText(u.listeServices.get(i).getDescription());
                            mail.setText(u.listeServices.get(i).getCourriel());
                            telephone.setText(u.listeServices.get(i).getNoTelephone());
                        } else if (u.listeServices.get(i).getNomSservice().equals(menu)){
                            nom.setText(u.listeServices.get(i).getNomSservice());
                            description.setText(u.listeServices.get(i).getDescription());
                            mail.setText(u.listeServices.get(i).getCourriel());
                            telephone.setText(u.listeServices.get(i).getNoTelephone());
                        }

                    }
                    //courriel = (TextView) findViewById(R.id.courrielProfil);
                    //telephone = (TextView) findViewById(R.id.telProfil);
                    //prenom = (TextView) findViewById(R.id.prenomProfil);
                    //prenom.setText(u.profile.prenom);
                    //courriel.setText(u.profile.adresseCourriel);
                    //telephone.setText(u.profile.numeroTelephone);
                    //toggle.setChecked(u.disponible);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Orchestrateur o = new Orchestrateur();
                Utilisateur u;
                try {
                    u = o.recupererUtilisateur(us);
                    for (int i = 0; i < u.listeServices.size(); i++)
                    {

                        if (u.listeServices.get(i).getNomSservice().equals(se) ){
                             o.retirerOffreDeService(us, u.listeServices.get(i));

                        } else if (u.listeServices.get(i).getNomSservice().equals(plomb)){
                            o.retirerOffreDeService(us,u.listeServices.get(i));

                        } else if (u.listeServices.get(i).getNomSservice().equals(menu)){
                            o.retirerOffreDeService(us, u.listeServices.get(i));
                        }

                    }
                    Intent profil = new Intent(AfficherSupprimerService.this, ProfilActivity.class);
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
                        orchestrateur.modifierDisponibiliteService(us, se, true);
                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                } else {

                    try{
                        orchestrateur.modifierDisponibiliteService(us, se, false);
                    }catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
    }
}
