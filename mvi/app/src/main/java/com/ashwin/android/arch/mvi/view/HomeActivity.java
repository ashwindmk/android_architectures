package com.ashwin.android.arch.mvi.view;

import androidx.appcompat.app.AppCompatActivity;
import com.ashwin.android.arch.mvi.R;
import com.ashwin.android.arch.mvi.intent.Intent;
import com.ashwin.android.arch.mvi.intent.LogoutIntent;
import com.ashwin.android.arch.mvi.model.LoggedInState;
import com.ashwin.android.arch.mvi.model.LoggedOutState;
import com.ashwin.android.arch.mvi.model.State;

import android.os.Bundle;
import android.widget.TextView;

import io.reactivex.subjects.PublishSubject;

public class HomeActivity extends AppCompatActivity implements View {
    private HomePresenter presenter;
    private PublishSubject<Intent> observable;

    private State currState;

    private TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usernameTextView = (TextView) findViewById(R.id.username_textview);

        presenter = new HomePresenter(this);
        presenter.onAttachView();

        android.content.Intent intent = getIntent();
        if (intent.hasExtra("state")) {
            render((State) intent.getParcelableExtra("state"));
        }
    }

    public void logout(android.view.View view) {
        Intent intent = new LogoutIntent(currState);
        observable.onNext(intent);
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
        if (state instanceof LoggedInState) {
            LoggedInState loggedInState = (LoggedInState) state;
            String username = loggedInState.getUsername();
            renderLoggedIn(username);
            currState = state;
        }

        else if (state instanceof LoggedOutState) {
            currState = state;
            renderLoggedOut();
        }
    }

    private void renderLoggedIn(String username) {
        usernameTextView.setText(username);
    }

    private void renderLoggedOut() {
        android.content.Intent intent = new android.content.Intent(HomeActivity.this, LoginActivity.class);
        intent.putExtra("state", currState);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();
    }
}
