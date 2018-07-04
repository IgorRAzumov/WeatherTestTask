package something.ru.weathertesttask.ui.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import something.ru.weathertesttask.model.Repository;
import something.ru.weathertesttask.model.database.data.entity.CityEntity;

public class MainActivityViewModel extends ViewModel {
    private final Repository repository;

    private LiveData<CityEntity> city;
    private LiveData<List<String>> seasons;

    public MainActivityViewModel(Repository repository) {
        this.repository = repository;
        seasons = repository.getAllSeasons();
    }

    public List<CityEntity> getCities(String constraint) {
        return repository.getCities(constraint.toUpperCase().trim());

    }

    public LiveData<CityEntity> getCityById(int id) {
        LiveData<CityEntity> result;
        if (city != null) {
            CityEntity cityEntity = city.getValue();
            if (cityEntity != null && cityEntity.getId() == id) {
                result = city;
            } else {
                result = repository.getCityById(id);
            }
        } else {
            result = repository.getCityById(id);
        }
        return result;
    }

    public LiveData<List<String>> getAllSeasons() {
        return seasons;
    }

    public int getSeasonAvgTemp() {
        return 0;
    }

    public LiveData<CityEntity> getCurrentCity() {
        return city;
    }
}
