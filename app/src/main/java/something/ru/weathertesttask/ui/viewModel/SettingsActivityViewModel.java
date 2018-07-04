package something.ru.weathertesttask.ui.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import something.ru.weathertesttask.model.Repository;
import something.ru.weathertesttask.model.database.data.CityWithMonthsTemp;

public class SettingsActivityViewModel extends ViewModel {
    private LiveData<List<String>> months;
    private LiveData<List<String>> seasons;
    private LiveData<CityWithMonthsTemp> cityMonthsTemp;

    private Repository repository;

    public LiveData<CityWithMonthsTemp> getCityMonthsTemp() {
        return cityMonthsTemp;
    }


}
