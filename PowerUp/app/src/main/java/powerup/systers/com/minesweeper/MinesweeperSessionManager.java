package powerup.systers.com.minesweeper;

import android.content.SharedPreferences;
import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by sachinaggarwal on 25/06/17.
 */

public class MinesweeperSessionManager {

    private final String SCORE = "MINESWEEPER_SCORE";
    private final String ROUNDS_COMPLETED = "MINESWEEPER_ROUND_COMPLETED";

    SharedPreferences pref;
    Context context;
    SharedPreferences.Editor editor;

    /**
     * @desc Returns the object of SessionManager through which session changes can be made
     * @param context - context of the calling activity
     */
    public MinesweeperSessionManager(Context context) {
        this.context = context;
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = pref.edit();
    }

    /**
     * @desc updates the score and roundsCompleted in the session database
     * @param roundsCompleted - number of rounds completed till now
     * @param score - total current score
     */
    public void saveData(int score, int roundsCompleted) {
        editor.putInt(SCORE, score);
        editor.putInt(ROUNDS_COMPLETED, roundsCompleted);
        editor.commit();
    }

    public int getScore() {
        return pref.getInt(SCORE, 0);
    }

    public int getCompletedRounds() {
        return pref.getInt(ROUNDS_COMPLETED, 1);
    }
}
