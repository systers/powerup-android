package powerup.systers.com.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Accessories")
public class Accessory {

    @PrimaryKey
    public int accessoryId;

    public String accessoryName;

    public int accessoryPoints;

    public int accessoryPurchased;

    public Accessory(int accessoryId, String accessoryName, int accessoryPoints, int accessoryPurchased) {
        this.accessoryId = accessoryId;
        this.accessoryName = accessoryName;
        this.accessoryPoints = accessoryPoints;
        this.accessoryPurchased = accessoryPurchased;
    }
}
