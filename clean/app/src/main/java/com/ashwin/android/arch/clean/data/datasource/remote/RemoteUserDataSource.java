package com.ashwin.android.arch.clean.data.datasource.remote;

import android.content.Context;

import com.ashwin.android.arch.clean.data.datasource.UserDataSource;
import com.ashwin.android.arch.clean.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class RemoteUserDataSource implements UserDataSource {
    private User user;
    private Context context;

    public RemoteUserDataSource(Context c) {
        context = c;
    }

    private List<User> load() {
        // Fetch users list from remote server

        // For simplicity, I am creating dummy remote data here
        List<User> userList = new ArrayList<>();
        userList.add(new User("alice", "pass123", "Alice"));
        userList.add(new User("bob", "pass123", "Bob"));
        userList.add(new User("clara", "pass123", "Clara"));
        return userList;
    }

    @Override
    public User get() {
        return null;
    }

    @Override
    public User get(String username, String password) {
        List<User> userList = load();
        if (userList != null && userList.size() > 0) {
            for (User user : userList) {
                if (user.getUsername().equals(username)) {
                    if (user.getPassword().equals(password)) {
                        return user;
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void set(User user) {
        if (user != null) {
            // Pass the updated user to remote server
        }
    }
}
