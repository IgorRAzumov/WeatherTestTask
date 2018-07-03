package something.ru.weathertesttask.model.database.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "type")
public class CityTypeEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "type_id")
    private int typeId;

    @Ignore
    public CityTypeEntity(int typeId) {
        this.typeId = typeId;
    }

    public CityTypeEntity(int id, int typeId) {
        this.id = id;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
