package com.android.laszlo.githubusers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.laszlo.githubusers.R;
import com.android.laszlo.githubusers.adapters.EndlessRecyclerOnScrollListener;
import com.android.laszlo.githubusers.adapters.ItemsAdapter;
import com.android.laszlo.githubusers.api.ApiClient;
import com.android.laszlo.githubusers.api.ApiInterface;
import com.android.laszlo.githubusers.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubUsersActivity extends AppCompatActivity implements EndlessRecyclerOnScrollListener.OnLoadMoreListener {

    private final int USER_QUANTITY_PER_CALL = 20;

    public static ApiInterface apiService;

    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private LinearLayoutManager layoutManager;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_github_users);

        users = new ArrayList<>();
        apiService = new ApiClient().getClient().create(ApiInterface.class);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemsAdapter(users, this);
        recyclerView.setAdapter(adapter);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(layoutManager, this);
        endlessRecyclerOnScrollListener.setDataList(users);
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
        loadDataSinceId(0);
    }


    private void loadDataSinceId(int lastUserId) {
        Call<List<User>> call = apiService.getUsers(USER_QUANTITY_PER_CALL, lastUserId);
        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.code() == 200) {
                    users.addAll(response.body());
                    adapter.setData(users);
                }
                Toast.makeText(getApplicationContext(), "status: " + response.code() + " list size: " + users.size(), Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("e", t.toString());
            }
        });

    }

    @Override
    public void onLoadMore(int lastUserId) {
        loadDataSinceId(lastUserId);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
