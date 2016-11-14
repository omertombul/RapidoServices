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
import android.widget.Switch;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.ConstanteAbstraiteServices;
import inm5001.rapidoservices.service.TypeServices;

/**
 * Created by joy-reybabagbeto on 16-11-10.
 * and Omer Tombul
 */

public class AjouterServiceActivity extends Activity implements  AdapterView.OnItemSelectedListener{

    Button ajouter = null;
    EditText description = null;
    EditText prix = null;
    EditText tauxHorraire = null;
    EditText emailService = null;
    EditText noTelService = null;
    Orchestrateur o;
    String userName;
    String nomService;
    String ville;
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
        prix = (EditText)findViewById(R.id.editTextPrix);
        emailService = (EditText)findViewById(R.id.editTextServiceEmail);
        noTelService = (EditText)findViewById(R.id.editTextServiceNoTel);



        //creation du alert box
        dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);

        //creation de l'objet orchestrateur pour ajouter a la bd le service
        o = new Orchestrateur();

////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * menu deroulant Service
 */
///////////////////////////////////////////////////////////////////////////////////////////////////


        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinnerServiceAjout);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ConstanteAbstraiteServices.listeNomService);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 *menu deroulant de ville
 */
////////////////////////////////////////////////////////////////////////////////////////////////////

        Spinner spinnerVille = (Spinner) findViewById(R.id.spinnerVilleAjoutService);

        // Spinner click listener
        spinnerVille.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categoriesVille = new ArrayList<String>();
        categoriesVille.add("Montreal");
        categoriesVille.add("Laval");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterVille = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ConstanteAbstraiteServices.listeVille);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerVille.setAdapter(dataAdapterVille);

////////////////////////////////////////////////////////////////////////////////////////////////////

        ajouter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try{

                        AbstraiteServices s = new TypeServices(Float.valueOf(tauxHorraire.getText().toString()),
                            Float.valueOf(prix.getText().toString()),
                            nomService,false,ville,
                            (byte)1,noTelService.getText().toString(),
                            emailService.getText().toString(),description.getText().toString());

                    final AbstraiteServices a = s;

                    //System.out.println("username dans ajouter service "+userName);
                    connect(a);

                    Intent profilActivity = new Intent(AjouterServiceActivity.this,ProfilActivity.class);
                    profilActivity.putExtra("userName",userName);
                    startActivity(profilActivity);

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
        String item = "";
        switch (parent.getId()) {
            case R.id.spinnerServiceAjout:
            // On selecting a spinner item
            item = parent.getItemAtPosition(position).toString();
            nomService = item;
                break;
            case R.id.spinnerVilleAjoutService:
                item = parent.getItemAtPosition(position).toString();
                ville = item;
                break;
            default:

        }
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }
}