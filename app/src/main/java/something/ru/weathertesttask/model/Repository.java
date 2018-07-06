package something.ru.weathertesttask.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import something.ru.weathertesttask.AppExecutors;
import something.ru.weathertesttask.model.database.dao.CityDao;
import something.ru.weathertesttask.model.database.dao.CityTypeDao;
import something.ru.weathertesttask.model.database.dao.MonthDao;
import something.ru.weathertesttask.model.database.dao.SeasonDao;
import something.ru.weathertesttask.model.database.dao.TempDao;
import something.ru.weathertesttask.model.database.data.CityWithMonthsTemp;
import something.ru.weathertesttask.model.database.data.CityWithType;
import something.ru.weathertesttask.model.database.data.TempWithMonth;
import something.ru.weathertesttask.model.database.data.entity.CityEntity;
import something.ru.weathertesttask.model.database.data.entity.CityTypeEntity;
import something.ru.weathertesttask.model.database.data.entity.MonthEntity;
import something.ru.weathertesttask.model.database.data.entity.SeasonEntity;
import something.ru.weathertesttask.model.database.data.entity.TempEntity;


public class Repository {
    private static final Object LOCK = new Object();
    private static Repository instance;
    private final CityDao cityDao;
    private final CityTypeDao cityTypeDao;
    private final MonthDao monthDao;
    private final SeasonDao seasonDao;
    private final TempDao tempDao;
    private final AppExecutors executors;


    private Repository(CityDao cityDao, MonthDao monthDao, SeasonDao seasonDao,
                       CityTypeDao cityTypeDao, TempDao tempDao, AppExecutors executors) {
        this.cityDao = cityDao;
        this.monthDao = monthDao;
        this.seasonDao = seasonDao;
        this.cityTypeDao = cityTypeDao;
        this.tempDao = tempDao;
        this.executors = executors;
    }

    public static Repository getInstance(CityDao cityDao, MonthDao monthDao, SeasonDao seasonDao,
                                         CityTypeDao cityTypeDao, TempDao tempDao, AppExecutors executors) {
        if (instance == null) {
            synchronized (LOCK) {
                instance = new Repository(cityDao, monthDao, seasonDao, cityTypeDao, tempDao, executors);
            }
        }
        return instance;
    }

    public List<CityWithType> getCities(String nameSequence) {
        return cityDao.getCitiesWithType("%" + nameSequence + "%");
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

    public LiveData<List<CityTypeEntity>> getAllCityTypes() {
        return cityTypeDao.getCityTypes();
    }

    public LiveData<CityWithMonthsTemp> getCityWithTempList(int id) {
        return cityDao.getCityWithMonths(id);
    }

    public LiveData<List<TempWithMonth>> getCityTempList(int id) {
        return tempDao.getAllTempByCityId(id);
    }


    public void saveCityWithTemp(String cityName, String cityTypeName, List<TempWithMonth> temps) {
        executors.diskIO().execute(() -> {
            int cityTypeId = cityTypeDao.getCityTypeIdByName(cityTypeName);
            CityEntity city = new CityEntity(cityTypeId, cityName.toUpperCase());

            int cityId = cityDao.getCityIdByName(cityName);
            if (cityId > 0) {
                cityDao.updateCity(city);
                tempDao.updateAll(getTempArray(temps, cityId));
            } else {
                cityDao.insertCity(city);
                cityId = cityDao.getCityIdByName(cityName);
                tempDao.insertAll(getTempArray(temps, cityId));
            }
        });
    }

    @NonNull
    private TempEntity[] getTempArray(List<TempWithMonth> temps, int cityId) {
        TempEntity[] tempEntityArray = new TempEntity[temps.size()];
        for (int i = 0; i < tempEntityArray.length; i++) {
            TempEntity tempEntity = temps.get(i).temperature;
            tempEntity.setCityId(cityId);
            tempEntity.setMonthId(tempEntity.getMonthId());
            tempEntity.setSeasonId(3);
            tempEntityArray[i] = tempEntity;
        }
        return tempEntityArray;
    }

    public void deleteCityByName(String name) {
        executors.diskIO().execute(() -> {
            CityEntity cityEntity = cityDao.getCityByName(name);
            if (cityEntity != null) {
                cityDao.deleteCity(cityEntity);
            }
        });
    }

    public LiveData<Float> getSeasonAvgTemp(String seasonsName, String cityName) {
        return null;
    }

    /*public LiveData<Float> getSeasonAvgTemp(String seasonsName) {
        return Transformations.map(seasonDao.getSeasonIdByName(seasonsName), input -> {
            null
        });
    }*/
}

