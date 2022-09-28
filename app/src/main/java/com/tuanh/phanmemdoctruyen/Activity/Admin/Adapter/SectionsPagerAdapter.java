package com.tuanh.phanmemdoctruyen.Activity.Admin.Adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tuanh.phanmemdoctruyen.Activity.Admin.Fragment.QuanLyTheLoaiFragment;
import com.tuanh.phanmemdoctruyen.Activity.Admin.Fragment.QuanLyTruyenFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String[] TAB_TITLES = new String[]{"Quản lý truyện", "Quản lý thể loại"};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) return new QuanLyTruyenFragment();
        else return new QuanLyTheLoaiFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}