package powerup.systers.com.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Clothes {

    @PrimaryKey
    public int clothesId;

    public String clothesName;

    public int clothesPoints;

    public int clothesPurchased;

    public Clothes(int clothesId, String clothesName, int clothesPoints, int clothesPurchased) {
        this.clothesId = clothesId;
        this.clothesName = clothesName;
        this.clothesPoints = clothesPoints;
        this.clothesPurchased = clothesPurchased;
    }
}
