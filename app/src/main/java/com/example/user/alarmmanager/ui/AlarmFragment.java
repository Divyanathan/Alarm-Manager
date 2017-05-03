package com.example.user.alarmmanager.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;

import com.example.user.alarmmanager.R;
import com.example.user.alarmmanager.broad_cast_reciver.AlarmReciver;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmFragment extends Fragment {


    TimePicker mTimePicker;
    CheckBox mSundayCheckBox, mMondayCheckBox, mTuesDayCheckBox, mWednessDayCheckBox, mThursdayCheckBox, mFridayCheckBox, mSaturdayCheckBox;
    Switch mSetRepeat;

    Boolean mISAlarmSet =false;

    ArrayList<Integer> mDayToAlarm = new ArrayList<Integer>();
    final int mSUNDAY = 1;
    final int mMODAY = 2;
    final int mTUESDAY = 3;
    final int mWEDNESS_DAY = 4;
    final int mTHURS_DAY = 5;
    final int mFRIDAY = 6;
    final int mSATURDAY = 7;

    final int mALARM_REQUEST_CODE = 8;


    AlarmManager mAlarmManger;
    Intent mIntentAlarm;
    PendingIntent mPendingIntent;



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View lAlarmFragmentView=inflater.inflate(R.layout.alarm_fragment,container,false);

        setHasOptionsMenu(true);





        /**
         *                  initialiazation
         */
        mAlarmManger = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);

        mIntentAlarm = new Intent(getContext(), AlarmReciver.class);

        mPendingIntent = PendingIntent.getBroadcast(getContext(), mALARM_REQUEST_CODE, mIntentAlarm, 0);


        /**
         *                  initializing the elements
         */


        mTimePicker = (TimePicker) lAlarmFragmentView.findViewById(R.id.timepicker);

        mSundayCheckBox = (CheckBox) lAlarmFragmentView.findViewById(R.id.sundayCheckbox);
        mMondayCheckBox = (CheckBox) lAlarmFragmentView.findViewById(R.id.mondayCheckbox);
        mTuesDayCheckBox = (CheckBox) lAlarmFragmentView.findViewById(R.id.tuesdayCheckbox);
        mWednessDayCheckBox = (CheckBox) lAlarmFragmentView.findViewById(R.id.wednessdayCheckbox);
        mThursdayCheckBox = (CheckBox) lAlarmFragmentView.findViewById(R.id.thursdayCheckbox);
        mFridayCheckBox = (CheckBox) lAlarmFragmentView.findViewById(R.id.fridayCheckbox);
        mSaturdayCheckBox = (CheckBox) lAlarmFragmentView.findViewById(R.id.saturdayCheckbox);

        mSetRepeat = (Switch) lAlarmFragmentView.findViewById(R.id.setRepeat);

//        mISAlarmSet = (Switch) lAlarmFragmentView.findViewById(R.id.alarmSwitchButton);



        /**
         *                  on Click listener for checkbox
         */
        mSundayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (mSundayCheckBox.isChecked()) {
                    mDayToAlarm.add(mSUNDAY);
                } else {


                }

                Log.v("clicked", "" + mSundayCheckBox.isChecked());
            }
        });
        mMondayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mDayToAlarm.add(mMODAY);
                Log.v("clicked", "" + mMondayCheckBox.isChecked());
            }
        });
        mTuesDayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mDayToAlarm.add(mTUESDAY);
                Log.v("clicked", "" + mTuesDayCheckBox.isChecked());
            }
        });
        mWednessDayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mDayToAlarm.add(mWEDNESS_DAY);
                Log.v("clicked", "" + mWednessDayCheckBox.isChecked());
            }
        });
        mThursdayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mDayToAlarm.add(mTHURS_DAY);
                Log.v("clicked", "" + mThursdayCheckBox.isChecked());
            }
        });
        mFridayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mDayToAlarm.add(mFRIDAY);
                Log.v("clicked", "" + mFridayCheckBox.isChecked());
            }
        });
        mSaturdayCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                mDayToAlarm.add(mSATURDAY);
                Log.v("clicked", "" + mSaturdayCheckBox.isChecked());
            }
        });
        setEnableFase();

        /**
         *                  on change listner for set repeat alarm
         */

        mSetRepeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (mSetRepeat.isChecked()) {


                    mSundayCheckBox.setEnabled(true);
                    mMondayCheckBox.setEnabled(true);
                    mTuesDayCheckBox.setEnabled(true);
                    mWednessDayCheckBox.setEnabled(true);
                    mThursdayCheckBox.setEnabled(true);
                    mFridayCheckBox.setEnabled(true);
                    mSaturdayCheckBox.setEnabled(true);

                } else {

                    setEnableFase();
                    mDayToAlarm.clear();


                }
                Log.v("alarm_Repeat_clicked", "" + mSetRepeat.isChecked());


            }
        });




        return lAlarmFragmentView;
    }


    void setEnableFase() {

        mSundayCheckBox.setEnabled(false);
        mMondayCheckBox.setEnabled(false);
        mTuesDayCheckBox.setEnabled(false);
        mWednessDayCheckBox.setEnabled(false);
        mThursdayCheckBox.setEnabled(false);
        mFridayCheckBox.setEnabled(false);
        mSaturdayCheckBox.setEnabled(false);


        mSundayCheckBox.setChecked(false);
        mMondayCheckBox.setChecked(false);
        mTuesDayCheckBox.setChecked(false);
        mWednessDayCheckBox.setChecked(false);
        mThursdayCheckBox.setChecked(false);
        mFridayCheckBox.setChecked(false);
        mSaturdayCheckBox.setChecked(false);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.alarm_button, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.alarmMenuButton) {

            if (!mISAlarmSet){


                if (mDayToAlarm.size() > 0) {

                    Calendar lCalenderForDays = Calendar.getInstance();
                    for (int i = 0; i < mDayToAlarm.size(); i++) {

//                            mPendingIntent = PendingIntent.getBroadcast(AlarmFragment.this, mDayToAlarm.get(i), mIntentAlarm, 0);

                        lCalenderForDays.set(Calendar.DAY_OF_WEEK, mDayToAlarm.get(i));
                        lCalenderForDays.set(Calendar.HOUR_OF_DAY, mTimePicker.getHour());
                        lCalenderForDays.set(Calendar.MINUTE, mTimePicker.getMinute());
                        lCalenderForDays.set(Calendar.SECOND, 0);
                        lCalenderForDays.set(Calendar.MILLISECOND, 0);

                        mAlarmManger.setExact(AlarmManager.RTC_WAKEUP, lCalenderForDays.getTimeInMillis(), mPendingIntent);
                    }


                } else {

                    /**
                     *                  calender value
                     */
                    Calendar lCalender = Calendar.getInstance();
                    lCalender.set(Calendar.SECOND, 0);
                    lCalender.set(Calendar.MILLISECOND, 0);

                    /**
                     *                  calculating the difference of the time
                     */
                    Long lCurrentHour = (long) (lCalender.get(Calendar.HOUR_OF_DAY) * 60 + lCalender.get(Calendar.MINUTE)) * 60 * 1000;
                    Long lTime = (long) (mTimePicker.getHour() * 60 * 60 * 1000) + (mTimePicker.getMinute() * 60 * 1000);

                    mAlarmManger.set(mAlarmManger.RTC_WAKEUP, System.currentTimeMillis() + (lTime - lCurrentHour), mPendingIntent);

                    Log.v("hour_and_min", lTime + " " + lCurrentHour + " " + (lTime - lCurrentHour) + " " + System.currentTimeMillis());

//
                    if (lTime >= lCurrentHour) {

                        mAlarmManger.setExact(mAlarmManger.RTC_WAKEUP, lCalender.getTimeInMillis() + lTime - lCurrentHour, mPendingIntent);

                    } else {

                        mAlarmManger.setExact(mAlarmManger.RTC_WAKEUP, lCalender.getTimeInMillis() + (24 * 60 * 60 * 1000) + lTime - lCurrentHour, mPendingIntent);


                        Log.v("set_alarm_clicked", "time in minus" + ((24 * 60 * 60 * 1000) + lTime - lCurrentHour));
                    }

//                        Log.v("set_alarm_clicked", "" + mISAlarmSet.isChecked());
                }


//                item.setTitle("Stop");
                mISAlarmSet=true;

            } else {
//                item.setTitle("Start");
                mAlarmManger.cancel(mPendingIntent);
                mISAlarmSet=false;

            }

        }

        return super.onOptionsItemSelected(item);
    }
}
