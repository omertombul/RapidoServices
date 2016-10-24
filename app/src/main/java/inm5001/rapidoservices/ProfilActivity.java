package inm5001.rapidoservices;

        import android.app.Activity;
        import android.os.Bundle;
        import android.widget.TextView;
        import android.content.Intent;

        import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by joy-reybabagbeto on 16-10-24.
 */

public class ProfilActivity extends Activity {
    TextView nom = null;
    TextView prenom = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Intent intent = getIntent();
        String prenom_Inscrip= intent.getStringExtra("prenom");
        String nom_Inscrip = intent.getStringExtra("nom");



        nom = (TextView)findViewById(R.id.nomProfil);
        prenom = (TextView)findViewById(R.id.nomProfil);
        nom.setText(nom_Inscrip);
        prenom.setText(prenom_Inscrip);
    }
}
