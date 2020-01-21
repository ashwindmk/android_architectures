package com.ashwin.android.arch.clean.data.datasource.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.ashwin.android.arch.clean.data.datasource.UserDataSource;
import com.ashwin.android.arch.clean.domain.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class CacheUserDataSource implements UserDataSource {
    private static final String FILENAME = "mysharedprefs";

    private Context context;
    private SharedPreferences sharedPreferences;

    public CacheUserDataSource(Context c) {
        context = c;
        sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }

    public User get() {
        String str = sharedPreferences.getString("user", "{}");
        try {
            JSONObject json = new JSONObject(str);
            User user = new User(json.getString("username"), json.getString("password"), json.getString("name"));
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User get(String username, String password) {
        User user = get();
        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public void set(User user) {
        sharedPreferences.edit().putString("user", String.valueOf(user)).apply();
    }
}
