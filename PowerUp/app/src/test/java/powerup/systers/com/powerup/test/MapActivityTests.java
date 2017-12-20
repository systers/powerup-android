package powerup.systers.com.powerup.test;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import powerup.systers.com.MapActivity;
import powerup.systers.com.R;
import powerup.systers.com.datamodel.Scenario;
import powerup.systers.com.db.DatabaseHandler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.replace;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MapActivity.class)
public class MapActivityTests {


    private MapActivity activity;

    @Mock
    private Button storeButton;

    @Mock
    private Button homeButton;

    @Mock
    private ImageView imageView;

    @Mock
    private Window window;

    @Before
    public void setUp() {
        activity = spy(MapActivity.class);

        DatabaseHandler handler = new DatabaseHandler(activity) {
            @Override
            public Scenario getScenarioFromID(int id) {
                return new Scenario() {
                    @Override
                    public int getCompleted() {
                        return 0;
                    }
                };
            }
        };

        replace(method(MapActivity.class, "findViewById")).with(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                switch ((int)args[0]) {
                    case R.id.school_building: case R.id.hospital_building: case R.id.library_building:
                    case R.id.school: case R.id.house:case R.id.hospital: case R.id.library:
                        return imageView;
                    case R.id.store:
                        return storeButton;
                    case R.id.home_button:
                        return homeButton;
                }
                return null;
            }
        });

        doReturn(window).when(activity).getWindow();
        doReturn(handler).when(activity).getmDbHandler();

        suppress(method(Activity.class, "onCreate", Bundle.class));
        suppress(method(Activity.class, "setContentView", int.class));
    }

    @Test
    public void testStoreClick() {
        ArgumentCaptor<View.OnClickListener> captor =
                ArgumentCaptor.forClass(View.OnClickListener.class);

        doNothing().when(storeButton).setOnClickListener(captor.capture());

        activity.onCreate(mock(Bundle.class));

        doNothing().when(activity).startActivity((Intent) any());
        captor.getValue().onClick(storeButton);
        verify(activity).startActivity((Intent) any());
    }
}
