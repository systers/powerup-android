package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Medha on 01-11-2015.
 */
public class aboutpage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        ImageButton start = (ImageButton)findViewById(R.id.startButtonMain1);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(aboutpage.this,MapActivity.class);
                startActivity(start);
            }
        });
    }
}
