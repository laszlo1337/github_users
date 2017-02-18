package com.android.laszlo.githubusers.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.laszlo.githubusers.fragments.RepoContentFragment;
import com.android.laszlo.githubusers.model.Repository;

import java.util.ArrayList;

/**
 * Created by laszlo on 2017-01-31.
 */

public class ReposAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Repository> repos;

    public ReposAdapter(FragmentManager manager, ArrayList<Repository> repos) {
        super(manager);
        this.repos = repos;
    }

    @Override
    public Fragment getItem(int p) {
        Repository r = repos.get(p);
        return RepoContentFragment.newInstance(p, r.getDescription(), r.getForks(), r.getWatchers(), r.getOpenIssuesCount(), r.getSshUrl());
    }

    @Override
    public int getCount() {
        return repos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return repos.get(position).getName();
    }


}
