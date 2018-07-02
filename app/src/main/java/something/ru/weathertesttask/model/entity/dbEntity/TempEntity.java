package something.ru.weathertesttask.model.entity.dbEntity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "temperature")
public class TempEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "city_id")
    private int cityId;
    @ColumnInfo(name = "month_id")
    private int monthId;
    @ColumnInfo(name = "season_id")
    private int seasonId;
    @ColumnInfo(name = "value")
    private float value;

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