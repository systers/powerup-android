package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.ColorRes;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import powerup.systers.com.datamodel.Tutorial;

/**
 * Created by sachinaggarwal on 11/02/17.
 */

public class TutorialAdapter extends PagerAdapter {

    private final Activity mActivity;
    private final Tutorial[] mTutorials;
    private final ListColor[] colors = ListColor.values();
    private SharedPreferences preferences;

    public TutorialAdapter(Activity activity) {
        mActivity = activity;
        mTutorials = Tutorial.values();
    }

    @Override
    public int getCount() {
        return mTutorials.length;
    }

    private Tutorial getItem(int position) {
        return mTutorials[position];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        Tutorial tutorial = getItem(position);
        View convertView = View.inflate(mActivity, R.layout.tutorial_layout, null);
        //replaces skip button by done button on last intro slide
        if (position == mTutorials.length - 1) {
            Button skipButton = (Button) convertView.findViewById(R.id.skip_button);
            skipButton.setText(R.string.done);
            skipButton.setScaleX((float) 1.8);
            skipButton.setScaleY((float) 1.8);
            skipButton.setTypeface(null, Typeface.BOLD);
        }
        ImageView deviceImage = (ImageView) convertView.findViewById(R.id.device_image);
        TextView title = (TextView) convertView.findViewById(R.id.tutorial_title);
        TextView description = (TextView) convertView.findViewById(R.id.tutorial_desc);
        int color = colors[position % colors.length].getColor();
        convertView.findViewById(R.id.tutorial_layout).setBackgroundColor(color);
        deviceImage.setImageResource(tutorial.getImage());
        title.setText(tutorial.getTitle());
        description.setText(tutorial.getDescription());

        convertView.findViewById(R.id.skip_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mark preference as true so that tutorials are not opened on subsequent visits
                preferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean(String.valueOf(R.string.already_visited), Boolean.TRUE);
                edit.apply();
                mActivity.startActivity(new Intent(mActivity, StartActivity.class));
                mActivity.finish();
            }
        });
        container.addView(convertView, 0);
        return convertView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ViewGroup) object);
    }

    private enum ListColor {
        //list of colors for slides
        BLUE("#29A6D4"),
        ORANGE("#F77400"),
        PURPLE("#AB47BC"),
        RED("#FF4365"),
        YELLOW("#F9A01E");

        private
        @ColorRes
        final
        int color;

        ListColor(String colorCode) {
            this.color = Color.parseColor(colorCode);
        }

        public int getColor() {
            return color;
        }
    }
}
