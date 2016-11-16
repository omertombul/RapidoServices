package inm5001.rapidoservices.interfaceGraphique;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.R;
import inm5001.rapidoservices.PaireNomUtilisateurEtTypeService;
import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by joy-reybabagbeto on 16-11-15.
 */

public class AfficherSupprimerService extends Activity {
    PaireNomUtilisateurEtTypeService objPaire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supservice);

/**

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Orchestrateur o = new Orchestrateur();
                orc = o;
                Utilisateur u;
                try {
                    u = o.recupererUtilisateur(userName);
                    user = u;
                    System.out.println("**************** nom :" +u.profile.nom);
                    courriel = (TextView) findViewById(R.id.courrielProfil);
                    telephone = (TextView) findViewById(R.id.telProfil);
                    nom = (TextView) findViewById(R.id.nomProfil);
                    prenom = (TextView) findViewById(R.id.prenomProfil);
                    nom.setText(u.profile.nom);
                    prenom.setText(u.profile.prenom);
                    courriel.setText(u.profile.adresseCourriel);
                    telephone.setText(u.profile.numeroTelephone);
                    toggle.setChecked(u.disponible);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
**/
    }
}
