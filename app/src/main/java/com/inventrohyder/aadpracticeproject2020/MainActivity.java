package com.inventrohyder.aadpracticeproject2020;

import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.inventrohyder.aadpracticeproject2020.ui.main.SectionsPagerAdapter;

public class MainActivity extends FragmentActivity {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_learning, R.string.tab_skill};
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a ViewPager2 and a PagerAdapter.
        /*
          The pager widget, which handles animation and allows swiping horizontally to access previous
          and next wizard steps.
         */
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        /*
          The pager adapter, which provides the pages to the view pager widget.
         */
        FragmentStateAdapter pagerAdapter = new SectionsPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(
                        this.getResources().getString(TAB_TITLES[position])
                )
        ).attach();
    }

}