package inm5001.rapidoservices;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.util.Log;
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
    protected ProgressDialog mProgressDialog;


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



        seConnecter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                try {
//                   u = o.recupererUtilisateur(username.getText().toString(),password.getText().toString());
//
//                    System.out.println(" Nom : " + u.profile.nom);
                        connect();

                }catch (Exception e) {
                        System.out.print(e + "aaaaaaaaaaa");
                    }
                }





        });

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

    private void connect() {
        mProgressDialog = ProgressDialog.show(this, "Attendez svp","Tentative de Connection", true);
        new Thread() {
            @Override
            public void run() {


                try {

                    u = o.validationLogin(username.getText().toString(),password.getText().toString());
                    System.out.println(" Nom : " + u.profile.nom);

                    // code runs in a thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressDialog.dismiss();
                        }
                    });
                } catch (final Exception ex) {
                    Log.i("---","Exception in thread");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgressDialog.dismiss();
                        }
                    });
                }
            }
        }.start();

    }


}

