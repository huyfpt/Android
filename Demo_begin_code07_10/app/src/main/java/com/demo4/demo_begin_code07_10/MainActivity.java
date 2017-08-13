package com.demo4.demo_begin_code07_10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtVSayHi, txtSuject, txtVCalculator;
    Button btnclick,btncong,btntru;
    String s = txtVCalculator.getText().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVSayHi = (TextView)findViewById(R.id.textViewSayHi);
        txtSuject = (TextView)findViewById(R.id.textViewSuject);
        txtVCalculator = (TextView)findViewById(R.id.textView);
        btnclick = (Button)findViewById(R.id.buttonClick);
        btncong = (Button)findViewById(R.id.btnCong);
        btntru = (Button)findViewById(R.id.btnTru);

        int a = Integer.parseInt("0");//Convert String to int
        String.valueOf(123);//Convert Int to String
        txtVSayHi.setText("change words");

                btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =txtVSayHi.getText().toString();
                txtSuject.setText(s);
                    }
        });

        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(s);
                a++;
                String str = String.valueOf(a);
                txtVCalculator.setText(str);
            }
        });
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(s);
                a--;
                String str = String.valueOf(a);
                txtVCalculator.setText(str);
            }
        });
    }
}
