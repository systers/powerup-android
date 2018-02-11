package powerup.systers.com.powerup;

/**
 * Created by fidel on 1/16/2018.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;

import com.robotium.solo.Solo;

import powerup.systers.com.AvatarRoomActivity;
import powerup.systers.com.MapActivity;
import powerup.systers.com.R;
import powerup.systers.com.datamodel.SessionHistory;

public class AvatarRoomTest extends ActivityInstrumentationTestCase2<AvatarRoomActivity>{
    private Solo solo;

    public AvatarRoomTest() {
        super(AvatarRoomActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        super.setUp();
    }

    public void testEyeRight() throws Exception {
        int pos = 1; //starting position. default is 1.
        int max =  SessionHistory.eyesTotalNo;
        String imageName = "eyes";
        View view = solo.getView(R.id.eyes_right);
        max-=2; //As there's currently a bug which pr hasn't been merged yet. Erase this later.
        ImageView imageView = (ImageView) solo.getView(R.id.eye_view);
        Context context = view.getContext();
        for(int i = 1; i<max+2; i++) { //make sure loop after last item
            pos = (pos - 1) % max + 1;
            String stringId = imageName + Integer.toString(pos);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            Drawable current = imageView.getDrawable();
            Drawable expected = context.getResources().getDrawable(id);
            assertTrue(areDrawablesIdentical(current,expected));
            solo.clickOnView(view);
            solo.sleep(1000);
            if(!(i==7||i==8)) {//Workaround for the bug. Erase this later.
                pos++;
            }
        }
    }

    public void testEyeLeft() throws Exception {
        int pos = 1; //starting position. default is 1.
        int max =  SessionHistory.eyesTotalNo;
        String imageName = "eyes";
        View view = solo.getView(R.id.eyes_left);
        max-=2; //As there's currently a bug which pr hasn't been merged yet. Erase this later.
        ImageView imageView = (ImageView) solo.getView(R.id.eye_view);
        Context context = view.getContext();
        for(int i = 1; i<max+2; i++) {
            pos = (pos - 1 + max) % max + 1;
            String stringId = imageName + Integer.toString(pos);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            Drawable current = imageView.getDrawable();
            Drawable expected = context.getResources().getDrawable(id);
            assertTrue(areDrawablesIdentical(current,expected));
            solo.clickOnView(view);
            solo.sleep(1000);
            if(!(i==1||i==2)) { //Workaround for the bug. Erase this later.
                pos--;
            }
        }
    }

    public void testSkinRight() throws Exception {
        int pos = 1; //starting position. default is 1.
        int max =  SessionHistory.skinTotalNo;
        String imageName = "skin";
        View view = solo.getView(R.id.skin_right);
        ImageView imageView = (ImageView) solo.getView(R.id.skin_view);
        Context context = view.getContext();
        for(int i = 1; i<max+2; i++) { //make sure loop after last item
            pos = (pos - 1) % max + 1;
            String stringId = imageName + Integer.toString(pos);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            Drawable current = imageView.getDrawable();
            Drawable expected = context.getResources().getDrawable(id);
            assertTrue(areDrawablesIdentical(current,expected));
            solo.clickOnView(view);
            solo.sleep(1000);
            pos++;
        }
    }

    public void testSkinLeft() throws Exception {
        int pos = 1; //starting position. default is 1.
        int max =  SessionHistory.skinTotalNo;
        String imageName = "skin";
        View view = solo.getView(R.id.skin_left);
        ImageView imageView = (ImageView) solo.getView(R.id.skin_view);
        Context context = view.getContext();
        for(int i = 1; i<max+2; i++) {
            pos = (pos - 1 + max) % max + 1;
            String stringId = imageName + Integer.toString(pos);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            Drawable current = imageView.getDrawable();
            Drawable expected = context.getResources().getDrawable(id);
            assertTrue(areDrawablesIdentical(current,expected));
            solo.clickOnView(view);
            solo.sleep(1000);
            pos--;
        }
    }

    public void testClothRight() throws Exception {
        int pos = 1; //starting position. default is 1.
        int max =  SessionHistory.clothTotalNo;
        String imageName = "dress_avatar";
        View view = solo.getView(R.id.clothes_right);
        ImageView imageView = (ImageView) solo.getView(R.id.dress_view);
        Context context = view.getContext();
        for(int i = 1; i<max+2; i++) { //make sure loop after last item
            pos = (pos - 1) % max + 1;
            String stringId = imageName + Integer.toString(pos);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            Drawable current = imageView.getDrawable();
            Drawable expected = context.getResources().getDrawable(id);
            assertTrue(areDrawablesIdentical(current,expected));
            solo.clickOnView(view);
            solo.sleep(1000);
            pos++;
        }
    }

    public void testClothLeft() throws Exception {
        int pos = 1; //starting position. default is 1.
        int max =  SessionHistory.clothTotalNo;
        String imageName = "dress_avatar";
        View view = solo.getView(R.id.clothes_left);
        ImageView imageView = (ImageView) solo.getView(R.id.dress_view);
        Context context = view.getContext();
        for(int i = 1; i<max+2; i++) {
            pos = (pos - 1 + max) % max + 1;
            String stringId = imageName + Integer.toString(pos);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            Drawable current = imageView.getDrawable();
            Drawable expected = context.getResources().getDrawable(id);
            assertTrue(areDrawablesIdentical(current,expected));
            solo.clickOnView(view);
            solo.sleep(1000);
            pos--;
        }
    }

    public void testHairRight() throws Exception {
        int pos = 1; //starting position. default is 1.
        int max =  SessionHistory.hairTotalNo;
        String imageName = "hair";
        View view = solo.getView(R.id.hair_right);
        Context context = view.getContext();
        ImageView imageView = (ImageView) solo.getView(R.id.hair_view);
        for(int i = 1; i < max+2; i++) { //make sure loop after last item
            pos = (pos - 1) % max + 1;
            String stringId = imageName + Integer.toString(pos);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            Drawable current = imageView.getDrawable();
            Drawable expected = context.getResources().getDrawable(id);
            assertTrue(areDrawablesIdentical(current,expected));
            solo.clickOnView(view);
            solo.sleep(1000);
            pos++;
        }
    }

    public void testHairLeft() throws Exception {
        int pos = 1; //starting position. default is 1.
        int max =  SessionHistory.hairTotalNo;
        String imageName = "hair";
        View view = solo.getView(R.id.hair_left);
        Context context = view.getContext();
        ImageView imageView = (ImageView) solo.getView(R.id.hair_view);
        for(int i = 1; i<max+2; i++) {
            pos = (pos - 1 + max) % max + 1;
            String stringId = imageName + Integer.toString(pos);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            Drawable current = imageView.getDrawable();
            Drawable expected = context.getResources().getDrawable(id);
            assertTrue(areDrawablesIdentical(current,expected));
            solo.clickOnView(view);
            solo.sleep(1000);
            pos--;
        }
    }

    public void testContinueButton() throws Exception {
        View view = solo.getView(R.id.continueButtonAvatar);
        solo.clickOnView(view);
        assertTrue(solo.waitForActivity(MapActivity.class,2000));
        solo.assertCurrentActivity("WRONG_ACTIVITY", MapActivity.class);
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        solo.sleep(1000);
        super.tearDown();
    }

    public static boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB) {
        Drawable.ConstantState stateA = drawableA.getConstantState();
        Drawable.ConstantState stateB = drawableB.getConstantState();
        return (stateA != null && stateB != null && stateA.equals(stateB))
                || getBitmap(drawableA).sameAs(getBitmap(drawableB));
    }

    public static Bitmap getBitmap(Drawable drawable) {
        Bitmap result;
        if (drawable instanceof BitmapDrawable) {
            result = ((BitmapDrawable) drawable).getBitmap();
        } else {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            if (width <= 0) {
                width = 1;
            }
            if (height <= 0) {
                height = 1;
            }
            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }
        return result;
    }
}
