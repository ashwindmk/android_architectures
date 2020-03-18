package com.ashwin.android.arch.mvvm.model;

import androidx.annotation.NonNull;

public class User {
    private String username;
    private String name;
    private String birthdate;

    public User(String u, String n, String dob) {
        username = u;
        name = n;
        birthdate = dob;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    @NonNull
    @Override
    public String toString() {
        return "Username: " + username + "\nName: " + name + "\nDOB: " + birthdate;
    }
}
