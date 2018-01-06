/**
 * @desc finishes dialogue situation when the “continue” or “back” button is pressed.
 * Brings user to ending screen displaying current progress of power/health
 * bars and karma points.
 */

package powerup.systers.com;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.tv.TvInputService;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        scene = getmDbHandler().getScenario();
        scenarioActivityDone = 1;
        ImageView replayButton = (ImageView) findViewById(R.id.replayButton);
        ImageView continueButton = (ImageView) findViewById(R.id.continueButton);
        Button mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PowerUpUtils.SHOW_DIALOG=false;
                finish();
                startActivity(new Intent(ScenarioOverActivity.this, MapActivity.class));
            }
        });

        TextView karmaPoints = (TextView) findViewById(R.id.karmaPoints);
        
        karmaPoints.setText(String.valueOf(SessionHistory.totalPoints));
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PowerUpUtils.SHOW_DIALOG=true;
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
                PowerUpUtils.SHOW_DIALOG=true;
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

        if (PowerUpUtils.SHOW_DIALOG || SessionHistory.prevSessionID==SessionHistory.currSessionID) {
            String overTitleMessage = getResources().getString(R.string.over_title_message);
            String overDismissMessage = getResources().getString(R.string.over_dismiss_message);
            String overDialogMessage = getResources().getString(R.string.over_dialog_start_message) + " ";
            overDialogMessage += SessionHistory.currScenePoints + " " + getResources().getString(R.string.over_dialog_end_message);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int dialogHeight = (int) (displayMetrics.heightPixels * 0.5);
            int dialogWidth = (int) (displayMetrics.widthPixels * 0.43 );

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ScenarioOverActivity.this);
            alertDialogBuilder
                    .setTitle(overTitleMessage)
                    .setMessage(overDialogMessage)
                    .setCancelable(false)
                    .setNeutralButton(overDismissMessage, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });

            TextView titleText = new TextView(this);
            titleText.setGravity(Gravity.CENTER_HORIZONTAL);
            titleText.setText(overTitleMessage);
            titleText.setTextSize(20);
            titleText.setTypeface(null, Typeface.BOLD);
            titleText.setPadding(5,20,5,5);
            titleText.setTextColor(getResources().getColor(R.color.powerup_black));
            alertDialogBuilder.setCustomTitle(titleText);
            final AlertDialog alert = alertDialogBuilder.create();

            alert.show();
            alert.getWindow().setLayout(dialogWidth,dialogHeight);

            TextView messageText = (TextView) alert.findViewById(android.R.id.message);
            messageText.setPadding(5,10,5,5);
            messageText.setGravity(Gravity.CENTER);

            final Button dismissButton = alert.getButton(AlertDialog.BUTTON_NEUTRAL);
            LinearLayout.LayoutParams dismissButtonLL = (LinearLayout.LayoutParams) dismissButton.getLayoutParams();
            dismissButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
            dismissButton.setLayoutParams(dismissButtonLL);
        }
    }

    /**
     * If the "back" button is pressed, the current situation closes itself.
     */
    @Override
    public void onBackPressed() {
        PowerUpUtils.SHOW_DIALOG=true;
        super.onBackPressed();
        new GameActivity().gameActivityInstance.finish();
    }

    public DatabaseHandler getmDbHandler() {
        return mDbHandler;
    }

    public void setmDbHandler(DatabaseHandler mDbHandler) {
        this.mDbHandler = mDbHandler;
    }
}
