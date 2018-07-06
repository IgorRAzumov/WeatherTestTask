package something.ru.weathertesttask.model.database.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_COLUMN_NAME;
import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_TYPE;

@Entity(tableName = TABLE_CITY_TYPE,
        indices = {@Index(value = {TABLE_CITY_COLUMN_NAME}, unique = true)})
public class CityTypeEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    @Ignore
    public CityTypeEntity(String name) {
        this.name = name;
    }

    public CityTypeEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
