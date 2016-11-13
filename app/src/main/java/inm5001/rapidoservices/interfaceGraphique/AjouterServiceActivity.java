package inm5001.rapidoservices.interfaceGraphique;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.TypeServices;

/**
 * Created by joy-reybabagbeto on 16-11-10.
 * and Omer Tombul
 */

public class AjouterServiceActivity extends Activity implements  AdapterView.OnItemSelectedListener{

    Button ajouter = null;
    EditText description = null;
    EditText ville = null;
    EditText prix = null;
    EditText tauxHorraire = null;
    EditText emailService = null;
    EditText noTelService = null;
    EditText competence = null;
    Orchestrateur o;
    String userName;
    String nomService;
    AlertDialog.Builder dlgAlert;



    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //recuperer le username de la page precedente
        Intent intent = getIntent();
        String us = intent.getStringExtra("userName");
        userName = us;
        //recupere les valeures dans les champs
        ajouter = (Button) findViewById(R.id.buttonAjouter);
        tauxHorraire = (EditText)findViewById(R.id.editTextTauxHorraire);
        description= (EditText)findViewById(R.id.editTextDescription);
        ville = (EditText)findViewById(R.id.editTextVille);
        prix = (EditText)findViewById(R.id.editTextPrix);
        emailService = (EditText)findViewById(R.id.editTextServiceEmail);
        noTelService = (EditText)findViewById(R.id.editTextServiceNoTel);
        competence = (EditText)findViewById(R.id.editTextCompetence);


        //creation du alert box
        dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);

        //creation de l'objet orchestrateur pour ajouter a la bd le service
        o = new Orchestrateur();


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
        Spinner spinner = (Spinner) findViewById(R.id.spinnerServiceAjout);

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


////////////////////////////////////////////////////////////////////////////////////////////////////

        ajouter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try{

                    AbstraiteServices s = new TypeServices(Float.valueOf(tauxHorraire.getText().toString()),
                            Float.valueOf(prix.getText().toString()),
                            nomService,false,ville.getText().toString(),
                            (byte)1,noTelService.getText().toString(),
                            emailService.getText().toString(),description.getText().toString());

                    final AbstraiteServices a = s;

                    //System.out.println("username dans ajouter service "+userName);
                    connect(a);


                }catch(MyException e){
                    System.out.println(e.getMessage());
                    dlgAlert.setTitle("Erreur!");

                    dlgAlert.setMessage(e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dlgAlert.create().show();
                        }
                    });
                }



            }
        });
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    //Methode de connection de l'utilisateur et validation des credentials
    private void connect(final AbstraiteServices s) {


        new Thread() {
            @Override
            public void run() {


                try {

                    final String u = userName;
                    o.ajouterOffreDeService(u, (TypeServices) s);
                    dlgAlert.setTitle("Ajout de Service");
                    dlgAlert.setMessage("Le service a ete ajouter !");


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dlgAlert.create().show();
                        }
                    });


                } catch (final MyException ex) {

                    System.out.println(ex.getMessage());

                    dlgAlert.setTitle("Erreur!");
                    dlgAlert.setMessage(ex.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dlgAlert.create().show();
                        }
                    });
                }
            }
        }.start();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        nomService = item;

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }
}