package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import powerup.systers.com.datamodel.SessionHistory;

public class DisplayPointsActivity extends Activity {
    private TextView currentPointsTextView;
    private TextView totalPointsTextView;
    private Button mapButton;
    private Button nextScenarioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_points);
        mapButton = (Button) findViewById(R.id.mapButton);
        nextScenarioButton = (Button) findViewById(R.id.nextScenarioButton);
        Integer currentPointsValue = getIntent().getExtras().getInt(Keys.currentPointsKey);
        currentPointsTextView = (TextView) findViewById(R.id.current_points_text);
        totalPointsTextView = (TextView) findViewById(R.id.total_points_text);
        currentPointsTextView.setText(currentPointsValue.toString());
        totalPointsTextView.setText(new Integer(SessionHistory.totalPoints).toString());

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED, new Intent());
                finish();
            }
        });

        nextScenarioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
        });


    }
}
