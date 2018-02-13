package powerup.systers.com.powerup.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import powerup.systers.com.GameOverActivity;
import powerup.systers.com.R;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GameOverActivity.class)
public class GameOverActivityTests {
    private GameOverActivity activity;

    @Mock
    private Button backButton;

    @Before
    public void setUp() {
        activity = spy(GameOverActivity.class);

        doReturn(backButton).when(activity).findViewById(R.id.ContinueButtonMap);

        suppress(method(Activity.class, "onCreate", Bundle.class));
        suppress(method(Activity.class, "setContentView", int.class));
    }

    @Test
    public void testBackToMapClick() {
        ArgumentCaptor<View.OnClickListener> captor =
                ArgumentCaptor.forClass(View.OnClickListener.class);

        doNothing().when(backButton).setOnClickListener(captor.capture());

        activity.onCreate(mock(Bundle.class));

        doNothing().when(activity).startActivityForResult((Intent) any(), anyInt());
        doNothing().when(activity).finish();

        captor.getValue().onClick(backButton);
        verify(activity).startActivityForResult((Intent) any(), anyInt());
    }
}
