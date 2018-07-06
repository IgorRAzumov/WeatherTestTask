
package something.ru.weathertesttask.utils;

import android.content.Context;

import something.ru.weathertesttask.AppExecutors;
import something.ru.weathertesttask.model.Repository;
import something.ru.weathertesttask.model.database.AppRoomDatabase;
import something.ru.weathertesttask.ui.viewModel.factory.MainViewModelFactory;
import something.ru.weathertesttask.ui.viewModel.factory.SettingsViewModelFactory;


public class InjectorUtils {
    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        Repository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }

    public static SettingsViewModelFactory provideSettingsViewModelFactory(Context context) {
        Repository repository = provideRepository(context.getApplicationContext());
        return new SettingsViewModelFactory(repository);
    }

    private static Repository provideRepository(Context context) {
        AppRoomDatabase database = AppRoomDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return Repository.getInstance(database.cityDao(), database.monthDao(), database.seasonDao(),
                database.cityTypeDao(), database.tempDao(), executors);
    }
}