package powerup.systers.com.powerup.test;

import android.os.Build;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import powerup.systers.com.BuildConfig;
import powerup.systers.com.R;
import powerup.systers.com.minesweeper.MinesweeperGameActivity;
import powerup.systers.com.minesweeper.ProsAndConsActivity;
import powerup.systers.com.powerup.PowerUpUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sachinaggarwal on 02/07/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class ProsConsTests {

    ProsAndConsActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(ProsAndConsActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull( activity );
    }

    @Test
    public void setProsConsTextProperly(){
        activity.completedRounds = 2;
        activity.setTexts();
        TextView textView = (TextView)activity.findViewById(R.id.pro_one);
        assertEquals(textView.getText().toString(), PowerUpUtils.ROUNDS_PROS_CONS[1][0]);
    }

    @Test
    public void continueshouldLaunchActivity(){

    }
}
