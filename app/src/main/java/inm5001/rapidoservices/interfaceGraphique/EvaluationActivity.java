package inm5001.rapidoservices.interfaceGraphique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.recherche.RechercheACoter;
import inm5001.rapidoservices.recherche.RechercheServices;

/**
 * Created by joy-reybabagbeto on 16-11-28.
 */

public class EvaluationActivity extends Activity {

    Orchestrateur o;
    ArrayList <RechercheACoter> cotation;
    ArrayList <String> resultat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        final ListView lView = (ListView) findViewById(R.id.ListViewRateClient);
        final ArrayAdapter<String> adapter ;

        resultat = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,resultat);


        Intent intent = getIntent();
        final String userName = intent.getStringExtra("userName");


                o = new Orchestrateur();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //recuper la liste
                            cotation = o.obtenirMesEvaluationsADonner(userName);


                            if (!cotation.isEmpty()) {
                                for (int i =0; i < cotation.size(); i++){
                                    resultat.add(cotation.get(i).toString());
                                }

                            } else {

                                resultat.add("Aucun Évaluation ");
                                System.out.println("Liste vide");
                            }
                            //Ajouter adapteur pour lui donne la liste de nom et services
                            lView.setAdapter(adapter);
                        }
                    });


                lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!resultat.get(position).equals("Aucun Évaluation ")) {
                    Intent intentServ = new Intent(EvaluationActivity.this, RateActivity.class);
                    intentServ.putExtra("userName", userName);
                    intentServ.putExtra("nomService", cotation.get(position).getNomService());
                    intentServ.putExtra("userACoter", cotation.get(position).getNomUtilisateurACoter());
                    startActivity(intentServ);
                }
            }

        });


    }
}

