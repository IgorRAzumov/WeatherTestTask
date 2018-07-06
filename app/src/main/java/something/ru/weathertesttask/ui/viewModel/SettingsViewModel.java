package something.ru.weathertesttask.ui.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import something.ru.weathertesttask.model.Repository;
import something.ru.weathertesttask.model.database.data.CityWithMonthsTemp;
import something.ru.weathertesttask.model.database.data.TempWithMonth;
import something.ru.weathertesttask.model.database.data.entity.CityTypeEntity;

public class SettingsViewModel extends ViewModel {
    private final LiveData<List<CityTypeEntity>> citiesTypes;
    private LiveData<CityWithMonthsTemp> currentCity;
    private CityWithMonthsTemp temporaryCity;
    private LiveData<List<String>> months;

    private final Repository repository;

    public SettingsViewModel(Repository repository) {
        this.repository = repository;
        citiesTypes = repository.getAllCityTypes();
        months = repository.getAllMonths();
    }

    public LiveData<List<String>> getAllCitiesTypes() {
        return Transformations.map(citiesTypes, citiesTypes -> {
            List<String> results = new ArrayList<>();
            if (citiesTypes != null) {
                for (CityTypeEntity type : citiesTypes) {
                    results.add(type.getName());
                }
            }
            return results;
        });
    }

    public LiveData<CityWithMonthsTemp> getCityById(int id) {
        if (currentCity == null) {
            currentCity = repository.getCityWithTempList(id);
        }
        return currentCity;
    }

    public LiveData<String> getCityTypeNameById(int id) {
        return Transformations.map(citiesTypes, citiesTypes -> {
            String result = "";
            if (citiesTypes != null) {
                for (CityTypeEntity type : citiesTypes) {
                    if (type.getId() == id) {
                        return type.getName();
                    }
                }
            }
            return result;
        });
    }

    public LiveData<List<TempWithMonth>> getCityTempListById(int id) {
        return repository.getCityTempList(id);
    }

    public LiveData<List<TempWithMonth>> startCreateCity() {
        temporaryCity = new CityWithMonthsTemp();

        return Transformations.map(repository.getAllMonths(),
                monthsList -> {
                    List<TempWithMonth> tempEntities = new ArrayList<>();
                    for (int i = 0; i < monthsList.size(); i++) {
                        tempEntities.add(new TempWithMonth(monthsList.get(i)));
                    }
                    return tempEntities;
                });

    }

    public void saveCity(List<TempWithMonth> temps, String cityName, String cityTypeName) {
        repository.saveCityWithTemp(cityName, cityTypeName, temps);

    }

    public boolean isCityDataValid(String cityName, String cityTypeName,
                                   List<TempWithMonth> tempWithMonth) {
        //по идее должна быть проверка введенных данных, но ввиду отсутствия времени ее опустил
        return !cityName.isEmpty();
    }

    public void deleteCity(String cityName) {
        repository.deleteCityByName(cityName);
    }
}
