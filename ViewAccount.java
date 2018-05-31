package com.example.michealbailey.intrinsicbookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);
    }

    //function used to take the user to the salesHistory screen.
    public void salesHistory(View view){
        Intent salesHistory = new Intent(this, SalesHistory.class);
        startActivity(salesHistory);
    }
}
