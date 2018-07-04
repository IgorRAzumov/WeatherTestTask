package something.ru.weathertesttask.model.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import something.ru.weathertesttask.model.database.data.entity.TempEntity;

@Dao
public interface TempDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(TempEntity... tempEntities);
}