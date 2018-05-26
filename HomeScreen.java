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
        try {
            //Open database.
            SQLiteDatabase dataBaseObj = this.openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);
            Log.i("Success", "values successfully inserted into table");
        }catch(Exception e){
            Log.i("Error", e.getMessage());
        }
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
