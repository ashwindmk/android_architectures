package com.ashwin.android.arch.viper.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwin.android.arch.viper.R;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    private HomeContract.Presenter homePresenter;

    private TextView userTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String username = getIntent().getStringExtra("username");

        homePresenter = new HomePresenter();
        homePresenter.onAttachView(this);

        userTextView = (TextView) findViewById(R.id.user_textview);

        homePresenter.init(username);
    }

    public void logout(View view) {
        homePresenter.logout();
    }

    @Override
    public Activity getActivity() {
        return HomeActivity.this;
    }

    @Override
    public void onSuccess(final String user) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                userTextView.setText(user);
            }
        });
    }

    @Override
    public void onFailure(final String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(HomeActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.onDetachView();
    }
}
