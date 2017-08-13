package com.a1.demo12.redemo_listview_with_adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView lvItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItem = (ListView)findViewById(R.id.listView);
        ArrayList<Products> productses = new ArrayList<Products>();
        productses.add(new Products("Iphone 6+","ISO", 15000000, R.drawable.ip6s));
        productses.add(new Products("SamSung J7","Android", 30000000, R.drawable.j7));
        productses.add(new Products("Lumia 5","Mircosoft", 70000000, R.drawable.lumia1));
        productses.add(new Products("BlackBeary","BlackBeary", 60000000, R.drawable.bb1));
        productses.add(new Products("Iphone 6","ISO", 38999999, R.drawable.ip5));
        productses.add(new Products("Iphone 5 plus","ISO", 21399999, R.drawable.iphone5s));
        ProductAdapter adapter = new ProductAdapter(
                MainActivity.this,
                R.layout.product_items_view,
                productses
        );
        lvItem.setAdapter(adapter);
    }
}
