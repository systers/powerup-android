/**
 * @desc displays avatar features in dressing room and updates power/health bars.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;
import powerup.systers.com.util.UiUtils;

public class DressingRoomActivity extends AppCompatActivity implements View.OnClickListener {

    public static Activity dressingRoomInstance;

    private DatabaseHandler mDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dressing_room);

        mDbHandler = new DatabaseHandler(this);
        mDbHandler.open();

        dressingRoomInstance = this;

        setupLayout();
    }

    private void setupLayout() {
        UiUtils.setupEyesFaceClothesHair(this, mDbHandler);
        UiUtils.setupProgressBars(this, mDbHandler);

        TextView karmaPoints = (TextView) findViewById(R.id.karmaPoints);
        karmaPoints.setText(String.valueOf(SessionHistory.totalPoints));

        findViewById(R.id.clothesImageView).setOnClickListener(this);
        findViewById(R.id.hairImageView).setOnClickListener(this);
        findViewById(R.id.accessoriesImageView).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DressingRoomActivity.this, SelectFeaturesActivity.class);

        switch (v.getId()) {
            case R.id.clothesImageView:
                intent.putExtra(getResources().getString(R.string.feature),
                        getResources().getString(R.string.cloth));
                break;
            case R.id.hairImageView:
                intent.putExtra(getResources().getString(R.string.feature),
                        getResources().getString(R.string.hair));
                break;
            case R.id.accessoriesImageView:
                intent.putExtra(getResources().getString(R.string.feature),
                        getResources().getString(R.string.accessory));
                break;
        }

        startActivity(intent);
    }
}
