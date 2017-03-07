package com.android.laszlo.githubusers.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.laszlo.githubusers.R;
import com.android.laszlo.githubusers.adapters.ReposAdapter;
import com.android.laszlo.githubusers.model.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.laszlo.githubusers.activities.GitHubUsersActivity.apiService;

/**
 * Created by laszlo on 2017-01-29.
 */

public class RepositoriesActivity extends AppCompatActivity {

    private ReposAdapter reposAdapter;
    private ViewPager viewPager;
    private String currentUser;
    private ArrayList<Repository> repos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_tab_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        currentUser = getIntent().getStringExtra("login");
        getSupportActionBar().setTitle(currentUser);
        getSupportActionBar().setElevation(0);
        repos = new ArrayList<>();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        reposAdapter = new ReposAdapter(getSupportFragmentManager(), repos);
        viewPager.setAdapter(reposAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        loadRepos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
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
