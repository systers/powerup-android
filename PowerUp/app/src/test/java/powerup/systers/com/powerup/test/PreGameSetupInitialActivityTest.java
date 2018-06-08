package powerup.systers.com.powerup.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.powerup.PowerUpUtils;
import powerup.systers.com.pregame_setup.PreGameSetupInitialActivity;

import static junit.framework.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PreGameSetupInitialActivityTest {

    @Mock
    private PreGameSetupInitialActivity mPreGameSetupInitialActivity;

    @Before
    public void setupActivity(){
        mPreGameSetupInitialActivity = new PreGameSetupInitialActivity();
    }

    @Test
    public void testSisterClick(){
        mPreGameSetupInitialActivity.setNpcType(PowerUpUtils.SISTER_TYPE);
        assertEquals(PowerUpUtils.SISTER_TYPE, SessionHistory.npcType);
    }

    @Test
    public void testTeacherClick(){
        mPreGameSetupInitialActivity.setNpcType(PowerUpUtils.TEACHER_TYPE);
        assertEquals(PowerUpUtils.TEACHER_TYPE, SessionHistory.npcType);
    }

    @Test
    public void testFriendClick(){
        mPreGameSetupInitialActivity.setNpcType(PowerUpUtils.FRIEND_TYPE);
        assertEquals(PowerUpUtils.FRIEND_TYPE, SessionHistory.npcType);
    }

    @Test
    public void testDoctorClick(){
        mPreGameSetupInitialActivity.setNpcType(PowerUpUtils.DOCTOR_TYPE);
        assertEquals(PowerUpUtils.DOCTOR_TYPE, SessionHistory.npcType);
    }
}
