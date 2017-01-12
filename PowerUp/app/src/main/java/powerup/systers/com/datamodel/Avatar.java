/**
 * @desc this class holds getter and setter methods for displaying avatar features
 * examples include getEyes() and setClothes()
 */

package powerup.systers.com.datamodel;

public class Avatar {

    private int id;
    private int face;
    private int eyes;
    private int hair;
    private int clothes;

    public int getId() {
        return id;
    }

    public void setId(int iD) {
        id = iD;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getEyes() {
        return eyes;
    }

    public void setEyes(int eyes) {
        this.eyes = eyes;
    }

    public int getHair() {
        return hair;
    }

    public void setHair(int hair) {
        this.hair = hair;
    }

    public int getClothes() {
        return clothes;
    }

    public void setClothes(int clothes) {
        this.clothes = clothes;
    }
}
