package com.ashwin.android.arch.viper.home;

import com.ashwin.android.arch.viper.entity.User;

public class HomePresenter implements HomeContract.Presenter, HomeContract.Interactor.Output {
    private HomeContract.View view;
    private HomeContract.Interactor.Input interactor;
    private HomeContract.Router router;

    public HomePresenter() {
        interactor = new HomeInteractor();
        router = new HomeRouter();
    }

    @Override
    public void onAttachView(HomeContract.View v) {
        view = v;
    }

    @Override
    public void init(String username) {
        interactor.getUser(username, this);
    }

    @Override
    public void logout() {
        interactor.logout(this);
    }

    @Override
    public void onSuccess(User user) {
        if (view != null) {
            view.onSuccess(user.toString());
        }
    }

    @Override
    public void onFailure(String error) {
        if (view != null) {
            view.onFailure(error);
        }
    }

    @Override
    public void onLogoutSuccess() {
        if (view != null) {
            router.logout(view.getActivity());
        }
    }

    @Override
    public void onDetachView() {
        view = null;
    }
}
