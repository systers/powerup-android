package powerup.systers.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
* Put general methods here to prevent code duplication
* */
public abstract class BaseActivity extends AppCompatActivity {

    protected void backToHome() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
