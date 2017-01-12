package powerup.systers.com.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;

import java.lang.reflect.Field;

import powerup.systers.com.R;
import powerup.systers.com.db.DatabaseHandler;

public final class UiUtils {

    private UiUtils() {
        throw new AssertionError();
    }

    /**
     * Sets the image resource of specified {@link ImageView}s dynamically using the image name.
     *
     * @param imageName The name of the image resource
     * @param imageViews The {@link ImageView}s that will use the new image resource
     */
    public static void setDrawable(String imageName, ImageView... imageViews) {
        if (imageViews.length == 0) {
            return;
        }

        R.drawable resId = new R.drawable();
        Field photoNameField;

        try {
            photoNameField = resId.getClass().getField(imageName);

            for (ImageView imageView : imageViews) {
                imageView.setImageResource(photoNameField.getInt(resId));
            }
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void setupEyesFaceClothesHair(Activity activity, DatabaseHandler dbHandler) {
        ImageView eyeView = (ImageView) activity.findViewById(R.id.eyeView);
        ImageView faceView = (ImageView) activity.findViewById(R.id.faceView);
        ImageView hairView = (ImageView) activity.findViewById(R.id.hairView);
        ImageView clothView = (ImageView) activity.findViewById(R.id.clothView);

        String eyeImageName = activity.getString(R.string.eye);
        eyeImageName = eyeImageName + dbHandler.getAvatarEye();
        setDrawable(eyeImageName, eyeView);

        String faceImageName = activity.getString(R.string.face);
        faceImageName = faceImageName + dbHandler.getAvatarFace();
        setDrawable(faceImageName, faceView);

        String clothImageName = activity.getString(R.string.cloth);
        clothImageName = clothImageName + dbHandler.getAvatarCloth();
        setDrawable(clothImageName, clothView);

        String hairImageName = activity.getString(R.string.hair);
        hairImageName = hairImageName + dbHandler.getAvatarHair();
        setDrawable(hairImageName, hairView);
    }

    public static void setupProgressBars(Activity activity, DatabaseHandler dbHandler) {
        IconRoundCornerProgressBar powerBarHealing =
                (IconRoundCornerProgressBar) activity.findViewById(R.id.powerbarHealing);
        powerBarHealing.setIconImageResource(R.drawable.icon_healing);
        powerBarHealing.setProgress(dbHandler.getHealing());

        IconRoundCornerProgressBar powerBarInvisibility =
                (IconRoundCornerProgressBar) activity.findViewById(R.id.powerbarInvisibility);
        powerBarInvisibility.setIconImageResource(R.drawable.icon_invisibility);
        powerBarInvisibility.setProgress(dbHandler.getInvisibility());

        IconRoundCornerProgressBar powerBarStrength =
                (IconRoundCornerProgressBar) activity.findViewById(R.id.powerbarStrength);
        powerBarStrength.setIconImageResource(R.drawable.icon_strength);
        powerBarStrength.setProgress(dbHandler.getStrength());

        IconRoundCornerProgressBar powerBarTelepathy =
                (IconRoundCornerProgressBar) activity.findViewById(R.id.powerbarTelepathy);
        powerBarTelepathy.setIconImageResource(R.drawable.icon_telepathy);
        powerBarTelepathy.setProgress(dbHandler.getTelepathy());
    }

}
