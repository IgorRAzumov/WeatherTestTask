package something.ru.weathertesttask.ui.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class UiUtils {

    @SuppressLint("ClickableViewAccessibility")
    public static void setupUI(View view, Activity activity) {
        if (!(view instanceof EditText && !(view instanceof AutoCompleteTextView))) {
            view.setOnTouchListener((v, event) -> {
                hideSoftKeyboard(view, activity);
                return false;
            });
        }

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View innerView = viewGroup.getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    private static void hideSoftKeyboard(View view, Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        View currentFocus = activity.getCurrentFocus();
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
