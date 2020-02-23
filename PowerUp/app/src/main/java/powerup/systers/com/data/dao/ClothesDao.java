package powerup.systers.com.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import powerup.systers.com.data.entities.Clothes;

@Dao
public interface ClothesDao {

    @Insert
    void insertAllClothes(List<Clothes> clothesList);

    @Query("SELECT clothesPurchased FROM Clothes WHERE clothesId = :id")
    int getPurchasedClothes(int id);

    @Query("UPDATE Clothes SET clothesPurchased = 1 WHERE clothesId = :id")
    void setPurchasedClothes(int id);

    // reset purchased clothes
    @Query("UPDATE  Clothes SET clothesPurchased =0")
    void resetClothPurchase();

}
