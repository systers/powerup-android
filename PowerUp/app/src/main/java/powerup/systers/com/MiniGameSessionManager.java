package powerup.systers.com;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mu'aaz Kasker on 1/2/2018.
 */

public class MiniGameSessionManager {

    public final static String VOCAB_MATCH = "VOCAB_MATCH";
    public final static String SINK_TO_SWIM = "SINK_TO_SWIM";
    public final static String MINESWEEPER = "MINESWEEPER";
    public final String PREF_NAME = "MINIGAMES_PREFS";

    SharedPreferences prefs;
    Context context;
    SharedPreferences.Editor editor;

    public MiniGameSessionManager(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void startSession(String game){
        editor.putBoolean(game, true);
        editor.apply();
    }

    public boolean hasStarted(String game){
        return prefs.getBoolean(game, false);
    }

    public void Complete(String game){
        editor.putBoolean(game, false);
        editor.apply();
    }

    public void resetSession(String game){
        editor.remove(game);
        editor.apply();
    }

    public void resetSession(){
        editor.remove(MINESWEEPER);
        editor.remove(VOCAB_MATCH);
        editor.remove(SINK_TO_SWIM);
        editor.apply();
    }
}
