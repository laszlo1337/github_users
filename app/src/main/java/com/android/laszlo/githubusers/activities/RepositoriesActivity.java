package com.android.laszlo.githubusers.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.laszlo.githubusers.R;
import com.android.laszlo.githubusers.adapters.ReposAdapter;
import com.android.laszlo.githubusers.fragments.RepoContentFragment;
import com.android.laszlo.githubusers.model.Repository;
import com.android.laszlo.githubusers.model.TempUserRepoList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.laszlo.githubusers.activities.GitHubUsers.apiService;

/**
 * Created by laszlo on 2017-01-29.
 */

public class RepositoriesActivity extends AppCompatActivity {

    ReposAdapter reposAdapter;
    ViewPager viewPager;
    private String currentUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_tab_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        currentUser = getIntent().getStringExtra("login");
        getSupportActionBar().setTitle(currentUser);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        reposAdapter = new ReposAdapter(getSupportFragmentManager());
        viewPager.setAdapter(reposAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        loadRepos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                TempUserRepoList.clear();
                finish();
                break;
        }
        return true;
    }

    private void loadRepos() {
        Call<List<Repository>> call = apiService.getUserRepos(currentUser);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                ArrayList<Repository> repos = TempUserRepoList.getInstance();
                if (response.code() == 200) {
                    repos.addAll(response.body());
                    reposAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "error " + response.code(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {

            }
        });
    }

}
