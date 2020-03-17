package com.ashwin.android.arch.viper.login;

import android.app.Activity;
import android.content.Intent;

import com.ashwin.android.arch.viper.home.HomeActivity;

public class LoginRouter implements LoginContract.Router {
    @Override
    public void login(Activity activity, String username) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra("username", username);
        activity.startActivity(intent);
        activity.finish();
    }
}
