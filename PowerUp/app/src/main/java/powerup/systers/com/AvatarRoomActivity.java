/**
 * @desc sets up the “Avatar Room” for user to customize avatar features.
 * Allows user to scroll through different face/hair/clothing options.
 */

package powerup.systers.com;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.util.Random;
import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;

import static powerup.systers.com.R.string.continue_text;

public class AvatarRoomActivity extends Activity {

    public Activity avatarRoomInstance;
    private DatabaseHandler mDbHandler;
    private Button continueButton;
    private ImageView eyeView;
    private ImageView faceView;
    private ImageView clothView;
    private ImageView hairView;
    private ImageView eyeAvatar;
    private ImageView faceAvatar;
    private ImageView clothAvatar;
    private ImageView hairAvatar;
    private Integer eye = 1;
    private Integer hair = 1;
    private Integer face = 1;
    private Integer cloth = 1;

    public AvatarRoomActivity() {
        avatarRoomInstance = this;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmDbHandler(new DatabaseHandler(this));
        getmDbHandler().open();
        setContentView(R.layout.avatar_room);
        eyeView = (ImageView) findViewById(R.id.eyes);
        faceView = (ImageView) findViewById(R.id.face);
        clothView = (ImageView) findViewById(R.id.clothes);
        hairView = (ImageView) findViewById(R.id.hair);
        eyeAvatar = (ImageView) findViewById(R.id.eyeView);
        hairAvatar = (ImageView) findViewById(R.id.hairView);
        faceAvatar = (ImageView) findViewById(R.id.faceView);
        clothAvatar = (ImageView) findViewById(R.id.clothView);
        ImageButton eyeLeft = (ImageButton) findViewById(R.id.eyeLeft);
        ImageButton eyeRight = (ImageButton) findViewById(R.id.eyeRight);
        ImageButton faceLeft = (ImageButton) findViewById(R.id.faceLeft);
        ImageButton faceRight = (ImageButton) findViewById(R.id.faceRight);
        ImageButton clothLeft = (ImageButton) findViewById(R.id.clotheLeft);
        ImageButton clothRight = (ImageButton) findViewById(R.id.clotheRight);
        ImageButton hairLeft = (ImageButton) findViewById(R.id.hairLeft);
        ImageButton hairRight = (ImageButton) findViewById(R.id.hairRight);
        continueButton = (Button) findViewById(R.id.continueButtonAvatar);

        eyeLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eye = (eye - 1) % SessionHistory.eyesTotalNo;
                if (eye == 0) {
                    eye = SessionHistory.eyesTotalNo;
                }
                resourceSetter(eye,getResources().getString(R.string.eye));
            }
        });

        eyeRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eye = (eye + SessionHistory.eyesTotalNo)
                        % SessionHistory.eyesTotalNo + 1;
                resourceSetter(eye,getResources().getString(R.string.eye));

            }
        });

        faceLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                face = (face - 1) % SessionHistory.faceTotalNo;
                if (face == 0) {
                    face = SessionHistory.faceTotalNo;
                }
                resourceSetter(face,getResources().getString(R.string.face));
            }
        });

        faceRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                face = (face + SessionHistory.faceTotalNo)
                        % SessionHistory.faceTotalNo + 1;
                resourceSetter(face,getResources().getString(R.string.face));
            }
        });

        clothLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloth = (cloth - 1) % SessionHistory.clothTotalNo;
                if (cloth == 0) {
                    cloth = SessionHistory.clothTotalNo;
                }
                resourceSetter(cloth,getResources().getString(R.string.cloth));
            }
        });

        clothRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloth = (cloth + SessionHistory.clothTotalNo)
                        % SessionHistory.clothTotalNo + 1;
                resourceSetter(cloth,getResources().getString(R.string.cloth));
            }
        });

        hairLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hair = (hair - 1) % SessionHistory.hairTotalNo;
                if (hair == 0) {
                    hair = SessionHistory.hairTotalNo;
                }
                resourceSetter(hair,getResources().getString(R.string.hair));
            }
        });

        hairRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hair = (hair + SessionHistory.hairTotalNo)
                        % SessionHistory.hairTotalNo + 1;
                resourceSetter(hair,getResources().getString(R.string.hair));
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmDbHandler().open();
                getmDbHandler().setAvatarEye(eye);
                getmDbHandler().setAvatarFace(face);
                getmDbHandler().setAvatarHair(hair);
                getmDbHandler().setAvatarCloth(cloth);
                getmDbHandler().setAvatarBag(0);
                getmDbHandler().setAvatarGlasses(0);
                getmDbHandler().setAvatarHat(0);
                getmDbHandler().setAvatarNecklace(0);
                getmDbHandler().updateComplete();//set all the complete fields back to 0
                getmDbHandler().updateReplayed();//set all the replayed fields back to 0
                SessionHistory.totalPoints = 0;    //reset the points stored
                SessionHistory.currSessionID = 1;
                SessionHistory.currScenePoints = 0;
                getmDbHandler().resetPurchase();
                Random random = new Random();
                Integer healing = random.nextInt(101 - 1) + 1;
                getmDbHandler().setHealing(healing);

                random = new Random();
                Integer strength = random.nextInt(101 - 1) + 1;
                getmDbHandler().setStrength(strength);

                random = new Random();
                Integer invisibility = random.nextInt(101 - 1) + 1;
                getmDbHandler().setInvisibility(invisibility);

                random = new Random();
                Integer telepathy = random.nextInt(101 - 1) + 1;
                getmDbHandler().setTelepathy(telepathy);
                Log.i("Powers", mDbHandler.getHealing() + " " + mDbHandler.getInvisibility() +
                        " " + mDbHandler.getStrength());
                Intent intent = new Intent(AvatarRoomActivity.this, AvatarActivity.class);
                intent.putExtra(getResources().getString(R.string.from_activity), 1);
                startActivityForResult(intent, 0);
            }
        });
        getmDbHandler().close();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("eyeNum", eye);
        savedInstanceState.putInt("faceNum", face);
        savedInstanceState.putInt("hairNum", hair);
        savedInstanceState.putInt("clothNum", cloth);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        eye = savedInstanceState.getInt("eyeNum");
        face = savedInstanceState.getInt("faceNum");
        cloth = savedInstanceState.getInt("clothNum");;
        hair = savedInstanceState.getInt("hairNum");
        resourceSetter(eye,getResources().getString(R.string.eye));
        resourceSetter(face,getResources().getString(R.string.face));
        resourceSetter(hair,getResources().getString(R.string.hair));
        resourceSetter(cloth,getResources().getString(R.string.cloth));
    }

    public void resourceSetter(int value,String type){
        String imageName=type;
        imageName+=value;
        R.drawable ourRID = new R.drawable();
        java.lang.reflect.Field photoNameField;
        try {
            photoNameField = ourRID.getClass().getField(imageName);
            if(type.equalsIgnoreCase(getResources().getString(R.string.face))){
                faceView.setImageResource(photoNameField.getInt(ourRID));
                faceAvatar.setImageResource(photoNameField.getInt(ourRID));
            }else if(type.equalsIgnoreCase(getResources().getString(R.string.eye))){
                eyeView.setImageResource(photoNameField.getInt(ourRID));
                eyeAvatar.setImageResource(photoNameField.getInt(ourRID));
            }else if(type.equalsIgnoreCase(getResources().getString(R.string.cloth))){
                clothView.setImageResource(photoNameField.getInt(ourRID));
                clothAvatar.setImageResource(photoNameField.getInt(ourRID));
            }else if(type.equalsIgnoreCase(getResources().getString(R.string.hair))){
                hairView.setImageResource(photoNameField.getInt(ourRID));
                hairAvatar.setImageResource(photoNameField.getInt(ourRID));
            }

        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public DatabaseHandler getmDbHandler() {
        return mDbHandler;
    }

    public void setmDbHandler(DatabaseHandler mDbHandler) {
        this.mDbHandler = mDbHandler;
    }
}
