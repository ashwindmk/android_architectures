package com.ashwin.android.arch.mvi.view;

// Takes intent from view and converts it into state
public interface Presenter {
    void onAttachView();
    void onDetachView();
}
