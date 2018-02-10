package powerup.systers.com.alarm;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import powerup.systers.com.R;
import powerup.systers.com.StartActivity;

/**
 * Created by prajwalm on 31/01/18.
 */

public class AlertService extends IntentService {

    private static final String TAG = AlertService.class.getSimpleName();

    public AlertService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent startGame = new Intent(this, StartActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, startGame, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification reminderNotification = new NotificationCompat.Builder(this)
                .setContentTitle("Play Game Now")
                .setContentText("Its been a long time since you played the game...")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.app_icon)
                .build();


        if (notificationManager != null) {
            notificationManager.notify(9, reminderNotification);
        }


    }
}
