package com.a1.demo16.resource_string;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView txtinfo;
    EditText edtname, edtemail, edtphone;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtinfo = (TextView) findViewById(R.id.textViewinfo2);
        edtname = (EditText) findViewById(R.id.editTextName);
        edtemail = (EditText) findViewById(R.id.editTextEmail);
        edtphone = (EditText) findViewById(R.id.editTextPhone);
        btnConfirm = (Button) findViewById(R.id.buttonCon);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtname.getText().toString();
                String email = edtemail.getText().toString();
                String phone = edtphone.getText().toString();
               // txtinfo.setText("Name: " + name+ "\n" + "Email: " +email+"\n" +"Phone: "+phone);
                // set tranlations for text
                txtinfo.setText(getResources().getString(R.string.Text_ten) + name+ "\n" + "Email: " +email+"\n" +getResources().getString(R.string.Text_phoneNum)+phone);
            }
        });

    }
}
