package com.example.michealbailey.intrinsicbookstore;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    //Login function that takes the user to the login screen.
    public void goToLogIn(View view){
            Intent toLogin = new Intent(this, LogIn.class);
            startActivity(toLogin);
    }

    //Sign up function that takes the user to the sign up screen.
    public void goToSignUp(View view){
            Intent toSignUp = new Intent(this, CreateAccount.class);
            startActivity(toSignUp);
    }
}
