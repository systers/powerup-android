package powerup.systers.com.sink_to_swim_game;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Aryaman21 on 31-12-2017.
 */

public class SinkToSwimSessionManager {
    
    private final String PREF_NAME = "SINKTOSWIM_PREFERENCE";
    private final int PRIVATE_MODE = 0;
    private final String GAME_OPENED = "IS_SINKTOSWIM_OPENED";

    SharedPreferences pref;
    Context context;
    SharedPreferences.Editor editor;

    /**
     * @param context - context of the calling activity
     * @desc Returns the object of SessionManager through which session changes can be made
     */
    public SinkToSwimSessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * @return true if app was closed without completing the sinktoswim game
     * @desc used to know if sinktoswim game was being played when user last left the app
     */
    public boolean isSinkToSwimOpened() {
        return pref.getBoolean(GAME_OPENED, false);
    }

    public void saveSinkToSwimOpenedStatus(boolean isOpened) {
        editor.putBoolean(GAME_OPENED, isOpened);
        editor.clear();
        editor.commit();
    }
}
