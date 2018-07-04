package something.ru.weathertesttask.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.List;

import something.ru.weathertesttask.R;
import something.ru.weathertesttask.model.database.data.entity.CityEntity;


public class CitiesAdapter extends ArrayAdapter {
    private List<CityEntity> cities;
    private int itemLayout;
    private Filter filter;

    public CitiesAdapter(@NonNull Context context, @LayoutRes int resource, Filter filter) {
        super(context, resource);
        itemLayout = resource;
        this.filter = filter;
    }

    @Override
    public int getCount() {
        return cities == null ? 0 : cities.size();
    }

    @Override
    public CityEntity getItem(int position) {
        return cities.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(itemLayout, parent, false);
        }
        CityEntity cityEntity = getItem(position);
        TextView cityName = view.findViewById(R.id.tv_auto_complete_item_city_name);
        TextView cityType = view.findViewById(R.id.tv_auto_complete_item_city_type);
        cityName.setText(cityEntity.getName());
        cityType.setText(String.valueOf(cityEntity.getTypeId()));
        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    public void setData(List<CityEntity> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    public CityEntity getData(int position) {
        return cities.get(position);
    }
}
