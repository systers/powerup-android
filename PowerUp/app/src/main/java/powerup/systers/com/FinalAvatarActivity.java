package powerup.systers.com;

/**
 * Created by fidel on 12/21/2017.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import powerup.systers.com.data.DataSource;
import powerup.systers.com.utils.DbResourceUtils;
import powerup.systers.com.utils.InjectionClass;

public class FinalAvatarActivity extends Activity{
    private DataSource dataSource;
    public Activity finalAvatarInstance;
    public FinalAvatarActivity() {
        finalAvatarInstance = this;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the database instance
        dataSource = InjectionClass.provideDataSource(this);

        // initialize variables
        setContentView(R.layout.final_avatar);
        ImageView eyeAvatar = findViewById(R.id.eye_view);
        ImageView hairAvatar = findViewById(R.id.hair_view);
        ImageView skinAvatar = findViewById(R.id.skin_view);
        ImageView clothAvatar = findViewById(R.id.dress_view);

        // use DbResourceUtil to extract data from database & apply it on avatar
        DbResourceUtils resourceUtils = new DbResourceUtils(dataSource, FinalAvatarActivity.this);
        eyeAvatar.setImageResource(resourceUtils.getEyeResourceId());
        skinAvatar.setImageResource(resourceUtils.getSkinResourceId());
        clothAvatar.setImageResource(resourceUtils.getClothResourceId());
        hairAvatar.setImageResource(resourceUtils.getHairResourceId());

        //initialize continue & back button
        ImageView continueButton = findViewById(R.id.continueButtonFinal);
        ImageView backButton = findViewById(R.id.backButtonFinal);

        // setting has previously started to true in preferences & start MapActivity
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasPreviouslyStarted = dataSource.checkFirstTime();
                if (!hasPreviouslyStarted) {
                    dataSource.setFirstTime(true);
                }
                startActivityForResult(new Intent(FinalAvatarActivity.this, MapActivity.class), 0);
                overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
            }
        });

        //re-open avatar room activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalAvatarActivity.this, AvatarRoomActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        DataSource.clearInstance();
    }
}
