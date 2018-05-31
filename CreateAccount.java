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
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void createAccount(View view){
        try {

            //Reference to EditTexts.
            EditText editFirstName = findViewById(R.id.fname_input);
            EditText editLastName = findViewById(R.id.lname_input);
            EditText editUserName = findViewById(R.id.uname_input);
            EditText editPassword = findViewById(R.id.pword_input);
            EditText editEmail = findViewById(R.id.email_input);
            EditText editEmployeeID = findViewById(R.id.employee_id);

            //Grabbing input from references and putting into Strings.
            String firstName = editFirstName.getText().toString();
            String lastName = editLastName.getText().toString();
            String employeeID = editEmployeeID.getText().toString();
            String userName = editUserName.getText().toString();
            String password = editPassword.getText().toString();
            String email = editEmail.getText().toString();

            //Reference to spinner
            Spinner employeeSpinner = (Spinner)findViewById(R.id.employee_spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.employee_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            employeeSpinner.setAdapter(adapter);

            //Grabbing input from spinner reference and putting it into a string.
            String employeeType = employeeSpinner.getSelectedItem().toString();

            //Content values for new employee to insert into database.
            ContentValues dataToInsert = new ContentValues();
            dataToInsert.put("first_name", firstName);
            dataToInsert.put("last_name", lastName);
            dataToInsert.put("user_name", userName);
            dataToInsert.put("password", password);
            dataToInsert.put("employee_id", employeeID);
            dataToInsert.put("email", email);
            dataToInsert.put("employee_type", employeeType);


            //Open database.
            SQLiteDatabase dataBaseObj = this.openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);

            //Insert new employees into table.
            dataBaseObj.insert("employees", null, dataToInsert);

            //If successfull insert log to the consol and toast to the screen.
            Log.i("Sucesss", "New employee successfully inserted.");
            Toast.makeText(this, "employee successfully added.", Toast.LENGTH_LONG).show();

            //Then move on to the next activity.
            Intent next = new Intent(this, MainMenu.class);
            startActivity(next);
        }catch(Exception e){
            Log.i("Error", e.getMessage());
        }
    }
}
