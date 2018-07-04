package something.ru.weathertesttask.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import something.ru.weathertesttask.R;
import something.ru.weathertesttask.model.database.data.entity.CityEntity;
import something.ru.weathertesttask.ui.viewModel.MainActivityViewModel;
import something.ru.weathertesttask.ui.viewModel.factory.MainViewModelFactory;
import something.ru.weathertesttask.utils.InjectorUtils;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView cityAutoCompleteText;
    private Spinner spinner;
    private TextView cityNameText;
    private TextView cityTypeText;
    private TextView seasonAvgTempText;

    private ArrayAdapter<String> spinnerAdapter;
    private CitiesAdapter cityAutoCompleteAdapter;
    private MainActivityViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        initUi();
    }


    private void initViewModel() {
        MainViewModelFactory factory = InjectorUtils
                .provideMainActivityViewModelFactory(this.getApplicationContext());
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
    }

    private void initUi() {
        initCityAutoComplete();
        initSeasonsSpinner();
        initTextViews();
    }

    private void initCityAutoComplete() {
        cityAutoCompleteText = findViewById(R.id.actv_act_main_city_entry);
        cityAutoCompleteAdapter = new CitiesAdapter(this, R.layout.auto_complete_item,
                getCitiesFilter());
        cityAutoCompleteText.setAdapter(cityAutoCompleteAdapter);
        cityAutoCompleteText.setOnItemClickListener((parent, view, position, id) ->
                viewModel.getCityById(cityAutoCompleteAdapter.getData(position).getId())
                        .observe(this, this::completeCityLoad));
    }

    private Filter getCitiesFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                List<CityEntity> results = viewModel.getCities(constraint.toString());
                if (results == null) {
                    results = new ArrayList<>();
                }
                filterResults.count = results.size();
                filterResults.values = results;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.values != null && results.count != 0) {
                    cityAutoCompleteAdapter.setData((List<CityEntity>) results.values);
                } else {
                    cityAutoCompleteAdapter.notifyDataSetInvalidated();
                }
            }
        };
    }

    private void initSeasonsSpinner() {
        spinner = findViewById(R.id.sp_act_main_city_seasons);
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        spinner.setPrompt("Title");
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeSelectedSeason();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        viewModel.getAllSeasons().observe(this, seasons -> {
            spinnerAdapter.clear();
            spinnerAdapter.notifyDataSetInvalidated();
            if (seasons != null && seasons.size() > 0) {
                spinnerAdapter.addAll(seasons);
            }
        });
    }

    private void initTextViews() {
        cityNameText = findViewById(R.id.tv_act_main_city_city_name);
        cityTypeText = findViewById(R.id.tv_act_main_city_type_text);
        seasonAvgTempText = findViewById(R.id.tv_act_main_avg_temp_text);
    }

    private void changeSelectedSeason() {
        //viewModel.getCurrentCity();
    }

    private void completeCityLoad(CityEntity cityEntity) {
        cityAutoCompleteText.clearComposingText();
        cityNameText.setText(cityEntity.getName());
        cityTypeText.setText(String.valueOf(cityEntity.getTypeId()));
        spinner.setVisibility(View.VISIBLE);
    }

}
