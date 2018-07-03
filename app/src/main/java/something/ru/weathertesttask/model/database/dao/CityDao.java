package something.ru.weathertesttask.model.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;

import something.ru.weathertesttask.model.database.model.CityWithMonthsTemperature;
import something.ru.weathertesttask.model.database.model.CityWithSeasonsTemperature;
import something.ru.weathertesttask.model.database.model.dbEntity.CityEntity;
@Dao
public interface CityDao {
    @Transaction
    @Query("select  * from city where id =:id")
    List<CityWithSeasonsTemperature> getCityWithSeasons(int id);

    @Transaction
    @Query("select  * from city where id =:id")
    List<CityWithMonthsTemperature> getCityWithMonths(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(CityEntity city);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCity(CityEntity city);

    @Delete
    void deleteCity(CityEntity city);
}
