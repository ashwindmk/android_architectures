package com.ashwin.android.arch.mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ashwin.android.arch.mvvm.R;
import com.ashwin.android.arch.mvvm.model.User;
import com.ashwin.android.arch.mvvm.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity {
    private HomeViewModel homeViewModel;
    private LiveData<User> userLiveData;

    private TextView userTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userTextView = (TextView) findViewById(R.id.user_textview);

        homeViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(HomeViewModel.class);

        userLiveData = homeViewModel.getUser();

        userLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user == null) {
                    goLogin();
                } else {
                    userTextView.setText(user.toString());
                }
            }
        });
    }

    public void logout(View view) {
        homeViewModel.logout();
    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
