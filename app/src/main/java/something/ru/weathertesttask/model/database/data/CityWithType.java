package something.ru.weathertesttask.model.database.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import something.ru.weathertesttask.model.database.data.entity.CityEntity;
import something.ru.weathertesttask.model.database.data.entity.CityTypeEntity;

public class CityWithType {
    @Embedded
    public CityEntity city;
    @Relation(parentColumn = "type_id", entityColumn = "id")
    public List<CityTypeEntity> cityTypeEntity;

    public String getTypeName() {
        return cityTypeEntity.get(0).getName();
    }
}
