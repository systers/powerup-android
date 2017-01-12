/**
 * @desc displays avatar features, sets hasPreviouslyStarted to true, and
 * returns the user to map after clicking “continue”.
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
import powerup.systers.com.util.UiUtils;

public class AvatarActivity extends Activity implements View.OnClickListener {

    private int mFromActivity;
    private DatabaseHandler mDbHandler;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar);

        mDbHandler = new DatabaseHandler(this);
        mDbHandler.open();

        mFromActivity = getIntent().getExtras().getInt(getResources().getString(R.string.from_activity));

        setupLayout();
    }

    private void setupLayout() {
        findViewById(R.id.continueButton).setOnClickListener(this);
        findViewById(R.id.backButton).setOnClickListener(this);

        UiUtils.setupEyesFaceClothesHair(this, mDbHandler);
        setupBagGlassesHatNecklace();
    }

    private void setupBagGlassesHatNecklace() {
        ImageView bagView = (ImageView) findViewById(R.id.bagView);
        ImageView glassesView = (ImageView) findViewById(R.id.glassesView);
        ImageView hatView = (ImageView) findViewById(R.id.hatView);
        ImageView necklaceView = (ImageView) findViewById(R.id.necklaceView);

        if (mDbHandler.getAvatarBag() != 0) {
            String bagImageName = getResources().getString(R.string.bag);
            bagImageName = bagImageName + mDbHandler.getAvatarBag();
            UiUtils.setDrawable(bagImageName, bagView);
        }

        if (mDbHandler.getAvatarGlasses() != 0) {
            String glassesImageName = getResources().getString(R.string.glasses);
            glassesImageName = glassesImageName + mDbHandler.getAvatarGlasses();
            UiUtils.setDrawable(glassesImageName, glassesView);
        }

        if (mDbHandler.getAvatarHat() != 0) {
            String hatImageName = getResources().getString(R.string.hat);
            hatImageName = hatImageName + mDbHandler.getAvatarHat();
            UiUtils.setDrawable(hatImageName, hatView);
        }

        if (mDbHandler.getAvatarNecklace() != 0) {
            String necklaceImageName = getResources().getString(R.string.necklace);
            necklaceImageName = necklaceImageName + mDbHandler.getAvatarNecklace();
            UiUtils.setDrawable(necklaceImageName, necklaceView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continueButton:
                if (mFromActivity == 1) {
                    SharedPreferences prefs =
                            PreferenceManager.getDefaultSharedPreferences(AvatarActivity.this);
                    boolean hasPreviouslyStarted = prefs.getBoolean(
                            getString(R.string.preferences_has_previously_started), false);
                    if (!hasPreviouslyStarted) {
                        SharedPreferences.Editor edit = prefs.edit();
                        edit.putBoolean(getString(R.string.preferences_has_previously_started),
                                Boolean.TRUE);
                        edit.apply();
                    }
                    AvatarRoomActivity.avatarRoomInstance.finish();
                    finish();
                    startActivityForResult(new Intent(AvatarActivity.this, MapActivity.class), 0);
                } else {
                    DressingRoomActivity.dressingRoomInstance.finish();
                    SelectFeaturesActivity.selectFeatureInstance.finish();
                    finish();
                    startActivityForResult(new Intent(AvatarActivity.this, MapActivity.class), 0);
                }
                break;

            case R.id.backButton:
                finish();
                break;
        }
    }
}
