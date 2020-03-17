package com.ashwin.android.arch.mvi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ashwin.android.arch.mvi.R;
import com.ashwin.android.arch.mvi.intent.Intent;
import com.ashwin.android.arch.mvi.intent.LaunchIntent;
import com.ashwin.android.arch.mvi.intent.LoginIntent;
import com.ashwin.android.arch.mvi.model.LaunchState;
import com.ashwin.android.arch.mvi.model.LoggedInState;
import com.ashwin.android.arch.mvi.model.LoggedOutState;
import com.ashwin.android.arch.mvi.model.LoggingInState;
import com.ashwin.android.arch.mvi.model.State;

import io.reactivex.subjects.PublishSubject;

public class LoginActivity extends AppCompatActivity implements View {
    private LoginPresenter presenter;
    private PublishSubject<Intent> observable;

    private TextView stateTextView;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private State currState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);

        stateTextView = (TextView) findViewById(R.id.state_textview);
        usernameEditText = (EditText) findViewById(R.id.username_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Username / Password cannot be empty", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new LoginIntent(currState, username, password);
                    observable.onNext(intent);
                }
            }
        });

        presenter.onAttachView();

        // Initial state
        android.content.Intent intent = getIntent();
        if (intent.hasExtra("state")) {
            render((State) intent.getParcelableExtra("state"));
        } else {
            observable.onNext(new LaunchIntent());
        }
    }

    @Override
    public PublishSubject<Intent> intent() {
        if (observable == null) {
            observable = PublishSubject.create();
        }
        return observable;
    }

    @Override
    public void render(State state) {
        if (state instanceof LaunchState) {
            LaunchState launchState = (LaunchState) state;
            String message = launchState.getMessage();
            renderLaunch(message);
            currState = state;
        }

        else if (state instanceof LoggedOutState) {
            LoggedOutState loggedOutState = (LoggedOutState) state;
            renderLoggedOut(loggedOutState.getMessage());
            currState = state;
        }

        else if (state instanceof LoggingInState) {
            LoggingInState loggingInState = (LoggingInState) state;
            renderLoggingIn(loggingInState.getMessage());
            currState = state;
        }

        else if (state instanceof LoggedInState) {
            LoggedInState loggedInState = (LoggedInState) state;
            renderLoggedIn(loggedInState);
        }
    }

    private void renderLaunch(String message) {
        loginButton.setEnabled(false);
        stateTextView.setText(message);
    }

    private void renderLoggedOut(String message) {
        loginButton.setEnabled(true);
        stateTextView.setText(message);
    }

    private void renderLoggingIn(String message) {
        loginButton.setEnabled(false);
        stateTextView.setText(message);
    }

    private void renderLoggedIn(LoggedInState state) {
        android.content.Intent intent = new android.content.Intent(this, HomeActivity.class);
        intent.putExtra("state", state);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();
    }
}
