package something.ru.weathertesttask.model.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import something.ru.weathertesttask.model.database.data.entity.MonthEntity;

import static something.ru.weathertesttask.model.database.Contract.TABLE_MONTH;

@Dao
public interface MonthDao {
    @Query("select * from  " + TABLE_MONTH)
    LiveData<List<MonthEntity>> getMonths();

    @Query("select count(*) from " + TABLE_MONTH )
    LiveData<Integer> getMonthsCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MonthEntity... seasons);

}
