/**
 * @desc presents the user with a dialogue scenario and updates the scenario
 * with more questions and answers as needed. Also updates power/health bars.
 */

package powerup.systers.com;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import java.util.ArrayList;
import java.util.List;
import powerup.systers.com.datamodel.Answer;
import powerup.systers.com.datamodel.Question;
import powerup.systers.com.datamodel.Scenario;
import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;
import powerup.systers.com.util.UiUtils;

@SuppressLint("NewApi")
public class GameActivity extends Activity {

    public static Activity gameActivityInstance;

    private DatabaseHandler mDbHandler;
    private List<Answer> mAnswers;
    private Scenario mScene;
    private Scenario mPrevScene;
    private TextView mQuestionTextView;
    private TextView mScenarioNameTextView;
    private Button mReplay;
    private Button mGoToMap;
    private ArrayAdapter<String> mListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        mDbHandler = new DatabaseHandler(this);
        mDbHandler.open();

        gameActivityInstance = this;

        setupLayout();
    }

    private void setupLayout() {
        mQuestionTextView = (TextView) findViewById(R.id.questionView);
        mScenarioNameTextView = (TextView) findViewById(R.id.scenarioNameEditText);
        mAnswers = new ArrayList<>();
        mGoToMap = (Button) findViewById(R.id.continueButtonGoesToMap);
        mReplay = (Button) findViewById(R.id.redoButton);

        UiUtils.setupEyesFaceClothesHair(this, mDbHandler);

        mListAdapter = new ArrayAdapter<>(this, R.layout.simplerow, new ArrayList<String>());

        // Update scene
        updateScenario();
        if (mScene.getReplayed() == 1) {
            mGoToMap.setAlpha((float) 0.0);
            mReplay.setAlpha((float) 0.0);
        }

        ListView mainListView = (ListView) findViewById(R.id.mainListView);
        setupListView(mainListView);

        UiUtils.setupProgressBars(this, mDbHandler);
    }

    private void setupListView(ListView listView) {
        listView.setAdapter(mListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                        if (mAnswers.get(position).getNextQuestionId() > 0) {
                            // Next question
                            SessionHistory.currQId = mAnswers.get(position)
                                    .getNextQuestionId();
                            updatePoints(position);
                            updateQA();
                        } else {
                            SessionHistory.currSessionId = mScene
                                    .getNextScenarioId();
                            if (SessionHistory.currSessionId == -1) {
                                // Check to make sure all scenes are completed
                                SessionHistory.currSessionId = 1;
                            }
                            updatePoints(position);
                            mDbHandler.setCompletedScenario(mScene.getId());
                            SessionHistory.currScenePoints = 0;
                            updateScenario();
                        }
                    }
                });
    }

    /**
     * Add karma points to the session.
     * @param position the current question user is on
     */
    private void updatePoints(int position) {
        // Update the scene points
        SessionHistory.currScenePoints += mAnswers.get(position).getPoints();

        // Update total points
        SessionHistory.totalPoints += mAnswers.get(position).getPoints();
    }

    /**
     * Finish, replay, or go to another scenario as needed. Updates the
     * question and answer if the last scenario has not yet been reached.
     */
    private void updateScenario() {
        if (ScenarioOverActivity.scenarioActivityDone == 1) {
            ScenarioOverActivity.scenarioOverActivityInstance.finish();
        }

        if (mScene != null) {
            mPrevScene = mDbHandler.getScenarioFromID(mScene.getId());
        }

        mScene = mDbHandler.getScenario();

        // Replay a scenario
        if (mScene.getReplayed() == 0) {
            // mGoToMap Mechanics
            mGoToMap.setAlpha((float) 1.0);
            mGoToMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // In case the user move back to map in between a running scenario.
                    SessionHistory.totalPoints -= SessionHistory.currScenePoints;
                    
                    mGoToMap.setClickable(false);

                    Intent myIntent = new Intent(GameActivity.this, MapActivity.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivityForResult(myIntent, 0);

                    mDbHandler.setReplayedScenario(mScene.getScenarioName());
                    mGoToMap.setAlpha((float) 0.0);
                    mReplay.setAlpha((float) 0.0);
                }
            });

            // Replay Mechanics
            mReplay.setAlpha((float) 1.0);
            mReplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // In case the user moves back to map in between a running scenario.
                    SessionHistory.totalPoints -= SessionHistory.currScenePoints;
                    
                    mReplay.setClickable(false);

                    Intent myIntent = new Intent(GameActivity.this, GameActivity.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivityForResult(myIntent, 0);

                    mDbHandler.setReplayedScenario(mScene.getScenarioName());
                    mGoToMap.setAlpha((float) 0.0);
                    mReplay.setAlpha((float) 0.0);
                }
            });
        }

        // If completed check if it is last mScene
        if (mPrevScene != null && mPrevScene.getCompleted() == 1) {
            if (mScene.getNextScenarioId() == -1) {
                Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
                finish();
                startActivityForResult(intent, 0);
            } else {
                SessionHistory.currSessionId = mScene.getNextScenarioId();
                Intent intent = new Intent(GameActivity.this, ScenarioOverActivity.class);
                intent.putExtra(String.valueOf(R.string.scene), mPrevScene.getScenarioName());
                startActivity(intent);
            }
        }

        SessionHistory.currQId = mScene.getFirstQuestionId();
        mScenarioNameTextView.setText(mScene.getScenarioName());
        updateQA();
    }

    /**
     * Replace the current scenario with another question/answer.
     */
    private void updateQA() {
        mListAdapter.clear();
        mDbHandler.getAllAnswer(mAnswers, SessionHistory.currQId);
        for (Answer ans : mAnswers) {
            mListAdapter.add(ans.getAnswerDescription());
        }
        Question questions = mDbHandler.getCurrentQuestion();
        mQuestionTextView.setText(questions.getQuestionDescription());
    }
}
