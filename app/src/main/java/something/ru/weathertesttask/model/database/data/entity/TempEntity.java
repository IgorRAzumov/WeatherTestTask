package something.ru.weathertesttask.model.database.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static something.ru.weathertesttask.model.database.Contract.TABLE_CITY_TYPE_COLUMN_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_MONTH_COLUMN_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_SEASON_COLUMN_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_TEMPERATURE;
import static something.ru.weathertesttask.model.database.Contract.TABLE_TEMPERATURE_COLUMN_CITY_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_TEMPERATURE_COLUMN_MONTH_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_TEMPERATURE_COLUMN_SEASON_ID;
import static something.ru.weathertesttask.model.database.Contract.TABLE_TEMPERATURE_COLUMN_VALUE;


@Entity(tableName = TABLE_TEMPERATURE, foreignKeys = {
        @ForeignKey(entity = CityEntity.class,
                parentColumns = TABLE_CITY_TYPE_COLUMN_ID,
                childColumns = TABLE_TEMPERATURE_COLUMN_CITY_ID,
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = MonthEntity.class,
                parentColumns = TABLE_MONTH_COLUMN_ID,
                childColumns = TABLE_TEMPERATURE_COLUMN_MONTH_ID,
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = SeasonEntity.class,
                parentColumns = TABLE_SEASON_COLUMN_ID,
                childColumns = TABLE_TEMPERATURE_COLUMN_SEASON_ID,
                onDelete = ForeignKey.SET_NULL)})
public class TempEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = TABLE_TEMPERATURE_COLUMN_CITY_ID)
    private int cityId;
    @ColumnInfo(name = TABLE_TEMPERATURE_COLUMN_MONTH_ID)
    private int monthId;
    @ColumnInfo(name = TABLE_TEMPERATURE_COLUMN_SEASON_ID)
    private int seasonId;
    @ColumnInfo(name = TABLE_TEMPERATURE_COLUMN_VALUE)
    private float value;

    @Ignore
    public TempEntity() {
    }

    @Ignore
    public TempEntity(float value, int cityId, int monthId, int seasonId) {
        this.value = value;
        this.cityId = cityId;
        this.monthId = monthId;
        this.seasonId = seasonId;
    }

    public TempEntity(int id, float value, int cityId, int monthId, int seasonId) {
        this.id = id;
        this.value = value;
        this.cityId = cityId;
        this.monthId = monthId;
        this.seasonId = seasonId;
    }

    public int getId() {
        return id;
    }

    public float getValue() {
        return value;
    }

    public int getCityId() {
        return cityId;
    }

    public int getMonthId() {
        return monthId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }
}
