package com.example.asus.mdcommunity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by asus on 2016/2/16.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> myFragments;
    private List<String>   myTitles;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> myFragments, List<String> myTitles) {
        super(fm);
        this.myFragments = myFragments;
        this.myTitles    = myTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return myTitles.get(position);
    }

    @Override
    public int getCount() {
        return myFragments.size();
    }
}
