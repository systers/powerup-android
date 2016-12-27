package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import powerup.systers.com.db.DatabaseHandler;

public class AvatarActivity extends Activity implements View.OnClickListener {

    private int mFromActivity;
    private DatabaseHandler mDbHandler;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avatar);

        mDbHandler = new DatabaseHandler(this);
        mDbHandler.open();
        mFromActivity = getIntent().getExtras().getInt(getResources().getString(R.string.from_activity));

        findViewById(R.id.continueButton).setOnClickListener(this);
        findViewById(R.id.backButton).setOnClickListener(this);

        ImageView eyeView = (ImageView) findViewById(R.id.eyeView);
        ImageView faceView = (ImageView) findViewById(R.id.faceView);
        ImageView hairView = (ImageView) findViewById(R.id.hairView);
        ImageView clothView = (ImageView) findViewById(R.id.clothView);
        ImageView bagView = (ImageView) findViewById(R.id.bagView);
        ImageView glassesView = (ImageView) findViewById(R.id.glassesView);
        ImageView hatView = (ImageView) findViewById(R.id.hatView);
        ImageView necklaceView = (ImageView) findViewById(R.id.necklaceView);

        String eyeImageName =getResources().getString(R.string.eye);
        eyeImageName = eyeImageName + mDbHandler.getAvatarEye();
        R.drawable ourRID = new R.drawable();
        java.lang.reflect.Field photoNameField;
        try {
            photoNameField = ourRID.getClass().getField(eyeImageName);
            eyeView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String faceImageName = getResources().getString(R.string.face);
        faceImageName = faceImageName + mDbHandler.getAvatarFace();
        try {
            photoNameField = ourRID.getClass().getField(faceImageName);
            faceView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String clothImageName = getResources().getString(R.string.cloth);
        clothImageName = clothImageName + mDbHandler.getAvatarCloth();
        try {
            photoNameField = ourRID.getClass().getField(clothImageName);
            clothView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String hairImageName = getResources().getString(R.string.hair);
        hairImageName = hairImageName + mDbHandler.getAvatarHair();
        try {
            photoNameField = ourRID.getClass().getField(hairImageName);
            hairView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(mDbHandler.getAvatarBag()!= 0){
            String bagImageName = getResources().getString(R.string.bag);
            bagImageName = bagImageName + mDbHandler.getAvatarBag();
            try {
                photoNameField = ourRID.getClass().getField(bagImageName);
                bagView.setImageResource(photoNameField.getInt(ourRID));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if(mDbHandler.getAvatarGlasses()!= 0){
            String glassesImageName = getResources().getString(R.string.glasses);
            glassesImageName = glassesImageName + mDbHandler.getAvatarGlasses();
            try {
                photoNameField = ourRID.getClass().getField(glassesImageName);
                glassesView.setImageResource(photoNameField.getInt(ourRID));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if(mDbHandler.getAvatarHat()!= 0){
            String hatImageName = getResources().getString(R.string.hat);
            hatImageName = hatImageName + mDbHandler.getAvatarHat();
            try {
                photoNameField = ourRID.getClass().getField(hatImageName);
                hatView.setImageResource(photoNameField.getInt(ourRID));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if(mDbHandler.getAvatarNecklace()!= 0){
            String necklaceImageName = getResources().getString(R.string.necklace);
            necklaceImageName = necklaceImageName + mDbHandler.getAvatarNecklace();
            try {
                photoNameField = ourRID.getClass().getField(necklaceImageName);
                necklaceView.setImageResource(photoNameField.getInt(ourRID));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
                    AvatarRoomActivity.mAvatarRoomInstance.finish();
                    finish();
                    startActivityForResult(new Intent(AvatarActivity.this, MapActivity.class), 0);
                } else {
                    DressingRoomActivity.mDressingRoomInstance.finish();
                    SelectFeaturesActivity.mSelectFeatureInstance.finish();
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
