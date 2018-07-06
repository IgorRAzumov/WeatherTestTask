package something.ru.weathertesttask.model.database.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import something.ru.weathertesttask.model.database.data.entity.CityEntity;
import something.ru.weathertesttask.model.database.data.entity.TempEntity;

import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_COLUMN_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_TEMPERATURE_COLUMN_ID;


public class CityWithMonthsTemp {
    @Embedded
    public CityEntity city;
    @Relation(parentColumn = TABLE_CITY_COLUMN_ID, entityColumn = TABLE_TEMPERATURE_COLUMN_ID)
    public List<TempEntity> tempEntityList;

    public CityEntity getCity() {
        return city;
    }

}
