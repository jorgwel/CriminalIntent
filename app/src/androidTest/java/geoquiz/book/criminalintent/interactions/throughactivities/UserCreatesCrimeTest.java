package geoquiz.book.criminalintent.interactions.throughactivities;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import geoquiz.book.criminalintent.CrimeListActivity;
import geoquiz.book.criminalintent.CrimePagerActivity;
import geoquiz.book.criminalintent.R;
import geoquiz.book.criminalintent.database.CrimeBaseHelper;

/**
 * Created by jorge.bautista on 16/10/15.
 */
public class UserCreatesCrimeTest extends ActivityInstrumentationTestCase2<CrimeListActivity> {

    private static final String TAG = "UserCreatesCrimeTest";



    public UserCreatesCrimeTest() {
        super(CrimeListActivity.class);

    }


    @Override
    public void setUp() throws Exception {
        super.setUp();
        getInstrumentation().getTargetContext().deleteDatabase(CrimeBaseHelper.DATABASE_NAME);
    }


    /**
     * Test: User creates a crime
     * - Preconditions: None
     * - Expected: a user has created a new Crime and the crime list in the main view is already showing it
     */
    public void testUserCreatesACrime() {

        Instrumentation.ActivityMonitor crimePagerMonitor = getInstrumentation().addMonitor(CrimePagerActivity.class.getName(), null, false);
        TouchUtils.clickView(this, getActivity().findViewById(R.id.menu_item_new_crime));
        CrimePagerActivity receiverActivity = (CrimePagerActivity) crimePagerMonitor.waitForActivityWithTimeout(1000);
        EditText editText = (EditText)receiverActivity.findViewById(R.id.crime_title);
        receiverActivity.finish();

        Log.d(TAG, "editText: " + editText);
//        assertNotNull("ReceiverActivity is null", receiverActivity);
//        assertEquals("Monitor for ReceiverActivity has not been called", 1, crimePagerMonitor.getHits());
//        assertEquals("Activity is of wrong type", CrimePagerActivity.class, receiverActivity.getClass());
//        getInstrumentation().removeMonitor(crimePagerMonitor);



    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }



}
