/**
 * @desc sets up the “Final Avatar Room” for user to review their avatar.
 * Allows user to review the avatar that they selected in "Avatar Room", and go back to make any more changes
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import powerup.systers.com.db.DatabaseHandler;

public class FinalAvatarActivity extends Activity {

    public Activity finalAvatarInstance;
    private DatabaseHandler mDbHandler;

    public FinalAvatarActivity() {
        finalAvatarInstance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmDbHandler(new DatabaseHandler(this));
        getmDbHandler().open();
        setContentView(R.layout.activity_final_avatar);

        ImageView eyeAvatar = (ImageView) findViewById(R.id.eye_view),
                hairAvatar = (ImageView) findViewById(R.id.hair_view),
                skinAvatar = (ImageView) findViewById(R.id.skin_view),
                clothAvatar = (ImageView) findViewById(R.id.dress_view);
        final ImageView continueButton = (ImageView) findViewById(R.id.continueButtonAvatar),
                backButton = (ImageView) findViewById(R.id.backButtonAvatar);

        Integer eye = getmDbHandler().getAvatarEye(),
                hair = getmDbHandler().getAvatarHair(),
                skin = getmDbHandler().getAvatarSkin(),
                cloth = getmDbHandler().getAvatarCloth();

        R.drawable ourRID = new R.drawable();
        java.lang.reflect.Field photoNameField;

        String eyeImageName = getResources().getString(R.string.eye);
        eyeImageName = eyeImageName + eye.toString();
        try {
            photoNameField = ourRID.getClass().getField(eyeImageName);
            eyeAvatar.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }
        String skinImageName = getResources().getString(R.string.skin);
        skinImageName = skinImageName + skin.toString();
        try {
            photoNameField = ourRID.getClass().getField(skinImageName);
            skinAvatar.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }
        String clothImageName = getResources().getString(R.string.cloth);
        clothImageName = clothImageName + cloth.toString();
        try {
            photoNameField = ourRID.getClass().getField(clothImageName);
            clothAvatar.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }
        String hairImageName = getResources().getString(R.string.hair);
        hairImageName = hairImageName + hair.toString();
        try {
            photoNameField = ourRID.getClass().getField(hairImageName);
            hairAvatar.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(FinalAvatarActivity.this);
                boolean hasPreviouslyStarted = prefs.getBoolean(getString(R.string.preferences_has_previously_started), false);
                if (!hasPreviouslyStarted) {
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putBoolean(getString(R.string.preferences_has_previously_started), Boolean.TRUE);
                    edit.apply();
                }
                finish();
                startActivityForResult(new Intent(FinalAvatarActivity.this, MapActivity.class), 0);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // avatar room is at the previous position of the stack of activities
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
