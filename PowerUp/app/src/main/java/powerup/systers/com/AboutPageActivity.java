package powerup.systers.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutPageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page);
        TextView content = (TextView) findViewById(R.id.aboutPageContent);
        content.setText("" + getString(R.string.about_page_content));
    }
}
