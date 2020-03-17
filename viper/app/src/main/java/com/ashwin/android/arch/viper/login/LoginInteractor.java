package com.ashwin.android.arch.viper.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.ashwin.android.arch.viper.MainApplication;

public class LoginInteractor implements LoginContract.Interactor.Input {
    @Override
    public String getLoggedinUser() {
        SharedPreferences sharedPreferences = MainApplication.appContext.getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("username", null);
    }

    @Override
    public void login(final String username, final String password, final LoginContract.Interactor.Output output) {
        // Make the network request to authenticate the username and password
        // But I am being lazy and simply doing the following
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && username.equals(password)) {
                    SharedPreferences sharedPreferences = MainApplication.appContext.getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString("username", username).apply();
                    output.onSuccess(username);
                } else {
                    output.onFailure("Invalid username or password");
                }
            }
        }).start();
    }
}
