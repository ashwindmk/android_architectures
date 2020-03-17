package com.ashwin.android.arch.viper.home;

import android.app.Activity;
import android.content.Intent;

import com.ashwin.android.arch.viper.login.LoginActivity;

public class HomeRouter implements HomeContract.Router {
    @Override
    public void logout(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
