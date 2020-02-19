package com.ashwin.android.arch.mvi.intent;

import com.ashwin.android.arch.mvi.model.State;

public class LogoutIntent implements Intent {
    private State currState;

    public LogoutIntent(State currState) {
        this.currState = currState;
    }

    public State getCurrState() {
        return currState;
    }
}
