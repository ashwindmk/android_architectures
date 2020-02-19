package com.ashwin.android.arch.mvi.model;

import android.os.Parcel;

public class LaunchState implements State {
    private static final String message = "Loading...";

    public LaunchState() {
    }

    public String getMessage() {
        return message;
    }

    protected LaunchState(Parcel in) {
    }

    public static final Creator<LaunchState> CREATOR = new Creator<LaunchState>() {
        @Override
        public LaunchState createFromParcel(Parcel in) {
            return new LaunchState(in);
        }

        @Override
        public LaunchState[] newArray(int size) {
            return new LaunchState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
