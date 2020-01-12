package com.hoyt.pigeondb.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hoyt.pigeondb.DiseaseTab;
import com.hoyt.pigeondb.R;
import com.hoyt.pigeondb.pairs.PairsTab;
import com.hoyt.pigeondb.pigeons.PigeonsTab;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab1, R.string.tab2, R.string.tab3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PigeonsTab x = new PigeonsTab();
                return x;
            case 1:
                PairsTab b = new PairsTab();
                return b;
            case 2:
                DiseaseTab d = new DiseaseTab();
                return d;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}