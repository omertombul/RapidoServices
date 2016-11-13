package inm5001.rapidoservices;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import java.sql.SQLException;
import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.TypeServices;

/**
 * Created by joy-reybabagbeto on 16-11-10.
 * and Omer Tombul
 */

public class AjouterServiceActivity extends Activity {

    Button ajouter = null;
    EditText nomService = null;
    EditText description = null;
    EditText ville = null;
    EditText prix = null;
    EditText tauxHorraire = null;
    EditText emailService = null;
    EditText noTelService = null;
    EditText competence = null;
    Orchestrateur o;
    String userName;
    AlertDialog.Builder dlgAlert;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //recuperer le username de la page precedente
        Intent intent = getIntent();
        String us = intent.getStringExtra("userName");
        userName = us;
        //recupere les valeures dans les champs
        ajouter = (Button) findViewById(R.id.buttonAjouter);
        tauxHorraire = (EditText)findViewById(R.id.editTextTauxHorraire);
        nomService = (EditText)findViewById(R.id.editTextNomService);
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



        ajouter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try{

                    AbstraiteServices s = new TypeServices(Float.valueOf(tauxHorraire.getText().toString()),
                            Float.valueOf(prix.getText().toString()),
                            nomService.getText().toString(),false,ville.getText().toString(),
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
}