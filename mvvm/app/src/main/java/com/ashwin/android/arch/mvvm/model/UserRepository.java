package com.ashwin.android.arch.mvvm.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Date;

public class UserRepository {
    private SharedPreferences sharedPreferences;

    private MutableLiveData<User> user;

    public UserRepository(Context context) {
        user = new MutableLiveData<>();

        sharedPreferences = context.getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        if (username != null) {
            String name = sharedPreferences.getString("name", null);
            String birthdate = sharedPreferences.getString("birthdate", null);
            User currentUser = new User(username, name, birthdate);
            user.setValue(currentUser);
        } else {
            user.setValue(null);
        }
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void login(final String username, final String password) {
        // User authentication
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(username) && username.equals(password)) {
                    String name = username.substring(0, 1).toUpperCase() + username.substring(1);
                    String birthdate = String.valueOf(new Date());

                    sharedPreferences.edit()
                            .putString("username", username)
                            .putString("name", name)
                            .putString("birthdate", birthdate)
                            .apply();

                    final User newUser = new User(username, name, birthdate);

                    // LiveData must be updated in the main thread
                    new Handler(Looper.getMainLooper())
                        .post(new Runnable() {
                            @Override
                            public void run() {
                                user.setValue(newUser);
                            }
                        });
                }
            }
        }).start();
    }

    public void logout() {
        sharedPreferences.edit()
                .putString("username", null)
                .putString("name", null)
                .putString("birthdate", null)
                .apply();
        user.setValue(null);
    }
}
