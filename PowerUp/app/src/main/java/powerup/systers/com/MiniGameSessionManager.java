package powerup.systers.com;

import android.content.Context;
import android.content.SharedPreferences;

import powerup.systers.com.sink_to_swim_game.SinkToSwimGame;

/**
 * Created by Mu'aaz Kasker on 1/4/2018.
 */

public class MiniGameSessionManager {
    public static final String MINESWEEPER = "MINSWEEPER";
    public static final String VOCAB_MATCH = "VOCAB_MATCH";
    public static final String SINK_TO_SWIM = "SINK_TO_SWIM";

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public MiniGameSessionManager(Context context){
        prefs = context.getSharedPreferences("miniGamePrefs", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void start(String game){
        editor.putBoolean(game, true);
        editor.apply();
    }

    public void complete(String game){
        editor.putBoolean(game, false);
        editor.apply();
    }

    public boolean isIncomplete(String game){
        return prefs.getBoolean(game, false);
    }

    public void resetSession(){
        editor.remove(MINESWEEPER);
        editor.remove(VOCAB_MATCH);
        editor.remove(SINK_TO_SWIM);
        editor.apply();
    }
}
