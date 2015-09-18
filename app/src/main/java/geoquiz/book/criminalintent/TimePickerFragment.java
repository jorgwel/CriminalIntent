package geoquiz.book.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jorge.bautista on 17/09/15.
 */
public class TimePickerFragment extends DialogFragment {

    private static final String ARG_TIME = "time";
    public static final String EXTRA_TIME = "com.libro.regresarafragento.target.time";

    private TimePicker mTimePicker;

    public static TimePickerFragment newInstance(Date date) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View timePickerView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);
        mTimePicker = (TimePicker) timePickerView.findViewById(R.id.dialog_time_time_picker);
        setTimeOnTimePicker();

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour = mTimePicker.getHour();
                        Log.d("TimePicker", "AT return hour " + hour);
                        int minute = mTimePicker.getMinute();
                        Log.d("TimePicker", "AT return minute " + minute);
                        Date date = new GregorianCalendar(2015, 2, 2, hour, minute, 0).getTime();
                        sendResult(Activity.RESULT_OK, date);
                    }
                })
                        //.setView(R.layout.dialog_date)
                .setView(timePickerView)
                .create();

    }

    private void sendResult(int resultCode, Date date) {

        if (getTargetFragment() == null) {
            return;
        }

        Intent i = new Intent();
        i.putExtra(EXTRA_TIME, date);
        Log.d("TimePicker sendRes: ", "Date: " + date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);

    }

    private void setTimeOnTimePicker() {
        Date date = (Date) getArguments().getSerializable(ARG_TIME);
        Log.d("TimePicker: ", "Date: " + date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        mTimePicker.setHour(hour);
        mTimePicker.setMinute(minute);

    }

}
