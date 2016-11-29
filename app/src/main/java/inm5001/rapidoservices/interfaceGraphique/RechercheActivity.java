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

import inm5001.rapidoservices.ConstanteRecherche;
import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.recherche.RechercheServices;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.service.ConstanteAbstraiteServices;

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
    ArrayList<RechercheServices> listeDePaire;
    ArrayList<String> resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        rechercher = (Button) findViewById(R.id.buttonRechercherRecherche);
        tauxHorraire = (EditText) findViewById(R.id.editTextPrixRecherche);

        //Liste cliquable pour la recherche
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
        dataAdapterVille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerVille.setAdapter(dataAdapterVille);
////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 *Menu deroulant Type de trie
 */
////////////////////////////////////////////////////////////////////////////////////////////////////
        //Spinne type trie
        Spinner spinnerTypeTrie = (Spinner) findViewById(R.id.spinnerTypeTrie);

        // Spinner click listener
        spinnerTypeTrie.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterTrie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                ConstanteRecherche.typeDeTrie);

        // Drop down layout style - list view with radio button
        dataAdapterTrie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerTypeTrie.setAdapter(dataAdapterTrie);

////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Listener sur les bouttons
 */
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
                                        int i = 0;
                                        for (RechercheServices p : listeDePaire) {
                                            i++;
                                            affichage = i + " - " + p.recupererService().getNomSservice()
                                                    + "\nTaux Horraire : " + p.recupererService().getTauxHorraire() + " " + p.recupererService().getVille() ;

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

                            //Set an Item Click Listener for ListView items
                            lView.setOnItemClickListener(new OnItemClickListener(){
                                //onItemClick() callback method
                                public void onItemClick(AdapterView<?> parent, View v, int position, long id){

                                    lView.invalidateViews();
                                    //Generate a Toast message
                                    String toastMessage = "Selected : "+  resultat.get(position) ;

                                    System.out.println(resultat.get(position));
                                    Intent intent = new Intent(RechercheActivity.this, ServiceRechercherAvtivity.class);
                                    RechercheServices r = listeDePaire.get(position);
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
