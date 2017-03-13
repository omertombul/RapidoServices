package inm5001.rapidoservices.interfaceGraphique;

		import android.app.Activity;
		import android.app.AlertDialog;
		import android.app.ProgressDialog;
		import android.os.Bundle;
		import android.os.StrictMode;
		import android.view.View;
		import android.content.Intent;
		import android.widget.Button;
		import android.widget.EditText;

		import inm5001.rapidoservices.MyException;
		import inm5001.rapidoservices.Orchestrateur;
		import inm5001.rapidoservices.R;
		import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by joy-reybabagbeto on 16-10-24.
 * and Omer Tombul
 */

public class LoginActivity extends Activity {
	Button seConnecter = null;
	Button sInscrire = null;
	EditText username = null;
	EditText password = null;
	Orchestrateur o;
	Utilisateur u;
	AlertDialog.Builder dlgAlert  ;
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

		//Creation des objets
		o = new Orchestrateur();
		try {
			u = new Utilisateur();
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}

		//Permission de connection
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		//Dialog box pour erreur dans les champs
		dlgAlert  = new AlertDialog.Builder(this);
		dlgAlert.setPositiveButton("OK", null);
		dlgAlert.setCancelable(true);
		username.setSelection(0);


		seConnecter.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				connect();
		    }

        });

		sInscrire.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Le premier paramètre est le nom de l'activité actuelle
				// Le second est le nom de l'activité de destination
				Intent secondeActivite = new Intent(LoginActivity.this, InscriptionActivity.class);
				// Puis on lance l'intent !
				startActivity(secondeActivite);
			}
		});
	}



	//Methode de connection de l'utilisateur et validation des credentials
	private void connect() {


        new Thread() {
			@Override
			public void run() {


				try {


					u = o.validationLogin(username.getText().toString(),password.getText().toString());
					System.out.println(" Nom : " + u.profile.nom);

//                    dlgAlert.setTitle("Welcome to RapidoServices");
//                    dlgAlert.setMessage("Connection accepter!");
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            dlgAlert.create().show();
//                        }
//                    });
					Intent profilactivite = new Intent(LoginActivity.this, ProfilActivity.class);
                    profilactivite.putExtra("userName",username.getText().toString());
                    profilactivite.putExtra("password",password.getText().toString());
					startActivity(profilactivite);

		} catch (final Exception ex) {

					System.out.println(ex.getMessage());

                        dlgAlert.setTitle("Erreur!");
                        dlgAlert.setMessage(ex.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dlgAlert.create().show();
                            }
                        });
                }
			}
		}.start();

	}


}

