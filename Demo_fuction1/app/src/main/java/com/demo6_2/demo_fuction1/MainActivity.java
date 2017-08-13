package com.demo6_2.demo_fuction1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        Button btnNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNotification = (Button)findViewById(R.id.button);

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, ".............", Toast.LENGTH_LONG).show();
                notifi("đ", 4);
                notifi("a" , 5);
                notifi("..........", 7);
            }
        });
    }
    private void notifi(String a, int num){
      Toast.makeText(MainActivity.this," " + a, Toast.LENGTH_LONG).show();
    }
}
