package powerup.systers.com;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class AppIntroActivity extends AppIntro {

    @Override
    public void init(Bundle savedInstanceState) {

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(new Screen1Fragment());
        addSlide(new Screen2Fragment());
        addSlide(new Screen3Fragment());
        addSlide(new Screen4Fragment());

//        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.screen_1_title),
//                getResources().getString(R.string.screen_1_desc),
//                R.drawable.screenintro1, getResources().getColor(R.color.powerup_yellow)));
//        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.screen_2_title),
//                getResources().getString(R.string.screen_2_desc),
//                R.drawable.screenintro2, getResources().getColor(R.color.powerup_yellow)));
//        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.screen_3_title),
//                getResources().getString(R.string.screen_3_desc),
//                R.drawable.screenintro3, getResources().getColor(R.color.powerup_yellow)));
//        addSlide(AppIntroFragment.newInstance(getResources().getString(R.string.screen_4_title),
//                getResources().getString(R.string.screen_4_desc),
//                R.drawable.screenintro4, getResources().getColor(R.color.powerup_yellow)));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(true);
        showDoneButton(true);
        setProgressButtonEnabled(false);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        Intent i = new Intent(AppIntroActivity.this, StartActivity.class);
        startActivity(i);
    }

    @Override
    public void onSlideChanged() {
        // Do something when the slide changes.
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

}
