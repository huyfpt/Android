package com.demo21.animation_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgAlpha, imgScale,imgTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgAlpha = (ImageView) findViewById(R.id.imageViewAlpha);
        imgScale = (ImageView)findViewById(R.id.imageViewScale);
        imgTranslate = (ImageView)findViewById(R.id.imageViewTranslate);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        final Animation animAlpha1 = AnimationUtils.loadAnimation(this, R.anim.scale_anim);
        final Animation animAlpha2 = AnimationUtils.loadAnimation(this, R.anim.translate);
        imgAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            imgAlpha.startAnimation(animAlpha);
            }
        });
        imgScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgScale.startAnimation(animAlpha1);
            }
        });
        imgTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgTranslate.startAnimation(animAlpha2);
            }
        });
    }
}
