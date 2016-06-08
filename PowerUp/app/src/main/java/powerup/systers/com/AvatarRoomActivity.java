package powerup.systers.com;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;

public class AvatarRoomActivity extends Activity {

	private DatabaseHandler mDbHandler;

	private ImageView eyeView;
	private ImageView faceView;
	private ImageView clothView;
	private ImageView hairView;

	private Integer eye = 1;
	private Integer hair = 1;
	private Integer face = 1;
	private Integer cloth = 1;

	public static Activity avatarRoomInstance;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setmDbHandler(new DatabaseHandler(this));
		getmDbHandler().open();
		avatarRoomInstance = this;
		setContentView(R.layout.avatar_room);
		eyeView = (ImageView) findViewById(R.id.eyes);
		faceView = (ImageView) findViewById(R.id.face);
		clothView = (ImageView) findViewById(R.id.clothes);
		hairView = (ImageView) findViewById(R.id.hair);
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
				eye = (eye - 1) % SessionHistory.eyesTotalNo;
				if (eye == 0) {
					eye = SessionHistory.eyesTotalNo;
				}

				String eyeImageName = "eye";
				eyeImageName = eyeImageName + eye.toString();
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
			}
		});

		eyeRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				eye = (eye + SessionHistory.eyesTotalNo)
						% SessionHistory.eyesTotalNo + 1;
				String eyeImageName = "eye";
				eyeImageName = eyeImageName + eye.toString();
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
			}
		});

		faceLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				face = (face - 1) % SessionHistory.faceTotalNo;
				if (face == 0) {
					face = SessionHistory.faceTotalNo;
				}

				String faceImageName = "face";
				faceImageName = faceImageName + face.toString();
				R.drawable ourRID = new R.drawable();
				java.lang.reflect.Field photoNameField;
				try {
					photoNameField = ourRID.getClass().getField(faceImageName);
					faceView.setImageResource(photoNameField.getInt(ourRID));
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
				face = (face + SessionHistory.faceTotalNo)
						% SessionHistory.faceTotalNo + 1;
				String faceImageName = "face";
				faceImageName = faceImageName + face.toString();
				R.drawable ourRID = new R.drawable();
				java.lang.reflect.Field photoNameField;
				try {
					photoNameField = ourRID.getClass().getField(faceImageName);
					faceView.setImageResource(photoNameField.getInt(ourRID));
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
				cloth = (cloth - 1) % SessionHistory.clothTotalNo;
				if (cloth == 0) {
					cloth = SessionHistory.clothTotalNo;
				}

				String clothImageName = "cloth";
				clothImageName = clothImageName + cloth.toString();
				R.drawable ourRID = new R.drawable();
				java.lang.reflect.Field photoNameField;
				try {
					photoNameField = ourRID.getClass().getField(clothImageName);
					clothView.setImageResource(photoNameField.getInt(ourRID));
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
				cloth = (cloth + SessionHistory.clothTotalNo)
						% SessionHistory.clothTotalNo + 1;
				String clothImageName = "cloth";
				clothImageName = clothImageName + cloth.toString();
				R.drawable ourRID = new R.drawable();
				java.lang.reflect.Field photoNameField;
				try {
					photoNameField = ourRID.getClass().getField(clothImageName);
					clothView.setImageResource(photoNameField.getInt(ourRID));
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
				hair = (hair - 1) % SessionHistory.hairTotalNo;
				if (hair == 0) {
					hair = SessionHistory.hairTotalNo;
				}

				String hairImageName = "hair";
				hairImageName = hairImageName + hair.toString();
				R.drawable ourRID = new R.drawable();
				java.lang.reflect.Field photoNameField;
				try {
					photoNameField = ourRID.getClass().getField(hairImageName);
					hairView.setImageResource(photoNameField.getInt(ourRID));
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
				hair = (hair + SessionHistory.hairTotalNo)
						% SessionHistory.hairTotalNo + 1;
				String hairImageName = "hair";
				hairImageName = hairImageName + hair.toString();
				R.drawable ourRID = new R.drawable();
				java.lang.reflect.Field photoNameField;
				try {
					photoNameField = ourRID.getClass().getField(hairImageName);
					hairView.setImageResource(photoNameField.getInt(ourRID));
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
				getmDbHandler().open();
				getmDbHandler().setAvatarEye(eye);
				getmDbHandler().setAvatarFace(face);
				getmDbHandler().setAvatarHair(hair);
				getmDbHandler().setAvatarCloth(cloth);
				Intent myIntent = new Intent(AvatarRoomActivity.this, AvatarActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
		getmDbHandler().close();
	}

	public DatabaseHandler getmDbHandler() {
		return mDbHandler;
	}

	public void setmDbHandler(DatabaseHandler mDbHandler) {
		this.mDbHandler = mDbHandler;
	}
}
