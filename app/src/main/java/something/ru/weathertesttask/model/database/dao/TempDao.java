package something.ru.weathertesttask.model.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;

import something.ru.weathertesttask.model.database.data.TempWithMonth;
import something.ru.weathertesttask.model.database.data.entity.TempEntity;

import static something.ru.weathertesttask.model.database.Contract.TABLE_TEMPERATURE;
import static something.ru.weathertesttask.model.database.Contract.TABLE_TEMPERATURE_COLUMN_CITY_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_TEMPERATURE_COLUMN_SEASON_ID;

@Dao
public interface TempDao {
    @Transaction
    @Query("select * from " + TABLE_TEMPERATURE
            + " where " +TABLE_TEMPERATURE_COLUMN_CITY_ID + " =:id")
    LiveData<List<TempWithMonth>> getAllTempByCityId(int id);

    @Query("select * from " + TABLE_TEMPERATURE
            + " where " +TABLE_TEMPERATURE_COLUMN_SEASON_ID + " =:seasonId and "
            + TABLE_TEMPERATURE_COLUMN_CITY_ID  +" =:cityId ")
    LiveData<List<TempEntity>> getAllTempByCityId(int cityId, int seasonId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(TempEntity... tempEntities);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAll(TempEntity... tempEntities);
}
