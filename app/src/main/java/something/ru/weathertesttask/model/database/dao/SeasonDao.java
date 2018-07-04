package something.ru.weathertesttask.model.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import something.ru.weathertesttask.model.database.data.entity.SeasonEntity;

@Dao
public interface SeasonDao {
    @Query("select id, name from season")
    LiveData<List<SeasonEntity>> getSeasons();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SeasonEntity... seasons);
}
