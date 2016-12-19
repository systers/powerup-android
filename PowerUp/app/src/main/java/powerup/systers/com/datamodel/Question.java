package powerup.systers.com.datamodel;

/**
 * An object for a question asked in the PowerUp game.
 *
 * @see Answer
 */
public class Question {

    private Integer questionID;
    private String questionDescription;
    private Integer scenarioID;

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer qId) {
        this.questionID = qId;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String qDes) {
        this.questionDescription = qDes;
    }

    public Integer getScenarioID() {
        return scenarioID;
    }

    public void setScenarioID(Integer scenarioID) {
        this.scenarioID = scenarioID;
    }
}
