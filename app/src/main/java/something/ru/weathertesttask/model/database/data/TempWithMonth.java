package something.ru.weathertesttask.model.database.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.ArrayList;
import java.util.List;

import something.ru.weathertesttask.model.database.data.entity.MonthEntity;
import something.ru.weathertesttask.model.database.data.entity.TempEntity;

import static something.ru.weathertesttask.model.database.Contract.TABLE_MONTH_COLUMN_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_TEMPERATURE_COLUMN_MONTH_ID;

public class TempWithMonth {
    @Embedded
    public TempEntity temperature;

    @Relation(parentColumn = TABLE_TEMPERATURE_COLUMN_MONTH_ID, entityColumn = TABLE_MONTH_COLUMN_ID)
    public List<MonthEntity> monthEntity;

    public TempWithMonth() {

    }

    public TempWithMonth(String monthName) {
        monthEntity = new ArrayList<>();
        monthEntity.add(new MonthEntity(monthName));
        temperature = new TempEntity();
    }

    public String getMonthName() {
        return monthEntity.get(0).getName();
    }

    public float getMonthTemp() {
        return temperature.getValue();
    }

    public int getMonthId() {
        return monthEntity.get(0).getId();
    }
}

