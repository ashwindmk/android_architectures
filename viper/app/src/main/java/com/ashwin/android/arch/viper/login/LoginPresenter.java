package com.ashwin.android.arch.viper.login;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.Interactor.Output {
    private LoginContract.View view;
    private LoginInteractor interactor;
    private LoginRouter router;

    public LoginPresenter() {
        interactor = new LoginInteractor();
        router = new LoginRouter();
    }

    @Override
    public void init() {
        if (view != null) {
            String username = interactor.getLoggedinUser();
            if (username != null) {
                router.login(view.getActivity(), username);
            }
        }
    }

    @Override
    public void login(String username, String password) {
        if (view != null) {
            interactor.login(username, password, this);
        }
    }

    @Override
    public void onAttachView(LoginContract.View v) {
        view = v;
    }

    @Override
    public void onSuccess(String username) {
        if (view != null) {
            router.login(view.getActivity(), username);
        }
    }

    @Override
    public void onFailure(String error) {
        if (view != null) {
            view.onLoginError(error);
        }
    }

    @Override
    public void onDetachView() {
        view = null;
    }
}
