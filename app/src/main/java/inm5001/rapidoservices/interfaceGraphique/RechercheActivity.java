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
import java.util.List;
import android.widget.ListView;

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
    float prix = 0f;
    Orchestrateur o;
    ArrayList<Recherche> listeDePaire;
    ArrayList<String> resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
        rechercher = (Button) findViewById(R.id.buttonRechercherRecherche);
        tauxHorraire = (EditText) findViewById(R.id.editTextPrixRecherche);
        final ListView lView = (ListView) findViewById(R.id.ListViewRechercheResult);
        final ArrayAdapter<String> adapter ;
        resultat = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultat);




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



                            float tHorraire = 0.0f;

                            //validation taux horraire jamais vide ou null
                            if (tauxHorraire.getText().toString().isEmpty() || tauxHorraire.getText().toString() == null) {
                                tHorraire = 0.0f;
                            } else {
                                tHorraire = Float.valueOf(tauxHorraire.getText().toString());
                            }

                            //recuper la liste
                            listeDePaire = o.rechercheDeServices(tHorraire, prix, nomService, ville);
                            if (!listeDePaire.isEmpty()) {
                                resultat.clear();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String affichage = "";
                                        for (Recherche p : listeDePaire) {

                                            affichage = p.getUtilisateur().profile.nom + " " + p.getService().getNomSservice() + " " + p.getService().getNoTelephone();
                                            resultat.add(affichage);
                                            System.out.println(affichage);
                                            System.out.println(resultat.isEmpty());


                                        }
                                    }
                                });


                                lView.setAdapter(adapter);
                                lView.invalidateViews();
                            } else {

                                resultat.add("Liste vide !");
                                System.out.println("LISTE VIDE ******");
                            }


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
 * Methode override pour le menu deroulant, ou la listViewResult qui est obligatoire quand
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
            case R.id.ListViewRechercheResult:
                item = parent.getItemAtPosition(position).toString();
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
