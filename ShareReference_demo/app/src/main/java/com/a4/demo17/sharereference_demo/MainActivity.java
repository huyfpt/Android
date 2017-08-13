package com.a4.demo17.sharereference_demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtUser, txtPass;
    EditText edtUser, edtPass;
    CheckBox cbRemem;
    Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      Anh xa  --------------------------------
        txtUser = (TextView) findViewById(R.id.textViewUser);
        txtPass = (TextView) findViewById(R.id.textViewPass);
        edtUser = (EditText) findViewById(R.id.editTextUser);
        edtPass = (EditText) findViewById(R.id.editTextPass);
        cbRemem = (CheckBox) findViewById(R.id.checkBoxRemember);
        btnLog = (Button) findViewById(R.id.buttonLog);
//----------------------------------------------
        //khai bao shareRefer
        final SharedPreferences sharedPreferences = getSharedPreferences("DataLog", MODE_PRIVATE);
       //get data
        edtUser.setText(sharedPreferences.getString("username",""));
        edtPass.setText(sharedPreferences.getString("password",""));
        cbRemem.setChecked(sharedPreferences.getBoolean("remember", false));// nhận get true false
        //even button login
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                if(user.equals("huy")&& pass.equals("123")){
                    Toast.makeText(MainActivity.this, "Login success", Toast.LENGTH_SHORT).show();

                    //làm s lưu lại (check remember )
                    if(cbRemem.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", user);
                        editor.putString("password", pass);
                        editor.putBoolean("remember", true);
                        editor.commit();
                    }else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", "");
                        editor.putString("password", "");
                        editor.putBoolean("remember", false);
                       // editor.remove("username"); // remove user login data
                        editor.commit();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
