package com.example.user.alarmmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.user.alarmmanager.R;
import com.example.user.alarmmanager.adapter.InputFilterMinMax;
import com.example.user.alarmmanager.utility.TagUtilityClass;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 02/05/17.
 */

public class TimerFragment extends Fragment implements View.OnClickListener {

    EditText mHour, mMinute, mSecond;

    Button mStartButton, mStopButton, mPauseButton;

    Timer mTimer;

    Boolean mIsTimerRunning = false;
    Boolean mIsPaused=false;

    View lTimerFragmentView;

    LinearLayout mStartTimerLayout, mStopTimerLayout;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        lTimerFragmentView = inflater.inflate(R.layout.timer_fragment, container, false);

        mHour = (EditText) lTimerFragmentView.findViewById(R.id.houreditText);
        mMinute = (EditText) lTimerFragmentView.findViewById(R.id.minuteEditText);
        mSecond = (EditText) lTimerFragmentView.findViewById(R.id.secondsEditText);

        mStartButton = (Button) lTimerFragmentView.findViewById(R.id.startBtn);
        mStopButton = (Button) lTimerFragmentView.findViewById(R.id.stopBtn);
        mPauseButton = (Button) lTimerFragmentView.findViewById(R.id.pauseBtn);

        mStartTimerLayout=(LinearLayout) lTimerFragmentView.findViewById(R.id.startTimerLayout);
        mStopTimerLayout=(LinearLayout) lTimerFragmentView.findViewById(R.id.stopTimerLayout);




        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(mHour.getText().toString().isEmpty() && mMinute.getText().toString().isEmpty() && mSecond.getText().toString().isEmpty())) {

                    if (mSecond.getText().toString() == null || mSecond.getText().toString().isEmpty()) {

                        mSecond.setText("0");
                    }
                    if (mMinute.getText().toString() == null || mMinute.getText().toString().isEmpty()) {

                        mMinute.setText("0");
                    }
                    if (mHour.getText().toString() == null || mHour.getText().toString().isEmpty()) {

                        mHour.setText("0");
                    }

                    if (!(mHour.getText().toString().equals("0") && mMinute.getText().toString().equals("0") && mSecond.getText().toString().equals("0"))){

                        if  (!mIsTimerRunning) {

                            mStartTimerLayout.setVisibility(View.INVISIBLE);
                            mStopTimerLayout.setVisibility(View.VISIBLE);

                            mIsTimerRunning = true;

                            mHour.setEnabled(false);
                            mMinute.setEnabled(false);
                            mSecond.setEnabled(false);

//                        mMinute.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
//                        mSecond.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
//                        mHour.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

                            mTimer = new Timer();
                            mTimer.schedule(new MyTimer(), 1000, 1000);
                        }
                    }


                }
            }
        });


        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStartTimerLayout.setVisibility(View.VISIBLE);
                mStopTimerLayout.setVisibility(View.INVISIBLE);

                clearText();

                mHour.setEnabled(true);
                mMinute.setEnabled(true);
                mSecond.setEnabled(true);

                mIsTimerRunning = false;
                mTimer.cancel();
            }
        });

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mIsPaused){
                    mTimer.cancel();
                    mIsPaused=true;
                    mPauseButton.setText("Resume");
                }
                else {
                    mTimer = new Timer();
                    mTimer.schedule(new MyTimer(), 1000, 1000);
                    mPauseButton.setText("Pause");
                    mIsPaused=false;
                }
            }
        });

        mHour.setFilters(new InputFilter[]{new InputFilterMinMax(00, 99)});
        mHour.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

        mMinute.setFilters(new InputFilter[]{new InputFilterMinMax(0, 59)});
        mMinute.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

        mSecond.setFilters(new InputFilter[]{new InputFilterMinMax(0, 59)});
        mSecond.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});


        return lTimerFragmentView;
    }

    /**
     *
     */
    void clearText() {
        mHour.setText("");
        mMinute.setText("");
        mSecond.setText("");
    }

    @Override
    public void onClick(View v) {

    }


    /**
     *
     */
    class MyTimer extends TimerTask {

        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {



                    int lHour = Integer.parseInt(mHour.getText().toString());
                    int lMin = Integer.parseInt(mMinute.getText().toString());

                    if (Integer.parseInt(mSecond.getText().toString()) == 0) {

                        if (lMin == 0 && lHour == 0) {

                            clearText();


                            mTimer.cancel();
                            mIsTimerRunning = false;

                            mHour.setEnabled(true);
                            mMinute.setEnabled(true);
                            mSecond.setEnabled(true);

                            mStartTimerLayout.setVisibility(View.VISIBLE);
                            mStopTimerLayout.setVisibility(View.INVISIBLE);

                            Intent lStartTimer = new Intent(getContext(), DisplayAlarm.class);
                            lStartTimer.putExtra(TagUtilityClass.INTENT_VALUE,"Elapsed Time");
                            startActivity(lStartTimer);
                        } else {

                            mSecond.setText("59");
                            if (lMin == 0) {

                                mMinute.setText("" + 59);
                                if (lHour != 0) {
                                    mHour.setText("" + (lHour - 1));
                                }

                            } else {
                                mMinute.setText("" + (lMin - 1));
                            }

                        }
                    } else
                        mSecond.setText("" + (Integer.parseInt(mSecond.getText().toString()) - 1));

                }
            });


        }
    }
}
