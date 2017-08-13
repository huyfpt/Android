package com.a1.demo18.dialog_customer;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLog = (Button) findViewById(R.id.buttonLog);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Please input your username and password");
                dialog.setContentView(R.layout.layout_dialog); //set 1 cai layout for customer
                dialog.setCanceledOnTouchOutside(false);// function này giúp ng dùng k bị out ra dialog khi bấm ngoài màn hình dialog
                final EditText edtUser = (EditText) dialog.findViewById(R.id.editTextUser);
                final EditText edtPass = (EditText) dialog.findViewById(R.id.editTextPass);
                Button btnLog = (Button) dialog.findViewById(R.id.buttonLogin);
                Button btnCancel = (Button) dialog.findViewById(R.id.buttonCancel);
                dialog.show();
                btnLog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = edtUser.getText().toString();
                        String pass = edtPass.getText().toString();
                        if(user.equals("huy")&&pass.equals("123")){
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();// khi login success dialog sẽ ẩn đi
                        }else {
                            Toast.makeText(MainActivity.this, "Login Fair", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

}
