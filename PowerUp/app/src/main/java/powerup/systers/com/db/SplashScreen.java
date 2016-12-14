package powerup.systers.com.db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import powerup.systers.com.StartActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(powerup.systers.com.R.layout.activity_splash_screen);
            Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    Intent intent = new Intent(getApplicationContext(),StartActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        };
                myThread.start();
    }
}
