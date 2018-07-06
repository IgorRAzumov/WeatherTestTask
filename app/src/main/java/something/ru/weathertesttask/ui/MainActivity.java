package something.ru.weathertesttask.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import something.ru.weathertesttask.R;
import something.ru.weathertesttask.model.database.data.CityWithType;
import something.ru.weathertesttask.model.database.data.entity.CityEntity;
import something.ru.weathertesttask.ui.adapters.CitiesAdapter;
import something.ru.weathertesttask.ui.utils.UiUtils;
import something.ru.weathertesttask.ui.viewModel.MainViewModel;
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
    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UiUtils.setupUI(findViewById(R.id.cl_act_main_root_view), this);
        initViewModel();
        initUi();
    }


    private void initViewModel() {
        MainViewModelFactory factory = InjectorUtils
                .provideMainActivityViewModelFactory(this.getApplicationContext());
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    private void initUi() {
        initCityAutoComplete();
        initSeasonsSpinner();
        initTextViews();
    }

    private void initCityAutoComplete() {
        cityAutoCompleteText = findViewById(R.id.actv_act_main_city_entry);
        cityAutoCompleteAdapter = new CitiesAdapter(this, R.layout.auto_complete_item) {
            @NonNull
            @Override
            public Filter getFilter() {
                return new Filter() {
                    @Override
                    protected FilterResults performFiltering(CharSequence constraint) {
                        FilterResults filterResults = new FilterResults();
                        List<CityWithType> results = viewModel.getCities(constraint.toString());
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
                            cityAutoCompleteAdapter.setData((List<CityWithType>) results.values);
                        } else {
                            cityAutoCompleteAdapter.notifyDataSetInvalidated();
                        }
                    }
                };
            }
        };
        cityAutoCompleteText.setAdapter(cityAutoCompleteAdapter);
        cityAutoCompleteText.setOnItemClickListener((parent, view, position, id) ->
                viewModel.getCityById(cityAutoCompleteAdapter.getData(position).city.getId())
                        .observe(this, this::completeCityLoad));
    }

    private void initSeasonsSpinner() {
        spinner = findViewById(R.id.sp_act_main_city_seasons);
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public void addAll(@NonNull Collection<? extends String> collection) {
                super.add(getString(R.string.spinner_adapter_no_selected_season));
                super.addAll(collection);
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    changeSelectedSeason(position);
                }
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

    private void changeSelectedSeason(int position) {
        viewModel
                .getSeasonAvgTemp(spinnerAdapter.getItem(position), cityNameText.getText().toString())
                .observe(this, aFloat -> {
            seasonAvgTempText.setText(String.valueOf(aFloat));
        });
    }

    private void completeCityLoad(CityEntity cityEntity) {
        spinner.setVisibility(View.VISIBLE);
        cityAutoCompleteText.setText("");
        cityNameText.setText(cityEntity.getName());
        cityTypeText.setText(String.valueOf(cityEntity.getTypeId()));
        spinner.setVisibility(View.VISIBLE);
    }

}
