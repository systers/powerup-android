package powerup.systers.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    private SharedPreferences sharedPreferences;
    private boolean isfirstRun;

    @Override
    public void init(Bundle savedInstanceState) {

        sharedPreferences = getSharedPreferences("prefs", 0);
        isfirstRun = sharedPreferences.getBoolean("firstRun", true);
        if (isfirstRun) {
            addSlide(new IntroFragment(1));
            addSlide(new IntroFragment(2));
            addSlide(new IntroFragment(3));
            addSlide(new IntroFragment(4));
            addSlide(new IntroFragment(5));
            addSlide(new IntroFragment(6));
        } else {
            loadMainActivity();
        }
    }

    private void loadMainActivity() {
        sharedPreferences = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putBoolean("firstRun", false);
        sharedPreferencesEditor.commit();
        Intent startPageIntent = new Intent(this, StartActivity.class);
        startActivity(startPageIntent);
        finish();
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onSlideChanged() {

    }


}