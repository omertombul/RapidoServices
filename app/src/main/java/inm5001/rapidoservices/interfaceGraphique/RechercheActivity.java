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
import android.widget.Toast;
import java.sql.SQLException;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.Recherche;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.service.ConstanteAbstraiteServices;
import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created By Omer Tombul
 * */

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
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,resultat);

        Intent intent = getIntent();
        final String userName = intent.getStringExtra("userName");


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
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                ConstanteAbstraiteServices.listeNomServiceRecherche);

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
        ArrayAdapter<String> dataAdapterVille = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                ConstanteAbstraiteServices.listeVilleRecherche);

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
                            listeDePaire = o.rechercheDeServices(tHorraire, prix, nomService, ville,0f,0f,0f);

                            if (!listeDePaire.isEmpty()) {
                                resultat.clear();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String affichage = "";
                                        for (Recherche p : listeDePaire) {

                                            affichage = p.getUtilisateur().profile.nom + " " + p.recupererService().getNomSservice()
                                                    + " " + p.recupererService().getNoTelephone();
                                            resultat.add(affichage);
                                            System.out.println(affichage);
                                            System.out.println(resultat.isEmpty());


                                        }
                                    }
                                });


                            } else {

                                resultat.add("******* Aucun Service pour Vos Criteres ******");
                                System.out.println("Liste vide");
                            }
                            //Ajouter adapteur pour lui donne la liste de nom et services
                            lView.setAdapter(adapter);
                            lView.invalidateViews();
                            //Set an Item Click Listener for ListView items
                            lView.setOnItemClickListener(new OnItemClickListener(){
                                //onItemClick() callback method
                                public void onItemClick(AdapterView<?> parent, View v, int position, long id){

                                    //Generate a Toast message
                                    String toastMessage = "Selected : "+  resultat.get(position) ;

                                    System.out.println(resultat.get(position));
                                    Intent intent = new Intent(RechercheActivity.this, ServiceRechercherAvtivity.class);
                                    Recherche r = listeDePaire.get(position);
                                    System.out.println(r.recupererService().getVille());
                                    Float a = r.recupererService().getTauxHorraire();
                                    String taux = a.toString();
                                    intent.putExtra("nomService", r.getNomService());
                                    intent.putExtra("villeService",r.recupererService().getVille());
                                    intent.putExtra("taux",taux);
                                    intent.putExtra("description",r.recupererService().getDescription());
                                    intent.putExtra("userNameService",r.getUtilisateur().identifiant.nomUtilisateur);
                                    intent.putExtra("userName",userName);
                                    startActivity(intent);


                                    //Display user response as a Toast message
                                   // Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                                }
                            });


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
