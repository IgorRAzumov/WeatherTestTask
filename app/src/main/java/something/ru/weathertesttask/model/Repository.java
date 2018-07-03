package something.ru.weathertesttask.model;

import android.arch.lifecycle.LiveData;

import java.util.List;

import something.ru.weathertesttask.AppExecutors;
import something.ru.weathertesttask.model.database.dao.CityDao;
import something.ru.weathertesttask.model.database.data.CityWithMonthsTemp;
import something.ru.weathertesttask.model.database.data.CityWithSeasonsTemp;


public class Repository {
    private static final Object LOCK = new Object();
    private static Repository instance;
    private final CityDao cityDao;
    private final AppExecutors executors;


    private Repository(CityDao cityDao, AppExecutors executors) {
        this.cityDao = cityDao;
        this.executors = executors;
    }

    public static Repository getInstance(CityDao cityDao, AppExecutors executors) {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new Repository(cityDao, executors);
            }
        }
        return instance;
    }

    public LiveData<List<CityWithMonthsTemp>> getCitiesWithMonth() {
        return cityDao.getAllCitiesWithMonths();
    }

    public LiveData<List<CityWithSeasonsTemp>> getCitiesWithSeason() {
        return cityDao.getAllCitiesWithSeasons();
    }
}
