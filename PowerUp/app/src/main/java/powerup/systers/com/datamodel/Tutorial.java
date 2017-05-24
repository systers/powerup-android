package powerup.systers.com.datamodel;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import powerup.systers.com.R;

/**
 * Created by sachinaggarwal on 11/02/17.
 */

public enum Tutorial {
    //defines list of intro drawable, title text and description text for each intro slide
    START(R.drawable.intro1, R.string.screen_1_title, R.string.screen_1_desc),
    FIRST(R.drawable.intro2, R.string.screen_2_title, R.string.screen_2_desc),
    SECOND(R.drawable.intro3, R.string.screen_3_title, R.string.screen_3_desc),
    THIRD(R.drawable.intro4, R.string.screen_4_title, R.string.screen_4_desc),
    FOURTH(R.drawable.intro5, R.string.screen_5_title, R.string.screen_5_desc);

    private
    @DrawableRes
    final
    int image;
    private
    @StringRes
    final
    int title;
    private
    @StringRes
    final
    int description;

    Tutorial(int image, int title, int description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public int getTitle() {
        return title;
    }

    public int getDescription() {
        return description;
    }
}
