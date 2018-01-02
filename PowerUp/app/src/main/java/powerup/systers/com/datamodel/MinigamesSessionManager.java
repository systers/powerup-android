package powerup.systers.com.datamodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MinigamesSessionManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public MinigamesSessionManager(Context context){
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = pref.edit();
    }

    public static final String MINESWEEPER = "MINESWEEPER";
    public static final String SINK_TO_SWIM = "SINK_TO_SWIM";
    public static final String VOCAB_MATCH = "VOCAB_MATCH";

    public boolean hasStarted(String game){
        return pref.getBoolean(game, false);
    }

    public void start(String game){
        editor.putBoolean(game, true);
        editor.apply();
    }

    public void finish(String game){
        editor.putBoolean(game, false);
        editor.apply();
    }

    public void reset(String game){
        editor.remove(game);
        editor.apply();
    }
}
