package com.ardoirdtest.androidform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtactivity, edtlocal, edtdate, edttime, edtname;
    Button btnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnsubmit = (Button) findViewById(R.id.check);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtactivity = (EditText) findViewById(R.id.edtActivity);
                edtlocal = (EditText) findViewById(R.id.edtLocation);
                edtdate = (EditText) findViewById(R.id.edtDate);
                edttime = (EditText) findViewById(R.id.edtTime);
                edtname = (EditText) findViewById(R.id.edtName);
                String s1 = edtactivity.getText().toString().trim();
                String s2 = edtdate.getText().toString();

                String s4 = edtname.getText().toString();
                if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)||TextUtils.isEmpty(s4)) {
                    edtactivity.setError("Text file not nul");
                    edtdate.setError("Your Date not nul");
                    edtname.setError("Text file not nul");
                }

            }
        });
    }
}
