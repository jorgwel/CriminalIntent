package geoquiz.book.criminalintent.util;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
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

    //Code found on
    //http://stackoverflow.com/questions/9279111/determine-if-the-device-is-a-smartphone-or-tablet
    public static boolean isTablet(FragmentActivity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
        if (diagonalInches>=6.5){
            return true;
        }else{
            return false;
        }
    }
}
