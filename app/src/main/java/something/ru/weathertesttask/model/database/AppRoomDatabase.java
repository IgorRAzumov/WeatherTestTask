package something.ru.weathertesttask.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import something.ru.weathertesttask.model.database.dao.CityDao;
import something.ru.weathertesttask.model.database.model.dbEntity.CityEntity;
import something.ru.weathertesttask.model.database.model.dbEntity.CityTypeEntity;
import something.ru.weathertesttask.model.database.model.dbEntity.MonthEntity;
import something.ru.weathertesttask.model.database.model.dbEntity.SeasonEntity;
import something.ru.weathertesttask.model.database.model.dbEntity.TempEntity;

@Database(entities = {CityEntity.class, CityTypeEntity.class, MonthEntity.class, SeasonEntity.class,
        TempEntity.class}, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "test";
    private static AppRoomDatabase appRoomDatabase;


    private static AppRoomDatabase getInstance(Context context) {
        if (appRoomDatabase == null) {
            synchronized (LOCK) {
                appRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppRoomDatabase.class, AppRoomDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return appRoomDatabase;
    }

    public abstract CityDao cityDao();
}
