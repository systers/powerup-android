/**
 * @desc this class holds getter and setter methods for each question/answer pair
 * each answer has a specific point value
 * examples include setAnswerID() and getPoints()
 */

package powerup.systers.com.datamodel;

public class Answer {

    private int answerId;
    private String answerDescription;
    private int questionId;
    private int nextQuestionId;
    private int points;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int aId) {
        this.answerId = aId;
    }

    public String getAnswerDescription() {
        return answerDescription;
    }

    public void setAnswerDescription(String aDes) {
        this.answerDescription = aDes;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int qId) {
        this.questionId = qId;
    }

    public int getNextQuestionId() {
        return nextQuestionId;
    }

    public void setNextQuestionId(int nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
