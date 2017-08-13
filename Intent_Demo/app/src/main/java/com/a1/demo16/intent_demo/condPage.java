package com.a1.demo16.intent_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class condPage extends Activity {
Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cond_page);
    btnBack =(Button)findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(condPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Log.d("A123","onCreate.........SecondPage");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("A123","onStart.........SecondPage");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("A123","onResume.........SecondPage");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("A123","onPause.........SecondPage");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("A123","onStop.........SecondPage");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("A123","onDestroy.........SecondPage");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("A123","onRestart.........SecondPage");
    }
}
