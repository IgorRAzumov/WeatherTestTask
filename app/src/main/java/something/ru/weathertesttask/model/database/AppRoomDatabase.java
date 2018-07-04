package something.ru.weathertesttask.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import something.ru.weathertesttask.AppExecutors;
import something.ru.weathertesttask.model.database.dao.CityDao;
import something.ru.weathertesttask.model.database.dao.CityTypeDao;
import something.ru.weathertesttask.model.database.dao.MonthDao;
import something.ru.weathertesttask.model.database.dao.SeasonDao;
import something.ru.weathertesttask.model.database.dao.TempDao;
import something.ru.weathertesttask.model.database.data.entity.CityEntity;
import something.ru.weathertesttask.model.database.data.entity.CityTypeEntity;
import something.ru.weathertesttask.model.database.data.entity.MonthEntity;
import something.ru.weathertesttask.model.database.data.entity.SeasonEntity;
import something.ru.weathertesttask.model.database.data.entity.TempEntity;

@Database(entities = {CityEntity.class, CityTypeEntity.class, MonthEntity.class, SeasonEntity.class,
        TempEntity.class}, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "test";
    private static AppRoomDatabase appRoomDatabase;


    public static AppRoomDatabase getInstance(Context context) {
        if (appRoomDatabase == null) {
            synchronized (LOCK) {
                appRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppRoomDatabase.class, AppRoomDatabase.DATABASE_NAME)
                        .addCallback(new Callback() {
                            @Override
                            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                List<CityTypeEntity> types = new ArrayList<>();
                                types.add(new CityTypeEntity("маленький"));
                                types.add(new CityTypeEntity("средний"));
                                types.add(new CityTypeEntity("большой"));

                                List<SeasonEntity> seasons = new ArrayList<>();
                                seasons.add(new SeasonEntity("ЗИМА"));
                                seasons.add(new SeasonEntity("ВЕСНА"));
                                seasons.add(new SeasonEntity("ЛЕТО"));
                                seasons.add(new SeasonEntity("ОСЕНЬ"));

                                List<MonthEntity> months = new ArrayList<>();
                                months.add(new MonthEntity("ЯНВАРЬ"));
                                months.add(new MonthEntity("ФЕВРАЛЬ"));
                                months.add(new MonthEntity("МАРТ"));
                                months.add(new MonthEntity("АПРЕЛЬ"));
                                months.add(new MonthEntity("МАЙ"));
                                months.add(new MonthEntity("ИЮНЬ"));
                                months.add(new MonthEntity("ИЮЛЬ"));
                                months.add(new MonthEntity("АВГУСТ"));
                                months.add(new MonthEntity("СЕНТЯБРЬ"));
                                months.add(new MonthEntity("ОКТЯБРЬ"));
                                months.add(new MonthEntity("НОЯБРЬ"));
                                months.add(new MonthEntity("ДЕКАБРЬ"));

                                List<CityEntity> cities = new ArrayList<>();
                                cities.add(new CityEntity(1, "ЧЕРНЯХОВСК"));
                                cities.add(new CityEntity(2, "КАЛИНИНГРАД"));
                                cities.add(new CityEntity(3, "МОСКВА"));


                                List<TempEntity> temps = new ArrayList<>();
                                temps.add(new TempEntity(-5f, 1, 1, 1));
                                temps.add(new TempEntity(0f, 1, 2, 1));
                                temps.add(new TempEntity(6.2f, 1, 3, 1));
                                temps.add(new TempEntity(15f, 1, 4, 2));
                                temps.add(new TempEntity(20.3f, 1, 5, 2));
                                temps.add(new TempEntity(22f, 1, 6, 2));
                                temps.add(new TempEntity(24f, 1, 7, 3));
                                temps.add(new TempEntity(24.5f, 1, 8, 3));
                                temps.add(new TempEntity(18f, 1, 9, 3));
                                temps.add(new TempEntity(14f, 1, 10, 4));
                                temps.add(new TempEntity(8f, 1, 11, 4));
                                temps.add(new TempEntity(-2.5f, 1, 12, 4));

                                temps.add(new TempEntity(-8f, 2, 1, 1));
                                temps.add(new TempEntity(-2.4f, 2, 2, 1));
                                temps.add(new TempEntity(1f, 2, 3, 1));
                                temps.add(new TempEntity(13f, 2, 4, 2));
                                temps.add(new TempEntity(16.3f, 2, 5, 2));
                                temps.add(new TempEntity(20f, 2, 6, 2));
                                temps.add(new TempEntity(22f, 2, 7, 3));
                                temps.add(new TempEntity(21.5f, 2, 8, 3));
                                temps.add(new TempEntity(18.3f, 2, 9, 3));
                                temps.add(new TempEntity(14f, 2, 10, 4));
                                temps.add(new TempEntity(8f, 2, 11, 4));
                                temps.add(new TempEntity(-2.5f, 2, 12, 4));

                                temps.add(new TempEntity(-15f, 3, 1, 1));
                                temps.add(new TempEntity(-12.3f, 3, 2, 1));
                                temps.add(new TempEntity(-3f, 3, 3, 1));
                                temps.add(new TempEntity(7f, 3, 4, 2));
                                temps.add(new TempEntity(10.8f, 3, 5, 2));
                                temps.add(new TempEntity(20f, 3, 6, 2));
                                temps.add(new TempEntity(27f, 3, 7, 3));
                                temps.add(new TempEntity(21.5f, 3, 8, 3));
                                temps.add(new TempEntity(19f, 3, 9, 3));
                                temps.add(new TempEntity(17.2f, 3, 10, 4));
                                temps.add(new TempEntity(8.3f, 3, 11, 4));
                                temps.add(new TempEntity(-5f, 3, 12, 4));

                                AppExecutors.getInstance().diskIO().execute(() -> {
                                    AppRoomDatabase appRoomDatabase = getInstance(context);

                                    appRoomDatabase
                                            .seasonDao()
                                            .insertAll(seasons
                                                    .toArray(new SeasonEntity[seasons.size()]));
                                    appRoomDatabase
                                            .monthDao()
                                            .insertAll(months
                                                    .toArray(new MonthEntity[months.size()]));
                                    appRoomDatabase
                                            .cityTypeDao()
                                            .insertAll(types
                                                    .toArray(new CityTypeEntity[types.size()]));

                                    appRoomDatabase
                                            .cityDao()
                                            .insertAll(cities
                                                    .toArray(new CityEntity[cities.size()]));
                                    appRoomDatabase
                                            .tempDao()
                                            .insertAll(temps
                                                    .toArray(new TempEntity[temps.size()]));
                                });
                            }
                        })
                        .build();
            }
        }


        return appRoomDatabase;
    }

    public abstract TempDao tempDao();

    public abstract CityDao cityDao();

    public abstract MonthDao monthDao();

    public abstract SeasonDao seasonDao();

    public abstract CityTypeDao cityTypeDao();
}
