package something.ru.weathertesttask.model.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import something.ru.weathertesttask.model.database.data.entity.MonthEntity;

@Dao
public interface MonthDao {
    @Query("select id,name from month")
    LiveData<List<MonthEntity>> getMonths();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MonthEntity... seasons);
}
