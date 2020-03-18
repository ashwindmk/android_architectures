package com.ashwin.android.arch.mvvm.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ashwin.android.arch.mvvm.model.User;
import com.ashwin.android.arch.mvvm.model.UserRepository;

public class LoginViewModel extends AndroidViewModel {
    private Context appContext;
    private UserRepository userRepository;

    private MutableLiveData<User> user = new MutableLiveData<User>();

    public LoginViewModel(@NonNull Application application) {
        super(application);

        appContext = application.getApplicationContext();

        userRepository = new UserRepository(appContext);
    }

    public LiveData<User> getUser() {
        return userRepository.getUser();
    }

    public void login(String username, String password) {
        userRepository.login(username, password);
    }

    public void logout() {
        userRepository.logout();
    }
}
