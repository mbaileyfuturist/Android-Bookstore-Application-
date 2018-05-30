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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PurchaseTransaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_transaction);
    }

    public void searchBook(View view){
        double TAX = 0.06;
        ArrayList<String> bookAttributes = new ArrayList<String>();
        ListView listOfAttributes = (ListView)findViewById(R.id.BookAttributes);
        EditText isbnText = (EditText)findViewById(R.id.Book_ISBN);
        String isbnNumber = isbnText.getText().toString();

        //Search the database for the matching ISBN
        try {
            String titleMatch = "", authorMatch = "", publisherMatch = "", genreMatch = "", retailPriceMatch = "";

            //Open database.
            SQLiteDatabase dataBaseObj = this.openOrCreateDatabase("myDatabase", MODE_PRIVATE, null);
            Cursor cursor = dataBaseObj.rawQuery("SELECT * FROM book_inventory",null);
            int columnIndex1 = cursor.getColumnIndex("isbn");
            int columnIndex2 = cursor.getColumnIndex("title");
            int columnIndex3 = cursor.getColumnIndex("author");
            int columnIndex4 = cursor.getColumnIndex("publisher");
            int columnIndex5 = cursor.getColumnIndex("genre");
            int columnIndex6 = cursor.getColumnIndex("retail_price");
            cursor.moveToFirst();
            while(cursor != null){
                String isbnMatch = cursor.getString(columnIndex1);
                if(isbnMatch.equals(isbnNumber)){

                    titleMatch = cursor.getString(columnIndex2);
                    authorMatch = cursor.getString(columnIndex3);
                    publisherMatch = cursor.getString(columnIndex4);
                    genreMatch = cursor.getString(columnIndex5);
                    retailPriceMatch = cursor.getString(columnIndex6);

                    break;
                }else{
                    Log.i("Error", "Unrecognized input.");
                }
                cursor.moveToNext();
            }

            bookAttributes.add(titleMatch);
            bookAttributes.add(authorMatch);
            bookAttributes.add(publisherMatch);
            bookAttributes.add(genreMatch);
            bookAttributes.add("Sub Total: $" + retailPriceMatch);
            bookAttributes.add("Tax: $" + TAX);
            bookAttributes.add("Total: $" + (Double.parseDouble(retailPriceMatch)+TAX));

            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookAttributes){
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
            listOfAttributes.setAdapter(listAdapter);

        }catch(Exception e){
            Log.i("Exception", e.getMessage());
        }
    }
}
