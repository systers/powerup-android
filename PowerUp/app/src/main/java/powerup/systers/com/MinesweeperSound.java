package powerup.systers.com;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

import powerup.systers.com.powerup.PowerUpUtils;

public class MinesweeperSound extends Service {

    MediaPlayer player = null;

    public int onStartCommand(Intent intent, int flags, int startId){
        try {
            String audioFile = null;
            audioFile = intent.getStringExtra(PowerUpUtils.MINESWEEPER_TILE_RESULT);
            AssetFileDescriptor afd = getBaseContext().getAssets().openFd(audioFile);
            player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
        player.start();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
