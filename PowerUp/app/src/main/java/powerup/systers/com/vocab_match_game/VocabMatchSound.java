package powerup.systers.com.vocab_match_game;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import powerup.systers.com.R;

/**
 * Created by fidel on 1/3/2018.
 */

public class VocabMatchSound extends Service{
    MediaPlayer mediaPlayer;
    final String SOUND_TYPE = "SOUND_TYPE";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Prevent too many if statements in the future
        int soundType = intent.getExtras().getInt(SOUND_TYPE);
        switch(soundType){
                case 0: mediaPlayer = MediaPlayer.create(this, R.raw.vocab_correct);
                break;
                case 1: mediaPlayer = MediaPlayer.create(this, R.raw.vocab_wrong);
                break;
        }
        mediaPlayer.start();
        return Service.START_NOT_STICKY;
    }

    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        stopSelf();
        super.onDestroy();
    }
}
