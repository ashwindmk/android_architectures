package com.ashwin.android.arch.mvi.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.ashwin.android.arch.mvi.MainApplication;
import com.ashwin.android.arch.mvi.intent.Intent;
import com.ashwin.android.arch.mvi.intent.LaunchIntent;
import com.ashwin.android.arch.mvi.intent.LoginIntent;
import com.ashwin.android.arch.mvi.model.LaunchState;
import com.ashwin.android.arch.mvi.model.LoggedInState;
import com.ashwin.android.arch.mvi.model.LoggedOutState;
import com.ashwin.android.arch.mvi.model.LoggingInState;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

public class LoginPresenter implements Presenter {
    private View loginView;

    private PublishSubject<Intent> observable;
    private DisposableObserver<Intent> observer;

    LoginPresenter(View v) {
        loginView = v;
    }

    @Override
    public void onAttachView() {
        observable = loginView.intent();

        observer = new DisposableObserver<Intent>() {
            @Override
            public void onNext(final Intent intent) {
                if (intent instanceof LaunchIntent) {
                    loginView.render(new LaunchState());

                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences sharedPreferences = MainApplication.appContext.getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                            String un = sharedPreferences.getString("username", null);
                            if (un == null) {
                                loginView.render(new LoggedOutState());
                            } else {
                                loginView.render(new LoggedInState(un));
                            }
                        }
                    }, 3000);
                }

                else if (intent instanceof LoginIntent) {
                    loginView.render(new LoggingInState());

                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LoginIntent loginIntent = (LoginIntent) intent;
                            String un = loginIntent.getUsername();
                            String pwd = loginIntent.getPassword();
                            if (un.equals(pwd)) {
                                SharedPreferences sharedPreferences = MainApplication.appContext.getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                                sharedPreferences.edit().putString("username", un).apply();
                                loginView.render(new LoggedInState(un));
                            } else {
                                loginView.render(new LoggedOutState());
                            }
                        }
                    }, 3000);
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
