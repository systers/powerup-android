package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import powerup.systers.com.datamodel.SessionHistory;


/**
 * Created by chhavi on 9/1/16.
 */
public class DisplayPointsActivity extends Activity {
    private TextView currentPoints;
    private TextView totalPoints;
    private Button mapButton;
    private Button nextScenarioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_points);
        mapButton = (Button)findViewById(R.id.mapButton);
        nextScenarioButton = (Button)findViewById(R.id.nextScenarioButton);
        Intent i = getIntent();
        int currentPointsValue = i.getExtras().getInt("current_scenario_points");
        currentPoints = (TextView)findViewById(R.id.current_points_text);
        totalPoints = (TextView)findViewById(R.id.total_points_text);
        currentPoints.setText(currentPointsValue + "");
        totalPoints.setText(SessionHistory.totalPoints + "");

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        nextScenarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });



    }
}
