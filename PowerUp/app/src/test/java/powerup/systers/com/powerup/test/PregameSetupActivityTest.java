package powerup.systers.com.powerup.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.powerup.PowerUpUtils;
import powerup.systers.com.pregame_setup.PregameSetupActivity;

import static junit.framework.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PregameSetupActivityTest {

    @Mock
    private PregameSetupActivity mPregameSetupActivity;
    private int position = 5, updateValue = 0;

    @Before
    public void setupActivity() {
        mPregameSetupActivity = new PregameSetupActivity();
    }

    //Labels are set according to npcType
    @Test
    public void testSisterTextLabel() {
        SessionHistory.npcType = 0;
        assertEquals("Sister", PowerUpUtils.NPC_TEXT[SessionHistory.npcType]);
    }

    @Test
    public void testFriendTextLabel() {
        SessionHistory.npcType = 1;
        assertEquals("Friend", PowerUpUtils.NPC_TEXT[SessionHistory.npcType]);
    }

    @Test
    public void testDoctorTextLabel() {
        SessionHistory.npcType = 2;
        assertEquals("Doctor", PowerUpUtils.NPC_TEXT[SessionHistory.npcType]);
    }

    @Test
    public void testTeacherTextLabel() {
        SessionHistory.npcType = 3;
        assertEquals("Teacher", PowerUpUtils.NPC_TEXT[SessionHistory.npcType]);
    }

    //Testing values for saved options
    @Test
    public void testSavedNpcHome() {
        mPregameSetupActivity.saveChosenNpc(0, position);
        assertEquals(SessionHistory.npcHome, position);
    }

    @Test
    public void testSavedNpcSchool() {
        mPregameSetupActivity.saveChosenNpc(1, position);
        assertEquals(SessionHistory.npcSchool, position);
    }

    @Test
    public void testSavedNpcHospital() {
        mPregameSetupActivity.saveChosenNpc(2, position);
        assertEquals(SessionHistory.npcHospital, position);
    }

    @Test
    public void testSavedNpcLibrary() {
        mPregameSetupActivity.saveChosenNpc(3, position);
        assertEquals(SessionHistory.npcLibrary, position);
    }

    //Testing values for updated options
    @Test
    public void testUpdatedNpcHome() {
        SessionHistory.npcHome = 4;
        updateValue = mPregameSetupActivity.updateClickedPosition(PowerUpUtils.SISTER_TYPE);
        assertEquals(SessionHistory.npcHome, updateValue);
    }

    @Test
    public void testUpdatedNpcSchool() {
        SessionHistory.npcSchool = 4;
        updateValue = mPregameSetupActivity.updateClickedPosition(PowerUpUtils.SISTER_TYPE);
        assertEquals(SessionHistory.npcHome, updateValue);
    }

    @Test
    public void testUpdatedNpcHospital() {
        SessionHistory.npcHospital = 4;
        updateValue = mPregameSetupActivity.updateClickedPosition(PowerUpUtils.SISTER_TYPE);
        assertEquals(SessionHistory.npcHome, updateValue);
    }

    @Test
    public void testUpdatedNpcLibrary() {
        SessionHistory.npcLibrary = 4;
        updateValue = mPregameSetupActivity.updateClickedPosition(PowerUpUtils.SISTER_TYPE);
        assertEquals(SessionHistory.npcHome, updateValue);
    }
}
