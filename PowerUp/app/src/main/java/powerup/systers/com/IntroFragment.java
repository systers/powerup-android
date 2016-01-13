package powerup.systers.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IntroFragment extends Fragment {

    private int fragmentResource;

    public IntroFragment() {
    }

    public IntroFragment(int id) {
        switch (id) {
            case 1:
                fragmentResource = R.layout.intro_screen_1;
                break;
            case 2:
                fragmentResource = R.layout.intro_screen_2;
                break;
            case 3:
                fragmentResource = R.layout.intro_screen_3;
                break;
            case 4:
                fragmentResource = R.layout.intro_screen_4;
                break;
            case 5:
                fragmentResource = R.layout.intro_screen_5;
                break;
            case 6:
                fragmentResource = R.layout.intro_screen_6;
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(fragmentResource, container, false);
    }
}
