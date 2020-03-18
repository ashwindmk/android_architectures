package com.ashwin.android.arch.mvvm.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ashwin.android.arch.mvvm.model.User;
import com.ashwin.android.arch.mvvm.model.UserRepository;

public class HomeViewModel extends AndroidViewModel {
    private Context appContext;

    private UserRepository userRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        appContext = application.getApplicationContext();

        userRepository = new UserRepository(appContext);
    }

    public LiveData<User> getUser() {
        return userRepository.getUser();
    }

    public void logout() {
        userRepository.logout();
    }
}
