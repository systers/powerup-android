package powerup.systers.com.minesweeper;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import powerup.systers.com.R;
import powerup.systers.com.powerup.PowerUpUtils;

/**
 * Created by vatsalkulshreshtha on 06/01/18.
 */

public class MinesweeperSound extends Service {
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String result = intent.getStringExtra(PowerUpUtils.MINE_RESULT);
        if (result.equals(PowerUpUtils.CORRECT_TILE)) {
            player = MediaPlayer.create(this, R.raw.right_tile_sound);
            player.setLooping(false);
            player.start();
        } else {
            player = MediaPlayer.create(this, R.raw.wrong_tile_sound);
            player.setLooping(false);
            player.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
        super.onDestroy();
    }
}
