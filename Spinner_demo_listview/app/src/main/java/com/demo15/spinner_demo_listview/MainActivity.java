package com.demo15.spinner_demo_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Spinner spListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    spListView = (Spinner)findViewById(R.id.spinner);
        final ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Android");
        arrayList.add("ISO");
        arrayList.add("ASUS");
        arrayList.add("Microsoft");
        arrayList.add("ACER");
        arrayList.add("DELL");
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_spinner_item,
                arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spListView.setAdapter(adapter);
    spListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        int first_spinner = 0, first_spinner_counter = 0;
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//            Toast.makeText(MainActivity.this, arrayList.get(position), Toast.LENGTH_LONG).show();
            if (first_spinner_counter < first_spinner) {
                first_spinner_counter++;
            }
            else
            {
                Toast.makeText(parent.getContext(), "The Subject is " +
                        position , Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            return;
        }
    });
    }

}
