/**
 * @desc sets up the “Avatar Room” for user to customize avatar features.
 * Allows user to scroll through different skin/hair/clothing options.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import powerup.systers.com.data.DataSource;
import powerup.systers.com.data.IDataSource;
import powerup.systers.com.data.SessionHistory;
import powerup.systers.com.utils.InjectionClass;

public class AvatarRoomActivity extends Activity {

    public Activity avatarRoomInstance;
    private ImageView eyeAvatar;
    private ImageView skinAvatar;
    private ImageView clothAvatar;
    private ImageView hairAvatar;
    private Integer eye;
    private Integer hair;
    private Integer skin;
    private Integer cloth;

    private DataSource source;

    public AvatarRoomActivity() {
        avatarRoomInstance = this;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the database instance
        source = InjectionClass.provideDataSource(this);

        // initialize views
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

        // check if avatar has been customized earlier
        if (!SessionHistory.hasPreviouslyCustomized) {
            eye=1;
            skin=1;
            hair=1;
            cloth=1;
            SessionHistory.hasPreviouslyCustomized = true;
        } else {
            // if yes, check the customization from database for eyes, skin, hair, skin
            source.getAvatarEye(new IDataSource.LoadIntegerCallback() {
                @Override
                public void onResultLoaded(int value) {
                    eye = value;
                }
            });
            source.getAvatarSkin(new IDataSource.LoadIntegerCallback() {
                @Override
                public void onResultLoaded(int value) {
                    skin = value;
                }
            });
            source.getAvatarHair(new IDataSource.LoadIntegerCallback() {
                @Override
                public void onResultLoaded(int value) {
                    hair = value;
                }
            });
            source.getAvatarCloth(new IDataSource.LoadIntegerCallback() {
                @Override
                public void onResultLoaded(int value) {
                    cloth = value;
                }
            });

            String eyeImageName = getResources().getString(R.string.eye);
            eyeImageName = eyeImageName + eye;
            R.drawable ourRID = new R.drawable();
            java.lang.reflect.Field photoNameField;
            try {
                photoNameField = ourRID.getClass().getField(eyeImageName);
                eyeAvatar.setImageResource(photoNameField.getInt(ourRID));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException error) {
                error.printStackTrace();
            }

            String skinImageName = getResources().getString(R.string.skin);
            skinImageName = skinImageName + skin;
            try {
                photoNameField = ourRID.getClass().getField(skinImageName);
                skinAvatar.setImageResource(photoNameField.getInt(ourRID));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException error) {
                error.printStackTrace();
            }

            String clothImageName = getResources().getString(R.string.cloth);
            clothImageName = clothImageName + cloth;
            try {
                photoNameField = ourRID.getClass().getField(clothImageName);
                clothAvatar.setImageResource(photoNameField.getInt(ourRID));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException error) {
                error.printStackTrace();
            }

            String hairImageName = getResources().getString(R.string.hair);
            hairImageName = hairImageName + hair;
            try {
                photoNameField = ourRID.getClass().getField(hairImageName);
                hairAvatar.setImageResource(photoNameField.getInt(ourRID));
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException error) {
                error.printStackTrace();
            }
        }
        // use previous eye set
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
        // use next eye set
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

                source.resetPurchase();

                // update avatar table with selected eye,skin,hair,skin
                source.setAvatarEye(eye);
                source.setAvatarSkin(skin);
                source.setAvatarHair(hair);
                source.setAvatarCloth(cloth);

                //update hair, clothes table as purchased
                source.setPurchasedHair(hair);
                source.setPurchasedClothes(cloth);

                source.updateComplete();//set all the complete fields back to 0
                source.updateReplayed();//set all the replayed fields back to 0

                SessionHistory.totalPoints = 0;    //reset the points stored
                SessionHistory.currSessionID = 1;
                SessionHistory.currScenePoints = 0;
                SessionHistory.sceneHomeIsReplayed = false;
                SessionHistory.sceneSchoolIsReplayed = false;
                SessionHistory.sceneHospitalIsReplayed = false;
                SessionHistory.sceneLibraryIsReplayed = false;
                finish();
                startActivity(new Intent(AvatarRoomActivity.this, FinalAvatarActivity.class));
                overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
            }
        });
        DataSource.clearInstance();
    }


}
