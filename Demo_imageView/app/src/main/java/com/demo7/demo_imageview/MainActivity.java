package com.demo7.demo_imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imgV;
    Button btnRandom;
    ArrayList<Integer> arrayImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgV = (ImageView)findViewById(R.id.imageView);
        btnRandom = (Button)findViewById(R.id.buttonRandom);
        arrayImage = new ArrayList<Integer>();
//        imgV.setImageResource(R.drawable.);
//        Toast.makeText(MainActivity.this, R.drawable. +"" +Toast.LENGTH_LONG).show();
        AddImange();
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random radom = new Random();
                int range = radom.nextInt(arrayImage.size());
                imgV.setImageResource(arrayImage.get(range));
            }
        });
    }
    public void AddImange(){
        arrayImage.add(R.drawable.r1);
        arrayImage.add(R.drawable.r2);
        arrayImage.add(R.drawable.r3);
        arrayImage.add(R.drawable.r4);
        arrayImage.add(R.drawable.r5);
        arrayImage.add(R.drawable.r6);
        arrayImage.add(R.drawable.r7);
        arrayImage.add(R.drawable.r8);
        arrayImage.add(R.drawable.r9);
    }
}
