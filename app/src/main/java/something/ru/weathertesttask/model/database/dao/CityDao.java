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
import something.ru.weathertesttask.model.database.data.CityWithType;
import something.ru.weathertesttask.model.database.data.entity.CityEntity;

import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY;
import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_COLUMN_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_COLUMN_NAME;


@Dao
public interface CityDao {
    @Transaction
    @Query("select  * from " + TABLE_CITY + " where " + TABLE_CITY_COLUMN_ID + "=:id")
    LiveData<CityWithMonthsTemp> getCityWithMonths(int id);

    @Transaction
    @Query("select * from " + TABLE_CITY + " where " + TABLE_CITY_COLUMN_NAME + " like :nameSequence")
    List<CityWithType> getCitiesWithType(String nameSequence);

    @Query("select  * from " + TABLE_CITY + " where " + TABLE_CITY_COLUMN_ID + "=:id")
    LiveData<CityEntity> getCityById(int id);

    @Query(("select " + TABLE_CITY_COLUMN_ID + " from " + TABLE_CITY + " where "
            + TABLE_CITY_COLUMN_NAME + " like :cityName"))
    int getCityIdByName(String cityName);

    @Query("select * from " + TABLE_CITY + " where " + TABLE_CITY_COLUMN_NAME + " like :name")
    CityEntity getCityByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(CityEntity... cityEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(CityEntity city);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCity(CityEntity city);

    @Delete
    void deleteCity(CityEntity city);
}