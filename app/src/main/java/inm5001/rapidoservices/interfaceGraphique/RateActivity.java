package inm5001.rapidoservices.interfaceGraphique;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import inm5001.rapidoservices.R;

/**
 * Created by joy-reybabagbeto on 16-11-18.
 */

public class RateActivity extends Activity {

    Button evaluer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        evaluer = (Button) findViewById(R.id.evaluer);
/**
        evaluer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent evaluation = new Intent(RateActivity.this, EvaluationActivity.class);
                startActivity(evaluation);


            }
        });

**/
    }

}