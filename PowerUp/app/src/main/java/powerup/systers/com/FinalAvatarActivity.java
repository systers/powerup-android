package powerup.systers.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import powerup.systers.com.db.DatabaseHandler;

public class FinalAvatarActivity extends AppCompatActivity {

    ImageView clothImageView, hairImageView, accessoryImageView, skinImageView, continueButton, eyeImageView;
    java.lang.reflect.Field photoNameField;
    private DatabaseHandler mDbHandler;
    R.drawable ourRID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_avatar);
        setmDbHandler(new DatabaseHandler(this));
        getmDbHandler().open();
        clothImageView = (ImageView) findViewById(R.id.dress_view);
        hairImageView = (ImageView) findViewById(R.id.hair_view);
        accessoryImageView = (ImageView) findViewById(R.id.acc_view);
        eyeImageView = (ImageView) findViewById(R.id.eye_view);
        skinImageView = (ImageView) findViewById(R.id.skin_view);
        continueButton = (ImageView) findViewById(R.id.continueButtonAvatar);
        String eyeImageName = getResources().getString(R.string.eye);
        eyeImageName = eyeImageName + getmDbHandler().getAvatarEye();
        String skinImageName = getResources().getString(R.string.skin);
        skinImageName = skinImageName + getmDbHandler().getAvatarSkin();
        String hairImageName = getResources().getString(R.string.hair);
        hairImageName = hairImageName + getmDbHandler().getAvatarHair();
        String clothImageName = getResources().getString(R.string.cloth);
        clothImageName = clothImageName + getmDbHandler().getAvatarCloth();
        String accessoryImageName = getResources().getString(R.string.accessory);
        accessoryImageName = accessoryImageName + getmDbHandler().getAvatarAccessory();
        ourRID = new R.drawable();

        try {
            photoNameField = ourRID.getClass().getField(eyeImageName);
            eyeImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }

        try {
            photoNameField = ourRID.getClass().getField(skinImageName);
            skinImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }

        try {
            photoNameField = ourRID.getClass().getField(hairImageName);
            hairImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }

        try {
            photoNameField = ourRID.getClass().getField(clothImageName);
            clothImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }

        try {
            photoNameField = ourRID.getClass().getField(accessoryImageName);
            accessoryImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }
    }

    public void goToMap(View view) {
        finish();
        startActivity(new Intent(FinalAvatarActivity.this, MapActivity.class));
    }

    public void goToStart(View view) {
        finish();
        startActivity(new Intent(FinalAvatarActivity.this, AvatarRoomActivity.class));
    }

    public DatabaseHandler getmDbHandler() {
        return mDbHandler;
    }

    public void setmDbHandler(DatabaseHandler mDbHandler) {
        this.mDbHandler = mDbHandler;
    }
}
