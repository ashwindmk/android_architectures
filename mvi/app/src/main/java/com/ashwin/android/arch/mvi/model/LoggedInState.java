package com.ashwin.android.arch.mvi.model;

import android.os.Parcel;

public class LoggedInState implements State {
    private String username;

    public LoggedInState(String u) {
        username = u;
    }

    public String getUsername() {
        return username;
    }

    protected LoggedInState(Parcel in) {
        username = in.readString();
    }

    public static final Creator<LoggedInState> CREATOR = new Creator<LoggedInState>() {
        @Override
        public LoggedInState createFromParcel(Parcel in) {
            return new LoggedInState(in);
        }

        @Override
        public LoggedInState[] newArray(int size) {
            return new LoggedInState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
    }
}
