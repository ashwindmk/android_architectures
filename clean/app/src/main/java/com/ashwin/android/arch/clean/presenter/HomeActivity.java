package com.ashwin.android.arch.clean.presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ashwin.android.arch.clean.R;
import com.ashwin.android.arch.clean.data.datasource.cache.CacheUserDataSource;
import com.ashwin.android.arch.clean.data.datasource.remote.RemoteUserDataSource;
import com.ashwin.android.arch.clean.data.repository.UserRepositoryImpl;
import com.ashwin.android.arch.clean.domain.repository.UserRepository;
import com.ashwin.android.arch.clean.domain.usecase.LoginUseCase;

public class HomeActivity extends AppCompatActivity {
    private LoginUseCase loginModel;

    private TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initData(getApplicationContext());

        initViews();
    }

    private void initData(Context context) {
        UserRepository userRepository = new UserRepositoryImpl(new CacheUserDataSource(context), new RemoteUserDataSource(context));
        loginModel = new LoginUseCase(userRepository);
    }

    private void initViews() {
        usernameTextView = (TextView) findViewById(R.id.username_textview);
        usernameTextView.setText("Welcome " + loginModel.getCurrentUser().getName());
    }

    public void logout(View view) {
        loginModel.logout();
        goLogin();
    }

    private void goLogin() {
        Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
