package com.ashwin.android.arch.mvi.view;

import com.ashwin.android.arch.mvi.intent.Intent;
import com.ashwin.android.arch.mvi.model.State;

import io.reactivex.subjects.PublishSubject;

// Publishes intents
public interface View {
    PublishSubject<Intent> intent();
    void render(State state);
}
