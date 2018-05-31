package com.example.michealbailey.intrinsicbookstore;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        //Reference to input fields.
        EditText usernameText = (EditText) findViewById(R.id.Username_Input);
        EditText passwordText = (EditText) findViewById(R.id.Password_Input);

        //Grab the input and store them into strings.
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        /*If the username and password are empty then prompt the user to enter the correct input.
          Otherwise check the employees database for the credentials entered.*/
        if (username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
            Log.i("Error", "input was empty.");
            Toast.makeText(this, "Please enter input.", Toast.LENGTH_LONG).show();
        }

        try {
                //Open the myDatabase database.
                SQLiteDatabase dataBaseObj = this.openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);

                //Grab the username and password columns.
                Cursor myCursor = dataBaseObj.rawQuery("SELECT * FROM employees", null);
                int columnIndex1 = myCursor.getColumnIndex("user_name");
                int columnIndex2 = myCursor.getColumnIndex("password");

                boolean found = false;
                myCursor.moveToFirst();

                //Search for the employee credentials.
                while (myCursor != null) {
                    //Get the Strings stored within row n of columns user_name and password
                    String usernameMatch = myCursor.getString(columnIndex1);
                    String passwordMatch = myCursor.getString(columnIndex2);

                    /*If the username and password has been has been found in the database then take
                    take them to the main menu*/
                    if (usernameMatch.equalsIgnoreCase(username) && passwordMatch.equals(password)) {
                        found = true;
                        Intent toMainMenu = new Intent(this, MainMenu.class);
                        startActivity(toMainMenu);
                        dataBaseObj.close();
                    }
                    myCursor.moveToNext();
                }

                //If the username and password have'nt been found then toast unsuccessful message.
                if (!found) {
                    Log.i("Error", "User credentials do not exist.");
                    Toast.makeText(this, "Unsuccessfull login, try again.", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.i("Error", e.getMessage());
        }
    }
}
