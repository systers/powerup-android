package powerup.systers.com.powerup;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StringDef;

public class MiniGameManager {
    public static final String GAME_MINESWEEPER = "minesweeper_tutorial";
    public static final String GAME_VOCAB = "vocab_tutorial";
    public static final String GAME_SINK = "sink_tutorial";

    private SharedPreferences preferences;

    public MiniGameManager(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isTutorialShown(@MiniGameName String miniGameName) {
        return preferences.getBoolean(miniGameName, false);
    }

    public void setTutorialShown(@MiniGameName String miniGameName) {
        preferences.edit()
                .putBoolean(miniGameName, true)
                .apply();
    }

    @StringDef({GAME_MINESWEEPER, GAME_VOCAB, GAME_SINK})
    @interface MiniGameName {
    }
}

