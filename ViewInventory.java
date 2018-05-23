package com.example.michealbailey.intrinsicbookstore;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewInventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);

        ListView list = (ListView)findViewById(R.id.BookList);
        ArrayList<String> bookArray = new ArrayList<String>();
        String bookTitle = "", isbnNumber = "", genre = "", retail = "", wholesale = "", author = "";
        int bookNumber = 1;
        try {
            //Access the Database.
            SQLiteDatabase dataBaseObj = this.openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);
            Cursor cursor = dataBaseObj.rawQuery("SELECT * FROM book_inventory", null);
            int columnIndex1 = cursor.getColumnIndex("isbn");
            int columnIndex2 = cursor.getColumnIndex("title");
            int columnIndex3 = cursor.getColumnIndex("author");
            //int columnIndex4 = cursor.getColumnIndex("publisher");
            int columnIndex5 = cursor.getColumnIndex("genre");
            int columnIndex6 = cursor.getColumnIndex("retail_price");
            int columnIndex7 = cursor.getColumnIndex("wholesale_price");
            //Extract all data and store into an ArrayList.
            cursor.moveToFirst();
            while (cursor != null) {
                Log.i("hi", "," + bookNumber);
                    isbnNumber = cursor.getString(columnIndex1);
                    bookTitle = cursor.getString(columnIndex2);
                    author = cursor.getString(columnIndex3);
                    genre = cursor.getString(columnIndex5);
                    retail = cursor.getString(columnIndex6);
                    wholesale = cursor.getString(columnIndex7);
                    bookArray.add(Integer.toString(bookNumber) + ": " + bookTitle + "     tisbn: " + isbnNumber);
                    bookArray.add("author: " + author + "     genre: " + genre);
                    bookArray.add("wholesale: $" +  wholesale + "     retial: $" + retail);
                    Log.i("title", bookTitle);
                    bookNumber++;
                    cursor.moveToNext();
            }
        } catch (Exception e) {
            Log.i("Error", e.getMessage());
        }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookArray){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.GRAY);

                // Generate ListView Item using TextView
                return view;
            }
        };
        list.setAdapter(listAdapter);
    }
}
