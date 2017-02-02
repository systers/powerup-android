/**
 * @desc sets up the “Avatar Room” for user to customize avatar features.
 * Allows user to scroll through different face/hair/clothing options.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.util.Random;
import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;
import powerup.systers.com.util.UiUtils;

public class AvatarRoomActivity extends Activity implements View.OnClickListener {

    public static Activity avatarRoomInstance;

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
        setContentView(R.layout.avatar_room);

        mDbHandler = new DatabaseHandler(this);
        mDbHandler.open();

        avatarRoomInstance = this;

        setupLayout();

        mDbHandler.close();
    }

    private void setupLayout() {
        assignViews();
        setClickListeners();
    }

    private void assignViews() {
        mEyeView = (ImageView) findViewById(R.id.eyes);
        mFaceView = (ImageView) findViewById(R.id.face);
        mClothView = (ImageView) findViewById(R.id.clothes);
        mHairView = (ImageView) findViewById(R.id.hair);
        mEyeAvatar = (ImageView) findViewById(R.id.eyeView);
        mHairAvatar = (ImageView) findViewById(R.id.hairView);
        mFaceAvatar = (ImageView) findViewById(R.id.faceView);
        mClothAvatar = (ImageView) findViewById(R.id.clothView);
    }

    private void setClickListeners() {
        findViewById(R.id.eyeLeft).setOnClickListener(this);
        findViewById(R.id.eyeRight).setOnClickListener(this);
        findViewById(R.id.faceLeft).setOnClickListener(this);
        findViewById(R.id.faceRight).setOnClickListener(this);
        findViewById(R.id.clotheLeft).setOnClickListener(this);
        findViewById(R.id.clotheRight).setOnClickListener(this);
        findViewById(R.id.hairLeft).setOnClickListener(this);
        findViewById(R.id.hairRight).setOnClickListener(this);
        findViewById(R.id.continueButtonAvatar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.eyeLeft:
            case R.id.eyeRight:
                if (id == R.id.eyeLeft) {
                    mEye = (mEye - 1) % SessionHistory.eyesTotalNo;
                    if (mEye == 0) mEye = SessionHistory.eyesTotalNo;
                } else {
                    mEye = (mEye + SessionHistory.eyesTotalNo) % SessionHistory.eyesTotalNo + 1;
                }

                String eyeImageName = getResources().getString(R.string.eye);
                eyeImageName = eyeImageName + mEye;
                UiUtils.setDrawable(eyeImageName, mEyeView, mEyeAvatar);
                break;

            case R.id.faceLeft:
            case R.id.faceRight:
                if (id == R.id.faceLeft) {
                    mFace = (mFace - 1) % SessionHistory.faceTotalNo;
                    if (mFace == 0) mFace = SessionHistory.faceTotalNo;
                } else {
                    mFace = (mFace + SessionHistory.faceTotalNo) % SessionHistory.faceTotalNo + 1;
                }

                String faceImageName = getResources().getString(R.string.face);
                faceImageName = faceImageName + mFace;
                UiUtils.setDrawable(faceImageName, mFaceView, mFaceAvatar);
                break;

            case R.id.clotheLeft:
            case R.id.clotheRight:
                if (id == R.id.clotheLeft) {
                    mCloth = (mCloth - 1) % SessionHistory.clothTotalNo;
                    if (mCloth == 0) mCloth = SessionHistory.clothTotalNo;
                } else {
                    mCloth = (mCloth + SessionHistory.clothTotalNo)
                            % SessionHistory.clothTotalNo + 1;
                }

                String clothImageName = getResources().getString(R.string.cloth);
                clothImageName = clothImageName + mCloth;
                UiUtils.setDrawable(clothImageName, mClothView, mClothAvatar);
                break;

            case R.id.hairLeft:
            case R.id.hairRight:
                if (id == R.id.hairLeft) {
                    mHair = (mHair - 1) % SessionHistory.hairTotalNo;
                    if (mHair == 0) mHair = SessionHistory.hairTotalNo;
                } else {
                    mHair = (mHair + SessionHistory.hairTotalNo) % SessionHistory.hairTotalNo + 1;
                }

                String hairImageName = getResources().getString(R.string.hair);
                hairImageName = hairImageName + mHair;
                UiUtils.setDrawable(hairImageName, mHairView, mHairAvatar);
                break;

            case R.id.continueButtonAvatar:
                mDbHandler.open();

                mDbHandler.setAvatarEye(mEye);
                mDbHandler.setAvatarFace(mFace);
                mDbHandler.setAvatarHair(mHair);
                mDbHandler.setAvatarCloth(mCloth);
                mDbHandler.setAvatarBag(0);
                mDbHandler.setAvatarGlasses(0);
                mDbHandler.setAvatarHat(0);
                mDbHandler.setAvatarNecklace(0);
                mDbHandler.updateComplete(); // set all the complete fields back to 0
                mDbHandler.updateReplayed(); // set all the replayed fields back to 0

                SessionHistory.totalPoints = 0; // reset the points stored
                SessionHistory.currSessionId = 1;
                SessionHistory.currScenePoints = 0;

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
                break;
        }
    }
}
