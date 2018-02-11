package powerup.systers.com;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends Activity {

    private boolean isAboutGameOpen = false;
    private boolean isAboutUrgencyOpen = false;
    private boolean isAboutHelpingOpen = false;
    private static String isGameOpen = "ABOUT_GAME_OPEN";
    private static String isUrgencyOpen = "ABOUT_URGENCY_OPEN";
    private static String isHelpingOpen = "ABOUT_HELPING_OPEN";
    private TextView aboutGameSection, aboutUrgencySection, aboutHelpingSection;
    private static final String TAG = AboutActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        if (savedInstanceState != null){
            isAboutGameOpen = savedInstanceState.getBoolean(isGameOpen);
            isAboutUrgencyOpen = savedInstanceState.getBoolean(isUrgencyOpen);
            isAboutHelpingOpen = savedInstanceState.getBoolean(isHelpingOpen);
        }
        aboutGameSection = (TextView) findViewById(R.id.about_the_game);
        aboutUrgencySection = (TextView) findViewById(R.id.about_the_urgency);
        aboutHelpingSection = (TextView) findViewById(R.id.about_helping_by);
        if (isAboutGameOpen){
            Log.d(TAG, "onCreate() setting visibility of aboutGameSection to VISIBLE");
           aboutGameSection.setVisibility(View.VISIBLE);
        }
        if (isAboutUrgencyOpen){
            Log.d(TAG, "onCreate() setting visibility of aboutUrgencySection to VISIBLE");
            aboutUrgencySection.setVisibility(View.VISIBLE);
        }
        if (isAboutHelpingOpen){
            Log.d(TAG, "onCreate setting visibility of aboutHelpingSection to VISIBLE");
            aboutHelpingSection.setVisibility(View.VISIBLE);
        }

    }
    public void aboutGamePressed(View view){
        if (aboutGameSection.getVisibility() == View.GONE){
            Log.d(TAG,"aboutGamePressed() setting visibility of aboutGameSection to VISIBLE");
            aboutGameSection.setVisibility(View.VISIBLE);
            Log.d(TAG, "aboutGamePressed() setting isAboutGameOpen to true");
            isAboutGameOpen = true;
        } else {
            Log.d(TAG, "aboutGamePressed() setting visibility of aboutGameSection to GONE");
            aboutGameSection.setVisibility(View.GONE);
            Log.d(TAG, "aboutGamePressed() setting isAboutGameOpen to false");
            isAboutGameOpen = false;
        }
    }

    public void aboutUrgencyPressed(View view){
        if (aboutUrgencySection.getVisibility() == View.GONE){
            Log.d(TAG, "aboutUrgencyPressed() setting visibility of aboutUrgencySection to VISIBLE");
            aboutUrgencySection.setVisibility(View.VISIBLE);
            Log.d(TAG, "aboutUrgencyPressed() setting isAboutUrgencyOpen to true");
            isAboutUrgencyOpen = true;
        } else {
            Log.d(TAG, "aboutUrgencyPressed() setting visibility of aboutUrgencySection to GONE");
            aboutUrgencySection.setVisibility(View.GONE);
            Log.d(TAG, "aboutUrgencyPressed() setting isAboutUrgencyOpen to false");
            isAboutUrgencyOpen = false;
        }
    }

    public void aboutHelpingByPressed(View view){
        if (aboutHelpingSection.getVisibility() == View.GONE){
            Log.d(TAG, "aboutHelpingByPressed() setting visibility of aboutHelpingSection to VISIBLE");
            aboutHelpingSection.setVisibility(View.VISIBLE);
            Log.d(TAG, "aboutHelpingByPressed() setting isAboutHelpingOpen to true");
            isAboutHelpingOpen = true;
        } else {
            Log.d(TAG, "aboutHelpingByPressed() setting visibility of aboutHelpingSection to GONE");
            aboutHelpingSection.setVisibility(View.GONE);
            Log.d(TAG, "aboutHelpingByPressed() setting isAboutHelpingOpen to false");
            isAboutHelpingOpen = false;
        }
    }

    public void pressHomeButton(View view){
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(isGameOpen,isAboutGameOpen);
        outState.putBoolean(isHelpingOpen,isAboutHelpingOpen);
        outState.putBoolean(isUrgencyOpen,isAboutUrgencyOpen);
    }
}
