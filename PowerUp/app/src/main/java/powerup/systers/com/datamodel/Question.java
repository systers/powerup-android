/**
 * @desc this class holds getter and setter methods for each question and the scenario it belongs to
 * examples include getQuestionDescription() and setScenarioID()
 */

package powerup.systers.com.datamodel;

public class Question {

    private int questionId;
    private String questionDescription;
    private int scenarioId;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int qId) {
        this.questionId = qId;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String qDes) {
        this.questionDescription = qDes;
    }

    public int getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(int scenarioId) {
        this.scenarioId = scenarioId;
    }
}
