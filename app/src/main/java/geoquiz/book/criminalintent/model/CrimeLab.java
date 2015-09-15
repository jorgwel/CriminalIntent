package geoquiz.book.criminalintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jorge.bautista on 11/09/15.
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {

        for (Crime currentCrime: mCrimes) {
            if(currentCrime.getId().equals(id)){
                return currentCrime;
            }
        }

        return null;
    }


    public int getCrimeIndex(UUID crimeId) {
        for (int i = 0; i < mCrimes.size(); i++) {
            Crime currentCrime = mCrimes.get(i);
            if(crimeId.equals(currentCrime.getId())) {
                return i;
            }
        }
        return -1;
    }
}
