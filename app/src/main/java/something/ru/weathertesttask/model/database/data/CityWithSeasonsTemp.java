package something.ru.weathertesttask.model.database.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import something.ru.weathertesttask.model.database.data.entity.CityEntity;
import something.ru.weathertesttask.model.database.data.entity.CityTypeEntity;

public class CityWithSeasonsTemp {
    @Embedded
    public CityEntity city;
    @Relation(parentColumn = "type_id", entityColumn = "id")
    public List<CityTypeEntity> cityTypeEntity;


}
