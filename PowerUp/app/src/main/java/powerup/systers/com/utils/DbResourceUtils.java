package powerup.systers.com.utils;

import android.content.Context;

import powerup.systers.com.R;
import powerup.systers.com.data.DataSource;
import powerup.systers.com.data.IDataSource;

public class DbResourceUtils {

    private DataSource source;
    private Context context;
    public DbResourceUtils(DataSource source, Context context) {
        this.source = source;
        this.context = context;
    }

    public int getEyeResourceId() {
        final int[] eye = new int[1];
        String eyeImageName = context.getResources().getString(R.string.eye);
        source.getAvatarEye(new IDataSource.LoadIntegerCallback() {
            @Override
            public void onResultLoaded(int value) {
                eye[0] = value;
            }
        });
        eyeImageName = eyeImageName + eye[0];
        R.drawable ourRID = new R.drawable();
        java.lang.reflect.Field photoNameField;
        try {
            photoNameField = ourRID.getClass().getField(eyeImageName);
            //returning eye resource id
            return photoNameField.getInt(ourRID);
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
            return 0;
        }
    }

    public int getSkinResourceId() {
        final int[] skin = new int[1];
        String skinmageName = context.getResources().getString(R.string.skin);
        source.getAvatarSkin(new IDataSource.LoadIntegerCallback() {
            @Override
            public void onResultLoaded(int value) {
                skin[0] = value;
            }
        });
        skinmageName = skinmageName + skin[0];
        R.drawable ourRID = new R.drawable();
        java.lang.reflect.Field photoNameField;
        try {
            photoNameField = ourRID.getClass().getField(skinmageName);
            //returning skin resource id
            return photoNameField.getInt(ourRID);
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
            return 0;
        }
    }

    public int getClothResourceId() {
        final int[] cloth = new int[1];
        String clothImageName = context.getResources().getString(R.string.cloth);
        source.getAvatarCloth(new IDataSource.LoadIntegerCallback() {
            @Override
            public void onResultLoaded(int value) {
                cloth[0] = value;
            }
        });
        clothImageName = clothImageName + cloth[0];
        R.drawable ourRID = new R.drawable();
        java.lang.reflect.Field photoNameField;
        try {
            photoNameField = ourRID.getClass().getField(clothImageName);
            //returning cloth resource id
            return photoNameField.getInt(ourRID);
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
            return 0;
        }
    }

    public int getHairResourceId() {
        final int[] hair = new int[1];
        String hairImageName = context.getResources().getString(R.string.hair);
        source.getAvatarHair(new IDataSource.LoadIntegerCallback() {
            @Override
            public void onResultLoaded(int value) {
                hair[0] = value;
            }
        });
        hairImageName = hairImageName + hair[0];
        R.drawable ourRID = new R.drawable();
        java.lang.reflect.Field photoNameField;
        try {
            photoNameField = ourRID.getClass().getField(hairImageName);
            //returning hair resource id
            return photoNameField.getInt(ourRID);
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
            return 0;
        }
    }

    public int getAvatarAccessoriesResourceId() {
        final int[] avatar = new int[1];
        String avatarImageName = context.getResources().getString(R.string.accessories);
        source.getAvatarAccessory(new IDataSource.LoadIntegerCallback() {
            @Override
            public void onResultLoaded(int value) {
                avatar[0] = value;
            }
        });
        avatarImageName = avatarImageName + avatar[0];
        R.drawable ourRID = new R.drawable();
        java.lang.reflect.Field photoNameField;
        try {
            photoNameField = ourRID.getClass().getField(avatarImageName);
            //returning accessory resource id
            return photoNameField.getInt(ourRID);
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException error) {
            error.printStackTrace();
            return 0;
        }
    }
}
