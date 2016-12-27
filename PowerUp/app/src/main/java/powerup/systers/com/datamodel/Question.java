package powerup.systers.com.datamodel;

public class Question {

    private int questionID;
    private String questionDescription;
    private int scenarioID;

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int qId) {
        this.questionID = qId;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String qDes) {
        this.questionDescription = qDes;
    }

    public int getScenarioID() {
        return scenarioID;
    }

    public void setScenarioID(int scenarioID) {
        this.scenarioID = scenarioID;
    }
}
