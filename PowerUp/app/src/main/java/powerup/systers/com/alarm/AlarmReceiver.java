package powerup.systers.com.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.Calendar;

import powerup.systers.com.R;

/**
 * Created by prajwalm on 31/01/18.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            setAlarm(context);
        }

    }


    public static void setAlarm(Context context) {


        AlarmManager alarmManager = AlarmManagerProvider.getAlarmManager(context);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String key = context.getString(R.string.setAlarm);
        String value = context.getString(R.string.timeSlots);

        boolean switched = preferences.getBoolean(key, false);

        PendingIntent pendingIntent = PendingIntent.getService(context, 1, new Intent(context, AlertService.class), 0);

        if (switched) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            String alarmTime = preferences.getString(value, "10:00");
            String[] arr = alarmTime.split(":");
            calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(arr[0]));
            Toast.makeText(context, arr[0] + arr[1], Toast.LENGTH_SHORT).show();
            calendar.set(Calendar.MINUTE, Integer.valueOf(arr[1]));

            if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


        } else {

            alarmManager.cancel(pendingIntent);
        }


    }
}
