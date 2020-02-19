package com.ashwin.android.arch.mvi.view;

import android.content.Context;
import android.content.SharedPreferences;

import com.ashwin.android.arch.mvi.MainApplication;
import com.ashwin.android.arch.mvi.intent.Intent;
import com.ashwin.android.arch.mvi.intent.LogoutIntent;
import com.ashwin.android.arch.mvi.model.LoggedOutState;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

public class HomePresenter implements Presenter {
    private View homeView;

    private PublishSubject<Intent> observable;
    private DisposableObserver<Intent> observer;

    HomePresenter(View v) {
        homeView = v;
    }

    @Override
    public void onAttachView() {
        observable = homeView.intent();

        observer = new DisposableObserver<Intent>() {
            @Override
            public void onNext(Intent intent) {
                if (intent instanceof LogoutIntent) {
                    SharedPreferences sharedPreferences = MainApplication.appContext.getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString("username", null).apply();
                    homeView.render(new LoggedOutState());
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };

        observable.subscribe(observer);
    }

    @Override
    public void onDetachView() {
        observer.dispose();
    }
}
