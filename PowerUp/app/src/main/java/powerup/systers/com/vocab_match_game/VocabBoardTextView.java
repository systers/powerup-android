package powerup.systers.com.vocab_match_game;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by sachinaggarwal on 23/07/17.
 */

public class VocabBoardTextView extends androidx.appcompat.widget.AppCompatTextView {

    private int position;

    public VocabBoardTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
