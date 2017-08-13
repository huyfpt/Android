package com.a1.demo16.intent_student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView txtInfor, txt2, txt3, txt4, txt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txtInfor = (TextView) findViewById(R.id.textView);
        txt2 = (TextView) findViewById(R.id.textView2);
        txt3 = (TextView) findViewById(R.id.textView3);
        txt4 = (TextView) findViewById(R.id.textView4);
        txt5 = (TextView) findViewById(R.id.textView5);
        Intent intent = getIntent();

//        Student student = (Student) intent.getSerializableExtra("data"); get object 1
//    txtInfor.setText(student.name +"   "+ student.age);
        Bundle bundle = intent.getBundleExtra("data");// phải đúng tên mình đặt trong intent.putExtra("data", bundle);
        // bundle = nullpoint -> đặt if !=null cho String...
        if (bundle != null) {
            String program = bundle.getString("keyString"); // get đúng tên key bên MainActivity khai báo
            String[] num = bundle.getStringArray("arrayName");//bundle.putStringArray("arrayName", array);
            Student student =(Student)bundle.getSerializable("Object");//bundle.putSerializable("Object",student);
            txtInfor.setText(program);
            txt2.setText(num[0]);
            txt3.setText(student.name);
        }
    }
}
