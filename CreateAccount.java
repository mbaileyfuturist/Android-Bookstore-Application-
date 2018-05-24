package com.example.michealbailey.intrinsicbookstore;

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
        //Insert values into employee table.
    }
}
