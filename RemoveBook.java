package com.example.michealbailey.intrinsicbookstore;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RemoveBook extends AppCompatActivity {

    private String isbnToSearch, titleMatch, authorMatch, publisherMatch, genreMatch, retailPriceMatch, wholesalePriceMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_book);
    }

    public void searchBook(View view){
        EditText isbnInput = (EditText)findViewById(R.id.isbnInput);
        isbnToSearch = isbnInput.getText().toString();
        //Textview references.
        TextView title = (TextView)findViewById(R.id.titleID);
        TextView author = (TextView)findViewById(R.id.authorID);
        TextView publisher = (TextView)findViewById(R.id.publisherID);
        TextView genre = (TextView)findViewById(R.id.genreID);
        TextView retailPrice = (TextView)findViewById(R.id.retailID);
        TextView wholesalePrice = (TextView)findViewById(R.id.wholeSaleID);

        try {
            //Opens database.
            SQLiteDatabase dataBaseObj = this.openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);
            Cursor cursor = dataBaseObj.rawQuery("SELECT * FROM book_inventory", null);
            int columnIndex1 = cursor.getColumnIndex("isbn");
            int columnIndex2 = cursor.getColumnIndex("title");
            int columnIndex3 = cursor.getColumnIndex("author");
            int columnIndex4 = cursor.getColumnIndex("publisher");
            int columnIndex5 = cursor.getColumnIndex("genre");
            int columnIndex6 = cursor.getColumnIndex("retail_price");
            int columnIndex7 = cursor.getColumnIndex("wholesale_price");
            cursor.moveToFirst();
            while (cursor != null) {
                String isbnMatch = cursor.getString(columnIndex1);
                if (isbnMatch.equals(isbnToSearch)) {

                    titleMatch = cursor.getString(columnIndex2);
                    authorMatch = cursor.getString(columnIndex3);
                    publisherMatch = cursor.getString(columnIndex4);
                    genreMatch = cursor.getString(columnIndex5);
                    retailPriceMatch = cursor.getString(columnIndex6);
                    wholesalePriceMatch = cursor.getString(columnIndex7);
                    break;
                } else {
                    Log.i("Error", "Unrecognized input.");
                }
                cursor.moveToNext();
            }
        }catch(Exception e){
            Log.i("Error Message", e.getMessage());
        }

        //Set the text to the book attributes that match with the isbn.
        title.setText(titleMatch);
        author.setText(authorMatch);
        publisher.setText(publisherMatch);
        genre.setText(genreMatch);
        retailPrice.setText(retailPriceMatch);
        wholesalePrice.setText(wholesalePriceMatch);

    }
    public void removeBook(View view){
        try {
            //Opens database.
            SQLiteDatabase dataBaseObj = this.openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);
            dataBaseObj.delete("book_inventory", "isbn = '" + isbnToSearch + "'", null);
            Log.i("Success", "Values were successfully removed.");
            Toast.makeText(this, "Book successfully removed.", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Log.i("Error", e.getMessage());
        }
    }
}
