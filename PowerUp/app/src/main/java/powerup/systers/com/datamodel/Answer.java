package powerup.systers.com.datamodel;

public class Answer {

    private int answerID;
    private String answerDescription;
    private int questionID;
    private int nextQuestionID;
    private int points;

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int aId) {
        this.answerID = aId;
    }

    public String getAnswerDescription() {
        return answerDescription;
    }

    public void setAnswerDescription(String aDes) {
        this.answerDescription = aDes;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int qId) {
        this.questionID = qId;
    }

    public int getNextQuestionID() {
        return nextQuestionID;
    }

    public void setNextQuestionID(int nextQuestionID) {
        this.nextQuestionID = nextQuestionID;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
