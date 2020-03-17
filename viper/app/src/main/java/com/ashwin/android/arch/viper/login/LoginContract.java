package com.ashwin.android.arch.viper.login;

import android.app.Activity;

public interface LoginContract {
    interface View {
        Activity getActivity();
        void onLoginError(String error);
    }

    interface Presenter {
        void onAttachView(View view);
        void onDetachView();
        void init();
        void login(String username, String password);
    }

    interface Interactor {
        // Interactor
        interface Input {
            String getLoggedinUser();
            void login(String username, String password, Output output);
        }

        // Presenter
        interface Output {
            void onSuccess(String username);
            void onFailure(String error);
        }
    }

    interface Router {
        void login(Activity activity, String username);
    }
}
