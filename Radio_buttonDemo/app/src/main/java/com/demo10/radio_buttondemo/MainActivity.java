package com.demo10.radio_buttondemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup =(RadioGroup)findViewById(R.id.RDG_Daytime);
        layout = (RelativeLayout)findViewById(R.id.layoutParent);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId: return radio's id button ứa checked
                switch(checkedId){
                    case R.id.radioButtonMr : layout.setBackgroundColor(Color.rgb(19,13,155));//rgb trộn màu
                        break;
                    case R.id.radioButtonNoon: layout.setBackgroundColor(Color.DKGRAY);
                        break;
                    case R.id.radioButtonANoon: layout.setBackgroundColor(Color.CYAN);
                }
            }
        });
    }
}
