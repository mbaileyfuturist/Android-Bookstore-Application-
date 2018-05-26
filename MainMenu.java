package com.example.michealbailey.intrinsicbookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void goToPurchaseTransaction(View view){
        Intent next1 = new Intent(this, PurchaseTransaction.class);
        startActivity(next1);
    }

    public void goToInventoryMenu(View view){
        Intent next2 = new Intent(this, InventoryMenu.class);
        startActivity(next2);
    }

    public void logOut(View view){
        Intent next = new Intent(this, HomeScreen.class);
        startActivity(next);
    }
}
