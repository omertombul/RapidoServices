package inm5001.rapidoservices;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
//import Orchestrateur.java;
public class InscriptionActivity extends Activity {
    Button sEnregistrer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        sEnregistrer = (Button)findViewById(R.id.inscrire);

        sEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination
                Intent troisiemeActivite = new Intent(InscriptionActivity.this, ProfilActivity.class);

                // On rajoute un extra
                //secondeActivite.putExtra(AGE, 31);

                // Puis on lance l'intent !
                startActivity(troisiemeActivite);
            }
        });
    }
}
