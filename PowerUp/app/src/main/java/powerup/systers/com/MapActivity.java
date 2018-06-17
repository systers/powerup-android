/**
 * @desc sets up the map screen.
 */

package powerup.systers.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import powerup.systers.com.data.DataSource;
import powerup.systers.com.data.IDataSource;
import powerup.systers.com.data.entities.Scenario;
import powerup.systers.com.data.SessionHistory;
import powerup.systers.com.minesweeper.MinesweeperGameActivity;
import powerup.systers.com.minesweeper.MinesweeperSessionManager;
import powerup.systers.com.utils.InjectionClass;
import powerup.systers.com.utils.PowerUpUtils;
import powerup.systers.com.sink_to_swim_game.SinkToSwimGame;
import powerup.systers.com.sink_to_swim_game.SinkToSwimSessionManager;
import powerup.systers.com.vocab_match_game.VocabMatchGameActivity;
import powerup.systers.com.vocab_match_game.VocabMatchSessionManager;

public class MapActivity extends Activity {

    private DataSource dataSource;
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            final ImageView scenarioChooser = (ImageView) v;
            if (v.isEnabled()){
                // checks whether has been already completed & then open GameActivity if setSessionId returns true
                dataSource.setSessionId(getScenarioName(scenarioChooser.getId()), new IDataSource.LoadBooleanCallback() {
                    @Override
                    public void onResultLoaded(boolean value) {
                        if(value) {
                            startActivityForResult(new Intent(MapActivity.this, GameActivity.class), 0);
                            overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
                        }
                        else if (new MinesweeperSessionManager(MapActivity.this).isMinesweeperOpened()) { //if minesweeper game was left incomplete
                            startActivity(new Intent(MapActivity.this, MinesweeperGameActivity.class));
                            overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
                        } else if (new SinkToSwimSessionManager(MapActivity.this).isSinkToSwimOpened()) {
                            startActivity(new Intent(MapActivity.this, SinkToSwimGame.class));
                        } else if (new VocabMatchSessionManager(MapActivity.this).isVocabMatchOpened()) {
                            startActivity(new Intent(MapActivity.this, VocabMatchGameActivity.class));
                        }else {
                            Intent intent = new Intent(MapActivity.this, ScenarioOverActivity.class);
                            intent.putExtra(PowerUpUtils.SOURCE,PowerUpUtils.MAP);
                            intent.putExtra(PowerUpUtils.SCENARIO_NAME,getScenarioName(scenarioChooser.getId()));
                            startActivityForResult(intent, 0);
                            overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
                        }

                    }
                });
                finish();
            }}
    };

    private String getScenarioName(int id) {
        switch (id){
            case R.id.school : return "School";
            case R.id.library : return "Library";
            case R.id.house : return "Home";
            case R.id.hospital : return "Hospital";

            default: return "Home";
        }
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // making windows full screen & hiding title bar of activity screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.gamemap);

        // inj  ect the database
        dataSource = InjectionClass.provideDataSource(this);

        // instantiating the views
        ImageView schoolBuilding = (ImageView) findViewById(R.id.school_building);
        ImageView hospitalBuilding = (ImageView) findViewById(R.id.hospital_building);
        ImageView libraryBuilding = (ImageView) findViewById(R.id.library_building);

        Button homeButton = (Button) findViewById(R.id.home_button);
        homeButton.setHeight(homeButton.getWidth());

        ImageView school = (ImageView) findViewById(R.id.school);
        school.setOnClickListener(onClickListener);

        ImageView house = (ImageView) findViewById(R.id.house);
        house.setOnClickListener(onClickListener);

        ImageView hospital = (ImageView)  findViewById(R.id.hospital);
        hospital.setOnClickListener(onClickListener);

        ImageView library = (ImageView) findViewById(R.id.library);
        library.setOnClickListener(onClickListener);

        //disable onclick for imageview
        school.setEnabled(false);
        hospital.setEnabled(false);
        library.setEnabled(false);

        // open StoreActivity on store click
        Button storeButton = (Button) findViewById(R.id.store);
        storeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, StoreActivity.class));
                overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
            }
        });

        // open StartActivity on home click
        homeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MapActivity.this,StartActivity.class));
                overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
            }
        });

        final int[] completed4 = new int[1];
        final int[] completed5 = new int[1];
        final int[] completed6 = new int[1];
        dataSource.getScenarioFromId(4, new IDataSource.LoadScenarioCallBack() {
            @Override
            public void onScenarioLoaded(Scenario scenario) {
                completed4[0] = scenario.getCompleted();
            }
        });
        dataSource.getScenarioFromId(5, new IDataSource.LoadScenarioCallBack() {
            @Override
            public void onScenarioLoaded(Scenario scenario) {
                completed5[0] = scenario.getCompleted();
            }
        });
        dataSource.getScenarioFromId(6, new IDataSource.LoadScenarioCallBack() {
            @Override
            public void onScenarioLoaded(Scenario scenario) {
                completed6[0] = scenario.getCompleted();
            }
        });

        //changes the Map building's greyscale color and unlocks according to the scenarios completions(check completed column in scenario table0
        if (completed4[0] == 1 || SessionHistory.sceneHomeIsReplayed){
            schoolBuilding.setImageDrawable(getResources().getDrawable(R.drawable.school_colored));
            school.setEnabled(true);
        }
        if (completed5[0] == 1 || SessionHistory.sceneSchoolIsReplayed){
            hospitalBuilding.setImageDrawable(getResources().getDrawable(R.drawable.hospital_colored));
            hospital.setEnabled(true);
        }
        if (completed6[0] == 1 || SessionHistory.sceneHospitalIsReplayed){
            libraryBuilding.setImageDrawable(getResources().getDrawable(R.drawable.library_colored));
            library.setEnabled(true);
        }

    }
    /**
     * Goes back to the start menu when user presses back button
     */
    @Override
    public void onBackPressed(){
        // The flag FLAG_ACTIVITY_CLEAR_TOP checks if an instance of the activity is present and it
        // clears the activities that were created after the found instance of the required activity
        startActivity(new Intent(MapActivity.this, StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        DataSource.clearInstance();
    }
}
