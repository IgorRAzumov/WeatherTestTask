package something.ru.weathertesttask.model.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import something.ru.weathertesttask.model.database.data.entity.CityTypeEntity;

import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_TYPE;
import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_TYPE_COLUMN_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_TYPE_COLUMN_NAME;

@Dao
public interface CityTypeDao {
    @Query("select * from "+TABLE_CITY_TYPE)
    LiveData<List<CityTypeEntity>> getCityTypes();

    @Query(("select " + TABLE_CITY_TYPE_COLUMN_ID + " from " + TABLE_CITY_TYPE + " where "
            + TABLE_CITY_TYPE_COLUMN_NAME + " like :cityTypeName"))
    int getCityTypeIdByName(String cityTypeName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(CityTypeEntity... entities);
}
