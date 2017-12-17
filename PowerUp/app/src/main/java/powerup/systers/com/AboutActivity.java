package powerup.systers.com;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends Activity {

    private static final String TAG = "Mymessage";
    private boolean isAboutGameOpen = false;
    private boolean isAboutUrgencyOpen = false;
    private boolean isAboutHelpingOpen = false;
    private static String isGameOpen = "ABOUT_GAME_OPEN";
    private static String isUrgencyOpen = "ABOUT_URGENCY_OPEN";
    private static String isHelpingOpen = "ABOUT_HELPING_OPEN";
    private TextView aboutGameSection, aboutUrgencySection, aboutHelpingSection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"Building the AboutActivity");
        Log.v(TAG,"Verbose for Building Activity");
        Log.i(TAG,"About Activity Awakening");
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
            Log.d(TAG,"Checking Visibility of Game Section");
           aboutGameSection.setVisibility(View.VISIBLE);
        }
        if (isAboutUrgencyOpen){
            aboutUrgencySection.setVisibility(View.VISIBLE);
        }
        if (isAboutHelpingOpen){
            aboutHelpingSection.setVisibility(View.VISIBLE);
        }

    }
    public void aboutGamePressed(View view){
        Log.d(TAG,"Info about the game");
        Log.i(TAG,"Game Info will Open or Close");
        Log.w(TAG,"Game Info will Open or Close");
        if (aboutGameSection.getVisibility() == View.GONE){
            aboutGameSection.setVisibility(View.VISIBLE);
            isAboutGameOpen = true;
        } else {
            aboutGameSection.setVisibility(View.GONE);
            isAboutGameOpen = false;
        }
    }

    public void aboutUrgencyPressed(View view){
        Log.d(TAG,"Info about the urgency of the game");
        Log.v(TAG,"Info about the urgency true or false = open or close");
        if (aboutUrgencySection.getVisibility() == View.GONE){
            aboutUrgencySection.setVisibility(View.VISIBLE);
            isAboutUrgencyOpen = true;
        } else {
            aboutUrgencySection.setVisibility(View.GONE);
            isAboutUrgencyOpen = false;
        }
    }

    public void aboutHelpingByPressed(View view){
        Log.d(TAG,"Info about how the game helps teenager");
        Log.i(TAG,"Helping Info will open or close");
        if (aboutHelpingSection.getVisibility() == View.GONE){
            aboutHelpingSection.setVisibility(View.VISIBLE);
            isAboutHelpingOpen = true;
        } else {
            aboutHelpingSection.setVisibility(View.GONE);
            isAboutHelpingOpen = false;
        }
    }

    public void pressHomeButton(View view){
        Log.d(TAG,"Destroying Activity, Going to StartActivity");
        Log.w(TAG,"AboutActvity to be destroyed");
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG,"Saving the status of info");
        Log.i(TAG,"Saving the status of info");
        super.onSaveInstanceState(outState);
        outState.putBoolean(isGameOpen,isAboutGameOpen);
        outState.putBoolean(isHelpingOpen,isAboutHelpingOpen);
        outState.putBoolean(isUrgencyOpen,isAboutUrgencyOpen);
    }
}
