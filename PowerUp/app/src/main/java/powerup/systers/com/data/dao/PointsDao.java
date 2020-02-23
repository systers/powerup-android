package powerup.systers.com.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import powerup.systers.com.data.entities.Points;

@Dao
public interface PointsDao {

    @Insert
    void insertPoints(Points points);
}
