package something.ru.weathertesttask.model.database.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static something.ru.weathertesttask.model.database.Contract.TABLE_SEASON;
import static something.ru.weathertesttask.model.database.Contract.TABLE_SEASON_COLUMN_NAME;


@Entity(tableName = TABLE_SEASON,
        indices = {@Index(value = {TABLE_SEASON_COLUMN_NAME}, unique = true)})
public class SeasonEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;


    @Ignore
    public SeasonEntity(String name) {
        this.name = name;
    }

    public SeasonEntity(int id, String name) {
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
