package powerup.systers.com.powerup;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;

import powerup.systers.com.AboutActivity;
import powerup.systers.com.AvatarRoomActivity;
import powerup.systers.com.R;
import powerup.systers.com.StartActivity;


public class StartActivityTest extends ActivityInstrumentationTestCase2<StartActivity> {
    private Solo solo;

    public StartActivityTest() {
        super(StartActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testOpenAboutActivity() throws Throwable {
        clickOnView(R.id.aboutButtonMain);
        checkActivityLaunched(AboutActivity.class);
    }

    public void testNewGameOpensStore() throws Throwable {
        clickOnView(R.id.newUserButtonFirstPage);
        solo.waitForDialogToOpen();
        solo.clickOnButton(solo.getString(R.string.start_confirm_message));
        checkActivityLaunched(AvatarRoomActivity.class);
    }

    @SuppressWarnings("unchecked")
    private <K extends View> K findView(int id) {
        return (K) solo.getCurrentActivity().findViewById(id);
    }

    private void clickOnView(int id) {
        solo.clickOnView(findView(id));
    }

    private void checkActivityLaunched(Class<? extends Activity> activityClass) {
        assertTrue(solo.waitForActivity(activityClass, 5000));
        solo.assertCurrentActivity("Wrong activity", activityClass);
    }
}
