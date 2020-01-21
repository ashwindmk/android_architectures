package com.ashwin.android.arch.clean.domain.model;

import androidx.annotation.NonNull;

public class User {
    private String username;
    private String password;
    private String name;

    public User(String u, String p, String n) {
        username = u;
        password = p;
        name = n;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "{\"username\": \"" + username + "\", \"password\": \"" + password + "\", \"name\": \"" + name + "\"}";
    }
}
