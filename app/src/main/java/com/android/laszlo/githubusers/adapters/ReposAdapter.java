package com.android.laszlo.githubusers.adapters;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by laszlo on 2017-01-31.
 */

public class ReposAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<Fragment> fragments = new ArrayList<>();
    private final ArrayList<String> fragmentTitles = new ArrayList<>();
    Context context;
    ViewPager viewPager;
    TabLayout tabLayout;

    public ReposAdapter(FragmentManager manager, Context context, ViewPager viewPager, TabLayout tabLayout) {
        super(manager);
        this.context = context;
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFrag(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentTitles.add(title);
    }


//    public View getTabView(final int position) {
//        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_item, null);
//        TextView tabItemName = (TextView) view.findViewById(R.id.textViewTabItemName);
//
//        tabItemName.setText(fragmentTitles.get(position));
//        tabItemName.setTextColor(context.getResources().getColor(android.R.color.background_light));
//
//        return view;
//    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}
