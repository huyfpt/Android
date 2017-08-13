package com.a1.demo19.sqlite_get_image_from_anywhere;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsertPage;

    public static SQLite database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsertPage = (Button) findViewById(R.id.buttonInsert);

        database = new SQLite(this, "Store.sqlite", null, 1);// Store.sqlite is database name
        //Create table Product
        database.QueryData("Create table if not exists Product" +
                " (Id integer Primary key AutoIncrement, ProductName varchar, Price float, ImageProduct Blob)");
        //Type Blob luu trử file
        btnInsertPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertItems.class);
                startActivity(intent);
            }
        });
    }


}
