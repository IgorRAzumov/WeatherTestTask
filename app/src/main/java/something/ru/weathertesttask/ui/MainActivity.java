package something.ru.weathertesttask.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import something.ru.weathertesttask.R;
import something.ru.weathertesttask.model.database.data.CityWithMonthsTemp;
import something.ru.weathertesttask.utils.InjectorUtils;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModelFactory factory = InjectorUtils
                .provideMainActivityViewModelFactory(this.getApplicationContext());
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);

        viewModel.getCityMonthTempList().observe(this, cities -> {
            if (cities != null) {
                completeLoad(cities);
            } else {
                errorLoadCities();
            }
        });
    }

    private void completeLoad(List<CityWithMonthsTemp> cities) {

    }

    private void errorLoadCities() {

    }
}
