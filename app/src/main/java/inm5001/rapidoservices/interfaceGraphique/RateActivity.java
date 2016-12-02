package inm5001.rapidoservices.interfaceGraphique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import java.sql.SQLException;

import inm5001.rapidoservices.MyException;
import inm5001.rapidoservices.Orchestrateur;
import inm5001.rapidoservices.R;

/**
 * Created by joy-reybabagbeto on 16-11-18.
 */

public class RateActivity extends Activity {

    Button evaluer = null;
    RatingBar rate = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        evaluer = (Button) findViewById(R.id.evaluerRate);
        rate = (RatingBar) findViewById(R.id.ratingBarRate);
        Intent intent = getIntent();
        final String userName = intent.getStringExtra("userName");
        final String nomService = intent.getStringExtra("nomService");
        final String userACoter = intent.getStringExtra("userACoter");


        evaluer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        float eval = rate.getRating();
                        Orchestrateur orc = new Orchestrateur();
                        try {
                            orc.faireUneEvaluation(userACoter,userName,nomService,eval);
                        } catch (MyException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Intent evaluation = new Intent(RateActivity.this, EvaluationActivity.class);
                        startActivity(evaluation);
                    }
                });

            }
        });


    }

}