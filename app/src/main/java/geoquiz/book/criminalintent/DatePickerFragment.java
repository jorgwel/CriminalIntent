package geoquiz.book.criminalintent;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import geoquiz.book.criminalintent.util.Util;

/**
 * Created by jorge.bautista on 17/09/15.
 */
public class DatePickerFragment extends DialogFragment {

    private static final String TAG = "DatePickerFragment";

    private static final String ARG_DATE = "date";
    private static final String ARG_IS_TABLET = "isTablet";
    public static final String EXTRA_DATE = "com.libro.regresarafragento.target.date";


    private DatePicker mDatePicker;
    private Button mOkButton;

    public static DatePickerFragment newInstance(Date date, boolean isTablet) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        args.putBoolean(ARG_IS_TABLET, isTablet);

        DatePickerFragment f = new DatePickerFragment();
        f.setArguments(args);
        return f;
    }


    @Nullable
    @Override//For a phone
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(getArguments().getBoolean(ARG_IS_TABLET)){
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        View datePickerView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        mDatePicker = (DatePicker) datePickerView.findViewById(R.id.dialog_date_date_picker);
        setDateOnTimePicker();

        mOkButton = (Button)datePickerView.findViewById(R.id.ok_button_on);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                Date date = new GregorianCalendar(year, month, day).getTime();
                sendResult(Activity.RESULT_OK, date);
            }
        });



        return datePickerView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (!getArguments().getBoolean(ARG_IS_TABLET)) {//If this is a phone
            //Then an activity with it's own button is shown
            return super.onCreateDialog(savedInstanceState);
        }

        View datePickerView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        mDatePicker = (DatePicker) datePickerView.findViewById(R.id.dialog_date_date_picker);
        setDateOnTimePicker();

        mOkButton = (Button)datePickerView.findViewById(R.id.ok_button_on);
        ((ViewManager)mDatePicker.getParent()).removeView(mOkButton);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Date date = new GregorianCalendar(year, month, day).getTime();
                        sendResult(Activity.RESULT_OK, date);
                    }
                })
                //.setView(R.layout.dialog_date)
                .setView(datePickerView)
                .create();
    }

    private void setDateOnTimePicker() {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year, month, day, null);
    }

    private void sendResult(int resultCode, Date date) {

        if(getTargetFragment() == null) {
            Intent i = new Intent();
            i.putExtra(EXTRA_DATE, date);
            getActivity().setResult(resultCode, i);
            getActivity().finish();

            return;
        }

        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
