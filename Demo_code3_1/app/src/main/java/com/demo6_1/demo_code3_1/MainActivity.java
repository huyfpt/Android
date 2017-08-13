package com.demo6_1.demo_code3_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText edtadd,edtRandom;
    TextView txtNum;
    Button btnAdd,btnRandom;
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    String listNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtadd = (EditText)findViewById(R.id.editTextMin);
        edtRandom = (EditText)findViewById(R.id.editTextMax);
        btnAdd =(Button)findViewById(R.id.buttonAdd);
        btnRandom = (Button)findViewById(R.id.buttonRandom);
        txtNum = (TextView)findViewById(R.id.textView);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = Integer.parseInt(edtadd.getText().toString());
                int max = Integer.parseInt(edtRandom.getText().toString());
                for (int i = min; i<= max; i++){
                    arrayList.add(i);
                }
                Toast.makeText(MainActivity.this, String.valueOf(arrayList.size()), Toast.LENGTH_SHORT).show();
            }
        });
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                if(arrayList.size()>0){
                int range = random.nextInt(arrayList.size());
                int num = arrayList.get(range);
                arrayList.remove(range);
                listNum += num + " - ";
                    arrayList.contains(1);//check tồn tại
//            txtNum.setText(String.valueOf(num));
                txtNum.setText(String.valueOf(listNum));
                }else{
                    Toast.makeText(MainActivity.this, "limmit", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

