package com.android.laszlo.githubusers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.laszlo.githubusers.R;

/**
 * Created by laszlo on 2017-02-04.
 */

public class RepoContentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
        inflater.inflate(R.layout.fragment,container,false)
    }
}
