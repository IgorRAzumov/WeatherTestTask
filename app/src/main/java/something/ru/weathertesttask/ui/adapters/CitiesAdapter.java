package something.ru.weathertesttask.ui.adapters;

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
import something.ru.weathertesttask.model.database.data.CityWithType;


public class CitiesAdapter extends ArrayAdapter {
    private List<CityWithType> cities;
    private int itemLayout;
    private Filter filter;

    public CitiesAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        itemLayout = resource;
    }

    @Override
    public int getCount() {
        return cities == null ? 0 : cities.size();
    }

    @Override
    public CityWithType getItem(int position) {
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

        CityWithType cityWithType = cities.get(position);
        TextView cityName = view.findViewById(R.id.tv_auto_complete_item_city_name);
        TextView cityType = view.findViewById(R.id.tv_auto_complete_item_city_type);
        cityName.setText(cityWithType.city.getName());
        cityType.setText(String.valueOf(cityWithType.getTypeName()));
        return view;
    }


    public void setData(List<CityWithType> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    public CityWithType getData(int position) {
        return cities.get(position);
    }
}
