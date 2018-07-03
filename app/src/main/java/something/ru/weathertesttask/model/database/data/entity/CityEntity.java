package something.ru.weathertesttask.model.database.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "city")
public class CityEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "type_id")
    private int typeId;
    private String name;

    @Ignore
    public CityEntity(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    public CityEntity(int id, int typeId, String name) {
        this.id = id;
        this.typeId = typeId;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
