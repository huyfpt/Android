package com.a1.demo16.intent_student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btnSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    btnSP = (Button)findViewById(R.id.button);
        btnSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, SecondActivity.class);
                Student student = new Student("Huy",12);
//                intent.putExtra("data", student); // truyen object 1
                String[] array ={"1","2","3"};
                Bundle bundle = new Bundle(); // truyền tất cả loại data : int, String , array, object(serializable)
                bundle.putString("keyString", "Value");
                bundle.putInt("keyInt", 123);
                bundle.putStringArray("arrayName", array);
                bundle.putSerializable("Object",student);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
    }
}
