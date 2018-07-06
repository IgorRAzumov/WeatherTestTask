package something.ru.weathertesttask.model.database.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static something.ru.weathertesttask.model.database.Contract.TABLE_MONTH;
import static something.ru.weathertesttask.model.database.Contract.TABLE_MONTH_COLUMN_NAME;

@Entity(tableName = TABLE_MONTH,
        indices = {@Index(value = {TABLE_MONTH_COLUMN_NAME}, unique = true)})
public class MonthEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    @Ignore
    public MonthEntity(String name) {
        this.name = name;
    }

    public MonthEntity(int id, String name) {
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
