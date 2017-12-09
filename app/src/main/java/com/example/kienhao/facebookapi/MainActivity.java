package com.example.kienhao.facebookapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    Button logoutButton;
    Button gotoProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fresco.initialize(this);

        logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                goLoginScreen();
            }
        });

        gotoProfileButton = findViewById(R.id.btn_profile);
        gotoProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AccessToken.getCurrentAccessToken() == null) {
                    Intent profileIntent = new Intent(MainActivity.this, LoginActivity
                            .class);
                    startActivity(profileIntent);
                } else {
                    Intent profilentent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(profilentent);
                }
            }
        });

        if (AccessToken.getCurrentAccessToken() == null){
            goLoginScreen();
        }
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
