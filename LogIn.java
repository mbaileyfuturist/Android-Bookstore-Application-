package com.example.michealbailey.intrinsicbookstore;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void logIn(View view) {
        EditText usernameText = (EditText) findViewById(R.id.Username_Input);
        EditText passwordText = (EditText) findViewById(R.id.Password_Input);
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        if (username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
            Log.i("Error", "input was empty.");
            Toast.makeText(this, "Please enter input.", Toast.LENGTH_LONG).show();
        } else {
            try {
                SQLiteDatabase dataBaseObj = this.openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);
                //Search for employee.
                Cursor myCursor = dataBaseObj.rawQuery("SELECT * FROM employees", null);
                int columnIndex1 = myCursor.getColumnIndex("user_name");
                int columnIndex2 = myCursor.getColumnIndex("password");

                myCursor.moveToFirst();
                while (myCursor != null) {
                    String usernameMatch = myCursor.getString(columnIndex1);
                    String passwordMatch = myCursor.getString(columnIndex2);
                    if (usernameMatch.equalsIgnoreCase(username) && passwordMatch.equals(password)) {
                        Intent next = new Intent(this, MainMenu.class);
                        startActivity(next);
                    }
                    myCursor.moveToNext();
                }
                if (myCursor == null) {
                    Log.i("Error", "User credentials do not exist.");
                    Toast.makeText(this, "Unsuccessfull login.", Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                Log.i("Error", e.getMessage());
            }
        }
    }
}
