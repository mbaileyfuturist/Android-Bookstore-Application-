package com.example.michealbailey.intrinsicbookstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class InventoryMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_menu);

        //ArrayList containing the inventory menu items in String format.
        final ArrayList<String> inventoryItemsArray = new ArrayList<String>();

        //Inserting the items.
        inventoryItemsArray.add("View Inventory");
        inventoryItemsArray.add("Add Book");
        inventoryItemsArray.add("Edit Book");
        inventoryItemsArray.add("Remove Book");
        inventoryItemsArray.add("Return to main menu");

        //Reference to ListView
        final ListView inventoryMenuItems = (ListView) findViewById(R.id.Inventory_Menu_Items);

        //Adapter used to Insert ArrayList into ListView.
        final ArrayAdapter<String> inventoryMenuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, inventoryItemsArray){
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

        //Set the adapter to the ListView.
        inventoryMenuItems.setAdapter(inventoryMenuAdapter);

        //Create intents for each item in the list view.
        final Intent viewInv = new Intent(this, ViewInventory.class);
        final Intent addB = new Intent(this, AddBook.class);
        final Intent removeB = new Intent(this, RemoveBook.class);
        final Intent backToMain = new Intent(this, MainMenu.class);

        //Takes the user to the corresponding item clicked on in the ListView.
        inventoryMenuItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(inventoryItemsArray.get(i).equalsIgnoreCase("View Inventory")){
                    startActivity(viewInv);
                }
                if(inventoryItemsArray.get(i).equalsIgnoreCase("Add Book")){
                    startActivity(addB);
                }
                if(inventoryItemsArray.get(i).equalsIgnoreCase("Remove Book")){
                    startActivity(removeB);
                }
                if(inventoryItemsArray.get(i).equalsIgnoreCase("Return to main menu")){
                    startActivity(backToMain);
                }
            }
        });
    }
}