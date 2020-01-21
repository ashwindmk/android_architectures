package com.ashwin.android.arch.clean.data.datasource;

import com.ashwin.android.arch.clean.domain.model.User;

public interface UserDataSource {
    User get();
    void set(User user);
    User get(String username, String password);
}
