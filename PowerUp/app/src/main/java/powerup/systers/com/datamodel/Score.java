/**
 * @desc this class holds getter and setter methods for user's score in power/health bars
 * examples include setHealing() and getInvisibility()
 */

package powerup.systers.com.datamodel;

class Score {
    private Integer healing;
    private Integer strength;
    private Integer telepathy;
    private Integer invisibility;

    public Integer getHealing() {
        return healing;
    }

    public void setHealing(Integer healing) {
        this.healing = healing;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getTelepathy() {
        return telepathy;
    }

    public void setTelepathy(Integer telepathy) {
        this.telepathy = telepathy;
    }

    public Integer getInvisibilty() {
        return invisibility;
    }

    public void setInvisibilty(Integer invisibilty) {
        this.invisibility = invisibilty;
    }
}
