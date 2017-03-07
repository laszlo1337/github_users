package com.android.laszlo.githubusers.api;

import com.android.laszlo.githubusers.model.Repository;
import com.android.laszlo.githubusers.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by laszlo on 2017-01-28.
 *
 *
 * BASE_URL = "https://api.github.com/"
 */

public interface ApiInterface {


    @GET("users")
    Call<List<User>> getUsers(@Query("per_page") int perPage, @Query("since") int lastUserId);

    @GET("users/{user}/repos")
    Call<List<Repository>> getUserRepos(@Path("user") String user);
}
