package geoquiz.book.criminalintent;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.util.Log;

import junit.framework.Test;

import java.net.URL;

import geoquiz.book.criminalintent.database.CrimeBaseHelper;
import geoquiz.book.criminalintent.model.Crime;


/**
 * Created by jorge.bautista on 08/10/15.
 */
public class CrimeListActivityTest extends ActivityInstrumentationTestCase2<CrimeListActivity> {

    private final static String TAG = "CrimeListActivityTest";

    private RecyclerView mCrimeRecyclerView;
    private ActionBar actionBar;


    public CrimeListActivityTest() {
        super(CrimeListActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getInstrumentation().getTargetContext().deleteDatabase(CrimeBaseHelper.DATABASE_NAME);
    }

    /**
     * Test: The application opens in the CrimeListActivity as the Launcher Activity
     * - Preconditions: None
     * - Expected: the first activity shown is CrimeListActivity
     */
    public void testCrimeListActivity_being_Shown_AsTheLauncherActivity(){
        String activityThatShouldBeLaunched = "geoquiz.book.criminalintent.CrimeListActivity";
        String activityThatIsBeingLaunched = getNameOfLaunchedActivity();
        assertEquals("The launcher activity is wrong", activityThatShouldBeLaunched, activityThatIsBeingLaunched);
    }

    /**
     * Test: When application starts, the number of crimes is not being shown
     * - Preconditions: None
     * - Expected: The number of crimes is not being shown at start
     */
    public void testSubtitleNotShown_AtAppStart(){
        assertFalse("The number of crimes is being shown at startup", isNumberOfCrimesShown());
    }

    /**
     * Test: User clicks on "Show subtitle" when the number of crimes is being shown
     * - Preconditions: App is showing the main activity
     * - Expected: The number of crimes is hidden
     *
     * Useful resources: https://developer.android.com/training/activity-testing/activity-functional-testing.html
     *
     */
    public void testUserClicksOnShowSubtitleButton_TheNumberOfCrimes_IsShown(){
        TouchUtils.clickView(this, getActivity().findViewById(R.id.menu_item_show_subtitle));
        assertTrue("The number of crimes is NOT being shown at startup", isNumberOfCrimesShown());
    }

    /**
     * Test: User clicks on "Show subtitle" when the number of crimes is being shown
     * - Preconditions: App is showing the main activity
     * - Expected: The number of crimes is hidden
     */
    public void testUserClicksOnShowSubtitleButton_WhenNumberOfCrimesAreBeingShown_TheNumberOfCrimesAreNot_BeingShown(){
        testUserClicksOnShowSubtitleButton_TheNumberOfCrimes_IsShown();
        TouchUtils.clickView(this, getActivity().findViewById(R.id.menu_item_show_subtitle));
        assertFalse("The number of crimes is being shown after clicking twice the button \"Show subtitle\"", isNumberOfCrimesShown());
    }

    /**
     * Test: App is opened and has NOT registered crimes
     * - Preconditions: None
     * - Expected: the application should show the empty view of the list
     */
    public void testCrimeList_Empty_AtAppStart(){
        assignRecyclerView();
        int numberOfItems = mCrimeRecyclerView.getChildCount();
        assertEquals("The database is not empty at start", numberOfItems, 0);
    }


    /**
     * Test: User creates a crime
     * - Preconditions: None
     * - Expected: a user has created a new Crime and the crime list in the main view is already showing it
     */
    public void testUserCreatesACrime(){

        Instrumentation.ActivityMonitor crimePagerMonitor = getInstrumentation().addMonitor(CrimePagerActivity.class.getName(), null, false);
        TouchUtils.clickView(this, getActivity().findViewById(R.id.menu_item_new_crime));
        CrimePagerActivity receiverActivity = (CrimePagerActivity)crimePagerMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called", 1, crimePagerMonitor.getHits());
        assertEquals("Activity is of wrong type", CrimePagerActivity.class, receiverActivity.getClass());
        getInstrumentation().removeMonitor(crimePagerMonitor);

    }


    /**
     * Test: App is opened and has registered crimes
     * - Preconditions: Some crimes have been registered
     * - Expected: the application should show the list with the registered crimes
     */
    public void testCrimeList_HasRecords_AtApStart(){
        assertTrue(true);
    }



    private boolean isNumberOfCrimesShown() {
        assignActionBar();
        return actionBar.getSubtitle() != null;
    }

    private void assignActionBar() {
        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
    }

    private void assignRecyclerView() {
        mCrimeRecyclerView = (RecyclerView)getActivity().findViewById(R.id.crime_recycler_view);
    }

    private String getNameOfLaunchedActivity() {
        PackageManager pm = getInstrumentation().getTargetContext().getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("geoquiz.book.criminalintent");
        return intent.getComponent().getClassName();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }


}
