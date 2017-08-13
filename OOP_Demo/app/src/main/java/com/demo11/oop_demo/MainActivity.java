package com.demo11.oop_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Animal animal = new Animal();
//        animal.name = "Dog";
//        animal.color ="yellow";
//        animal.SetFoot(111);
//        Toast.makeText(MainActivity.this, "Animal:  " + animal.name+"  Color:   " +animal.color+"  Foot:  " + animal.GetFoot() , Toast.LENGTH_LONG).show();

    }
}
