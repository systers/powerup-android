package powerup.systers.com.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Hair {

    @PrimaryKey
    public int hairId;

    public String hairName;

    public int hairPoints;

    public int hairPurchased;

    public Hair(int hairId, String hairName, int hairPoints, int hairPurchased) {
        this.hairId = hairId;
        this.hairName = hairName;
        this.hairPoints = hairPoints;
        this.hairPurchased = hairPurchased;
    }
}
