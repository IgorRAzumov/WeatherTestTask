package something.ru.weathertesttask.ui.viewModel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import something.ru.weathertesttask.model.Repository;
import something.ru.weathertesttask.ui.viewModel.SettingsViewModel;

public class SettingsViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final Repository repository;

    public SettingsViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SettingsViewModel(repository);
    }
}
