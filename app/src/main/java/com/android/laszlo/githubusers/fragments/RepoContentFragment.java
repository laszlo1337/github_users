package com.android.laszlo.githubusers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.laszlo.githubusers.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by laszlo on 2017-02-04.
 */

public class RepoContentFragment extends Fragment {

    int pageNumber;
    @BindView(R.id.description_textview)TextView description;
    @BindView(R.id.ssh_link)TextView ssh;
    @BindView(R.id.problems_textview)TextView problems;
    @BindView(R.id.watchers_textview)TextView watchers;
    @BindView(R.id.forks_textview)TextView forks;

    private static final String KEY_DESCRIPTION = "DESCRIPTION";
    private static final String KEY_PAGE_NUMBER = "PAGE_NUMBER";
    private static final String KEY_FORKS_NUMBER = "FORKS_NUMBER";
    private static final String KEY_SSH = "SSH";
    private static final String KEY_WATCHERS_NUMBER = "WATCHERS_NUMBER";
    private static final String KEY_PROBLEMS_NUMBER = "PROBLEMS_NUMBER";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment, container, false);
        ButterKnife.bind(this,view);
        String desc = getArguments().getString(KEY_DESCRIPTION);
        if (desc != null) {description.setText(desc);}
        ssh.setText(getArguments().getString(KEY_SSH));
        problems.setText(String.valueOf(getArguments().getInt(KEY_PROBLEMS_NUMBER)));
        watchers.setText(String.valueOf(getArguments().getInt(KEY_WATCHERS_NUMBER)));
        forks.setText(String.valueOf(getArguments().getInt(KEY_FORKS_NUMBER)));
        return view;
    }

    public static RepoContentFragment newInstance(int pageNumber, String description, int forks, int watchers, int problems, String sshLink) {
        RepoContentFragment repoFragment = new RepoContentFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PAGE_NUMBER, pageNumber);
        args.putInt(KEY_FORKS_NUMBER, forks);
        args.putInt(KEY_WATCHERS_NUMBER, watchers);
        args.putInt(KEY_PROBLEMS_NUMBER, problems);
        args.putString(KEY_DESCRIPTION, description);
        args.putString(KEY_SSH, sshLink);
        repoFragment.setArguments(args);
        return repoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(KEY_PAGE_NUMBER);
    }
}
