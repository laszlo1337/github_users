package com.android.laszlo.githubusers.model;

import java.util.ArrayList;

/**
 * Created by laszlo on 2017-02-11.
 */

public class TempUserRepoList {

    private static ArrayList<Repository> repos = null;

    private TempUserRepoList(){

    }

    public static ArrayList<Repository> getInstance(){
        if(repos==null){
            repos = new ArrayList<>();
        }
        return repos;
    }

    public static void clear(){
        repos.clear();
    }
}
