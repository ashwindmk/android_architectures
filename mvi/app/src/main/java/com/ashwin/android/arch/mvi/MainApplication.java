package com.ashwin.android.arch.mvi;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
