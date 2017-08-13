package com.a1.demo16.intent_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
Button btnSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSwitch = (Button)findViewById(R.id.buttonSWP);
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, condPage.class);
                startActivity(intent);
            }
        });
        Log.d("A123","onCreate.........MainActivity");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("A123","onStart.........MainActivity");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("A123","onResume.........MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("A123","onPause.........MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("A123","onStop.........MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("A123","onDestroy.........MainActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("A123","onRestart.........MainActivity");
    }
}
