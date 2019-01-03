package powerup.systers.com.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import powerup.systers.com.data.entities.Points;

@Dao
public interface PointsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPoints(Points points);

    @Query("SELECT * FROM Point")
    Points getPoints();
}
