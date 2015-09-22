package geoquiz.book.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.DatePicker;

import java.util.Date;
import java.util.UUID;

import geoquiz.book.criminalintent.model.CrimeLab;
import geoquiz.book.criminalintent.util.Util;

public class DatePickerActivity extends SingleFragmentActivityAbstract {

    private static final String TAG = "DatePickerActivity";
    private final static String EXTRA_CRIME_ID =  "extra crime id on date picker activity";



    public static Intent newIntent(Context packageContext, UUID crimeId){

        Intent i = new Intent(packageContext, DatePickerActivity.class);
        i.putExtra(EXTRA_CRIME_ID, crimeId);

        return i;
    }

    @Override
    protected void preprocessing() {


        if (Util.isTablet(this)) {
            Log.d(TAG, "Es una tableta");
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            Log.d(TAG, "Es un teléfono");
        }


    }

    @Override
    protected Fragment createFragment() {
        UUID id = (UUID)getIntent().getExtras().get(EXTRA_CRIME_ID);
        Date d = CrimeLab.get(this).getCrime(id).getDate();

        return DatePickerFragment.newInstance(d);
    }

}
