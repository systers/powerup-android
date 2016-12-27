package powerup.systers.com;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.util.Random;
import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;

public class AvatarRoomActivity extends Activity {

    public static Activity mAvatarRoomInstance;
    private DatabaseHandler mDbHandler;
    private ImageView mEyeView;
    private ImageView mFaceView;
    private ImageView mClothView;
    private ImageView mHairView;
    private ImageView mEyeAvatar;
    private ImageView mFaceAvatar;
    private ImageView mClothAvatar;
    private ImageView mHairAvatar;
    private int mEye = 1;
    private int mHair = 1;
    private int mFace = 1;
    private int mCloth = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHandler = new DatabaseHandler(this);
        mDbHandler.open();
        mAvatarRoomInstance = this;
        setContentView(R.layout.avatar_room);
        mEyeView = (ImageView) findViewById(R.id.eyes);
        mFaceView = (ImageView) findViewById(R.id.face);
        mClothView = (ImageView) findViewById(R.id.clothes);
        mHairView = (ImageView) findViewById(R.id.hair);
        mEyeAvatar = (ImageView) findViewById(R.id.eyeView);
        mHairAvatar = (ImageView) findViewById(R.id.hairView);
        mFaceAvatar = (ImageView) findViewById(R.id.faceView);
        mClothAvatar = (ImageView) findViewById(R.id.clothView);
        ImageButton eyeLeft = (ImageButton) findViewById(R.id.eyeLeft);
        ImageButton eyeRight = (ImageButton) findViewById(R.id.eyeRight);
        ImageButton faceLeft = (ImageButton) findViewById(R.id.faceLeft);
        ImageButton faceRight = (ImageButton) findViewById(R.id.faceRight);
        ImageButton clothLeft = (ImageButton) findViewById(R.id.clotheLeft);
        ImageButton clothRight = (ImageButton) findViewById(R.id.clotheRight);
        ImageButton hairLeft = (ImageButton) findViewById(R.id.hairLeft);
        ImageButton hairRight = (ImageButton) findViewById(R.id.hairRight);
        Button continueButton = (Button) findViewById(R.id.continueButtonAvatar);

        eyeLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEye = (mEye - 1) % SessionHistory.eyesTotalNo;
                if (mEye == 0) {
                    mEye = SessionHistory.eyesTotalNo;
                }

                String eyeImageName = getResources().getString(R.string.eye);
                eyeImageName = eyeImageName + mEye;
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(eyeImageName);
                    mEyeView.setImageResource(photoNameField.getInt(ourRID));
                    mEyeAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        eyeRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEye = (mEye + SessionHistory.eyesTotalNo)
                        % SessionHistory.eyesTotalNo + 1;
                String eyeImageName = getResources().getString(R.string.eye);
                eyeImageName = eyeImageName + mEye;
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(eyeImageName);
                    mEyeView.setImageResource(photoNameField.getInt(ourRID));
                    mEyeAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        faceLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFace = (mFace - 1) % SessionHistory.faceTotalNo;
                if (mFace == 0) {
                    mFace = SessionHistory.faceTotalNo;
                }

                String faceImageName = getResources().getString(R.string.face);
                faceImageName = faceImageName + mFace;
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(faceImageName);
                    mFaceView.setImageResource(photoNameField.getInt(ourRID));
                    mFaceAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        faceRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFace = (mFace + SessionHistory.faceTotalNo)
                        % SessionHistory.faceTotalNo + 1;
                String faceImageName = getResources().getString(R.string.face);
                faceImageName = faceImageName + mFace;
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(faceImageName);
                    mFaceView.setImageResource(photoNameField.getInt(ourRID));
                    mFaceAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        clothLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCloth = (mCloth - 1) % SessionHistory.clothTotalNo;
                if (mCloth == 0) {
                    mCloth = SessionHistory.clothTotalNo;
                }

                String clothImageName = getResources().getString(R.string.cloth);
                clothImageName = clothImageName + mCloth;
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(clothImageName);
                    mClothView.setImageResource(photoNameField.getInt(ourRID));
                    mClothAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        clothRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCloth = (mCloth + SessionHistory.clothTotalNo)
                        % SessionHistory.clothTotalNo + 1;
                String clothImageName = getResources().getString(R.string.cloth);
                clothImageName = clothImageName + mCloth;
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(clothImageName);
                    mClothView.setImageResource(photoNameField.getInt(ourRID));
                    mClothAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        hairLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHair = (mHair - 1) % SessionHistory.hairTotalNo;
                if (mHair == 0) {
                    mHair = SessionHistory.hairTotalNo;
                }

                String hairImageName = getResources().getString(R.string.hair);
                hairImageName = hairImageName + mHair;
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(hairImageName);
                    mHairView.setImageResource(photoNameField.getInt(ourRID));
                    mHairAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        hairRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHair = (mHair + SessionHistory.hairTotalNo)
                        % SessionHistory.hairTotalNo + 1;
                String hairImageName = getResources().getString(R.string.hair);
                hairImageName = hairImageName + mHair;
                R.drawable ourRID = new R.drawable();
                java.lang.reflect.Field photoNameField;
                try {
                    photoNameField = ourRID.getClass().getField(hairImageName);
                    mHairView.setImageResource(photoNameField.getInt(ourRID));
                    mHairAvatar.setImageResource(photoNameField.getInt(ourRID));
                } catch (NoSuchFieldException | IllegalAccessException
                        | IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbHandler.open();
                mDbHandler.setAvatarEye(mEye);
                mDbHandler.setAvatarFace(mFace);
                mDbHandler.setAvatarHair(mHair);
                mDbHandler.setAvatarCloth(mCloth);
                mDbHandler.setAvatarBag(0);
                mDbHandler.setAvatarGlasses(0);
                mDbHandler.setAvatarHat(0);
                mDbHandler.setAvatarNecklace(0);
                mDbHandler.updateComplete();//set all the complete fields back to 0
                mDbHandler.updateReplayed();//set all the replayed fields back to 0
                SessionHistory.totalPoints=0;    //reset the points stored
                SessionHistory.currSessionID=1;
                SessionHistory.currScenePoints=0;
                mDbHandler.resetPurchase();
                Random r = new Random();
                int healing = r.nextInt(101 - 1) + 1;
                mDbHandler.setHealing(healing);

                r = new Random();
                int strength = r.nextInt(101 - 1) + 1;
                mDbHandler.setStrength(strength);

                r = new Random();
                int invisibility = r.nextInt(101 - 1) + 1;
                mDbHandler.setInvisibility(invisibility);

                r = new Random();
                int telepathy = r.nextInt(101 - 1) + 1;
                mDbHandler.setTelepathy(telepathy);
                Log.i("Powers", mDbHandler.getHealing() + " " + mDbHandler.getInvisibility() +
                        " " + mDbHandler.getStrength());
                Intent myIntent = new Intent(AvatarRoomActivity.this, AvatarActivity.class);
                myIntent.putExtra(getResources().getString(R.string.from_activity), 1);
                startActivityForResult(myIntent, 0);
            }
        });
        mDbHandler.close();
    }
}
