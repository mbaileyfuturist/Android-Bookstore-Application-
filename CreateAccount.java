package com.example.michealbailey.intrinsicbookstore;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateAccount extends AppCompatActivity {

    private String firstName, lastName, userName, password, email, employeeType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //Reference to EditTexts.
        EditText editFirstName = findViewById(R.id.fname_input);
        EditText editLastName = findViewById(R.id.lname_input);
        EditText editUserName = findViewById(R.id.uname_input);
        EditText editPassword = findViewById(R.id.pword_input);
        EditText editEmail = findViewById(R.id.email_input);

        //Grabbing input and putting into Strings.
        firstName = editFirstName.getText().toString();
        lastName = editLastName.getText().toString();
        userName = editUserName.getText().toString();
        password = editPassword.getText().toString();
        email = editEmail.getText().toString();

        //Reference to spinner
        Spinner employeeSpinner = (Spinner)findViewById(R.id.employee_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.employee_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employeeSpinner.setAdapter(adapter);
        employeeType = employeeSpinner.getSelectedItem().toString();
    }

    public void createAccount(View view){
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put("firstName", firstName);
        dataToInsert.put("lastName", lastName);
        dataToInsert.put("userName", userName);
        dataToInsert.put("password", password);
        dataToInsert.put("email", email);
        dataToInsert.put("employeeType", employeeType);

        try {
            //Open database.
            SQLiteDatabase dataBaseObj = this.openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);
            //Insert new employee into table.
            dataBaseObj.insert("employees", null, dataToInsert);
            Log.i("Sucesss", "New employee successfully inserted.");
            Intent next = new Intent(this, MainMenu.class);
            startActivity(next);
        }catch(Exception e){
            Log.i("Error", "Unsuccessful insert of new Employee.");
        }
    }
}
