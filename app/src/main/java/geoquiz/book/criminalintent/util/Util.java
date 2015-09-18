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

    public static int getMinuteATA(TimePicker timePicker) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            return timePicker.getMinute();
        } else {
            return timePicker.getCurrentMinute();
        }
    }

    public static void setHourATA(TimePicker timePicker, int hour) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            timePicker.setHour(hour);
        } else {
            timePicker.setCurrentHour(hour);
        }
    }


    public static void setMinuteATA(TimePicker timePicker, int minute) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            timePicker.setMinute(minute);
        } else {
            timePicker.setCurrentMinute(minute);
        }
    }

    public static int getHourATA(TimePicker timePicker) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            return timePicker.getHour();
        } else {
            return timePicker.getCurrentHour();
        }
    }


}
