package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;

public class SelectFeaturesActivity extends AppCompatActivity {

    public static Activity mSelectFeatureInstance;
    private int mBag = 1;
    int mGlasses = 1;
    int mHat = 1;
    int mNecklace = 1;
    int mHatPurchased = 0;
    int mGlassesPurchased = 0;
    int mBagPurchased = 0;
    int mNecklacePurchased = 0;
    private int mHair = 1;
    private int mAccessory = 1;
    private int mCloth = 1;
    private DatabaseHandler mDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_features);
        mDbHandler = new DatabaseHandler(this);
        mDbHandler.open();
        mSelectFeatureInstance = this;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        LinearLayout linearLayoutHandbag = (LinearLayout) findViewById(R.id.linearLayouthandbag);
        LinearLayout linearLayoutGlasses = (LinearLayout) findViewById(R.id.linearLayoutGlasses);
        LinearLayout linearLayoutHat = (LinearLayout) findViewById(R.id.linearLayoutHat);
        LinearLayout linearLayoutNecklace = (LinearLayout) findViewById(R.id.linearLayoutNecklace);
        TextView karmaPoints = (TextView) findViewById(R.id.karmaPoints);
        karmaPoints.setText(String.valueOf(SessionHistory.totalPoints));
        ImageView eyeView = (ImageView) findViewById(R.id.eyeView);
        ImageView faceView = (ImageView) findViewById(R.id.faceView);
        final ImageView hairView = (ImageView) findViewById(R.id.hairView);
        final ImageView clothView = (ImageView) findViewById(R.id.clothView);
        String eyeImageName = getResources().getString(R.string.eye);
        eyeImageName = eyeImageName + mDbHandler.getAvatarEye();
        R.drawable ourRID = new R.drawable();
        java.lang.reflect.Field photoNameField;
        try {
            photoNameField = ourRID.getClass().getField(eyeImageName);
            eyeView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }

        String faceImageName = getResources().getString(R.string.face);
        faceImageName = faceImageName + mDbHandler.getAvatarFace();
        try {
            photoNameField = ourRID.getClass().getField(faceImageName);
            faceView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }

        String clothImageName = getResources().getString(R.string.cloth);
        clothImageName = clothImageName + mDbHandler.getAvatarCloth();
        try {
            photoNameField = ourRID.getClass().getField(clothImageName);
            clothView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }

        String hairImageName = getResources().getString(R.string.hair);
        hairImageName = hairImageName + mDbHandler.getAvatarHair();
        try {
            photoNameField = ourRID.getClass().getField(hairImageName);
            hairView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }

        IconRoundCornerProgressBar powerBarHealing = (IconRoundCornerProgressBar) findViewById(R.id.powerbarHealing);
        powerBarHealing.setIconImageResource(R.drawable.icon_healing);
        powerBarHealing.setProgress(mDbHandler.getHealing());
        IconRoundCornerProgressBar powerbarInvisibility = (IconRoundCornerProgressBar) findViewById(R.id.powerbarInvisibility);
        powerbarInvisibility.setIconImageResource(R.drawable.icon_invisibility);
        powerbarInvisibility.setProgress(mDbHandler.getInvisibility());
        IconRoundCornerProgressBar powerbarStrength = (IconRoundCornerProgressBar) findViewById(R.id.powerbarStrength);
        powerbarStrength.setIconImageResource(R.drawable.icon_strength);
        powerbarStrength.setProgress(mDbHandler.getStrength());
        IconRoundCornerProgressBar powerbarTelepathy = (IconRoundCornerProgressBar) findViewById(R.id.powerbarTelepathy);
        powerbarTelepathy.setIconImageResource(R.drawable.icon_telepathy);
        powerbarTelepathy.setProgress(mDbHandler.getTelepathy());

        final String value = getIntent().getExtras().getString(getResources().getString(R.string.feature));
        Button continueButton = (Button) findViewById(R.id.continueButton);
        final ImageView imageViewSelectFeature = (ImageView) findViewById(R.id.imageViewSelectFeature);
        final TextView tvPaidSelectFeature = (TextView) findViewById(R.id.tvPaidSelectFeature);
        final TextView tvPaidHandbag = (TextView) findViewById(R.id.tvPaidHandbag);
        final TextView tvPaidGlasses = (TextView) findViewById(R.id.tvPaidGlasses);
        final TextView tvPaidHat = (TextView) findViewById(R.id.tvPaidHat);
        final TextView tvPaidNecklace = (TextView) findViewById(R.id.tvPaidNecklace);

        if (value.equalsIgnoreCase(getResources().getString(R.string.cloth))) {
            linearLayout.setVisibility(View.VISIBLE);
            linearLayoutGlasses.setVisibility(View.GONE);
            linearLayoutHandbag.setVisibility(View.GONE);
            linearLayoutHat.setVisibility(View.GONE);
            linearLayoutNecklace.setVisibility(View.GONE);
            imageViewSelectFeature.setImageDrawable(getResources().getDrawable(R.drawable.cloth1));
            TextView tv = (TextView) findViewById(R.id.textViewSelectFeature);
            final TextView tvPoints = (TextView) findViewById(R.id.tvSelectFeaturePoints);
            tvPoints.setText(String.valueOf(mDbHandler.getPointsClothes(mCloth)));
            tv.setText(R.string.cloth);
            ImageButton left = (ImageButton) findViewById(R.id.leftSelectFeature);
            ImageButton right = (ImageButton) findViewById(R.id.rightSelectFeature);
            left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCloth = (mCloth - 1) % SessionHistory.clothTotalNo;
                    if (mCloth == 0) {
                        mCloth = SessionHistory.clothTotalNo;
                    }
                    imageViewSelectFeature.setAlpha((float) 1);
                    tvPaidSelectFeature.setText(getResources().getString(R.string.empty));
                    int isPurchased = mDbHandler.getPurchasedClothes(mCloth);
                    if (isPurchased == 1) {
                        imageViewSelectFeature.setAlpha((float) 0.5);
                        tvPaidSelectFeature.setText(getResources().getString(R.string.paid_feature));
                    }
                    String clothImageName = getResources().getString(R.string.cloth);
                    clothImageName = clothImageName + mCloth;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(clothImageName);
                        imageViewSelectFeature.setImageResource(photoNameField.getInt(ourRID));
                        clothView.setImageResource(photoNameField.getInt(ourRID));
                        tvPoints.setText(String.valueOf(mDbHandler.getPointsClothes(mCloth)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });

            right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCloth = (mCloth + SessionHistory.clothTotalNo)
                            % SessionHistory.clothTotalNo + 1;
                    int isPurchased = mDbHandler.getPurchasedClothes(mCloth);
                    imageViewSelectFeature.setAlpha((float) 1);
                    tvPaidSelectFeature.setText(getResources().getString(R.string.empty));
                    if (isPurchased == 1) {
                        imageViewSelectFeature.setAlpha((float) 0.5);
                        tvPaidSelectFeature.setText(getResources().getString(R.string.paid_feature));
                    }
                    String clothImageName = getResources().getString(R.string.cloth);
                    clothImageName = clothImageName + mCloth;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(clothImageName);
                        imageViewSelectFeature.setImageResource(photoNameField.getInt(ourRID));
                        clothView.setImageResource(photoNameField.getInt(ourRID));
                        tvPoints.setText(String.valueOf(mDbHandler.getPointsClothes(mCloth)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (value.equalsIgnoreCase(getResources().getString(R.string.hair))) {
            linearLayout.setVisibility(View.VISIBLE);
            linearLayoutGlasses.setVisibility(View.GONE);
            linearLayoutHandbag.setVisibility(View.GONE);
            linearLayoutHat.setVisibility(View.GONE);
            linearLayoutNecklace.setVisibility(View.GONE);
            imageViewSelectFeature.setImageDrawable(getResources().getDrawable(R.drawable.hair1));
            TextView tv = (TextView) findViewById(R.id.textViewSelectFeature);
            final TextView tvPoints = (TextView) findViewById(R.id.tvSelectFeaturePoints);
            tvPoints.setText(String.valueOf(mDbHandler.getPointsHair(mHair)));
            tv.setText(R.string.hair);
            ImageButton left = (ImageButton) findViewById(R.id.leftSelectFeature);
            ImageButton right = (ImageButton) findViewById(R.id.rightSelectFeature);
            left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHair = (mHair - 1) % SessionHistory.hairTotalNo;
                    if (mHair == 0) {
                        mHair = SessionHistory.hairTotalNo;
                    }
                    int isPurchased = mDbHandler.getPurchasedHair(mHair);
                    imageViewSelectFeature.setAlpha((float) 1);
                    tvPaidSelectFeature.setText(getResources().getString(R.string.empty));
                    if (isPurchased == 1) {
                        imageViewSelectFeature.setAlpha((float) 0.5);
                        tvPaidSelectFeature.setText(getResources().getString(R.string.paid_feature));
                    }
                    String hairImageName = getResources().getString(R.string.hair);
                    hairImageName = hairImageName + mHair;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(hairImageName);
                        imageViewSelectFeature.setImageResource(photoNameField.getInt(ourRID));
                        hairView.setImageResource(photoNameField.getInt(ourRID));
                        tvPoints.setText(String.valueOf(mDbHandler.getPointsHair(mHair)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });

            right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHair = (mHair + SessionHistory.hairTotalNo)
                            % SessionHistory.hairTotalNo + 1;
                    int isPurchased = mDbHandler.getPurchasedHair(mHair);
                    imageViewSelectFeature.setAlpha((float) 1);
                    tvPaidSelectFeature.setText(getResources().getString(R.string.empty));
                    if (isPurchased == 1) {
                        imageViewSelectFeature.setAlpha((float) 0.5);
                        tvPaidSelectFeature.setText(getResources().getString(R.string.paid_feature));
                    }
                    String hairImageName = getResources().getString(R.string.hair);
                    hairImageName = hairImageName + mHair;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(hairImageName);
                        imageViewSelectFeature.setImageResource(photoNameField.getInt(ourRID));
                        hairView.setImageResource(photoNameField.getInt(ourRID));
                        tvPoints.setText(String.valueOf(mDbHandler.getPointsHair(mHair)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else if (value.equalsIgnoreCase(getResources().getString(R.string.accessory))) {
            linearLayout.setVisibility(View.GONE);
            linearLayoutGlasses.setVisibility(View.VISIBLE);
            linearLayoutHandbag.setVisibility(View.VISIBLE);
            linearLayoutHat.setVisibility(View.VISIBLE);
            linearLayoutNecklace.setVisibility(View.VISIBLE);
            ImageButton leftHandbag = (ImageButton) findViewById(R.id.leftHandbag);
            ImageButton rightHandbag = (ImageButton) findViewById(R.id.rightHandbag);
            ImageButton leftGlasses = (ImageButton) findViewById(R.id.leftGlasses);
            ImageButton rightGlasses = (ImageButton) findViewById(R.id.rightGlasses);
            ImageButton leftHat = (ImageButton) findViewById(R.id.leftHat);
            ImageButton rightHat = (ImageButton) findViewById(R.id.rightHat);
            ImageButton leftNecklace = (ImageButton) findViewById(R.id.leftNecklace);
            ImageButton rightNecklace = (ImageButton) findViewById(R.id.rightNecklace);
            final ImageView bagView = (ImageView) findViewById(R.id.bagView);
            final ImageView glassesView = (ImageView) findViewById(R.id.glassesView);
            final ImageView hatView = (ImageView) findViewById(R.id.hatView);
            final ImageView necklaceView = (ImageView) findViewById(R.id.necklaceView);
            final ImageView imageViewHandbag = (ImageView) findViewById(R.id.imageViewhandbag);
            final ImageView imageViewGlasses = (ImageView) findViewById(R.id.imageViewGlasses);
            final ImageView imageViewHat = (ImageView) findViewById(R.id.imageViewHat);
            final ImageView imageViewNecklace = (ImageView) findViewById(R.id.imageViewNecklace);
            final TextView tvHandbagPoints = (TextView) findViewById(R.id.tvHandbagPoints);
            final TextView tvGlassesPoints = (TextView) findViewById(R.id.tvGlassesPoints);
            final TextView tvHatPoints = (TextView) findViewById(R.id.tvHatPoints);
            final TextView tvNecklacePoints = (TextView) findViewById(R.id.tvNecklacePoints);
            tvHandbagPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mAccessory)));
            tvGlassesPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mAccessory + SessionHistory.bagTotalNo)));
            tvHatPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mAccessory
                    + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo)));
            tvNecklacePoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mAccessory
                    + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo + SessionHistory.hatTotalNo)));
            leftHandbag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBag = (mBag - 1) % SessionHistory.bagTotalNo;
                    if (mBag == 0) {
                        mBag = SessionHistory.bagTotalNo;
                    }
                    mBagPurchased = mBag;
                    int isPurchased = mDbHandler.getPurchasedAccessories(mBagPurchased);
                    imageViewHandbag.setAlpha((float) 1);
                    tvPaidHandbag.setText(getResources().getString(R.string.empty));
                    if (isPurchased == 1) {
                        imageViewHandbag.setAlpha((float) 0.5);
                        tvPaidHandbag.setText(getResources().getString(R.string.paid_feature_small));
                    }
                    String eyeImageName = getResources().getString(R.string.bag);
                    eyeImageName = eyeImageName + mBag;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(eyeImageName);
                        bagView.setImageResource(photoNameField.getInt(ourRID));
                        imageViewHandbag.setImageResource(photoNameField.getInt(ourRID));
                        tvHandbagPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mBag)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });

            rightHandbag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBag = (mBag + SessionHistory.bagTotalNo)
                            % SessionHistory.bagTotalNo + 1;
                    mBagPurchased = mBag;
                    int isPurchased = mDbHandler.getPurchasedAccessories(mBagPurchased);
                    imageViewHandbag.setAlpha((float) 1);
                    tvPaidHandbag.setText(getResources().getString(R.string.empty));
                    if (isPurchased == 1) {
                        imageViewHandbag.setAlpha((float) 0.5);
                        tvPaidHandbag.setText(getResources().getString(R.string.paid_feature_small));
                    }
                    String eyeImageName = getResources().getString(R.string.bag);
                    eyeImageName = eyeImageName + mBag;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(eyeImageName);
                        bagView.setImageResource(photoNameField.getInt(ourRID));
                        imageViewHandbag.setImageResource(photoNameField.getInt(ourRID));
                        tvHandbagPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mBag)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });

            leftGlasses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGlasses = (mGlasses - 1) % SessionHistory.bagTotalNo;
                    if (mGlasses == 0) {
                        mGlasses = SessionHistory.glassesTotalNo;
                    }
                    mGlassesPurchased = mGlasses + SessionHistory.bagTotalNo;
                    int isPurchased = mDbHandler.getPurchasedAccessories(mGlassesPurchased);
                    imageViewGlasses.setAlpha((float) 1);
                    tvPaidGlasses.setText(getResources().getString(R.string.empty));
                    if (isPurchased == 1) {
                        imageViewGlasses.setAlpha((float) 0.5);
                        tvPaidGlasses.setText(getResources().getString(R.string.paid_feature_small));
                    }
                    String eyeImageName = getResources().getString(R.string.glasses);
                    eyeImageName = eyeImageName + mGlasses;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(eyeImageName);
                        glassesView.setImageResource(photoNameField.getInt(ourRID));
                        imageViewGlasses.setImageResource(photoNameField.getInt(ourRID));
                        tvGlassesPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mGlasses + SessionHistory.bagTotalNo)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });

            rightGlasses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGlasses = (mGlasses + SessionHistory.glassesTotalNo)
                            % SessionHistory.glassesTotalNo + 1;
                    mGlassesPurchased = mGlasses + SessionHistory.bagTotalNo;
                    int isPurchased = mDbHandler.getPurchasedAccessories(mGlassesPurchased);
                    imageViewGlasses.setAlpha((float) 1);
                    tvPaidGlasses.setText(getResources().getString(R.string.empty));
                    if (isPurchased == 1) {
                        imageViewGlasses.setAlpha((float) 0.5);
                        tvPaidGlasses.setText(getResources().getString(R.string.paid_feature_small));
                    }
                    String eyeImageName = getResources().getString(R.string.glasses);
                    eyeImageName = eyeImageName + mGlasses;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(eyeImageName);
                        glassesView.setImageResource(photoNameField.getInt(ourRID));
                        imageViewGlasses.setImageResource(photoNameField.getInt(ourRID));
                        tvGlassesPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mGlasses + SessionHistory.bagTotalNo)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });

            leftHat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHat = (mHat - 1) % SessionHistory.hatTotalNo;
                    if (mHat == 0) {
                        mHat = SessionHistory.hatTotalNo;
                    }
                    mHatPurchased = mHat + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo;
                    int isPurchased = mDbHandler.getPurchasedAccessories(mHatPurchased);
                    imageViewHat.setAlpha((float) 1);
                    tvPaidHat.setText(getResources().getString(R.string.empty));
                    if (isPurchased == 1) {
                        imageViewHat.setAlpha((float) 0.5);
                        tvPaidHat.setText(getResources().getString(R.string.paid_feature_small));
                    }
                    String eyeImageName = getResources().getString(R.string.hat);
                    eyeImageName = eyeImageName + mHat;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(eyeImageName);
                        hatView.setImageResource(photoNameField.getInt(ourRID));
                        imageViewHat.setImageResource(photoNameField.getInt(ourRID));
                        tvHatPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mHat
                                + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });

            rightHat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHat = (mHat + SessionHistory.hatTotalNo)
                            % SessionHistory.hatTotalNo + 1;
                    mHatPurchased = mHat + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo;
                    int isPurchased = mDbHandler.getPurchasedAccessories(mHatPurchased);
                    imageViewHat.setAlpha((float) 1);
                    tvPaidHat.setText(getResources().getString(R.string.empty));
                    if (isPurchased == 1) {
                        imageViewHat.setAlpha((float) 0.5);
                        tvPaidHat.setText(getResources().getString(R.string.paid_feature_small));
                    }
                    String eyeImageName = getResources().getString(R.string.hat);
                    eyeImageName = eyeImageName + mHat;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(eyeImageName);
                        hatView.setImageResource(photoNameField.getInt(ourRID));
                        imageViewHat.setImageResource(photoNameField.getInt(ourRID));
                        tvHatPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mHat
                                + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });

            leftNecklace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNecklace = (mNecklace - 1) % SessionHistory.necklaceTotalNo;
                    if (mNecklace == 0) {
                        mNecklace = SessionHistory.necklaceTotalNo;
                    }
                    mNecklacePurchased = mNecklace
                            + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo + SessionHistory.hatTotalNo;
                    int isPurchased = mDbHandler.getPurchasedAccessories(mNecklacePurchased);
                    imageViewNecklace.setAlpha((float) 1);
                    tvPaidNecklace.setText(getResources().getString(R.string.empty));
                    if (isPurchased == 1) {
                        imageViewNecklace.setAlpha((float) 0.5);
                        tvPaidNecklace.setText(getResources().getString(R.string.paid_feature_small));
                    }
                    String eyeImageName = getResources().getString(R.string.necklace);
                    eyeImageName = eyeImageName + mNecklace;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(eyeImageName);
                        necklaceView.setImageResource(photoNameField.getInt(ourRID));
                        imageViewNecklace.setImageResource(photoNameField.getInt(ourRID));
                        tvNecklacePoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mNecklace
                                + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo + SessionHistory.hatTotalNo)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });

            rightNecklace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNecklace = (mNecklace + SessionHistory.necklaceTotalNo)
                            % SessionHistory.necklaceTotalNo + 1;
                    mNecklacePurchased = mNecklace
                            + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo + SessionHistory.hatTotalNo;
                    int isPurchased = mDbHandler.getPurchasedAccessories(mNecklacePurchased);
                    imageViewNecklace.setAlpha((float) 1);
                    tvPaidNecklace.setText(getResources().getString(R.string.empty));
                    if (isPurchased == 1) {
                        imageViewNecklace.setAlpha((float) 0.5);
                        tvPaidNecklace.setText(getResources().getString(R.string.paid_feature_small));
                    }
                    String eyeImageName = getResources().getString(R.string.necklace);
                    eyeImageName = eyeImageName + mNecklace;
                    R.drawable ourRID = new R.drawable();
                    java.lang.reflect.Field photoNameField;
                    try {
                        photoNameField = ourRID.getClass().getField(eyeImageName);
                        necklaceView.setImageResource(photoNameField.getInt(ourRID));
                        imageViewNecklace.setImageResource(photoNameField.getInt(ourRID));
                        tvNecklacePoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mNecklace
                                + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo + SessionHistory.hatTotalNo)));
                    } catch (NoSuchFieldException | IllegalAccessException
                            | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbHandler.open();
                if (value.equalsIgnoreCase(getResources().getString(R.string.cloth))) {
                    if(SessionHistory.totalPoints < mDbHandler.getPointsClothes(mCloth))
                        Toast.makeText(SelectFeaturesActivity.this, R.string.points_check, Toast.LENGTH_SHORT).show();
                    else{
                        mDbHandler.setAvatarCloth(mCloth);
                        mDbHandler.setPurchasedClothes(mCloth);
                        SessionHistory.totalPoints = SessionHistory.totalPoints - mDbHandler.getPointsClothes(mCloth);
                    }
                } else if (value.equalsIgnoreCase(getResources().getString(R.string.hair))) {
                    if(SessionHistory.totalPoints < mDbHandler.getPointsHair(mHair))
                        Toast.makeText(SelectFeaturesActivity.this, R.string.points_check, Toast.LENGTH_SHORT).show();
                    else{
                        mDbHandler.setAvatarHair(mHair);
                        mDbHandler.setPurchasedHair(mHair);
                        SessionHistory.totalPoints = SessionHistory.totalPoints - mDbHandler.getPointsClothes(mCloth);
                    }
                } else if (value.equalsIgnoreCase(getResources().getString(R.string.accessory))) {
                    if (mHatPurchased != 0){
                        if(SessionHistory.totalPoints < mDbHandler.getPointsAccessories(mHatPurchased))
                            Toast.makeText(SelectFeaturesActivity.this, R.string.points_check, Toast.LENGTH_SHORT).show();
                        else{
                            mDbHandler.setPurchasedAccessories(mHatPurchased);
                            mDbHandler.setAvatarHat(mHat);
                            SessionHistory.totalPoints = SessionHistory.totalPoints - mDbHandler.getPointsAccessories(mHatPurchased);
                        }
                    }
                    if (mGlassesPurchased != 0){
                        if(SessionHistory.totalPoints < mDbHandler.getPointsAccessories(mGlassesPurchased))
                            Toast.makeText(SelectFeaturesActivity.this, R.string.points_check, Toast.LENGTH_SHORT).show();
                        else{
                            SessionHistory.totalPoints = SessionHistory.totalPoints - mDbHandler.getPointsAccessories(mHatPurchased);
                            mDbHandler.setPurchasedAccessories(mGlassesPurchased);
                            mDbHandler.setAvatarGlasses(mGlasses);
                        }
                    }
                    if (mBagPurchased != 0){
                        if(SessionHistory.totalPoints < mDbHandler.getPointsAccessories(mBagPurchased))
                            Toast.makeText(SelectFeaturesActivity.this, R.string.points_check, Toast.LENGTH_SHORT).show();
                        else{
                            SessionHistory.totalPoints = SessionHistory.totalPoints - mDbHandler.getPointsAccessories(mHatPurchased);
                            mDbHandler.setPurchasedAccessories(mBagPurchased);
                            mDbHandler.setAvatarBag(mBag);
                        }
                    }
                    if (mNecklacePurchased != 0){
                        if(SessionHistory.totalPoints < mDbHandler.getPointsAccessories(mNecklacePurchased))
                            Toast.makeText(SelectFeaturesActivity.this, R.string.points_check, Toast.LENGTH_SHORT).show();
                        else{
                            SessionHistory.totalPoints = SessionHistory.totalPoints - mDbHandler.getPointsAccessories(mHatPurchased);
                            mDbHandler.setPurchasedAccessories(mNecklacePurchased);
                            mDbHandler.setAvatarNecklace(mNecklace);
                        }
                    }
                }
                Intent myIntent = new Intent(SelectFeaturesActivity.this, AvatarActivity.class);
                myIntent.putExtra(getResources().getString(R.string.feature), 2);
                startActivity(myIntent);
                mDbHandler.close();
            }
        });

    }
}
