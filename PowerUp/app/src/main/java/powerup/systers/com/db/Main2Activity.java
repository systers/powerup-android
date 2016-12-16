package powerup.systers.com.db;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import powerup.systers.com.R;
import powerup.systers.com.StartActivity;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(powerup.systers.com.R.layout.activity_main2);
        Typeface myTypface = Typeface.createFromAsset(getAssets(),"Windsong.ttf");
        TextView myTextView = (TextView)findViewById(R.id.initiative);
        myTextView.setTypeface(myTypface);
        Typeface myTf = Typeface.createFromAsset(getAssets(),"Amatic-Bold.ttf");
        TextView myTv=(TextView)findViewById(R.id.org);
        myTv.setTypeface(myTf);

        Thread a = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    finish();
                }
                Intent i = new Intent(Main2Activity.this, StartActivity.class);
                    startActivity(i);

            }

        };
        a.start();
    }

}

