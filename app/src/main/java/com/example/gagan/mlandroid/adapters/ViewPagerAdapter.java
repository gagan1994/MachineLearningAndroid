package com.example.gagan.mlandroid.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.example.gagan.mlandroid.fragments.BaseFragments;

import java.util.List;

/**
 * Created by Gagan on 6/8/2018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<BaseFragments> mData;

    public ViewPagerAdapter(List<BaseFragments> baseFragments, FragmentManager fm) {
        super(fm);
        this.mData = baseFragments;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    public void addFragment(BaseFragments fragment) {
        mData.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position).getTitleOfThis();
    }
}
