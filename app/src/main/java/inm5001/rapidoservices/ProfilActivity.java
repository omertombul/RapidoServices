package inm5001.rapidoservices;

        import android.app.Activity;
        import android.os.Bundle;
        import android.widget.TextView;

        import inm5001.rapidoservices.utilisateur.Utilisateur;

/**
 * Created by joy-reybabagbeto on 16-10-24.
 */

public class ProfilActivity extends Activity {
    TextView nom = null;
    TextView prenom = null;
    InscriptionActivity in ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        in = new InscriptionActivity();
        final Utilisateur user = in.user;
        nom = (TextView)findViewById(R.id.nomProfil);
        prenom = (TextView)findViewById(R.id.nomProfil);
        nom.setText(user.profile.nom);
        prenom.setText(user.profile.prenom);
    }
}
