package powerup.systers.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import powerup.systers.com.datamodel.Tutorial;

public class TutorialActivity extends AppCompatActivity {

    private LinearLayout indicatingDotsContainer;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getBoolean(String.valueOf(R.string.already_visited), false)) {
            startActivity(new Intent(this, StartActivity.class)); //skip tutorials if already visited
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tutorial);
        TutorialAdapter mAdapter = new TutorialAdapter(this);
        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        indicatingDotsContainer = (LinearLayout) findViewById(R.id.layout_dots);
        addBottomDots(0); //adds indicating dots to given slide i.e current slide

        assert mPager != null;
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    private void addBottomDots(int currentSlide) {

        TextView[] dots = new TextView[Tutorial.values().length];
        int dotColorActive = getResources().getColor(R.color.selected_dot);
        indicatingDotsContainer.removeAllViews();

        //number of dots added to container equals number of slides
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#9675;"));
            dots[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            indicatingDotsContainer.addView(dots[i]);
        }

        //dot corresponding to current slide is given active color i.e white color
        if (dots.length > 0) {
            dots[currentSlide].setTextColor(dotColorActive);
            dots[currentSlide].setText(Html.fromHtml("&#8226;"));
            dots[currentSlide].setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
        }
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        //invoked when slide is changed
        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

    };
}
