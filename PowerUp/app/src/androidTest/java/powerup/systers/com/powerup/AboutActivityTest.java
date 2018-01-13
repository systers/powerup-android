package powerup.systers.com.powerup;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import powerup.systers.com.AboutActivity;
import powerup.systers.com.R;


public class AboutActivityTest extends ActivityInstrumentationTestCase2<AboutActivity> {
    private Solo solo;

    public AboutActivityTest() {
        super(AboutActivity.class);
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

    public void testExpandGameSection() {
        TextView view = findView(R.id.about_the_game);
        View title = solo.getText(solo.getString(R.string.about_game_section_title));

        solo.clickOnView(title);
        solo.waitForCondition(new VisibilityCondition(title, View.VISIBLE), 1000);
        assertTrue(view.getVisibility() == View.VISIBLE);

        solo.clickOnView(title);
        solo.waitForCondition(new VisibilityCondition(title, View.GONE), 1000);
        assertTrue(view.getVisibility() == View.GONE);
    }

    public void testExpandUrgencySection() {
        TextView view = findView(R.id.about_the_urgency);
        View title = solo.getText(solo.getString(R.string.about_urgency_section_title));

        solo.clickOnView(title);
        solo.waitForCondition(new VisibilityCondition(title, View.VISIBLE), 1000);
        assertTrue(view.getVisibility() == View.VISIBLE);

        solo.clickOnView(title);
        solo.waitForCondition(new VisibilityCondition(title, View.GONE), 1000);
        assertTrue(view.getVisibility() == View.GONE);
    }

    public void testExpandDescription() {
        TextView view = findView(R.id.about_helping_by);
        View title = solo.getText(solo.getString(R.string.about_how_app_helps_section_title));

        solo.clickOnView(title);
        solo.waitForCondition(new VisibilityCondition(title, View.VISIBLE), 1000);
        assertTrue(view.getVisibility() == View.VISIBLE);

        solo.clickOnView(title);
        solo.waitForCondition(new VisibilityCondition(title, View.GONE), 1000);
        assertTrue(view.getVisibility() == View.GONE);
    }

    @SuppressWarnings("unchecked")
    private <K extends View> K findView(int id) {
        return (K) solo.getCurrentActivity().findViewById(id);
    }

    private class VisibilityCondition implements Condition {
        private View view;
        private int expectedVisibility;

        private VisibilityCondition(View view, int expectedVisibility) {
            this.view = view;
            this.expectedVisibility = expectedVisibility;
        }

        @Override
        public boolean isSatisfied() {
            return view.getVisibility() == expectedVisibility;
        }
    }
}
