package something.ru.weathertesttask.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import something.ru.weathertesttask.model.Repository;
import something.ru.weathertesttask.model.database.data.CityWithMonthsTemp;
import something.ru.weathertesttask.model.database.data.CityWithSeasonsTemp;

public class MainActivityViewModel extends ViewModel {
    private final LiveData<List<CityWithMonthsTemp>> cityMonthTempList;
    private final LiveData<List<CityWithSeasonsTemp>> citySeasonTempList;

    public MainActivityViewModel(Repository repository) {
        cityMonthTempList = repository.getCitiesWithMonth();
        citySeasonTempList = repository.getCitiesWithSeason();
    }

    public LiveData<List<CityWithMonthsTemp>> getCityMonthTempList() {
        return cityMonthTempList;
    }

    public LiveData<List<CityWithSeasonsTemp>> getCitySeasonTempList() {
        return citySeasonTempList;
    }
}
