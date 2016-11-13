package inm5001.rapidoservices;
import inm5001.rapidoservices.service.ConstanteAbstraiteServices;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.icu.text.DisplayContext;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;

import inm5001.rapidoservices.InscriptionActivity;
import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.ProfilActivity;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.service.AbstraiteServices;
import inm5001.rapidoservices.service.TypeServices;

/**
 * Created by joy-reybabagbeto on 16-11-10.
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
    AlertDialog.Builder dlgAlert;



        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_service);

            //recuperer le username de la page precedente
            Intent intent = getIntent();
            final String userName = intent.getStringExtra("userName");

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



            ajouter.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                        try{

                            AbstraiteServices s = new TypeServices(Float.valueOf(tauxHorraire.getText().toString()),
                                    Float.valueOf(prix.getText().toString()),
                                    nomService.getText().toString(),false,ville.getText().toString(),
                                    (byte)1,noTelService.getText().toString(),
                                    emailService.getText().toString(),description.getText().toString());
                            System.out.println(s.getNomSservice());


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

}
