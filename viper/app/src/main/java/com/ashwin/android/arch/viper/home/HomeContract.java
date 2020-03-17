package com.ashwin.android.arch.viper.home;

import android.app.Activity;

import com.ashwin.android.arch.viper.entity.User;

public interface HomeContract {
    interface View {
        Activity getActivity();
        void onSuccess(String user);
        void onFailure(String error);
    }

    interface Interactor {
        interface Input {
            void getUser(String username, Output output);
            void logout(Output output);
        }

        interface Output {
            void onSuccess(User user);
            void onFailure(String error);
            void onLogoutSuccess();
        }
    }

    interface Presenter {
        void onAttachView(View v);
        void init(String username);
        void logout();
        void onDetachView();
    }

    interface Router {
        void logout(Activity activity);
    }
}
