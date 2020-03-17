package com.ashwin.android.arch.viper.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.ashwin.android.arch.viper.MainApplication;
import com.ashwin.android.arch.viper.entity.User;

import java.util.Date;

public class HomeInteractor implements HomeContract.Interactor.Input {
    @Override
    public void getUser(final String username, final HomeContract.Interactor.Output output) {
        // Make network request to fetch user details
        // But as you know me, I will do the following :P
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(username)) {
                    String name = username.substring(0, 1).toUpperCase() + username.substring(1);
                    name = name.replaceAll("_", " ");
                    String dob = String.valueOf(new Date());
                    User user = new User(username, name, dob);
                    output.onSuccess(user);
                } else {
                    output.onFailure("Invalid username");
                }
            }
        }).start();
    }

    @Override
    public void logout(HomeContract.Interactor.Output output) {
        SharedPreferences sharedPreferences = MainApplication.appContext.getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", null).apply();
        output.onLogoutSuccess();
    }
}
