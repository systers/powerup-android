package powerup.systers.com.powerup.test;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowDrawable;


import powerup.systers.com.AvatarRoomActivity;
import powerup.systers.com.BuildConfig;
import powerup.systers.com.MapActivity;
import powerup.systers.com.R;
import powerup.systers.com.datamodel.SessionHistory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;



/**
 * Created by Phoebe on 30/12/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class AvatarRoomTests {
    AvatarRoomActivity activity;

    @Before
    public void setup() throws Exception {
        activity = Robolectric.buildActivity(AvatarRoomActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        assertNotNull(activity);
    }

    @Test
    public void continueButtonLaunchesMap() throws Exception {
        Class Map = MapActivity.class;
        Intent expectedIntent = new Intent(activity, Map);
        ImageView continueButton = (ImageView) activity.findViewById(R.id.continueButtonAvatar);

        continueButton.callOnClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(expectedIntent.filterEquals(actualIntent));
    }

    @Test
    public void rightEyeUpdatesAvatar() throws Exception {
        ImageView eyeAvatar = (ImageView) activity.findViewById(R.id.eye_view);
        ImageView eyeRight = (ImageView) activity.findViewById(R.id.eyes_right);
        Context context = eyeRight.getContext();

        // assumes the eye is set to 1 by default (it's a private variable, can't be
        // interrogated. Assume clicking right increases the eye variable and updates the
        // drawable - test all eyes work
        int maxEye = SessionHistory.eyesTotalNo;
        int eye = 1;

        for( int i = 1; i <= maxEye; i++) {
            String eyeImageName = "eyes";
            eyeImageName = eyeImageName + Integer.toString(eye);
            int expected = context.getResources().getIdentifier(eyeImageName, "drawable", context.getPackageName());
            ShadowDrawable shadowDrawable = Shadows.shadowOf(eyeAvatar.getDrawable());
            assertEquals(expected, shadowDrawable.getCreatedFromResId());
            eye = (eye + maxEye)
                    % maxEye + 1;
            eyeRight.callOnClick();
        }
    }

    @Test
    public void leftEyeUpdatesAvatar() throws Exception {
        ImageView eyeAvatar = (ImageView) activity.findViewById(R.id.eye_view);
        ImageView eyeLeft = (ImageView) activity.findViewById(R.id.eyes_left);
        Context context = eyeLeft.getContext();

        // assumes the eye is set to 1 by default (it's a private variable, can't be
        // interrogated. Assume clicking left decreases the eye variable and updates the
        // drawable - test all eyes work
        int maxEye = SessionHistory.eyesTotalNo;
        int eye = 1;

        for(int i = 1; i <= maxEye; i++) {
            String eyeImageName = "eyes";
            eyeImageName = eyeImageName + Integer.toString(eye);
            int expected = context.getResources().getIdentifier(eyeImageName, "drawable", context.getPackageName());
            ShadowDrawable shadowDrawable = Shadows.shadowOf(eyeAvatar.getDrawable());
            assertEquals(expected, shadowDrawable.getCreatedFromResId());
            eye = (eye - 1) % maxEye;
            if (eye == 0) {
                eye = maxEye;
            }
            eyeLeft.callOnClick();
        }
    }

    @Test
    public void leftSkinUpdatesAvatar() throws Exception {
        ImageView skinAvatar = (ImageView) activity.findViewById(R.id.skin_view);
        ImageView skinLeft = (ImageView) activity.findViewById(R.id.skin_left);
        Context context = skinLeft.getContext();

        // assumes the skin is set to 1 by default (it's a private variable, can't be
        // interrogated. Assume clicking left decreases the skin variable and updates the
        // drawable - test all skin work
        int maxSkin = SessionHistory.skinTotalNo;
        int skin = 1;

        for(int i = 1; i <= maxSkin; i++) {
            String skinImageName = "skin";
            skinImageName = skinImageName + Integer.toString(skin);
            int expected = context.getResources().getIdentifier(skinImageName, "drawable", context.getPackageName());
            ShadowDrawable shadowDrawable = Shadows.shadowOf(skinAvatar.getDrawable());
            assertEquals(expected, shadowDrawable.getCreatedFromResId());
            skin = (skin - 1) % maxSkin;
            if (skin == 0) {
                skin = maxSkin;
            }
            skinLeft.callOnClick();
        }
    }

    @Test
    public void rightSkinUpdatesAvatar() throws Exception {
        ImageView skinAvatar = (ImageView) activity.findViewById(R.id.skin_view);
        ImageView skinRight = (ImageView) activity.findViewById(R.id.skin_right);
        Context context = skinRight.getContext();

        // assumes the skin is set to 1 by default (it's a private variable, can't be
        // interrogated. Assume clicking right increases the skin variable and updates the
        // drawable - test all skin work
        int maxSkin = SessionHistory.skinTotalNo;
        int skin = 1;

        for(int i = 1; i <= maxSkin; i++) {
            String skinImageName = "skin";
            skinImageName = skinImageName + Integer.toString(skin);
            int expected = context.getResources().getIdentifier(skinImageName, "drawable", context.getPackageName());
            ShadowDrawable shadowDrawable = Shadows.shadowOf(skinAvatar.getDrawable());
            assertEquals(expected, shadowDrawable.getCreatedFromResId());
            skin = (skin + maxSkin)
                    % maxSkin + 1;
            skinRight.callOnClick();
        }
    }

    @Test
    public void leftClothUpdatesAvatar() throws Exception {
        ImageView clothAvatar = (ImageView) activity.findViewById(R.id.dress_view);
        ImageView clothLeft = (ImageView) activity.findViewById(R.id.clothes_left);
        Context context = clothLeft.getContext();

        // assumes the cloth is set to 1 by default (it's a private variable, can't be
        // interrogated. Assume clicking left decreases the cloth variable and updates the
        // drawable - test all cloth work
        int maxCloth = SessionHistory.clothTotalNo;
        int cloth = 1;

        for(int i = 1; i <= maxCloth; i++) {
            String clothImageName = "dress_avatar";
            clothImageName = clothImageName + Integer.toString(cloth);
            int expected = context.getResources().getIdentifier(clothImageName, "drawable", context.getPackageName());
            ShadowDrawable shadowDrawable = Shadows.shadowOf(clothAvatar.getDrawable());
            assertEquals(expected, shadowDrawable.getCreatedFromResId());
            cloth = (cloth - 1) % maxCloth;
            if (cloth == 0) {
                cloth = maxCloth;
            }
            clothLeft.callOnClick();
        }
    }

    @Test
    public void rightClothUpdatesAvatar() throws Exception {
        ImageView clothAvatar = (ImageView) activity.findViewById(R.id.dress_view);
        ImageView clothRight = (ImageView) activity.findViewById(R.id.clothes_right);
        Context context = clothRight.getContext();

        // assumes the cloth is set to 1 by default (it's a private variable, can't be
        // interrogated. Assume clicking right increases the cloth variable and updates the
        // drawable - test all cloth work
        int maxCloth = SessionHistory. clothTotalNo;
        int cloth = 1;

        for(int i = 1; i <= maxCloth; i++) {
            String clothImageName = "dress_avatar";
            clothImageName = clothImageName + Integer.toString(cloth);
            int expected = context.getResources().getIdentifier(clothImageName, "drawable", context.getPackageName());
            ShadowDrawable shadowDrawable = Shadows.shadowOf(clothAvatar.getDrawable());
            assertEquals(expected, shadowDrawable.getCreatedFromResId());
            cloth = (cloth + maxCloth)
                    % maxCloth + 1;
            clothRight.callOnClick();
        }
    }

    @Test
    public void leftHairUpdatesAvatar() throws Exception {
        ImageView hairAvatar = (ImageView) activity.findViewById(R.id.hair_view);
        ImageView hairLeft = (ImageView) activity.findViewById(R.id.hair_left);
        Context context = hairLeft.getContext();

        // assumes the hair is set to 1 by default (it's a private variable, can't be
        // interrogated. Assume clicking left decreases the hair variable and updates the
        // drawable - test all hair work
        int maxHair = SessionHistory.hairTotalNo;
        int hair = 1;

        for(int i = 1; i <= maxHair; i++) {
            String hairImageName = "hair";
            hairImageName = hairImageName + Integer.toString(hair);
            int expected = context.getResources().getIdentifier(hairImageName, "drawable", context.getPackageName());
            ShadowDrawable shadowDrawable = Shadows.shadowOf(hairAvatar.getDrawable());
            assertEquals(expected, shadowDrawable.getCreatedFromResId());
            hair = (hair - 1) % maxHair;
            if (hair == 0) {
                hair = maxHair;
            }
            hairLeft.callOnClick();
        }
    }

    @Test
    public void rightHairUpdatesAvatar() throws Exception {
        ImageView hairAvatar = (ImageView) activity.findViewById(R.id.hair_view);
        ImageView hairRight = (ImageView) activity.findViewById(R.id.hair_right);
        Context context = hairRight.getContext();

        // assumes the hair is set to 1 by default (it's a private variable, can't be
        // interrogated. Assume clicking right increases the hair variable and updates the
        // drawable - test all hair work
        int maxHair = SessionHistory.hairTotalNo;
        int hair = 1;

        for(int i = 1; i <= maxHair; i++) {
            String hairImageName = "hair";
            hairImageName = hairImageName + Integer.toString(hair);
            int expected = context.getResources().getIdentifier(hairImageName, "drawable", context.getPackageName());
            ShadowDrawable shadowDrawable = Shadows.shadowOf(hairAvatar.getDrawable());
            assertEquals(expected, shadowDrawable.getCreatedFromResId());
            hair = (hair + maxHair)
                    % maxHair + 1;
            hairRight.callOnClick();
        }
    }

}