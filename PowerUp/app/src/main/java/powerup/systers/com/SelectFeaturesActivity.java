/**
 * @desc allows user to purchase/change clothes using the current session's total points
 * (karma) and sets progress of power and health bars.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;

import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;
import powerup.systers.com.util.UiUtils;

public class SelectFeaturesActivity extends AppCompatActivity implements View.OnClickListener {

    public static Activity selectFeatureInstance;

    private int mBag = 1;
    private int mGlasses = 1;
    private int mHat = 1;
    private int mNecklace = 1;
    private int mHatPurchased = 0;
    private int mGlassesPurchased = 0;
    private int mBagPurchased = 0;
    private int mNecklacePurchased = 0;
    private int mHair = 1;
    private int mAccessory = 1;
    private int mCloth = 1;
    private DatabaseHandler mDbHandler;

    private ImageView mImageViewSelectFeature, mHairView, mClothView;
    private TextView mTvPoints, mTvPaidSelectFeature;
    private String mValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_features);

        mDbHandler = new DatabaseHandler(this);
        mDbHandler.open();

        selectFeatureInstance = this;

        mValue = getIntent().getExtras().getString(getResources().getString(R.string.feature));

        setupLayout();
    }

    private void setupLayout() {
        TextView karmaPoints = (TextView) findViewById(R.id.karmaPoints);
        karmaPoints.setText(String.valueOf(SessionHistory.totalPoints));

        UiUtils.setupEyesFaceClothesHair(this, mDbHandler);
        UiUtils.setupProgressBars(this, mDbHandler);

        findViewById(R.id.continueButton).setOnClickListener(this);

        setLinearLayoutVisibilities();

        mImageViewSelectFeature = (ImageView) findViewById(R.id.imageViewSelectFeature);
        mTvPaidSelectFeature = (TextView) findViewById(R.id.tvPaidSelectFeature);

        mHairView = (ImageView) findViewById(R.id.hairView);
        mClothView = (ImageView) findViewById(R.id.clothView);

        if (mValue.equalsIgnoreCase(getResources().getString(R.string.cloth))) {
            setLayoutForClothes();
        } else if (mValue.equalsIgnoreCase(getResources().getString(R.string.hair))) {
            setLayoutForHair();
        } else if (mValue.equalsIgnoreCase(getResources().getString(R.string.accessory))) {
            setLayoutForAccessory();
        }
    }

    private void setLinearLayoutVisibilities() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        LinearLayout linearLayoutHandbag = (LinearLayout) findViewById(R.id.linearLayouthandbag);
        LinearLayout linearLayoutGlasses = (LinearLayout) findViewById(R.id.linearLayoutGlasses);
        LinearLayout linearLayoutHat = (LinearLayout) findViewById(R.id.linearLayoutHat);
        LinearLayout linearLayoutNecklace = (LinearLayout) findViewById(R.id.linearLayoutNecklace);

        if (mValue.equalsIgnoreCase(getResources().getString(R.string.accessory))) {
            linearLayout.setVisibility(View.GONE);
            linearLayoutGlasses.setVisibility(View.VISIBLE);
            linearLayoutHandbag.setVisibility(View.VISIBLE);
            linearLayoutHat.setVisibility(View.VISIBLE);
            linearLayoutNecklace.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
            linearLayoutGlasses.setVisibility(View.GONE);
            linearLayoutHandbag.setVisibility(View.GONE);
            linearLayoutHat.setVisibility(View.GONE);
            linearLayoutNecklace.setVisibility(View.GONE);
        }
    }

    private void setLayoutForClothes() {
        mImageViewSelectFeature.setImageDrawable(getResources().getDrawable(R.drawable.cloth1));

        mTvPoints = (TextView) findViewById(R.id.tvSelectFeaturePoints);
        mTvPoints.setText(String.valueOf(mDbHandler.getPointsClothes(mCloth)));

        TextView tvSelectFeature = (TextView) findViewById(R.id.textViewSelectFeature);
        tvSelectFeature.setText(R.string.cloth);

        findViewById(R.id.leftSelectFeature).setOnClickListener(this);
        findViewById(R.id.rightSelectFeature).setOnClickListener(this);
    }

    private void setLayoutForHair() {
        mImageViewSelectFeature.setImageDrawable(getResources().getDrawable(R.drawable.hair1));

        mTvPoints = (TextView) findViewById(R.id.tvSelectFeaturePoints);
        mTvPoints.setText(String.valueOf(mDbHandler.getPointsHair(mHair)));

        TextView tvSelectFeature = (TextView) findViewById(R.id.textViewSelectFeature);
        tvSelectFeature.setText(R.string.hair);

        findViewById(R.id.leftSelectFeature).setOnClickListener(this);
        findViewById(R.id.rightSelectFeature).setOnClickListener(this);
    }

    private void setLayoutForAccessory() {
        // Set click listeners for ImageButtons
        findViewById(R.id.leftHandbag).setOnClickListener(this);
        findViewById(R.id.rightHandbag).setOnClickListener(this);
        findViewById(R.id.leftGlasses).setOnClickListener(this);
        findViewById(R.id.rightGlasses).setOnClickListener(this);
        findViewById(R.id.leftHat).setOnClickListener(this);
        findViewById(R.id.rightHat).setOnClickListener(this);
        findViewById(R.id.leftNecklace).setOnClickListener(this);
        findViewById(R.id.rightNecklace).setOnClickListener(this);

        // Set point texts
        TextView tvHandbagPoints = (TextView) findViewById(R.id.tvHandbagPoints);
        tvHandbagPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mAccessory)));

        TextView tvGlassesPoints = (TextView) findViewById(R.id.tvGlassesPoints);
        tvGlassesPoints.setText(String.valueOf(
                mDbHandler.getPointsAccessories(mAccessory + SessionHistory.bagTotalNo)));

        TextView tvHatPoints = (TextView) findViewById(R.id.tvHatPoints);
        tvHatPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mAccessory
                + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo)));

        TextView tvNecklacePoints = (TextView) findViewById(R.id.tvNecklacePoints);
        tvNecklacePoints.setText(String.valueOf(
                mDbHandler.getPointsAccessories(mAccessory + SessionHistory.bagTotalNo +
                        SessionHistory.glassesTotalNo + SessionHistory.hatTotalNo)));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.leftSelectFeature:
            case R.id.rightSelectFeature:
                selectFeatureClick(id);
                break;

            case R.id.leftHandbag:
            case R.id.rightHandbag:
                handbagClick(id);
                break;

            case R.id.leftGlasses:
            case R.id.rightGlasses:
                glassesClick(id);
                break;

            case R.id.leftHat:
            case R.id.rightHat:
                hatClick(id);
                break;

            case R.id.leftNecklace:
            case R.id.rightNecklace:
                necklaceClick(id);
                break;

            case R.id.continueButton:
                continueButtonClick();
                break;
        }
    }

    private void selectFeatureClick(int viewId) {
        if (mValue.equalsIgnoreCase(getResources().getString(R.string.cloth))) {
            if (viewId == R.id.leftSelectFeature) {
                mCloth = (mCloth - 1) % SessionHistory.clothTotalNo;
                if (mCloth == 0) mCloth = SessionHistory.clothTotalNo;
            } else {
                mCloth = (mCloth + SessionHistory.clothTotalNo)
                        % SessionHistory.clothTotalNo + 1;
            }

            setPaidFeatureText(
                    mDbHandler.getPurchasedClothes(mCloth),
                    mImageViewSelectFeature,
                    mTvPaidSelectFeature);

            String clothImageName = getResources().getString(R.string.cloth);
            clothImageName = clothImageName + mCloth;

            UiUtils.setDrawable(clothImageName, mClothView, mImageViewSelectFeature);
            mTvPoints.setText(String.valueOf(mDbHandler.getPointsClothes(mCloth)));

        } else if (mValue.equalsIgnoreCase(getResources().getString(R.string.hair))) {
            if (viewId == R.id.leftSelectFeature) {
                mHair = (mHair - 1) % SessionHistory.hairTotalNo;
                if (mHair == 0) mHair = SessionHistory.hairTotalNo;
            } else {
                mHair = (mHair + SessionHistory.hairTotalNo)
                        % SessionHistory.hairTotalNo + 1;
            }

            setPaidFeatureText(
                    mDbHandler.getPurchasedClothes(mHair),
                    mImageViewSelectFeature,
                    mTvPaidSelectFeature);

            String hairImageName = getResources().getString(R.string.hair);
            hairImageName = hairImageName + mHair;

            UiUtils.setDrawable(hairImageName, mHairView, mImageViewSelectFeature);
            mTvPoints.setText(String.valueOf(mDbHandler.getPointsHair(mHair)));
        }
    }

    private void handbagClick(int viewId) {
        ImageView imageViewHandbag = (ImageView) findViewById(R.id.imageViewhandbag);
        ImageView bagView = (ImageView) findViewById(R.id.bagView);
        TextView tvPaidHandbag = (TextView) findViewById(R.id.tvPaidHandbag);

        if (viewId == R.id.leftHandbag) {
            mBag = (mBag - 1) % SessionHistory.bagTotalNo;
            if (mBag == 0) mBag = SessionHistory.bagTotalNo;
        } else {
            mBag = (mBag + SessionHistory.bagTotalNo) % SessionHistory.bagTotalNo + 1;
        }

        mBagPurchased = mBag;

        setPaidFeatureText(
                mDbHandler.getPurchasedAccessories(mBagPurchased),
                imageViewHandbag,
                tvPaidHandbag);

        String eyeImageName = getResources().getString(R.string.bag);
        eyeImageName = eyeImageName + mBag;
        UiUtils.setDrawable(eyeImageName, bagView, imageViewHandbag);

        TextView tvHandbagPoints = (TextView) findViewById(R.id.tvHandbagPoints);
        tvHandbagPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(mBag)));
    }

    private void glassesClick(int viewId) {
        ImageView imageViewGlasses = (ImageView) findViewById(R.id.imageViewGlasses);
        ImageView glassesView = (ImageView) findViewById(R.id.glassesView);
        TextView tvPaidGlasses = (TextView) findViewById(R.id.tvPaidGlasses);

        if (viewId == R.id.leftGlasses) {
            mGlasses = (mGlasses - 1) % SessionHistory.bagTotalNo;
            if (mGlasses == 0) mGlasses = SessionHistory.glassesTotalNo;
        } else {
            mGlasses = (mGlasses + SessionHistory.glassesTotalNo)
                    % SessionHistory.glassesTotalNo + 1;
        }

        mGlassesPurchased = mGlasses + SessionHistory.bagTotalNo;

        setPaidFeatureText(
                mDbHandler.getPurchasedAccessories(mGlassesPurchased),
                imageViewGlasses,
                tvPaidGlasses);

        String glassesImageName = getResources().getString(R.string.glasses);
        glassesImageName = glassesImageName + mGlasses;
        UiUtils.setDrawable(glassesImageName, glassesView, imageViewGlasses);

        TextView tvGlassesPoints = (TextView) findViewById(R.id.tvGlassesPoints);
        tvGlassesPoints.setText(String.valueOf(
                mDbHandler.getPointsAccessories(mGlasses + SessionHistory.bagTotalNo)));
    }

    private void hatClick(int id) {
        ImageView hatView = (ImageView) findViewById(R.id.hatView);
        ImageView imageViewHat = (ImageView) findViewById(R.id.imageViewHat);
        TextView tvPaidHat = (TextView) findViewById(R.id.tvPaidHat);

        if (id == R.id.leftHat) {
            mHat = (mHat - 1) % SessionHistory.hatTotalNo;
            if (mHat == 0) mHat = SessionHistory.hatTotalNo;
        } else {
            mHat = (mHat + SessionHistory.hatTotalNo) % SessionHistory.hatTotalNo + 1;
        }

        mHatPurchased = mHat + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo;

        setPaidFeatureText(
                mDbHandler.getPurchasedAccessories(mHatPurchased),
                imageViewHat,
                tvPaidHat);

        String hatImageView = getResources().getString(R.string.hat);
        hatImageView = hatImageView + mHat;
        UiUtils.setDrawable(hatImageView, hatView, imageViewHat);

        TextView tvHatPoints = (TextView) findViewById(R.id.tvHatPoints);
        tvHatPoints.setText(String.valueOf(mDbHandler.getPointsAccessories(
                mHat + SessionHistory.bagTotalNo + SessionHistory.glassesTotalNo)));
    }

    private void necklaceClick(int viewId) {
        ImageView necklaceView = (ImageView) findViewById(R.id.necklaceView);
        ImageView imageViewNecklace = (ImageView) findViewById(R.id.imageViewNecklace);
        TextView tvPaidNecklace = (TextView) findViewById(R.id.tvPaidNecklace);

        if (viewId == R.id.leftNecklace) {
            mNecklace = (mNecklace - 1) % SessionHistory.necklaceTotalNo;
            if (mNecklace == 0) mNecklace = SessionHistory.necklaceTotalNo;
        } else {
            mNecklace = (mNecklace + SessionHistory.necklaceTotalNo)
                    % SessionHistory.necklaceTotalNo + 1;
        }

        mNecklacePurchased = mNecklace + SessionHistory.bagTotalNo +
                SessionHistory.glassesTotalNo + SessionHistory.hatTotalNo;

        setPaidFeatureText(
                mDbHandler.getPurchasedAccessories(mNecklacePurchased),
                imageViewNecklace,
                tvPaidNecklace);

        String necklaceImageName = getResources().getString(R.string.necklace);
        necklaceImageName = necklaceImageName + mNecklace;
        UiUtils.setDrawable(necklaceImageName, necklaceView, imageViewNecklace);

        TextView tvNecklacePoints = (TextView) findViewById(R.id.tvNecklacePoints);
        tvNecklacePoints.setText(String.valueOf(
                mDbHandler.getPointsAccessories(mNecklace + SessionHistory.bagTotalNo +
                        SessionHistory.glassesTotalNo + SessionHistory.hatTotalNo)));
    }

    private void continueButtonClick() {
        mDbHandler.open();

        if (mValue.equalsIgnoreCase(getResources().getString(R.string.cloth))) {
            if (SessionHistory.totalPoints < mDbHandler.getPointsClothes(mCloth)) {
                Toast.makeText(SelectFeaturesActivity.this, R.string.points_check,
                        Toast.LENGTH_SHORT).show();
            } else {
                mDbHandler.setAvatarCloth(mCloth);
                mDbHandler.setPurchasedClothes(mCloth);
                SessionHistory.totalPoints = SessionHistory.totalPoints -
                        mDbHandler.getPointsClothes(mCloth);
            }
            return;
        }

        if (mValue.equalsIgnoreCase(getResources().getString(R.string.hair))) {
            if (SessionHistory.totalPoints < mDbHandler.getPointsHair(mHair)) {
                Toast.makeText(SelectFeaturesActivity.this, R.string.points_check,
                        Toast.LENGTH_SHORT).show();
            } else {
                mDbHandler.setAvatarHair(mHair);
                mDbHandler.setPurchasedHair(mHair);
                // TODO is this correct - should the line below be getPointsHair(...) ?
                SessionHistory.totalPoints = SessionHistory.totalPoints -
                        mDbHandler.getPointsClothes(mCloth);
            }
            return;
        }

        // mValue must be 'accessory'

        int[] accessoriesPurchased =
                {mHatPurchased, mGlassesPurchased, mBagPurchased, mNecklacePurchased};

        for (int i = 0; i < accessoriesPurchased.length; i++) {
            int accessoryPurchased = accessoriesPurchased[i];

            if (accessoryPurchased != 0) {
                if (SessionHistory.totalPoints < mDbHandler.getPointsAccessories(accessoryPurchased)) {
                    Toast.makeText(SelectFeaturesActivity.this, R.string.points_check,
                            Toast.LENGTH_SHORT).show();
                } else {
                    mDbHandler.setPurchasedAccessories(accessoryPurchased);
                    setAvatarAccessory(i);

                    /*
                    TODO is this correct - should the line below use accessoryPurchased?
                    I have put longer code into a 'for' loop here, but the code previously always
                    uses mHatPurchased regardless of whether or not it is checking mHatPurchased,
                    mGlassesPurchased, mBagPurchased, etc.

                    If the previous code was incorrect, please change the line below to use
                    accessoryPurchased instead of mHatPurchased, then delete this comment.
                    */
                    SessionHistory.totalPoints = SessionHistory.totalPoints -
                            mDbHandler.getPointsAccessories(mHatPurchased);
                }
            }
        }

        Intent intent = new Intent(SelectFeaturesActivity.this, AvatarActivity.class);
        intent.putExtra(getResources().getString(R.string.feature), 2);
        startActivity(intent);

        mDbHandler.close();
    }

    private void setAvatarAccessory(int i) {
        switch (i) {
            case 0:
                mDbHandler.setAvatarHat(mHat);
                break;
            case 1:
                mDbHandler.setAvatarGlasses(mGlasses);
                break;
            case 2:
                mDbHandler.setAvatarBag(mBag);
                break;
            case 3:
                mDbHandler.setAvatarNecklace(mNecklace);
                break;
            default:
                throw new IllegalStateException("i should be between 0 and 3 - it is currently '" +
                        i + "'. Please check the code for errors.");
        }
    }

    private void setPaidFeatureText(int isPurchased, ImageView imageView, TextView textView) {
        imageView.setAlpha((float) 1);
        textView.setText(getResources().getString(R.string.empty));

        if (isPurchased == 1) {
            imageView.setAlpha((float) 0.5);
            textView.setText(getResources().getString(R.string.paid_feature));
        }
    }
}
