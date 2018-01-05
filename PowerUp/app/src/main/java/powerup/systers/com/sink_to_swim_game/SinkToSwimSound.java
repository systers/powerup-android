package powerup.systers.com.sink_to_swim_game;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import powerup.systers.com.R;
import powerup.systers.com.powerup.LoopMediaPlayer;
import powerup.systers.com.powerup.PowerUpUtils;

public class SinkToSwimSound extends Service {

    LoopMediaPlayer player;

    // used to pause or resume sound according to the Intent received
    private BroadcastReceiver serviceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(PowerUpUtils.SWIM_SINK_SOUND_ACTION)) {
                String message = intent.getStringExtra(PowerUpUtils.SWIM_SINK_SOUND_EXTRA);

                if (message != null)
                    if (message.equals(PowerUpUtils.SWIM_SINK_SOUND_PAUSE)) { // pauses the sound
                        if (player != null && player.isPlaying())
                            player.pause();
                    } else if (message.equals(PowerUpUtils.SWIM_SINK_SOUND_RESUME)) { // resumes the sound
                        if (player == null)
                            player = getLoopMediaPlayer();
                        if (!player.isPlaying())
                            player.start();
                    }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        player = getLoopMediaPlayer();
        player.start();
        registerReceiver(serviceReceiver, new IntentFilter(PowerUpUtils.SWIM_SINK_SOUND_ACTION));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(serviceReceiver);
        player.release();
        player = null;
    }

    public LoopMediaPlayer getLoopMediaPlayer() {
        return LoopMediaPlayer.create(this, R.raw.sink_to_sail_boat_sound);
    }
}
