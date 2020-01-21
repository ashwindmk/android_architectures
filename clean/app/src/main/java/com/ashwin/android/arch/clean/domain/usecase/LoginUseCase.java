package com.ashwin.android.arch.clean.domain.usecase;

import com.ashwin.android.arch.clean.domain.model.User;
import com.ashwin.android.arch.clean.domain.repository.UserRepository;

public class LoginUseCase {
    private UserRepository userRepository;

    public LoginUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isLoggedIn() {
        return userRepository.getCurrentUser() != null;
    }

    public User getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public boolean login(String username, String password) {
        User user = userRepository.getUser(username, password);
        if (user != null) {
            userRepository.setUser(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean logout() {
        if (isLoggedIn()) {
            userRepository.setUser(null);
            return true;
        }
        return false;
    }
}
