package com.ashwin.android.arch.mvi.model;

import android.os.Parcel;

public class LoggingInState implements State {
    private static final String message = "Logging in...";

    public LoggingInState() {
    }

    public String getMessage() {
        return message;
    }

    protected LoggingInState(Parcel in) {
    }

    public static final Creator<LoggingInState> CREATOR = new Creator<LoggingInState>() {
        @Override
        public LoggingInState createFromParcel(Parcel in) {
            return new LoggingInState(in);
        }

        @Override
        public LoggingInState[] newArray(int size) {
            return new LoggingInState[size];
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
