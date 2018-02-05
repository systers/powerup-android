package powerup.systers.com.vocab_match_game;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

import java.io.IOException;

public class VocabMatchSound extends Service {
    private MediaPlayer player;
    private AssetFileDescriptor assetFileDescriptor;
    private String sound;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            assetFileDescriptor = getAssets().openFd(sound);
            player = new MediaPlayer();
            player.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        }catch (IOException ex){
            return;
        }
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        sound = (String) intent.getExtras().get("soundTitle");
        player.start();
        return Service.START_STICKY;
    }

    public void onStart(Intent intent, int startId) {

        // TO DO
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
}
