package com.inventrohyder.aadpracticeproject2020.ui.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStateAdapter {

    final String TAG = getClass().getSimpleName();

    public SectionsPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return PlaceholderFragment.newInstance(PlaceholderFragment.ARG_SECTION_LEARNED);
        } else if (position == 1) {
            return PlaceholderFragment.newInstance(PlaceholderFragment.ARG_SECTION_SKILLED);
        }

        Log.d(TAG, "getItem: Invalid Position");
        return new PlaceholderFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}