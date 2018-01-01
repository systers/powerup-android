package powerup.systers.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import powerup.systers.com.db.DatabaseHandler;

public class FinalAvatarActivity extends AppCompatActivity {

    private DatabaseHandler mDbHandler;
    java.lang.reflect.Field photoNameField;
    R.drawable ourRID;
    ImageView hairImageView;
    ImageView clothImageView;
    ImageView accessoryImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_avatar);

        setmDbHandler(new DatabaseHandler(this));
        getmDbHandler().open();

        Button backButton = (Button) findViewById(R.id.backButton);
        ImageView continueButton = (ImageView) findViewById(R.id.continueButton);

        ImageView eyeImageView = (ImageView) findViewById(R.id.eye_view);
        ImageView skinImageView = (ImageView) findViewById(R.id.skin_view);
        hairImageView = (ImageView) findViewById(R.id.hair_view);
        clothImageView = (ImageView) findViewById(R.id.dress_view);
        accessoryImageView = (ImageView) findViewById(R.id.acc_view);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalAvatarActivity.this, AvatarRoomActivity.class);
                intent.putExtra("previousActivity", "FinalAvatarActivity");
                startActivity(intent);
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalAvatarActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        String eyeImageName = getResources().getString(R.string.eye);
        eyeImageName = eyeImageName + getmDbHandler().getAvatarEye();
        ourRID = new R.drawable();

        try {
            photoNameField = ourRID.getClass().getField(eyeImageName);
            eyeImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }
        String skinImageName = getResources().getString(R.string.skin);
        skinImageName = skinImageName + getmDbHandler().getAvatarSkin();
        try {
            photoNameField = ourRID.getClass().getField(skinImageName);
            skinImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }
        setAvatarClothes(getmDbHandler().getAvatarCloth());
        setAvatarHair(getmDbHandler().getAvatarHair());
    }

    public void setAvatarHair(int index) {
        getmDbHandler().setAvatarHair(index);
        String hairImageName = getResources().getString(R.string.hair);
        hairImageName = hairImageName + index;
        try {
            photoNameField = ourRID.getClass().getField(hairImageName);
            hairImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }
    }

    public void setAvatarClothes(int index) {
        getmDbHandler().setAvatarCloth(index);
        String clothImageName = getResources().getString(R.string.cloth);
        clothImageName = clothImageName + index;
        try {
            photoNameField = ourRID.getClass().getField(clothImageName);
            clothImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }
    }

    public void setAvatarAccessories(int index) {
        getmDbHandler().setAvatarAccessory(index);
        String accessoryImageName = getResources().getString(R.string.accessories);
        accessoryImageName = accessoryImageName + index;
        try {
            photoNameField = ourRID.getClass().getField(accessoryImageName);
            accessoryImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
        }
    }

    public DatabaseHandler getmDbHandler() {
        return mDbHandler;
    }

    public void setmDbHandler(DatabaseHandler mDbHandler) {
        this.mDbHandler = mDbHandler;
    }
}