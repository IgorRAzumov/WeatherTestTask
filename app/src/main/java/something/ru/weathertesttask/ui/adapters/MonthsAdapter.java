package something.ru.weathertesttask.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import something.ru.weathertesttask.R;
import something.ru.weathertesttask.model.database.data.TempWithMonth;

public class MonthsAdapter extends RecyclerView.Adapter<MonthsAdapter.MonthViewHolder> {
    private int itemLayout;
    private List<TempWithMonth> monthsList;

    public MonthsAdapter(int layoutId) {
        itemLayout = layoutId;
        monthsList = new ArrayList<>();

    }

    @NonNull
    @Override
    public MonthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MonthViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(itemLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MonthViewHolder holder, int position) {
        TempWithMonth tempWithMonth = monthsList.get(position);
        holder.monthNameText.setText(tempWithMonth.getMonthName());
        holder.monthTempEditText.setText(String.valueOf(tempWithMonth.getMonthTemp()));
    }

    @Override
    public int getItemCount() {
        return monthsList == null ? 0 : monthsList.size();
    }

    public void setData(List<TempWithMonth> data) {
        monthsList.clear();
        monthsList.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        monthsList.clear();
        notifyDataSetChanged();
    }

    public List<TempWithMonth> getData() {
        return monthsList;
    }

    class MonthViewHolder extends RecyclerView.ViewHolder {
        private final TextView monthNameText;
        private final EditText monthTempEditText;

        MonthViewHolder(View itemView) {
            super(itemView);
            monthNameText = itemView.findViewById(R.id.tv_month_item_month_name);
            monthTempEditText = itemView.findViewById(R.id.et_month_item_month_temp);
            monthTempEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
}
