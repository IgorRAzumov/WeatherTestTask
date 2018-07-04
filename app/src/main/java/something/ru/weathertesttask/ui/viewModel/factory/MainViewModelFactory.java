package something.ru.weathertesttask.ui.viewModel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import something.ru.weathertesttask.model.Repository;
import something.ru.weathertesttask.ui.viewModel.MainActivityViewModel;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private final Repository repository;

    public MainViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(repository);
    }
}
