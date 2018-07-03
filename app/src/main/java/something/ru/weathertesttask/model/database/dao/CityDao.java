package something.ru.weathertesttask.model.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;

import something.ru.weathertesttask.model.database.data.CityWithMonthsTemp;
import something.ru.weathertesttask.model.database.data.CityWithSeasonsTemp;
import something.ru.weathertesttask.model.database.data.entity.CityEntity;


@Dao
public interface CityDao {
    @Transaction
    @Query("select  * from city where id =:id")
    LiveData<List<CityWithSeasonsTemp>> getCityWithSeasons(int id);

    @Transaction
    @Query("select  * from city where id =:id")
    LiveData<List<CityWithMonthsTemp>> getCityWithMonths(int id);

    @Transaction
    @Query("select * from city")
    LiveData<List<CityWithMonthsTemp>> getAllCitiesWithMonths();

    @Transaction
    @Query("select * from city")
    LiveData<List<CityWithSeasonsTemp>> getAllCitiesWithSeasons();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(CityEntity city);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCity(CityEntity city);

    @Delete
    void deleteCity(CityEntity city);
}
