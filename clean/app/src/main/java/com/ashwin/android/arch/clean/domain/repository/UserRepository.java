package com.ashwin.android.arch.clean.domain.repository;

import com.ashwin.android.arch.clean.domain.model.User;

public interface UserRepository {
    User getCurrentUser();
    User getUser(String username, String password);
    void setUser(User user);
}
