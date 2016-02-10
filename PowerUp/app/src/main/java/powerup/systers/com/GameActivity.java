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

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import powerup.systers.com.datamodel.Answer;
import powerup.systers.com.datamodel.Question;
import powerup.systers.com.datamodel.Scenario;
import powerup.systers.com.datamodel.SessionHistory;
import powerup.systers.com.db.DatabaseHandler;

@SuppressLint("NewApi")
public class GameActivity extends Activity {

    private DatabaseHandler mDbHandler;
    private List<Answer> answers;
    private Scenario scene;
    private TextView questionTextView;
    private TextView scenarioNameTextView;
    private Button replayButton;
    private Button goToMapButoon;
    private ArrayAdapter<String> listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmDbHandler(new DatabaseHandler(this));
        getmDbHandler().open();
        setContentView(R.layout.pgame);
        // Find the ListView resource.
        ListView mainListView = (ListView) findViewById(R.id.mainListView);
        questionTextView = (TextView) findViewById(R.id.questionView);
        scenarioNameTextView = (TextView) findViewById(R.id.scenarioNameEditText);
        listAdapter = new ArrayAdapter<>(this, R.layout.simplerow,
                new ArrayList<String>());
        answers = new ArrayList<>();
        goToMapButoon = (Button) findViewById(R.id.continueButtonGoesToMap);
        replayButton = (Button) findViewById(R.id.redoButton);

        ImageView eyeImageView = (ImageView) findViewById(R.id.eyeImageView);
        ImageView faceImageView = (ImageView) findViewById(R.id.faceImageView);
        ImageView hairImageView = (ImageView) findViewById(R.id.hairImageView);
        ImageView clothImageView = (ImageView) findViewById(R.id.clothImageView);

        String eyeImageName = "eye";
        eyeImageName = eyeImageName + getmDbHandler().getAvatarEye();
        R.drawable ourRID = new R.drawable();
        java.lang.reflect.Field photoNameField;
        try {
            photoNameField = ourRID.getClass().getField(eyeImageName);
            eyeImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String faceImageName = "face";
        faceImageName = faceImageName + getmDbHandler().getAvatarFace();
        try {
            photoNameField = ourRID.getClass().getField(faceImageName);
            faceImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String clothImageName = "cloth";
        clothImageName = clothImageName + getmDbHandler().getAvatarCloth();
        try {
            photoNameField = ourRID.getClass().getField(clothImageName);
            clothImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String hairImageName = "hair";
        hairImageName = hairImageName + getmDbHandler().getAvatarHair();
        try {
            photoNameField = ourRID.getClass().getField(hairImageName);
            hairImageView.setImageResource(photoNameField.getInt(ourRID));
        } catch (NoSuchFieldException | IllegalAccessException
                | IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Update Scene
        updateScenario();
        if (scene.getReplayed() == 1) {
            goToMapButoon.setAlpha((float) 0.0);
            replayButton.setAlpha((float) 0.0);
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

                        } else {
                            SessionHistory.currSessionID = scene
                                    .getNextScenarioID();
                            if (SessionHistory.currSessionID == -1) {
                                // Check to make sure all scenes are completed
                                SessionHistory.currSessionID = 1;
                            }
                            updatePoints(position);
                            Intent displayPointsIntent = new Intent(GameActivity.this, DisplayPointsActivity.class);
                            displayPointsIntent.putExtra(Keys.currentPointsKey, SessionHistory.currScenePoints);
                            startActivityForResult(displayPointsIntent, getResources().getInteger(R.integer.display_points_request_code));
                            SessionHistory.currScenePoints = 0;
                            getmDbHandler().setCompletedScenario(
                                    scene.getScenarioName());
                        }
                    }
                });
    }

    private void updatePoints(int position) {
        // Update the Scene Points
        SessionHistory.currScenePoints += answers.get(position).getPoints();
        // Update Total Points
        SessionHistory.totalPoints += answers.get(position).getPoints();
    }

    private void updateScenario() {
        scene = getmDbHandler().getScenario();
        // Replay a scenario
        if (scene.getReplayed() == 0) {
            // goToMapButoon Mechanics
            goToMapButoon.setAlpha((float) 1.0);
            goToMapButoon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Incase the user move back to map in between a running
                    // Scenario.
                    SessionHistory.totalPoints -= SessionHistory.currScenePoints;
                    goToMapButoon.setClickable(false);
                    Intent myIntent = new Intent(GameActivity.this, MapActivity.class);
                    startActivityForResult(myIntent, 0);
                    getmDbHandler()
                            .setReplayedScenario(scene.getScenarioName());
                    goToMapButoon.setAlpha((float) 0.0);
                    replayButton.setAlpha((float) 0.0);
                }
            });
            // Replay Mechanics
            replayButton.setAlpha((float) 1.0);
            replayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // In case the user moves back to map in between a running
                    // Scenario.

                    SessionHistory.totalPoints -= SessionHistory.currScenePoints;
                    replayButton.setClickable(false);
                    Intent myIntent = new Intent(GameActivity.this, GameActivity.class);
                    startActivityForResult(myIntent, 0);
                    getmDbHandler()
                            .setReplayedScenario(scene.getScenarioName());
                    goToMapButoon.setAlpha((float) 0.0);
                    replayButton.setAlpha((float) 0.0);
                }
            });
        }
        // If completed check if it is last scene
        if (scene.getCompleted() == 1) {
            if (scene.getNextScenarioID() == -1) {
                Intent myIntent = new Intent(GameActivity.this, GameOverActivity.class);
                finish();
                startActivityForResult(myIntent, 0);
            } else {
                SessionHistory.currSessionID = scene.getNextScenarioID();
                updateScenario();
            }
        }
        SessionHistory.currQID = scene.getFirstQuestionID();
        scenarioNameTextView.setText(scene.getScenarioName());
        updateQA();
    }

    private void updateQA() {

        listAdapter.clear();
        getmDbHandler().getAllAnswer(answers, SessionHistory.currQID);
        for (Answer ans : answers) {
            listAdapter.add(ans.getAnswerDescription());
        }
        Question questions = getmDbHandler().getCurrentQuestion();
        questionTextView.setText(questions.getQuestionDescription());
    }

    public DatabaseHandler getmDbHandler() {
        return mDbHandler;
    }

    public void setmDbHandler(DatabaseHandler mDbHandler) {
        this.mDbHandler = mDbHandler;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == getResources().getInteger(R.integer.display_points_request_code)) {
            if (resultCode == Activity.RESULT_OK) {
                updateScenario();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                startActivity(new Intent(GameActivity.this, MapActivity.class));
                finish();
            }


        }

    }
}