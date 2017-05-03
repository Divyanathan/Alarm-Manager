package com.example.user.alarmmanager.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.user.alarmmanager.R;
import com.example.user.alarmmanager.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {


    ViewPager mViewPager;
    TabLayout mTabLayout;
    ArrayList<Fragment> mFragmentArrayList=new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         *  adding the custum tool bar
         */

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setCustomView(R.layout.alarm_button);
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_CUSTOM);




        mViewPager=(ViewPager) findViewById(R.id.viewPager);

        mFragmentArrayList.add(new AlarmFragment());
        mFragmentArrayList.add(new TimerFragment());
        ViewPagerAdapter lViewPageAdapter=new ViewPagerAdapter(getSupportFragmentManager(),mFragmentArrayList);
        mViewPager.setAdapter(lViewPageAdapter);

        mTabLayout=(TabLayout)findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
