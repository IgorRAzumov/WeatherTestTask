package something.ru.weathertesttask.utils;

import android.content.Context;
import android.content.SharedPreferences;

import something.ru.weathertesttask.R;

public class PrefUtils {

    public static boolean isFirstRun(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),
                Context.MODE_PRIVATE);
        String firstRunKey = context.getString(R.string.first_run_key);
        boolean isFirstRun = sharedPreferences.getBoolean(firstRunKey, true);
        if (isFirstRun) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(firstRunKey, false);
            editor.apply();
        }
        return isFirstRun;
    }
}
