package powerup.systers.com.sink_to_swim_game;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class SinkToSwimSound extends Service {

    public MediaPlayer player;

    /**
     * @desc Default constructor for the Activity
     */
    public SinkToSwimSound() {
    }

    /**
     * @param mediaPlayer to receive MediaPlayer object from SinkToSwimGame
     * @desc Custom constructor with MediaPlayer parameter
     */
    public SinkToSwimSound(MediaPlayer mediaPlayer) {
        player = mediaPlayer;
    }

    /**
     * @desc OnCreate method to set MediaPlayer specifications
     */
    @Override
    public void onCreate() {
        player.setLooping(true);
        player.setVolume(100, 100);
        super.onCreate();
    }

    /**
     * @desc start playback
     */
    public void Start() {
        player.start();
    }

    /**
     * @desc pause playback
     */
    public void Pause() {
        player.pause();
    }

    /**
     * @desc stop playback
     */
    public void Stop() {
        player.stop();
        player.reset();
        player.release();
    }

    /**
     * @desc destroy function to check and release object at end
     */
    @Override
    public void onDestroy() {
        player.stop();
        player.reset();
        if (player != null) player.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
