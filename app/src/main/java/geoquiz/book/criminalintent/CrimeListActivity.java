package geoquiz.book.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by jorge.bautista on 11/09/15.
 */
public class CrimeListActivity extends SingleFragmentActivityAbstract {

    @Override
    protected void preprocessing() {

    }

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
