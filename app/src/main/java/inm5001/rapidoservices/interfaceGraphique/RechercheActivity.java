package inm5001.rapidoservices.interfaceGraphique;

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

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.Recherche;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.service.ConstanteAbstraiteServices;

public class RechercheActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String nomService;
    String ville;
    Button rechercher = null;
    EditText tauxHorraire = null;
    TextView affichageRecherche = null;
    float prix = 0f;
    Orchestrateur o;
    ArrayList<Recherche> listeDePaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        rechercher = (Button) findViewById(R.id.buttonRechercherRecherche);
        tauxHorraire = (EditText) findViewById(R.id.editTextPrixRecherche);
        affichageRecherche = (TextView) findViewById(R.id.textViewAffichageResultRecherche);


////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * Menu deroulant de Service
 */
///////////////////////////////////////////////////////////////////////////////////////////////////
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinnerRechercheService);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ConstanteAbstraiteServices.listeNomServiceRecherche);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 *Menu deroulant de ville
 */
////////////////////////////////////////////////////////////////////////////////////////////////////
        // Spinner element
        Spinner spinnerVille = (Spinner) findViewById(R.id.spinnerRechercheVille);

        // Spinner click listener
        spinnerVille.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterVille = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ConstanteAbstraiteServices.listeVilleRecherche);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerVille.setAdapter(dataAdapterVille);
////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * Listener sur les bouttons
 * */
////////////////////////////////////////////////////////////////////////////////////////////////////


        //listner sur le boutton rechercher
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // lance un processus qui recupere l'info de l'utilisateur de la bd
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        o = new Orchestrateur();
                        try {
                            String affichage = "";
                            float tHorraire = 0.0f;

                            if (tauxHorraire.getText().toString().isEmpty() || tauxHorraire.getText().toString() == null) {
                                tHorraire = 0.0f;
                            } else {
                                tHorraire = Float.valueOf(tauxHorraire.getText().toString());
                            }

                            listeDePaire = o.rechercheDeServices(tHorraire, prix, nomService, ville);
                            if (!listeDePaire.isEmpty()) {

                                for (Recherche p : listeDePaire) {
                                    System.out.println("NOM UTILISATEUR RECHERCHE  " + p.getUtilisateur().identifiant.nomUtilisateur);
                                    System.out.println("Service : " + p.getService().getNomSservice());
                                    affichage += ("\n" + "Nom Utilisateur : " + p.getUtilisateur().identifiant.nomUtilisateur + "     No. Tel : "
                                            + p.getService().getNoTelephone() + "    Service : " + p.getService().getNomSservice());
                                }
                            } else {
                                affichage = "Aucun service correspondant a la recherche ! ";
                                System.out.println("LISTE VIDE ******");
                            }
                            affichageRecherche.setText(affichage);
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        } catch (MyException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });

            }
        });
    }
////////////////////////////////////////////////////////////////////////////////////////////////////



/**
 * Methode override pour le menu deroulant, qui est obligatoire quand
 * on fait un "implements AdapterView.OnItemSelectedListener"
 **/
////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = "";
        switch (parent.getId()) {
            case R.id.spinnerRechercheService:
                // On selecting a spinner item
                item = parent.getItemAtPosition(position).toString();
                nomService = item;
                break;
            case R.id.spinnerRechercheVille:
                item = parent.getItemAtPosition(position).toString();
                ville = item;
                break;
        }
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
/**
 * rajouter automatiquement par un "implements AdapterView.OnItemSelectedListener"
 **/
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}
