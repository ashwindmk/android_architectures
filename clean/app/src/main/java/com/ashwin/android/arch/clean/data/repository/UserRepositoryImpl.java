package com.ashwin.android.arch.clean.data.repository;

import com.ashwin.android.arch.clean.data.datasource.cache.CacheUserDataSource;
import com.ashwin.android.arch.clean.data.datasource.remote.RemoteUserDataSource;
import com.ashwin.android.arch.clean.domain.model.User;
import com.ashwin.android.arch.clean.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private CacheUserDataSource cacheUserDataSource;
    private RemoteUserDataSource remoteUserDataSource;

    public UserRepositoryImpl(CacheUserDataSource cacheUserDataSource, RemoteUserDataSource remoteUserDataSource) {
        this.cacheUserDataSource = cacheUserDataSource;
        this.remoteUserDataSource = remoteUserDataSource;
    }

    public User getCurrentUser() {
        return cacheUserDataSource.get();
    }

    public void setUser(User user) {
        cacheUserDataSource.set(user);
    }

    @Override
    public User getUser(String username, String password) {
        return remoteUserDataSource.get(username, password);
    }
}
