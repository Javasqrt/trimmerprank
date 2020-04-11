package com.byanton.trimmerprank.ui.main;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.byanton.trimmerprank.TrimmerOne;
import com.byanton.trimmerprank.TrimmerThree;
import com.byanton.trimmerprank.TrimmerTwo;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new TrimmerOne();
                break;
            case 1:
                fragment = new TrimmerTwo();
                break;
            case 2:
                fragment = new TrimmerThree();
                break;
        }
        return fragment;

    }


    @Override
    public int getCount() {

        return 3;
    }
}