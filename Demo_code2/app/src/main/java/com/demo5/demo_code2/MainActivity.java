package com.demo5.demo_code2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText edtCong, edtTru;
TextView txtVresult;
Button btnCal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtCong = (EditText)findViewById(R.id.editTextCong);
        edtTru = (EditText)findViewById(R.id.editTextTru);
         txtVresult= (TextView)findViewById(R.id.textView);
        btnCal = (Button) findViewById(R.id.button);
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = edtCong.getText().toString();
                String s2 = edtTru.getText().toString();
                if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)) {
                    edtCong.setError("Your message");
                    edtTru.setError("Your message");
                } else {
                    int a = Integer.parseInt(s1);
                    int b = Integer.parseInt(s2);
                    int sum = (a + b);
                    String str = String.valueOf(sum);
                    txtVresult.setText(str);
                }
            }
        });
    }
}
