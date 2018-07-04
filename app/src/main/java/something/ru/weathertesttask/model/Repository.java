package something.ru.weathertesttask.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import something.ru.weathertesttask.AppExecutors;
import something.ru.weathertesttask.model.database.dao.CityDao;
import something.ru.weathertesttask.model.database.dao.MonthDao;
import something.ru.weathertesttask.model.database.dao.SeasonDao;
import something.ru.weathertesttask.model.database.data.entity.CityEntity;
import something.ru.weathertesttask.model.database.data.entity.MonthEntity;
import something.ru.weathertesttask.model.database.data.entity.SeasonEntity;


public class Repository {
    private static final Object LOCK = new Object();
    private static Repository instance;
    private final CityDao cityDao;
    private final MonthDao monthDao;
    private final SeasonDao seasonDao;
    private final AppExecutors executors;


    private Repository(CityDao cityDao, MonthDao monthDao, SeasonDao seasonDao, AppExecutors executors) {
        this.cityDao = cityDao;
        this.executors = executors;
        this.monthDao = monthDao;
        this.seasonDao = seasonDao;
    }

    public static Repository getInstance(CityDao cityDao, MonthDao monthDao, SeasonDao seasonDao,
                                         AppExecutors executors) {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new Repository(cityDao, monthDao, seasonDao, executors);
            }
        }
        return instance;
    }

    /*  public LiveData<List<CityWithMonthsTemp>> getCitiesWithMonth() {
          return cityDao.getAllCitiesWithMonths();
      }

      public LiveData<List<CityWithSeasonsTemp>> getCitiesWithSeason() {
          return cityDao.getAllCitiesWithSeasons();
      }
  */
    public List<CityEntity> getCities(String nameSequence) {
        return cityDao.getCities("%"+nameSequence+"%");
    }

    public LiveData<CityEntity> getCityById(int id) {
        return cityDao.getCityById(id);
    }

    public LiveData<List<String>> getAllSeasons() {
        return Transformations.map(seasonDao.getSeasons(), entityList -> {
            List<String> results = new ArrayList<>();
            for (SeasonEntity season : entityList) {
                results.add(season.getName());
            }
            return results;
        });

    }

    public LiveData<List<String>> getAllMonths() {
        return Transformations.map(monthDao.getMonths(), entityList -> {
            List<String> results = new ArrayList<>();
            for (MonthEntity month : entityList) {
                results.add(month.getName());
            }
            return results;
        });

    }
}


