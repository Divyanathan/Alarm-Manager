package com.example.user.alarmmanager.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    ArrayList<Fragment> mFragmentArrayList;
    final static String TAG="PageAdapter";

    public ViewPagerAdapter(FragmentManager pFragmentManager, ArrayList<Fragment> pFragmentArrayList) {
        super(pFragmentManager);
        this.mFragmentArrayList = pFragmentArrayList;
        Log.e(TAG, "ViewPagerAdapter: " );
    }

    @Override
    public Fragment getItem(int position) {
        Log.e(TAG, "getItem: " );
        return mFragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        Log.e(TAG, "getCount: " );
        return mFragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Log.e(TAG, "getPageTitle: " );
        if (position==0)
        return "Alarm";
        else
            return "Timer";
    }
}
