package geoquiz.book.criminalintent.util;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Date;

/**
 * Created by jorge.bautista on 18/09/15.
 */
public class Util {

    public static String formatTime(Date time) {
        return DateFormat.format("HH:mm'hrs'", time).toString();
    }

    @NonNull
    public static String formatDate(Date date) {
        return DateFormat.format("MMM d, yyyy", date).toString();
    }

}
