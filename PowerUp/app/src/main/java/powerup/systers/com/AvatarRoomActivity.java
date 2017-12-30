/**
 * @desc sets up the “Avatar Room” for user to customize avatar features.
 * Allows user to scroll through different skin/hair/clothing options.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;

public class AvatarRoomActivity extends Activity {

    public Activity avatarRoomInstance;
    private DatabaseHandler mDbHandler;
    private ImageView eyeAvatar;
    private ImageView skinAvatar;
    private ImageView clothAvatar;
    private ImageView hairAvatar;
    private Integer eye = 1;
    private Integer hair = 1;
    private Integer skin = 1;
    private Integer cloth = 1;

    public AvatarRoomActivity() {
        avatarRoomInstance = this;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmDbHandler(new DatabaseHandler(this));
        getmDbHandler().open();
        setContentView(R.layout.avatar_room);
        eyeAvatar = (ImageView) findViewById(R.id.eye_view);
        hairAvatar = (ImageView) findViewById(R.id.hair_view);
        skinAvatar = (ImageView) findViewById(R.id.skin_view);
        clothAvatar = (ImageView) findViewById(R.id.dress_view);
        ImageView eyeLeft = (ImageView) findViewById(R.id.eyes_left);
        ImageView eyeRight = (ImageView) findViewById(R.id.eyes_right);
        ImageView skinLeft = (ImageView) findViewById(R.id.skin_left);
        ImageView skinRight = (ImageView) findViewById(R.id.skin_right);
        final ImageView clothLeft = (ImageView) findViewById(R.id.clothes_left);
        ImageView clothRight = (ImageView) findViewById(R.id.clothes_right);
        final ImageView hairLeft = (ImageView) findViewById(R.id.hair_left);
        ImageView hairRight = (ImageView) findViewById(R.id.hair_right);
        ImageView continueButton = (ImageView) findViewById(R.id.continueButtonAvatar);

        eyeLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eye = (eye - 1) % SessionHistory.eyesTotalNo;
                if (eye == 0) {
                    eye = SessionHistory.eyesTotalNo;
                }
                String eyeImageName = getResources().getString(R.string.eye);
                eyeImageName = eyeImageName + eye.toString();
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(eyeImageName);
                    eyeAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });

        eyeRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eye = (eye + SessionHistory.eyesTotalNo)
                        % SessionHistory.eyesTotalNo + 1;
                String eyeImageName = getResources().getString(R.string.eye);
                eyeImageName = eyeImageName + eye.toString();
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(eyeImageName);
                    eyeAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });

        skinLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skin = (skin - 1) % SessionHistory.skinTotalNo;
                if (skin == 0) {
                    skin = SessionHistory.skinTotalNo;
                }
                String skinImageName = getResources().getString(R.string.skin);
                skinImageName = skinImageName + skin.toString();
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(skinImageName);
                    skinAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });

        skinRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skin = (skin + SessionHistory.skinTotalNo)
                        % SessionHistory.skinTotalNo + 1;
                String skinImageName = getResources().getString(R.string.skin);
                skinImageName = skinImageName + skin.toString();
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(skinImageName);
                    skinAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });

        clothLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloth = (cloth - 1) % SessionHistory.clothTotalNo;
                if (cloth == 0) {
                    cloth = SessionHistory.clothTotalNo;
                }
                String clothImageName = getResources().getString(R.string.cloth);
                clothImageName = clothImageName + cloth.toString();
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(clothImageName);
                    clothAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });

        clothRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloth = (cloth + SessionHistory.clothTotalNo)
                        % SessionHistory.clothTotalNo + 1;
                String clothImageName = getResources().getString(R.string.cloth);
                clothImageName = clothImageName + cloth.toString();
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(clothImageName);
                    clothAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });

        hairLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hair = (hair - 1) % SessionHistory.hairTotalNo;
                if (hair == 0) {
                    hair = SessionHistory.hairTotalNo;
                }
                String hairImageName = getResources().getString(R.string.hair);
                hairImageName = hairImageName + hair.toString();
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(hairImageName);
                    hairAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });

        hairRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hair = (hair + SessionHistory.hairTotalNo)
                        % SessionHistory.hairTotalNo + 1;
                String hairImageName = getResources().getString(R.string.hair);
                hairImageName = hairImageName + hair.toString();
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(hairImageName);
                    hairAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmDbHandler().open();
                getmDbHandler().resetPurchase();
                getmDbHandler().setAvatarEye(eye);
                getmDbHandler().setAvatarSkin(skin);
                getmDbHandler().setAvatarHair(hair);
                getmDbHandler().setAvatarCloth(cloth);
                getmDbHandler().setPurchasedHair(hair);
                getmDbHandler().setPurchasedClothes(cloth);
                getmDbHandler().updateComplete();//set all the complete fields back to 0
                getmDbHandler().updateReplayed();//set all the replayed fields back to 0
                SessionHistory.totalPoints = 0;    //reset the points stored
                SessionHistory.currSessionID = 1;
                SessionHistory.currScenePoints = 0;

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AvatarRoomActivity.this);
                boolean hasPreviouslyStarted = prefs.getBoolean(getString(R.string.preferences_has_previously_started), false);
                if (!hasPreviouslyStarted) {
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putBoolean(getString(R.string.preferences_has_previously_started), Boolean.TRUE);
                    edit.apply();
                }
                startActivityForResult(new Intent(AvatarRoomActivity.this, FinalAvatarActivity.class), 0);

            }
        });
        getmDbHandler().close();
    }

    public DatabaseHandler getmDbHandler() {
        return mDbHandler;
    }

    public void setmDbHandler(DatabaseHandler mDbHandler) {
        this.mDbHandler = mDbHandler;
    }
}
