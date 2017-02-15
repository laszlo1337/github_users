package com.android.laszlo.githubusers.model;

import java.util.ArrayList;

/**
 * Created by laszlo on 2017-02-11.
 */

public class MainUserDataList {

    private static ArrayList<User> users = null;

    private MainUserDataList(){

    }

    public static ArrayList<User> getInstance(){
        if(users==null){
            users = new ArrayList<>();
        }
        return users;
    }
}
