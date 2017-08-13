package com.a2.demo17.intent_data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtText;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtText = (TextView)findViewById(R.id.textView);
        btnEdit = (Button)findViewById(R.id.button);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("name", txtText.getText().toString());//gui text view
                startActivityForResult(intent,7774);
            }
        });
    }
// nhận kết quả từ màn hình khác "protected void onActivityResult"
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode ==7774 && resultCode == RESULT_OK && data !=null){
           String returnName = data.getStringExtra("nameData");
           txtText.setText(returnName);
       }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
