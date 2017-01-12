/**
 * @desc this class holds getter and setter methods for user's score in power/health bars
 * examples include setHealing() and getInvisibility()
 */

package powerup.systers.com.datamodel;

public class Score {

    private int healing;
    private int strength;
    private int telepathy;
    private int invisibility;

    public int getHealing() {
        return healing;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getTelepathy() {
        return telepathy;
    }

    public void setTelepathy(int telepathy) {
        this.telepathy = telepathy;
    }

    public int getInvisibility() {
        return invisibility;
    }

    public void setInvisibility(int invisibility) {
        this.invisibility = invisibility;
    }
}
