package inm5001.rapidoservices;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.TypeServices;
import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by joy-reybabagbeto on 16-10-24.
 * and Omer Tombul
 */

public class ProfilActivity extends Activity implements AdapterView.OnItemSelectedListener{
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

        ajouter = (Button) findViewById(R.id.ajouter);
        rechercher = (Button) findViewById(R.id.rechercher);

        //recuper le userName et password de la page precedente
        Intent intent = getIntent();
        final String userName = intent.getStringExtra("userName");
        //String pass = intent.getStringExtra("password");

/////////////////////////////////////////////////////////////////////////////////
//        AbstraiteServices plombier = null;
//        AbstraiteServices electricien = null;
//        try {
//
//            plombier = new TypeServices("Pombiere");
//            electricien = new TypeServices("Electricien");
//        }catch(MyException e){
//            System.out.println(e.getMessage());
//        }

//        List<AbstraiteServices> listServices = new ArrayList<>();
//        listServices.add(plombier);
//        listServices.add(electricien);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinnerServiceProfile);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Electricien");
        categories.add("Plombier");
//        for(AbstraiteServices s : listServices) {
//            categories.add(s.getNomSservice());
//        }



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
/////////////////////////////////////////////////////////////////////////////////

        // lance un processus qui recupere l'info de l'utilisateur de la bd
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Orchestrateur o = new Orchestrateur();
                Utilisateur u;
                try {
                    u = o.recupererUtilisateur(userName);
                    System.out.println("**************** nom :" +u.profile.nom);
                    courriel = (TextView) findViewById(R.id.courrielProfil);
                    telephone = (TextView) findViewById(R.id.telProfil);
                    nom = (TextView) findViewById(R.id.nomProfil);
                    prenom = (TextView) findViewById(R.id.prenomProfil);
                    nom.setText(u.profile.nom);
                    prenom.setText(u.profile.prenom);
                    courriel.setText(u.profile.adresseCourriel);
                    telephone.setText(u.profile.numeroTelephone);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });


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
                startActivity(rechercherService);
            }
        });


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }
}
