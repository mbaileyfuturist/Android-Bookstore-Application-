package com.example.michealbailey.intrinsicbookstore;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
    }

    public void addBook(View view){
        //References to inputs.
        EditText title = (EditText)findViewById(R.id.title_input);
        EditText author = (EditText)findViewById(R.id.author_Input);
        EditText publisher = (EditText)findViewById(R.id.publisher_Input);
        EditText genre = (EditText)findViewById(R.id.genre_Input);
        EditText isbn = (EditText)findViewById(R.id.isbn_Input);
        EditText wholesale = (EditText)findViewById(R.id.wholesale_input);
        EditText retail = (EditText)findViewById(R.id.retail_Input);

        //Reference to add book btn.
        Button addBook = (Button)findViewById(R.id.add_book_btn);

        //Get text from inputs.
        String newBookTitle = title.getText().toString();
        String newBookAuthor = author.getText().toString();
        String newBookPublisher = publisher.getText().toString();
        String newBookISBN = isbn.getText().toString();
        String newBookGenre = genre.getText().toString();
        String newWholeSale = wholesale.getText().toString();
        String newRetail = retail.getText().toString();

        try {
            //Content values to inser into table. key id the column name value is the column value.
            ContentValues dataToInsert = new ContentValues();
            dataToInsert.put("title", newBookTitle);
            dataToInsert.put("author", newBookAuthor);
            dataToInsert.put("publisher", newBookPublisher);
            dataToInsert.put("isbn", newBookISBN);
            dataToInsert.put("genre", newBookGenre);
            dataToInsert.put("wholesale_price", newWholeSale);
            dataToInsert.put("retail_price", newRetail);
            //Opens database.
            SQLiteDatabase dataBaseObj = this.openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);
            //Inserting update into book_inventory
            dataBaseObj.insert("book_inventory",null,dataToInsert);
            Log.i("Success", "Values were successfully inserted.");
            Toast.makeText(this, "Book successfully added.", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Log.i("Error", e.getMessage());
        }

    }
}
