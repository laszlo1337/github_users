package com.android.laszlo.githubusers.adapters;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.laszlo.githubusers.fragments.RepoContentFragment;
import com.android.laszlo.githubusers.model.Repository;
import com.android.laszlo.githubusers.model.TempUserRepoList;

import java.util.ArrayList;

/**
 * Created by laszlo on 2017-01-31.
 */

public class ReposAdapter extends FragmentStatePagerAdapter {


    public ReposAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int p) {
        Repository r = TempUserRepoList.getInstance().get(p);
        return RepoContentFragment.newInstance(p, r.getDescription(), r.getForks(), r.getWatchers(), r.getOpenIssuesCount(), r.getSshUrl());
    }

    @Override
    public int getCount() {
        return TempUserRepoList.getInstance().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TempUserRepoList.getInstance().get(position).getName();
    }


}
