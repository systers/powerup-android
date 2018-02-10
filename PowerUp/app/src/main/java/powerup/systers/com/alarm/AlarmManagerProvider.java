package powerup.systers.com.alarm;

import android.app.AlarmManager;
import android.content.Context;

/**
 * Created by prajwalm on 31/01/18.
 */

public class AlarmManagerProvider {

    private static AlarmManager sAlarmManager;

    public static synchronized AlarmManager getAlarmManager(Context context) {
        if (sAlarmManager == null) {
            sAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }
        return sAlarmManager;

    }
}
