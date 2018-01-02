package powerup.systers.com.powerup;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MiniGameSessionManager {
    private SharedPreferences preferences;

    public static final String SINK_TO_SWIM = "SINK_TO_SWIM";
    public static final String VOCAB_MATCH = "VOCAB_MATCH";

    public MiniGameSessionManager(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isSessionStarted(String gameName) {
        return preferences.getBoolean(gameName, false);
    }

    public void startSession(String gameName) {
        preferences.edit()
                .putBoolean(gameName, true)
                .apply();
    }

    public void finishSession(String gameName) {
        preferences.edit()
                .putBoolean(gameName, false)
                .apply();
    }

    public void reset() {
        preferences.edit()
                .remove(SINK_TO_SWIM)
                .remove(VOCAB_MATCH)
                .apply();
    }
}
