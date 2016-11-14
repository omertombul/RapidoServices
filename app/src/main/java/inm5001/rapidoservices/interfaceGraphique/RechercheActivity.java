package inm5001.rapidoservices.interfaceGraphique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.PaireNomUtilisateurEtTypeService;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.service.ConstanteAbstraiteServices;
import inm5001.rapidoservices.utilisateur.Utilisateur;

public class RechercheActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String nomService;
    String ville;
    Button rechercher = null;
    EditText tauxHorraire = null;
    float prix = 0f;
    Orchestrateur o;
    ArrayList<PaireNomUtilisateurEtTypeService> listeDePaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        rechercher =  (Button) findViewById(R.id.buttonRechercherRecherche);
        tauxHorraire = (EditText)findViewById(R.id.editTextPrixRecherche);


////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * menu deroulant Service
 */
///////////////////////////////////////////////////////////////////////////////////////////////////


        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinnerRechercheService);

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

        Spinner spinnerVille = (Spinner) findViewById(R.id.spinnerRechercheVille);

        // Spinner click listener
        spinnerVille.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterVille = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ConstanteAbstraiteServices.listeVille);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerVille.setAdapter(dataAdapterVille);

////////////////////////////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////////////////////////////


        //listner sur le boutton ajouter
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // lance un processus qui recupere l'info de l'utilisateur de la bd
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        o = new Orchestrateur();
                        try{

                            listeDePaire = o.rechercheDeServices(0.0f,prix,nomService,ville);
                            if(!listeDePaire.isEmpty())
                                for(PaireNomUtilisateurEtTypeService p : listeDePaire){

                                    System.out.println("NOM UTILISATEUR RECHERCHE  "+p.getNomUtilisateur());
                                }else{
                                System.out.println("LISTE VIDE ******");
                            }


                        }catch(SQLException e){
                            System.out.println(e.getMessage());
                        }catch(MyException e){
                            System.out.println(e.getMessage());
                        }
                    }
                });

            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////





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
        }

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }
}
