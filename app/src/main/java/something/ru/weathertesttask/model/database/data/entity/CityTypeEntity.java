package something.ru.weathertesttask.model.database.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "type", indices = {@Index(value = {"name"}, unique = true)})
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
