package powerup.systers.com.powerup.test;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import powerup.systers.com.BuildConfig;
import powerup.systers.com.powerup.PowerUpUtils;
import powerup.systers.com.sink_to_swim_game.SinkToSwimGame;
import powerup.systers.com.vocab_match_game.VocabMatchEndActivity;
import powerup.systers.com.vocab_match_game.VocabMatchGameActivity;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sachinaggarwal on 21/08/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class VocabMatchTests {

    VocabMatchGameActivity activity;

    //Setting Up VocabMatchGameActivity
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(VocabMatchGameActivity.class);
    }

    //Checking for Null Exceptions in VocabMatchGameActivity
    @Test
    public void shouldNotBeNull() throws Exception {

        assertNotNull(activity);
    }

    //Testing the end of VocabMatchGame when tiles finish
    @Test
    public void vocabEndActivityOnTilesFinished() {

        Class VocabMatchEnd = VocabMatchEndActivity.class;
        Intent expectedIntent = new Intent(activity, VocabMatchEnd);

        activity.latestTile = PowerUpUtils.VOCAB_TILES_IMAGES.length + 1;
        activity.startNewTile(1,activity.img1);

        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        //Comparing the expected vs the actual Intent
        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    //Testing the correct position of the text
    @Test
    public void getPositionFromText(){

        activity.tv1.setText("test1");
        activity.tv2.setText("test2");
        activity.tv3.setText("test3");

        TextView actual = activity.getPositionFromText("test2");

        //Comparing the expected vs the actual position
        assertEquals(activity.tv2,actual);
    }

    //Testing the Board from the position of the text
    @Test
    public void getBoardFromPosition(){

        activity.tv1.setPosition(1);
        activity.tv2.setPosition(2);
        activity.tv3.setPosition(3);

        TextView actual = activity.getBoardFromPosition(2);

        //Comparing the expected vs the actual Board
        assertEquals(activity.tv2,actual);
    }
}
