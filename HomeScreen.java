package com.example.michealbailey.intrinsicbookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    public void goToLogIn(View view){
            Intent next = new Intent(this, LogIn.class);
            startActivity(next);
    }

    public void goToSignUp(View view){
            Intent next = new Intent(this, CreateAccount.class);
            startActivity(next);
    }
}
