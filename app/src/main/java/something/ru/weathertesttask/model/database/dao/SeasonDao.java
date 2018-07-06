package something.ru.weathertesttask.model.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import something.ru.weathertesttask.model.database.data.entity.SeasonEntity;

import static something.ru.weathertesttask.model.database.Contract.TABLE_SEASON;
import static something.ru.weathertesttask.model.database.Contract.TABLE_SEASON_COLUMN_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_SEASON_COLUMN_NAME;

@Dao
public interface SeasonDao {
    @Query("select * from  " + TABLE_SEASON)
    LiveData<List<SeasonEntity>> getSeasons();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SeasonEntity... seasons);

    @Query("select "+ TABLE_SEASON_COLUMN_ID + " from " + TABLE_SEASON
            + " where " + TABLE_SEASON_COLUMN_NAME + " like :seasonsName")
    LiveData<Integer> getSeasonIdByName(String seasonsName);
}
