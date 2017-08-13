package com.demo14.demo_gridview_image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
GridView grV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    grV = (GridView)findViewById(R.id.gridView);
        ArrayList<Customer> cus = new ArrayList<Customer>();
        cus.add(new Customer(R.drawable.dr));
        cus.add(new Customer(R.drawable.fb));
        cus.add(new Customer(R.drawable.fg));
        cus.add(new Customer(R.drawable.jb));
        cus.add(new Customer(R.drawable.nm));
        cus.add(new Customer(R.drawable.ns));
        cus.add(new Customer(R.drawable.rr));
        cus.add(new Customer(R.drawable.tg));
        cus.add(new Customer(R.drawable.wampy));
        CustomerAdapter adapter = new CustomerAdapter(
                MainActivity.this,
                R.layout.customer_view,
                cus);
        grV.setAdapter(adapter);
    }
}
