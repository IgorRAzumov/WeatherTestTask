package something.ru.weathertesttask.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import something.ru.weathertesttask.R;
import something.ru.weathertesttask.model.database.data.CityWithMonthsTemp;
import something.ru.weathertesttask.model.database.data.CityWithType;
import something.ru.weathertesttask.model.database.data.TempWithMonth;
import something.ru.weathertesttask.model.database.data.entity.CityEntity;
import something.ru.weathertesttask.ui.adapters.CitiesAdapter;
import something.ru.weathertesttask.ui.adapters.MonthsAdapter;
import something.ru.weathertesttask.ui.utils.UiUtils;
import something.ru.weathertesttask.ui.viewModel.MainViewModel;
import something.ru.weathertesttask.ui.viewModel.SettingsViewModel;
import something.ru.weathertesttask.ui.viewModel.factory.MainViewModelFactory;
import something.ru.weathertesttask.ui.viewModel.factory.SettingsViewModelFactory;
import something.ru.weathertesttask.utils.InjectorUtils;

public class SettingsActivity extends AppCompatActivity {
    private View buttonsBarLayout;
    private View autocompleteLayout;
    private AutoCompleteTextView cityAutoCompleteText;

    private View updateContentLayout;
    private EditText cityNameEntryEditText;
    private Spinner cityTypeSpinner;

    private FloatingActionButton addCityButton;

    private SettingsViewModel settingsViewModel;
    private MainViewModel mainViewModel;


    private MonthsAdapter monthsRecyclerAdapter;
    private CitiesAdapter cityAutoCompleteAdapter;
    private ArrayAdapter<String> cityTypesSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        UiUtils.setupUI(findViewById(R.id.lly_act_settings_root_view), this);
        initViewModel();
        initUi();
    }

    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_buttons_bar_save: {
                List<TempWithMonth> tempWithMonth = monthsRecyclerAdapter.getData();
                String cityName = cityNameEntryEditText.getText().toString();
                String cityTypeName = cityTypesSpinnerAdapter.getItem(
                        cityTypeSpinner.getSelectedItemPosition());

                if (settingsViewModel.isCityDataValid(cityName, cityTypeName, tempWithMonth)) {
                    settingsViewModel.saveCity(tempWithMonth, cityName, cityTypeName);
                    clearContentLayout();
                    showAutocompleteLayout();

                } else {
                    showErrorMessage();
                }
                break;
            }
            case R.id.bt_buttons_bar_cancel: {
                clearContentLayout();
                showAutocompleteLayout();
                break;
            }
            case R.id.bt_buttons_bar_delete: {
                settingsViewModel.deleteCity(cityNameEntryEditText.getText().toString());
                clearContentLayout();
                showAutocompleteLayout();
                break;
            }
            case R.id.fab_act_settings_add_city: {
                showContentLayout();
                startCreateCity();
                break;
            }
        }
    }

    private void showErrorMessage() {

    }

    private void initViewModel() {
        Context appContext = getApplicationContext();
        MainViewModelFactory mainViewModelFactory = InjectorUtils
                .provideMainActivityViewModelFactory(appContext);
        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel.class);

        SettingsViewModelFactory settingsViewModelFactory = InjectorUtils
                .provideSettingsViewModelFactory(appContext);
        settingsViewModel = ViewModelProviders.of(this, settingsViewModelFactory)
                .get(SettingsViewModel.class);
    }

    private void initUi() {
        initCityAutoComplete();
        initContent();
        initButtons();
    }

    private void initContent() {
        updateContentLayout = findViewById(R.id.ucc_act_settings_content);
        cityNameEntryEditText = findViewById(R.id.et_act_settings_city_name);
        initSpinner();
        initMonthsRecycler();
    }

    private void initButtons() {
        buttonsBarLayout = findViewById(R.id.bb_act_settings_buttons);
        findViewById(R.id.bt_buttons_bar_save).setOnClickListener(this::onClick);
        findViewById(R.id.bt_buttons_bar_cancel).setOnClickListener(this::onClick);
        findViewById(R.id.bt_buttons_bar_delete).setOnClickListener(this::onClick);
        addCityButton = findViewById(R.id.fab_act_settings_add_city);
        addCityButton.setOnClickListener(this::onClick);
    }


    private void initCityAutoComplete() {
        autocompleteLayout = findViewById(R.id.actil_auto_complete_city);
        cityAutoCompleteText = findViewById(R.id.actv_act_main_city_entry);
        cityAutoCompleteAdapter = new CitiesAdapter(this, R.layout.auto_complete_item) {
            private final Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    List<CityWithType> results = mainViewModel.getCities(constraint.toString());
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
                        cityAutoCompleteAdapter.clear();
                    }
                }
            };

            @NonNull
            @Override
            public Filter getFilter() {
                return filter;
            }
        };
        cityAutoCompleteText.setAdapter(cityAutoCompleteAdapter);
        cityAutoCompleteText.setOnItemClickListener((parent, view, position, id) -> {
            settingsViewModel
                    .getCityById(cityAutoCompleteAdapter.getData(position).city.getId())
                    .observe(this, this::completeCityLoad);
        });
    }

    private void initSpinner() {
        cityTypeSpinner = findViewById(R.id.sp_act_settings_city_type);
        cityTypesSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public void addAll(@NonNull Collection<? extends String> collection) {
                super.add(getString(R.string.spinner_adapter_no_selected_text));
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
        cityTypesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityTypeSpinner.setAdapter(cityTypesSpinnerAdapter);
        cityTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    return;
                }// changeCityType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        settingsViewModel.getAllCitiesTypes().observe(this, cityTypes -> {
            cityTypesSpinnerAdapter.clear();
            if (cityTypes != null && cityTypes.size() > 0) {
                cityTypesSpinnerAdapter.addAll(cityTypes);
            }
        });
    }


    private void initMonthsRecycler() {
        RecyclerView monthsRecycler = findViewById(R.id.rv_act_settings_months_list);
        monthsRecycler.setLayoutManager(new LinearLayoutManager(this));
        monthsRecycler.setItemAnimator(new DefaultItemAnimator());
        monthsRecycler.setHasFixedSize(true);
        monthsRecyclerAdapter = new MonthsAdapter(R.layout.month_item);
        monthsRecycler.setAdapter(monthsRecyclerAdapter);
    }

    private void completeCityLoad(CityWithMonthsTemp cityWithMonthsTemp) {
        cityAutoCompleteText.clearListSelection();
        if (cityWithMonthsTemp != null) {
            CityEntity city = cityWithMonthsTemp.getCity();
            cityNameEntryEditText.setText(city.getName());
            showContentLayout();

            settingsViewModel
                    .getCityTypeNameById(city.getId())
                    .observe(this, cityTypeName -> {
                        cityTypeSpinner.setSelection(cityTypesSpinnerAdapter.getPosition(cityTypeName));
                    });

            settingsViewModel
                    .getCityTempListById(city.getId())
                    .observe(this, tempsList -> {
                        monthsRecyclerAdapter.setData(tempsList);
                    });
        } else {
            Toast.makeText(this, "dsfsdfsdfsd", Toast.LENGTH_LONG).show();
        }
        cityAutoCompleteText.setText("");
    }

    private void clearContentLayout() {
        cityNameEntryEditText.setText("");
        cityTypesSpinnerAdapter.notifyDataSetChanged();///////////////
        monthsRecyclerAdapter.clear();
    }

    private void showAutocompleteLayout() {
        autocompleteLayout.setVisibility(View.VISIBLE);
        addCityButton.setVisibility(View.VISIBLE);
        buttonsBarLayout.setVisibility(View.GONE);
        updateContentLayout.setVisibility(View.GONE);
    }

    private void showContentLayout() {
        autocompleteLayout.setVisibility(View.GONE);
        addCityButton.setVisibility(View.GONE);
        buttonsBarLayout.setVisibility(View.VISIBLE);
        updateContentLayout.setVisibility(View.VISIBLE);

    }

    private void startCreateCity() {
        settingsViewModel
                .startCreateCity()
                .observe(this, cityWithMonthsTemp -> {
                    monthsRecyclerAdapter.setData(cityWithMonthsTemp);
                });
    }
}

