package powerup.systers.com.data.pref;

public interface IPrefHelper {
    boolean checkFirstTime();
    void setFirstTime(boolean value);
    boolean checkPreviouslyCustomized();
    void setPreviouslyCustomized(boolean value);
}
