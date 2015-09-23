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
//        for (int i = 0; i < 100; i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);
//            addCrime(crime);
//        }
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }

    public void deleteCrime(UUID id) {

        int idOfFoundCrime = -1;

        for (int i = 0; i < mCrimes.size(); i++) {
            if(id.equals(mCrimes.get(i).getId())){
                idOfFoundCrime = i;
                break;
            }
        }

        if(idOfFoundCrime > -1)
            mCrimes.remove(idOfFoundCrime);


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


}
