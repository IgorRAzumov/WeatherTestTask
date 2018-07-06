package something.ru.weathertesttask.model.database.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY;
import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_COLUMN_NAME;
import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_COLUMN_TYPE_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_TYPE_COLUMN_ID;

@Entity(tableName = TABLE_CITY,
        foreignKeys = {
                @ForeignKey(entity = CityTypeEntity.class, parentColumns = TABLE_CITY_TYPE_COLUMN_ID,
                        childColumns = TABLE_CITY_COLUMN_TYPE_ID,
                        onDelete = ForeignKey.SET_NULL)},
        indices = {
                @Index(value = {TABLE_CITY_COLUMN_NAME}, unique = true),
                @Index(value = {TABLE_CITY_COLUMN_TYPE_ID})})

public class CityEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = TABLE_CITY_COLUMN_TYPE_ID)
    private int typeId;
    private String name;

    @Ignore
    public CityEntity() {
        name = "";
    }

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
