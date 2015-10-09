package geoquiz.book.criminalintent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import geoquiz.book.criminalintent.database.CrimeBaseHelper;

/**
 * Created by jorge.bautista on 08/10/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<CrimeListActivity> {

    private final static String TAG = "MainActivityTest";

    private RecyclerView mCrimeRecyclerView;


    public MainActivityTest() {
        super(CrimeListActivity.class);
//        Log.d(TAG, "¡¡MainActivity!!");
    }



    @Override
    public void setUp() throws Exception {
        super.setUp();
        List<String> tableList = new ArrayList<>();
//        tableList.add("crimes");
//        cleanUpDatabase(tableList);

        getInstrumentation().getTargetContext().deleteDatabase(CrimeBaseHelper.DATABASE_NAME);

        mCrimeRecyclerView = (RecyclerView)getActivity().findViewById(R.id.crime_recycler_view);

//        Log.d(TAG, "mCrimeRecyclerView: " + mCrimeRecyclerView);

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
//        Log.d(TAG, "¡¡teardown!!");
    }

    public void testListIsVisible() throws Throwable{
        assertNotNull("El recycler view no existe", mCrimeRecyclerView);
//        Log.d(TAG, "¡¡Testing!!");
    }



    private void cleanUpDatabase(List<String> dbTables) {
        Log.i(TAG, "Preparing to clean up database...");
//        DatabaseHelper dbHelper = new DatabaseHelper(getInstrumentation().getTargetContext());
//        ConnectionSource cs = dbHelper.getConnectionSource();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteDatabase db  = new CrimeBaseHelper(getInstrumentation().getContext()).getWritableDatabase();

        Log.i(TAG, "Dropping all tables");
        for (String table : dbTables) {
            Log.i(TAG, "Deleting table: " + table);
            db.execSQL("DROP TABLE IF EXISTS " + table);
        }

        Log.i(TAG, "Executing the onCreate(..)");
//        dbHelper.onCreate(db, cs);

        Log.i(TAG, "Verifying the data...");
        for (String table : dbTables) {
            Cursor c = db.query(table, new String[]{"id"}, null, null, null, null, null);
//            int count = c.getCount();
//            if (count != 1 && (table.equals("project") || table.equals("task"))) {
////                dbHelper.close();
//                Log.e(TAG, "We should have 1 record for table " + table + " after cleanup but we found " + count + " record(s)");
//                throw new RuntimeException("Error during cleanup of DB, exactly one record should be present for table " + table + " but we found " + count + " record(s)");
//            } else if (count != 0 && !(table.equals("project") || table.equals("task"))) {
////                dbHelper.close();
//                Log.e(TAG, "We should have 0 records for table " + table + " after cleanup but we found " + count + " record(s)");
//                throw new RuntimeException("Error during cleanup of DB, no records should be present for table " + table + " but we found " + count + " record(s)");
//            }
        }

        Log.i(TAG, "The database has been cleaned!");
//        dbHelper.close();
    }


//
//    private void cleanUpDatabase(List<String> dbTables) {
//        Log.i(TAG, "Preparing to clean up database...");
//        DatabaseHelper dbHelper = new DatabaseHelper(getInstrumentation().getTargetContext());
//        ConnectionSource cs = dbHelper.getConnectionSource();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        Log.i(TAG, "Dropping all tables");
//        for (String table : dbTables) {
//            db.execSQL("DROP TABLE IF EXISTS " + table);
//        }
//    }

    public void testNumberOfItems() throws Throwable{

        assertEquals("El número de items inicial es incorrecto", mCrimeRecyclerView.getChildCount(), 0);
//        Log.d(TAG, "¡¡Testing!!");
    }

    public void testPreconditions() {
//        System.out.println("¡¡testPreconditions!!");
        assertNotNull("mFirstTestActivity is null", "hola");
    }
}
