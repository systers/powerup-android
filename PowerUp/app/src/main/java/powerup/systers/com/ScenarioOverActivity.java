/**
 * @desc finishes dialogue situation when the “continue” or “back” button is pressed.
 * Brings user to ending screen displaying current progress of power/health
 * bars and karma points.
 */

package powerup.systers.com;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;

import powerup.systers.com.datamodel.Scenario;
import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;
import powerup.systers.com.powerup.PowerUpUtils;

import static powerup.systers.com.R.string.scene;

public class ScenarioOverActivity extends AppCompatActivity {

    public Activity scenarioOverActivityInstance;
    public static int scenarioActivityDone;
    private DatabaseHandler mDbHandler;
    public Scenario scene;
    public static boolean showKarmaPoints;

    public ScenarioOverActivity() {
        scenarioOverActivityInstance = this;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmDbHandler(new DatabaseHandler(this));
        getmDbHandler().open();
        setContentView(R.layout.activity_scenario_over);
        // Karma Points Alert
        if (showKarmaPoints) {
            showAlert();
        }
        scene = getmDbHandler().getScenario();
        scenarioActivityDone = 1;
        ImageView replayButton = (ImageView) findViewById(R.id.replayButton);
        ImageView continueButton = (ImageView) findViewById(R.id.continueButton);
        Button mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKarmaPoints = false;
                finish();
                startActivity(new Intent(ScenarioOverActivity.this, MapActivity.class));
            }
        });

        TextView karmaPoints = (TextView) findViewById(R.id.karmaPoints);
        
        karmaPoints.setText(String.valueOf(SessionHistory.totalPoints));
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKarmaPoints = false;
                new GameActivity().gameActivityInstance.finish();
                startActivity(new Intent(ScenarioOverActivity.this, GameActivity.class));
            }
        });
        if (getIntent().getExtras()!=null && PowerUpUtils.MAP.equals(getIntent().getExtras().getString(PowerUpUtils.SOURCE))){
            continueButton.setVisibility(View.GONE);
            continueButton.setOnClickListener(null);
        }


        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKarmaPoints = false;
                SessionHistory.currSessionID = SessionHistory.prevSessionID;
                SessionHistory.totalPoints -= SessionHistory.currScenePoints;
                SessionHistory.currScenePoints = 0;
                scenarioActivityDone = 0;
                DatabaseHandler dbHandler = new DatabaseHandler(ScenarioOverActivity.this);
                dbHandler.resetCompleted(SessionHistory.currSessionID);
                dbHandler.resetReplayed(SessionHistory.currSessionID);
                new GameActivity().gameActivityInstance.finish();
                scenarioOverActivityInstance.finish();
                startActivity(new Intent(ScenarioOverActivity.this, GameActivity.class));
            }
        });
    }

    /**
     * If the "back" button is pressed, the current situation closes itself.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new GameActivity().gameActivityInstance.finish();
    }

    public void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ScenarioOverActivity.this);
        String msg = (getString(R.string.alert_dialog_start) + String.valueOf(SessionHistory.currScenePoints) + getString(R.string.alert_dialog_end));
        builder.setTitle(getString(R.string.alert_dialog_title))
                .setMessage(msg);
        AlertDialog dialog = builder.create();
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.alert_dialog_ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        ColorDrawable drawable = new ColorDrawable(Color.WHITE);
        drawable.setAlpha(200);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(drawable);
        dialog.getWindow().setLayout(800,450);
    }

    public DatabaseHandler getmDbHandler() {
        return mDbHandler;
    }

    public void setmDbHandler(DatabaseHandler mDbHandler) {
        this.mDbHandler = mDbHandler;
    }
}
