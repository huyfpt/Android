package com.a1.demo16.drawable_clip;

import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.imageView);
        btn = (Button) findViewById(R.id.button);
        final ClipDrawable clipDrawable = (ClipDrawable) img.getDrawable();
        img.setImageLevel(1000);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Handler hder = new Handler(); //android.os
                hder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int currentLevel = clipDrawable.getLevel();//muốn + lên thỳ phải có vị trí hiện tại đển nó có thể lấy đc vị trí hiện tại
                        if (currentLevel > 10000) {
                            currentLevel = 0;
                        }
                        img.setImageLevel(currentLevel + 1000);
                        hder.postDelayed(this, 1000);
                    }
                },2000);//2000 = 2s




            }
        });
    }
}
