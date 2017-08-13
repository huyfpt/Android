package com.demo14.demo_gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
GridView grV;
ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    grV = (GridView) findViewById(R.id.gridViewBook);
        arrayList = new ArrayList<String>();
        arrayList.add("math");
        arrayList.add("love");
        arrayList.add("like");
        arrayList.add("trick");
        arrayList.add("treat");
        arrayList.add("cheat");
        arrayList.add("beat");
        arrayList.add("best");
        arrayList.add("bicth");
        arrayList.add("cake");
        arrayList.add("kay");
        arrayList.add("say");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        grV.setAdapter(adapter);

        grV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, arrayList.get(position)+"   "+ position, Toast.LENGTH_LONG).show();
            }
        });
    }
}
