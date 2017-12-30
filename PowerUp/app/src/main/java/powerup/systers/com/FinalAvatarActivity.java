package powerup.systers.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import java.lang.reflect.Field;
import powerup.systers.com.db.DatabaseHandler;

public class FinalAvatarActivity extends AppCompatActivity {

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_avatar);

        DatabaseHandler handler = new DatabaseHandler(this);
        handler.open();

        ImageView eyeImageView = (ImageView) findViewById(R.id.eye_view);
        ImageView skinImageView = (ImageView) findViewById(R.id.skin_view);
        ImageView hairImageView = (ImageView) findViewById(R.id.hair_view);
        ImageView clothImageView = (ImageView) findViewById(R.id.dress_view);
        ImageView accessoryImageView = (ImageView) findViewById(R.id.acc_view);

        String eyeImageName = getResources().getString(R.string.eye);
        eyeImageName = eyeImageName + handler.getAvatarEye();
        setImage(eyeImageView, eyeImageName);

        String skinImageName = getResources().getString(R.string.skin);
        skinImageName = skinImageName + handler.getAvatarSkin();
        setImage(skinImageView, skinImageName);

        String clothImageName = getResources().getString(R.string.cloth);
        clothImageName = clothImageName + handler.getAvatarCloth();
        setImage(clothImageView, clothImageName);

        String hairImageName = getResources().getString(R.string.hair);
        hairImageName = hairImageName + handler.getAvatarHair();
        setImage(hairImageView, hairImageName);

        String accessoryImageName = getResources().getString(R.string.accessories);
        accessoryImageName = accessoryImageName + handler.getAvatarAccessory();
        setImage(accessoryImageView, accessoryImageName);

        ImageView backButton = (ImageView) findViewById(R.id.backButtonAvatar);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageView continueButton = (ImageView) findViewById(R.id.continueButtonAvatar);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalAvatarActivity.this, MapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        handler.close();
    }

    private void setImage(ImageView view, String fieldName) {
        try {
            Field f = R.drawable.class.getField(fieldName);
            int imgRes = f.getInt(null);
            view.setImageResource(imgRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
