package com.a2.demo17.intent_data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    EditText edtText;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtText = (EditText) findViewById(R.id.editText);
        btnConfirm = (Button) findViewById(R.id.button2);
        Intent reciveIntent= getIntent();
        edtText.setText(reciveIntent.getStringExtra("name"));// nhận text view qua edit text
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edtText.getText().toString();
                Intent intentReturn = new Intent();// intentReturn in EditActivity =Intent data
                intentReturn.putExtra("nameData", text);
                setResult(RESULT_OK,intentReturn);//
                finish();// close current activity
            }
        });
    }
}
