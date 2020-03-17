package com.ashwin.android.arch.viper.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ashwin.android.arch.viper.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private LoginContract.Presenter loginPresenter;

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter();
        loginPresenter.onAttachView(this);

        usernameEditText = (EditText) findViewById(R.id.username_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);

        loginPresenter.init();
    }

    public void login(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        loginPresenter.login(username, password);
    }

    @Override
    public Activity getActivity() {
        return LoginActivity.this;
    }

    @Override
    public void onLoginError(final String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDetachView();
    }
}
