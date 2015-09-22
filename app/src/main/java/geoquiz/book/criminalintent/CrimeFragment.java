package geoquiz.book.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

import geoquiz.book.criminalintent.model.Crime;
import geoquiz.book.criminalintent.model.CrimeLab;
import geoquiz.book.criminalintent.util.Util;

/**
 * Created by jorge.bautista on 10/09/15.
 */
public class CrimeFragment extends Fragment{

    private static final String TAG =  "CrimeFragment";

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE= "DialogDate";
    private static final String DIALOG_TIME= "DialogTime";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    private Crime mCrime;
    private EditText mTitleField;

    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;

    public static CrimeFragment newInstance(UUID crimeId) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment cf = new CrimeFragment();
        cf.setArguments(bundle);
        return cf;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID)getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        assembleComponents(v);
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) {
            return;
        }

        if(requestCode == REQUEST_DATE) {

            Date d = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(d);
            updateDate(mCrime.getDate());

        } else if(requestCode == REQUEST_TIME) {
            Date d = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mCrime.setTime(d);
            updateTime(mCrime.getTime());

        }

    }

    private void updateTime(Date time) {
        String fTime = Util.formatTime(time);
        mTimeButton.setText(fTime);
    }

    private void updateDate(Date date) {
        String fDate = Util.formatDate(date);
        mDateButton.setText(fDate);
    }




    private void assembleComponents(View v) {
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.crime_date);
        updateDate(mCrime.getDate());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTablet = Util.isTablet(getActivity());
                if(!isTablet)
                    startActivityForResult(DatePickerActivity.newIntent(getActivity(), mCrime.getId(), isTablet), REQUEST_DATE);
                else {
                    FragmentManager f = getFragmentManager();
                    DatePickerFragment d = DatePickerFragment.newInstance(mCrime.getDate(), isTablet);
                    d.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                    d.show(f, DIALOG_DATE);
                }

            }
        });


        mTimeButton = (Button)v.findViewById(R.id.crime_time_button);
        updateTime(mCrime.getTime());
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager f = getFragmentManager();
                TimePickerFragment t = TimePickerFragment.newInstance(mCrime.getTime());
                t.setTargetFragment(CrimeFragment.this, REQUEST_TIME);
                t.show(f, DIALOG_TIME);
            }
        });




        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
    }



}
