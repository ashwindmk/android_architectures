package com.ashwin.android.arch.mvi.model;

import android.os.Parcel;

public class LoggedOutState implements State {
    private static final String message = "Welcome";

    public LoggedOutState() {
    }

    public String getMessage() {
        return message;
    }

    protected LoggedOutState(Parcel in) {
    }

    public static final Creator<LoggedOutState> CREATOR = new Creator<LoggedOutState>() {
        @Override
        public LoggedOutState createFromParcel(Parcel in) {
            return new LoggedOutState(in);
        }

        @Override
        public LoggedOutState[] newArray(int size) {
            return new LoggedOutState[size];
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
