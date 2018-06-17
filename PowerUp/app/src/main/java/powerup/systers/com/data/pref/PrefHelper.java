package powerup.systers.com.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefHelper implements IPrefHelper {

    private static final String PREF_KEY_CHECK_FIRST_TIME = "IS_THIS_FIRST_RUN";
    private static final String PREF_KEY_PREVIOUSLY_CUSTOMIZED = "IS_PREVIOUSLY_CUSTOMIZED";
    private final SharedPreferences mPrefs;

    public PrefHelper(Context context, String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public boolean checkFirstTime() {
        return mPrefs.getBoolean(PREF_KEY_CHECK_FIRST_TIME, false);
    }


    @Override
    public void setFirstTime(boolean value) {
        mPrefs.edit().putBoolean(PREF_KEY_CHECK_FIRST_TIME, value).apply();
    }

    @Override
    public boolean checkPreviouslyCustomized() {
        return mPrefs.getBoolean(PREF_KEY_PREVIOUSLY_CUSTOMIZED,false);
    }

    @Override
    public void setPreviouslyCustomized(boolean value) {
        mPrefs.edit().putBoolean(PREF_KEY_PREVIOUSLY_CUSTOMIZED,value).apply();
    }

}
