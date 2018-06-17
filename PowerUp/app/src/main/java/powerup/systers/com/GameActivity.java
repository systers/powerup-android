/**
 * @desc presents the user with a dialogue scenario and updates the scenario
 * with more questions and answers as needed. Also updates power/health bars.
 */

package powerup.systers.com;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import powerup.systers.com.data.DataSource;
import powerup.systers.com.data.IDataSource;
import powerup.systers.com.data.SessionHistory;
import powerup.systers.com.data.entities.Answer;
import powerup.systers.com.data.entities.Scenario;
import powerup.systers.com.minesweeper.MinesweeperGameActivity;
import powerup.systers.com.minesweeper.MinesweeperSessionManager;
import powerup.systers.com.minesweeper.MinesweeperTutorials;
import powerup.systers.com.utils.DbResourceUtils;
import powerup.systers.com.utils.InjectionClass;
import powerup.systers.com.utils.PowerUpUtils;
import powerup.systers.com.sink_to_swim_game.SinkToSwimGame;
import powerup.systers.com.sink_to_swim_game.SinkToSwimSessionManager;
import powerup.systers.com.sink_to_swim_game.SinkToSwimTutorials;
import powerup.systers.com.vocab_match_game.VocabMatchGameActivity;
import powerup.systers.com.vocab_match_game.VocabMatchSessionManager;
import powerup.systers.com.vocab_match_game.VocabMatchTutorials;

@SuppressLint("NewApi")
public class GameActivity extends Activity {

    public Activity gameActivityInstance;
    private DataSource dataSource;
    private List<Answer> answers;
    private Scenario scene;
    private Scenario prevScene;
    private TextView questionTextView;
    private TextView scenarioNameTextView;
    private Button goToMap;
    private ArrayAdapter<String> listAdapter;
    private static boolean isStateChanged = false;
    Context context;

    public GameActivity() {
        gameActivityInstance = this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        new ScenarioOverActivity(this).saveActivityOpenedStatus(false);
        context = GameActivity.this;


        // if any game was left incomplete, open respective gameactivity
        if (new MinesweeperSessionManager(this).isMinesweeperOpened()) {
            startActivity(new Intent(GameActivity.this, MinesweeperGameActivity.class));
            overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
        }
        if(new SinkToSwimSessionManager(this).isSinkToSwimOpened()) {
            startActivity(new Intent(GameActivity.this, SinkToSwimGame.class));
        }
        if(new VocabMatchSessionManager(this).isVocabMatchOpened()) {
            startActivity(new Intent(GameActivity.this, VocabMatchGameActivity.class));
        }
        //Todo Give reason
        if (savedInstanceState != null) {
            isStateChanged = true;
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_activity);
        // datasource injection
        dataSource = InjectionClass.provideDataSource(context);


        // instantiate views
        questionTextView = findViewById(R.id.questionView);
        scenarioNameTextView = findViewById(R.id.scenarioNameEditText);
        goToMap = findViewById(R.id.continueButtonGoesToMap);
        ImageView eyeImageView = findViewById(R.id.eye_view);
        ImageView skinImageView = findViewById(R.id.skin_view);
        ImageView hairImageView = findViewById(R.id.hair_view);
        ImageView clothImageView = findViewById(R.id.dress_view);
        ImageView accessoryImageView = findViewById(R.id.accessory_view);

        // Find the ListView resource.
        ListView mainListView = (ListView) findViewById(R.id.mainListView);
        listAdapter = new ArrayAdapter<>(this, R.layout.simplerow, new ArrayList<String>());
        answers = new ArrayList<>();

        // get scenario from database & set layout background
        dataSource.getScenarioFromId(SessionHistory.currSessionID, new IDataSource.LoadScenarioCallBack() {
            @Override
            public void onScenarioLoaded(Scenario scenario) {
                scene = scenario;
            }
        });
        findViewById(R.id.root).setBackground(getResources().getDrawable(PowerUpUtils.SCENARIO_BACKGROUNDS[scene.getScenarioId()-1]));
        SessionHistory.currScenePoints = 0;

        // sets the movement method for handling arrow key movement
        questionTextView.setMovementMethod(new ScrollingMovementMethod());

        // use DbResourceUtil to extract data from database & apply it on avatar
        DbResourceUtils resourceUtils = new DbResourceUtils(dataSource, context);
        eyeImageView.setImageResource(resourceUtils.getEyeResourceId());
        skinImageView.setImageResource(resourceUtils.getSkinResourceId());
        clothImageView.setImageResource(resourceUtils.getClothResourceId());
        hairImageView.setImageResource(resourceUtils.getHairResourceId());
        accessoryImageView.setImageResource(resourceUtils.getAvatarAccessoriesResourceId());

        // Update Scene
        updateScenario(0);
        updateQA();
        //Scene is Replayed
        if (scene.getReplayed() == 1) {
            goToMap.setAlpha((float) 1.0);
            goToMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(SessionHistory.currScenePoints != 0) {
                        gotToMapDialogue();
                        SessionHistory.totalPoints -= SessionHistory.currScenePoints;
                        goToMap.setClickable(false);
                        dataSource.setReplayedScenario(scene.getScenarioName());
                        goToMap.setAlpha((float) 0.0);
                    } else {
                        Intent intent = new Intent(getApplicationContext(),MapActivity.class);
                        finish();
                        startActivity(intent);
                    }
                }
            });
        }
        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);
        mainListView
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {
                        if (answers.get(position).getNextQuestionID() > 0) {
                            // Next Question
                            SessionHistory.currQID = answers.get(position)
                                    .getNextQuestionID();
                            updatePoints(position);
                            updateQA();
                        } else if (answers.get(position).getNextQuestionID() == -1) {
                            updatePoints(position);
                            dataSource.setCompletedScenario(scene.getScenarioId());
                            updateScenario(-1);
                        } else if (answers.get(position).getNextQuestionID() == -2) {
                            updatePoints(position);
                            dataSource.setCompletedScenario(scene.getScenarioId());
                            updateScenario(-2);
                        } else if (answers.get(position).getNextQuestionID() == -3){
                            updatePoints(position);
                            dataSource.setCompletedScenario(scene.getScenarioId());
                            updateScenario(-3);
                        }
                        else {
                            if (SessionHistory.currSessionID == -1) {
                                // Check to make sure all scenes are completed
                                SessionHistory.currSessionID = 1;
                            }
                            updatePoints(position);
                            dataSource.setCompletedScenario(scene.getScenarioId());
                            updateScenario(0);
                        }
                    }
                });
    }

    /**
     * Add karma points to the session.
     *
     * @param position the current question user is on
     */
    private void updatePoints(int position) {
        // Update the Scene Points
        SessionHistory.currScenePoints += answers.get(position).getAnswerPoints();
        // Update Total Points
        SessionHistory.totalPoints += answers.get(position).getAnswerPoints();
    }

    /**
     * Finish, replay, or go to another scenario as needed. Updates the
     * question and answer if the last scenario has not yet been reached.
     * @param type coding scheme for .csv files, -1 means minesweeper game, 0 means scenario completion
     */
    private void updateScenario(int type) {
        if (ScenarioOverActivity.scenarioActivityDone == 1)
            new ScenarioOverActivity().scenarioOverActivityInstance.finish();
        if (scene != null) {
            dataSource.getScenarioFromId(scene.getScenarioId(), new IDataSource.LoadScenarioCallBack() {
                @Override
                public void onScenarioLoaded(Scenario scenario) {
                    prevScene = scenario;
                }
            });
        }
        dataSource.getScenarioFromId(SessionHistory.currSessionID, new IDataSource.LoadScenarioCallBack() {
            @Override
            public void onScenarioLoaded(Scenario scenario) {
                scene = scenario;
            }
        });
        // Play the scenario first time
        if (scene.getReplayed() == 0) {
            // goToMap Mechanics
            goToMap.setAlpha((float) 1.0);
            goToMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Incase the user move back to map in between a running
                    // Scenario.
                    if(SessionHistory.currScenePoints != 0) {
                        gotToMapDialogue();
                        SessionHistory.totalPoints -= SessionHistory.currScenePoints;
                        goToMap.setClickable(false);
                        dataSource.setReplayedScenario(scene.getScenarioName());
                        goToMap.setAlpha((float) 0.0);
                    } else {
                        Intent intent = new Intent(GameActivity.this, MapActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(intent, 0);
                        overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
                    }
                }
            });
        }
        SessionHistory.currQID = scene.getFirstQuestionID();
        scenarioNameTextView.setText(scene.getScenarioName());
        // If completed check if it is last scene
        if (prevScene != null && prevScene.getCompleted() == 1) {
                SessionHistory.prevSessionID = scene.getScenarioId();
                SessionHistory.currSessionID = scene.getNextScenarioID();
                if (type == 0) {
                    Intent intent = new Intent(GameActivity.this, ScenarioOverActivity.class);
                    intent.putExtra(String.valueOf(R.string.scene), prevScene.getScenarioName());
                    startActivity(intent);
                    overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
                } else if (type == -1) {
                    new MinesweeperSessionManager(this).saveMinesweeperOpenedStatus(true); //marks minesweeper game as opened and incompleted
                    startActivity(new Intent(GameActivity.this, MinesweeperTutorials.class));
                    overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
                } else if (type == -2) {
                    new SinkToSwimSessionManager(this).saveSinkToSwimOpenedStatus(true);
                    startActivity(new Intent(GameActivity.this, SinkToSwimTutorials.class));
                    overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
                } else if (type == -3) {
                    new VocabMatchSessionManager(this).saveVocabMatchOpenedStatus(true);
                    startActivity(new Intent(GameActivity.this, VocabMatchTutorials.class));
                    overridePendingTransition(R.animator.fade_in_custom, R.animator.fade_out_custom);
                }

        }

    }

    /**
     * Replace the current scenario with another question/answer.
     */
    private void updateQA() {
        listAdapter.clear();
        dataSource.getAnswerList(SessionHistory.currQID, new IDataSource.LoadAnswerListCallBack() {
            @Override
            public void onDataLoaded(List<Answer> dataList) {
                answers = dataList;
            }
        });
        for (Answer ans : answers) {
            listAdapter.add(ans.getAnswerDescription());
        }
        final String[] questionDescription = new String[1];
        dataSource.getCurrentQuestion(new IDataSource.LoadStringCallBack() {
            @Override
            public void onDataLoaded(String data) {
                questionDescription[0] = data;
            }
        });
        questionTextView.setText(questionDescription[0]);
    }

    /**
     * Goes back to the map when user presses back button
     */
    @Override
    public void onBackPressed(){
        if(SessionHistory.currScenePoints != 0) {
            // clears the activities that were created after the found instance of the required activity
            gotToMapDialogue();
            } else {
            // The flag FLAG_ACTIVITY_CLEAR_TOP checks if an instance of the activity is present and it
            // clears the activities that were created after the found instance of the required activity
            startActivity(new Intent(GameActivity.this, MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
            }
    }
    
    public void gotToMapDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle(context.getResources().getString(R.string.start_title_message))
                .setMessage(getResources().getString(R.string.game_to_map_message));
        builder.setPositiveButton(getString(R.string.game_confirm_message), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(GameActivity.this, MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                SessionHistory.totalPoints -= SessionHistory.currScenePoints;
                finish();
                dataSource.setReplayedScenario(scene.getScenarioName());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                goToMap.setAlpha(1f);
                goToMap.setClickable(true);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        ColorDrawable drawable = new ColorDrawable(Color.WHITE);
        drawable.setAlpha(200);
        dialog.getWindow().setBackgroundDrawable(drawable);
        dialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        DataSource.clearInstance();
    }
}
