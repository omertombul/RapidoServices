package inm5001.rapidoservices;

        import android.app.Activity;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.view.View;
        import android.content.Intent;
        import android.widget.Button;
        import android.widget.EditText;

        import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by joy-reybabagbeto on 16-10-24.
 */

public class LoginActivity extends Activity {
    Button seConnecter = null;
    Button sInscrire = null;
    EditText username = null;
    EditText password = null;
    Orchestrateur o;
    Utilisateur u;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // On récupère toutes les vues dont on a besoin
        seConnecter = (Button)findViewById(R.id.seConnecter);
        sInscrire = (Button)findViewById(R.id.sInscrire);
        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
        o = new Orchestrateur();
        u = new Utilisateur();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // On attribue un listener adapté aux vues qui en ont besoin
        //envoyer.setOnClickListener(envoyerListener);
        //raz.setOnClickListener(razListener);
        //username.addTextChangedListener(textWatcher);
        //password.addTextChangedListener(textWatcher);

        seConnecter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                   u = o.recupererUtilisateur(username.getText().toString(),password.getText().toString());

                    System.out.println(" Nom : " + u.profile.nom);
                   // password.getText().toString();
                }catch (Exception e) {
                        System.out.print(e + "aaaaaaaaaaa");
                    }
                }





        });
        // Solution avec des onKey
        //taille.setOnKeyListener(modificationListener);
        //poids.setOnKeyListener(modificationListener);
        //mega.setOnClickListener(checkedListener);
        sInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Le premier paramètre est le nom de l'activité actuelle
                // Le second est le nom de l'activité de destination
                Intent secondeActivite = new Intent(LoginActivity.this, InscriptionActivity.class);

                // On rajoute un extra
                //secondeActivite.putExtra(AGE, 31);

                // Puis on lance l'intent !
                startActivity(secondeActivite);
            }
        });
    }

}
