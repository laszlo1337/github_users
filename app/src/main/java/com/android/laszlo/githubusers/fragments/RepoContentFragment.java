package com.android.laszlo.githubusers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.laszlo.githubusers.R;


/**
 * Created by laszlo on 2017-02-04.
 */

public class RepoContentFragment extends Fragment {

    int pageNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment, container, false);
        TextView textView = (TextView) view.findViewById(R.id.description_textview);
        String description = getArguments().getString("description");
        if (description != null) {
            textView.setText(description);
        }
        textView = (TextView) view.findViewById(R.id.ssh_link);
        textView.setText(getArguments().getString("ssh"));
        textView = (TextView) view.findViewById(R.id.problems_textview);
        textView.setText(String.valueOf(getArguments().getInt("problems")));
        textView = (TextView) view.findViewById(R.id.watchers_textview);
        textView.setText(String.valueOf(getArguments().getInt("watchers")));
        textView = (TextView) view.findViewById(R.id.forks_textview);
        textView.setText(String.valueOf(getArguments().getInt("forks")));
        return view;
    }

    public static RepoContentFragment newInstance(int pageNumber, String description, int forks, int watchers, int problems, String sshLink) {
        RepoContentFragment repoFragment = new RepoContentFragment();
        Bundle args = new Bundle();
        args.putInt("pageNumber", pageNumber);
        args.putInt("forks", forks);
        args.putInt("watchers", watchers);
        args.putInt("problems", problems);
        args.putString("description", description);
        args.putString("ssh", sshLink);
        repoFragment.setArguments(args);
        return repoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt("pageNumber");
    }
}
