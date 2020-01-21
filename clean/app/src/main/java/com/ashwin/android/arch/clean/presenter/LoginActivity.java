package com.ashwin.android.arch.clean.presenter;

import androidx.appcompat.app.AppCompatActivity;
import com.ashwin.android.arch.clean.R;
import com.ashwin.android.arch.clean.data.datasource.cache.CacheUserDataSource;
import com.ashwin.android.arch.clean.data.datasource.remote.RemoteUserDataSource;
import com.ashwin.android.arch.clean.data.repository.UserRepositoryImpl;
import com.ashwin.android.arch.clean.domain.repository.UserRepository;
import com.ashwin.android.arch.clean.domain.usecase.LoginUseCase;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private LoginUseCase loginModel;

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initData(getApplicationContext());

        if (loginModel.isLoggedIn()) {
            goHome();
        } else {
            initViews();
        }
    }

    private void initData(Context context) {
        UserRepository userRepository = new UserRepositoryImpl(new CacheUserDataSource(context), new RemoteUserDataSource(context));
        loginModel = new LoginUseCase(userRepository);
    }

    private void initViews() {
        usernameEditText = (EditText) findViewById(R.id.username_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
    }

    public void login(View v) {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Boolean doInBackground(String... params) {
                String username = params[0];
                String password = params[1];
                return loginModel.login(username, password);
            }

            @Override
            protected void onPostExecute(Boolean valid) {
                if (valid) {
                    goHome();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid login", Toast.LENGTH_LONG).show();
                }
            }
        }.execute(username, password);
    }

    public void goHome() {
        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(homeIntent);
        finish();
    }
}
