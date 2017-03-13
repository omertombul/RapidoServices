package inm5001.rapidoservices.interfaceGraphique;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ToggleButton;
import android.widget.CompoundButton;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by joy-reybabagbeto on 16-10-24.
 * and Omer Tombul
 */

public class ProfilActivity extends Activity {
    TextView nom = null;
    TextView prenom = null;
    TextView courriel = null;
    TextView telephone = null;
    Button ajouter = null;
    Button rechercher = null;
    Button rate = null;
    Button supprimerUsager = null;
    String us;
    Utilisateur user;
    Orchestrateur orc;
    ToggleButton toggle = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ajouter = (Button) findViewById(R.id.ajouter);
        rechercher = (Button) findViewById(R.id.rechercher);
        rate = (Button) findViewById(R.id.rate);
        supprimerUsager = (Button) findViewById(R.id.buttonDeleteUserProfile);
        toggle = (ToggleButton) findViewById(R.id.switchDispoUser);

        //recuper le userName et password de la page precedente
        final Intent intent = getIntent();
        final String userName = intent.getStringExtra("userName");
        us = userName;



        //Liste de Service offert cliquable

        final ListView lView = (ListView) findViewById(R.id.listViewMesServices);
        final ArrayAdapter<String> adapter ;







////////////////////////////////////////////////////////////////////////////////////////////////////
        // lance un processus qui recupere l'info de l'utilisateur de la bd
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Orchestrateur o = new Orchestrateur();
                orc = o;
                Utilisateur u;
                try {
                    u = o.recupererUtilisateur(userName);
                    user = u;
                    System.out.println("**************** nom :" +u.profile.nom);
                    courriel = (TextView) findViewById(R.id.courrielProfil);
                    telephone = (TextView) findViewById(R.id.telProfil);
                    nom = (TextView) findViewById(R.id.nomProfil);
                    prenom = (TextView) findViewById(R.id.prenomProfil);
                    nom.setText(u.profile.nom);
                    prenom.setText(u.profile.prenom);
                    courriel.setText(u.profile.adresseCourriel);
                    telephone.setText(u.profile.numeroTelephone);
                    toggle.setChecked(u.disponible);


                    } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////


        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    try {
                        // The toggle is enabled
                        orc.modifierDisponibiliteUsager(userName, true);
                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                } else {

                    try{
                        orc.modifierDisponibiliteUsager(userName,false);
                    }catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        });



////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 *
 *
 * */
        final ArrayList resultat = new ArrayList<String>();
        if(user.listeServices.size() == 0 || user.listeServices == null){
            resultat.add("Pas de Service");
        }

        for(int i = 0; i < user.listeServices.size(); i++) {
            resultat.add(user.listeServices.get(i).getNomSservice());
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,resultat);



        lView.setAdapter(adapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!resultat.get(position).equals("Pas de Service")) {
                    Intent intentServ = new Intent(ProfilActivity.this, AfficherSupprimerService.class);
                    intentServ.putExtra("userName", userName);

                    intentServ.putExtra("service", adapter.getItem(position));

                    startActivity(intentServ);
                }
            }

        });







////////////////////////////////////////////////////////////////////////////////////////////////////
        /**
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinnerServiceProfile);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();

        for(String s : user.listeCompetences) {
            categories.add(s);
        }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

         */
////////////////////////////////////////////////////////////////////////////////////////////////////


        /**
        * Listener sur les bouttons
        * */
////////////////////////////////////////////////////////////////////////////////////////////////////


        //listner sur le boutton ajouter
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ajouterService = new Intent(ProfilActivity.this, AjouterServiceActivity.class);
                // System.out.println("Username dans profile Activity " + userName);
                ajouterService.putExtra("userName", userName);
                startActivity(ajouterService);


            }
        });



        //listner sur le boutton rechercher
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rechercherService = new Intent(ProfilActivity.this, RechercheActivity.class);
                rechercherService.putExtra("userName",userName);
                startActivity(rechercherService);
            }
        });

        supprimerUsager.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    orc.supprimerCompte(userName);
                    Intent loginMenu = new Intent(ProfilActivity.this, LoginActivity.class);
                    startActivity(loginMenu);
                }catch(MyException e){
                    System.out.println(e.getMessage());
                }
            }
        });

        rate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent evaluer = new Intent(ProfilActivity.this, EvaluationActivity.class);
                evaluer.putExtra("userName", userName);
                startActivity(evaluer);

            }
        });

    }

}
