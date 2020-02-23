package powerup.systers.com.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import powerup.systers.com.data.entities.Accessory;

@Dao
public interface AccessoryDao {

    @Insert
    void insertAllAccessories(List<Accessory> hairsList);

    @Query("SELECT accessoryPurchased FROM accessories WHERE accessoryId = :id")
    int getPurchasedAccessories(int id);

    @Query("UPDATE accessories SET accessoryPurchased = 1 WHERE accessoryId = :id")
    void setPurchasedAccessories(int id);

    @Query("UPDATE  Accessories SET accessoryPurchased =0")
    void resetAccessoryPurchase();

}
