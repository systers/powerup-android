package powerup.systers.com.vocab_match_game;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by fidel on 12/31/2017.
 */

public class VocabMatchSessionManager {
    private final String GAME_OPENED = "IS_VOCAB_MATCH_OPENED";
    static final String CURR_SCORE = "currScore";
    static final String CURR_TILE = "currTile";

    SharedPreferences pref;
    Context context;
    SharedPreferences.Editor editor;
    public VocabMatchSessionManager(Context context) {
        this.context = context;
        String PREF_NAME = "VOCAB_MATCH_PREFERENCE";
        int PRIVATE_MODE = 0;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void saveData(int score, int currTile){
        editor.putInt(CURR_SCORE, score);
        editor.putInt(CURR_TILE,currTile);
        editor.commit();
    }

    public int getCurrScore() {return pref.getInt(CURR_SCORE,0);}
    public int getCurrTile() {return pref.getInt(CURR_TILE,0);}

    public boolean isVocabMatchOpened() {
        return pref.getBoolean(GAME_OPENED, false);
    }

    public void saveVocabMatchOpenedStatus(boolean isOpened) {
        editor.putBoolean(GAME_OPENED, isOpened);
        editor.clear();
        editor.commit();
    }
}