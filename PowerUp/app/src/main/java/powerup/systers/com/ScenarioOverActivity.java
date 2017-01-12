/**
 * @desc finishes dialogue situation when the “continue” or “back” button is pressed.
 * Brings user to ending screen displaying current progress of power/health
 * bars and karma points.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;
import powerup.systers.com.util.UiUtils;

public class ScenarioOverActivity extends AppCompatActivity implements View.OnClickListener {

    public static Activity scenarioOverActivityInstance;
    public static int scenarioActivityDone;

    private DatabaseHandler mDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_over);

        mDbHandler = new DatabaseHandler(this);
        mDbHandler.open();

        scenarioOverActivityInstance = this;
        scenarioActivityDone = 1;

        setupLayout();
    }

    private void setupLayout() {
        findViewById(R.id.continueButton).setOnClickListener(this);
        findViewById(R.id.homeButton).setOnClickListener(this);

        UiUtils.setupEyesFaceClothesHair(this, mDbHandler);
        UiUtils.setupProgressBars(this, mDbHandler);

        TextView scenarioTextView = (TextView) findViewById(R.id.scenarioTextView);
        scenarioTextView.setText("Current Scene: " +
                getIntent().getExtras().getString(String.valueOf(R.string.scene)));

        TextView karmaPoints = (TextView) findViewById(R.id.karmaPoints);
        karmaPoints.setText(String.valueOf(SessionHistory.totalPoints));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeButton:
                finish();
                startActivity(new Intent(ScenarioOverActivity.this, StartActivity.class));
                break;
            case R.id.continueButton:
                GameActivity.gameActivityInstance.finish();
                startActivity(new Intent(ScenarioOverActivity.this, GameActivity.class));
                break;
        }
    }

    /**
     * If the "back" button is pressed, the current situation closes itself.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        GameActivity.gameActivityInstance.finish();
    }
}
