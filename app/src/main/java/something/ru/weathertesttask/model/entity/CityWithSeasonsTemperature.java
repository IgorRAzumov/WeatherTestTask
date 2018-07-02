package something.ru.weathertesttask.model.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import something.ru.weathertesttask.model.entity.dbEntity.CityEntity;
import something.ru.weathertesttask.model.entity.dbEntity.CityTypeEntity;
import something.ru.weathertesttask.model.entity.dbEntity.SeasonEntity;
import something.ru.weathertesttask.model.entity.dbEntity.TempEntity;

public class CityWithSeasonsTemperature {
    @Embedded
    public CityEntity city;
    @Relation(parentColumn = "type_id", entityColumn = "id")
    public List<CityTypeEntity> cityTypeEntity;
    @Relation(parentColumn = "id", entityColumn = "city_id")
    public List<TempEntity> seasonList;


}
