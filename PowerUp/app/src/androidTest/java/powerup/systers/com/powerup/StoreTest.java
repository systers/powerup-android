package powerup.systers.com.powerup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.robotium.solo.Solo;

import powerup.systers.com.MapActivity;
import powerup.systers.com.R;
import powerup.systers.com.StoreActivity;

/**
 * Created by fidel on 1/16/2018.
 */

public class StoreTest extends ActivityInstrumentationTestCase2<StoreActivity> {
    private Solo solo;

    public StoreTest(){
        super(StoreActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
        super.setUp();
    }

    public void testHairTab() throws Exception {
        int max = PowerUpUtils.HAIR_IMAGES.length;
        View left = solo.getView(R.id.left_arrow);
        View right = solo.getView(R.id.right_arrow);
        View view = solo.getView(R.id.hair_button);
        GridView gridView = (GridView) solo.getView(R.id.grid_view);
        final int size = gridView.getChildCount();
        solo.clickOnView(view);
        solo.sleep(1000);
        Context context = view.getContext();
        String imageName = "hair";
        //test right arrow
        for (int i = 0; i < max; i++) {
            ViewGroup gridChild = (ViewGroup) gridView.getChildAt(i % size);
            String stringId = imageName + Integer.toString(i + 1);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            int childSize = gridChild.getChildCount();
            for (int k = 0; k < childSize; k++) {
                if (gridChild.getChildAt(k) instanceof ImageView) {
                    Drawable current = gridChild.getChildAt(k).getBackground();
                    Drawable expected = context.getResources().getDrawable(id);
                    assertTrue(areDrawablesIdentical(current,expected));
                }
            }
            if (i != 0 && (i + 1) % size == 0) {
                solo.clickOnView(right);
                solo.sleep(1000);
            }
        }
        //test left arrow
        for(int i = max-1; i > -1; i--) {
            ViewGroup gridChild = (ViewGroup) gridView.getChildAt(i % size);
            String stringId = imageName + Integer.toString(i+1);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            int childSize = gridChild.getChildCount();
            for (int k = 0; k < childSize; k++) {
                if (gridChild.getChildAt(k) instanceof ImageView) {
                    Drawable current = gridChild.getChildAt(k).getBackground();
                    Drawable expected = context.getResources().getDrawable(id);
                    assertTrue(areDrawablesIdentical(current,expected));
                }
            }
            if(i != 0 && i%size == 0) {
                solo.clickOnView(left);
                solo.sleep(1000);
            }
        }
    }

    public void testClothesTab() throws Exception{
        int max = PowerUpUtils.CLOTHES_IMAGES.length;
        View left = solo.getView(R.id.left_arrow);
        View right = solo.getView(R.id.right_arrow);
        View view = solo.getView(R.id.clothes_button);
        GridView gridView = (GridView) solo.getView(R.id.grid_view);
        final int size = gridView.getChildCount();
        solo.clickOnView(view);
        solo.sleep(1000);
        Context context = view.getContext();
        String imageName = "dress";
        //test right arrow
        for (int i = 0; i < max; i++) {
            ViewGroup gridChild = (ViewGroup) gridView.getChildAt(i % size);
            String stringId = imageName + Integer.toString(i + 1);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            int childSize = gridChild.getChildCount();
            for (int k = 0; k < childSize; k++) {
                if (gridChild.getChildAt(k) instanceof ImageView) {
                    Drawable current = gridChild.getChildAt(k).getBackground();
                    Drawable expected = context.getResources().getDrawable(id);
                    assertTrue(areDrawablesIdentical(current,expected));
                }
            }
            if (i != 0 && (i + 1) % size == 0) {
                solo.clickOnView(right);
                solo.sleep(1000);
            }
        }
        //test left arrow
        for(int i = max-1; i > -1; i--) {
            ViewGroup gridChild = (ViewGroup) gridView.getChildAt(i % size);
            String stringId = imageName + Integer.toString(i+1);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            int childSize = gridChild.getChildCount();
            for (int k = 0; k < childSize; k++) {
                if (gridChild.getChildAt(k) instanceof ImageView) {
                    Drawable current = gridChild.getChildAt(k).getBackground();
                    Drawable expected = context.getResources().getDrawable(id);
                    assertTrue(areDrawablesIdentical(current,expected));
                }
            }
            if(i != 0 && i%size == 0) {
                solo.clickOnView(left);
                solo.sleep(1000);
            }
        }
    }
    public void testAccTab() throws Exception{
        int max = PowerUpUtils.ACCESSORIES_IMAGES.length;
        View left = solo.getView(R.id.left_arrow);
        View right = solo.getView(R.id.right_arrow);
        View view = solo.getView(R.id.accessories_button);
        GridView gridView = (GridView) solo.getView(R.id.grid_view);
        final int size = gridView.getChildCount();
        solo.clickOnView(view);
        solo.sleep(1000);
        Context context = view.getContext();
        String imageName = "acc";
        //test right arrow
        for (int i = 0; i < max; i++) {
            ViewGroup gridChild = (ViewGroup) gridView.getChildAt(i % size);
            String stringId = imageName + Integer.toString(i + 1);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            int childSize = gridChild.getChildCount();
            for (int k = 0; k < childSize; k++) {
                if (gridChild.getChildAt(k) instanceof ImageView) {
                    Drawable current = gridChild.getChildAt(k).getBackground();
                    Drawable expected = context.getResources().getDrawable(id);
                    assertTrue(areDrawablesIdentical(current,expected));
                }
            }
            if (i != 0 && (i + 1) % size == 0) {
                solo.clickOnView(right);
                solo.sleep(1000);
            }
        }
        //test left arrow
        for(int i = max-1; i > -1; i--) {
            ViewGroup gridChild = (ViewGroup) gridView.getChildAt(i % size);
            String stringId = imageName + Integer.toString(i+1);
            int id = context.getResources()
                    .getIdentifier(stringId, "drawable", context.getPackageName());
            int childSize = gridChild.getChildCount();
            for (int k = 0; k < childSize; k++) {
                if (gridChild.getChildAt(k) instanceof ImageView) {
                    Drawable current = gridChild.getChildAt(k).getBackground();
                    Drawable expected = context.getResources().getDrawable(id);
                    assertTrue(areDrawablesIdentical(current,expected));
                }
            }
            if(i != 0 && i%size == 0) {
                solo.clickOnView(left);
                solo.sleep(1000);
            }
        }
    }

    public void testMapButton() throws Exception{
        View view = solo.getView(R.id.map_button);
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
