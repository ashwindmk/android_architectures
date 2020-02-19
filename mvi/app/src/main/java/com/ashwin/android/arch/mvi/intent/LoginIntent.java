package com.ashwin.android.arch.mvi.intent;

import com.ashwin.android.arch.mvi.model.State;

public class LoginIntent implements Intent {
    private State currState;
    private String username;
    private String password;

    public LoginIntent(State state, String un, String pwd) {
        currState = state;
        username = un;
        password = pwd;
    }

    public State getCurrState() {
        return currState;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
