package something.ru.weathertesttask.model.database;

public class Contract {
    public final static String TABLE_CITY = "city";
    public final static String TABLE_CITY_COLUMN_ID = "id";
    public final static String TABLE_CITY_COLUMN_TYPE_ID = "type_id";
    public final static String TABLE_CITY_COLUMN_NAME = "name";

    public final static String TABLE_CITY_TYPE = "type";
    public final static String TABLE_CITY_TYPE_COLUMN_ID = "id";
    public final static String TABLE_CITY_TYPE_COLUMN_NAME = "name";

    public final static String TABLE_MONTH = "month";
    public final static String TABLE_MONTH_COLUMN_ID = "id";
    public final static String TABLE_MONTH_COLUMN_NAME = "name";

    public final static String TABLE_SEASON = "season";
    public final static String TABLE_SEASON_COLUMN_ID = "id";
    public final static String TABLE_SEASON_COLUMN_NAME = "name";

    public final static String TABLE_TEMPERATURE = "temperature";
    public final static String TABLE_TEMPERATURE_COLUMN_ID = "id";
    public final static String TABLE_TEMPERATURE_COLUMN_MONTH_ID = "month_id";
    public final static String TABLE_TEMPERATURE_COLUMN_SEASON_ID = "season_id";
    public final static String TABLE_TEMPERATURE_COLUMN_CITY_ID = "city_id";
    public final static String TABLE_TEMPERATURE_COLUMN_VALUE = "value";
}
