package powerup.systers.com.powerup;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;

/**
 * Attributed from https://stackoverflow.com/a/29883923 written by Mattia Maestrini, and edited to fit
 * in the current context. This is used as using the setLooping() method of MediaPlayer produces gaps
 * between each iteration. It works only for Build Versions greater than 16, so if the Build Version is
 * 15 or below, setLooping() method is used as a fallback.
 */

public class LoopMediaPlayer {
    private Context mContext = null;
    private int mResId = 0;

    private MediaPlayer mCurrentPlayer = null;
    private MediaPlayer mNextPlayer = null;

    public static LoopMediaPlayer create(Context context, int resId) {
        return new LoopMediaPlayer(context, resId);
    }

    private LoopMediaPlayer(Context context, int resId) {
        mContext = context;
        mResId = resId;

        mCurrentPlayer = MediaPlayer.create(mContext, mResId);
        mCurrentPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mCurrentPlayer.start();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            createNextMediaPlayer(); // creates the next MediaPlayer having the same source
        else mCurrentPlayer.setLooping(true); // fallback code if Build Version is 15 or less
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void createNextMediaPlayer() {
        mNextPlayer = MediaPlayer.create(mContext, mResId); // creates a MediaPlayer with the same source
        mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
        mCurrentPlayer.setOnCompletionListener(onCompletionListener);
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release(); // release the current media player
            mCurrentPlayer = mNextPlayer; // assigns the next media player to the current as the next is the one playing

            createNextMediaPlayer(); // creates another media player for using as the next
        }
    };

    public boolean isPlaying() throws IllegalStateException {
        return mCurrentPlayer.isPlaying();
    }

    public void start() throws IllegalStateException {
        mCurrentPlayer.start();
    }

    public void pause() throws IllegalStateException {
        mCurrentPlayer.pause();
    }

    // release all the media player resources
    public void release() {
        mCurrentPlayer.release();
        mNextPlayer.release();
    }
}
