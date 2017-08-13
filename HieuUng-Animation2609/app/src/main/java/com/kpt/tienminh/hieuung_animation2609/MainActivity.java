package com.kpt.tienminh.hieuung_animation2609;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgAlpha, imgScale, imgTranslate, imgRotate, imgZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        final Animation animZoom = AnimationUtils.loadAnimation(this, R.anim.anim_zoom_in_rotate);

        imgZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animZoom);
            }
        });

        imgRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animRotate);
            }
        });

        imgTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
            }
        });

        imgScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animScale);
            }
        });

        imgAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                imgAlpha.startAnimation(animAlpha);
                view.startAnimation(animAlpha);
            }
        });
    }

    private void AnhXa() {
        imgRotate       = (ImageView) findViewById(R.id.imageviewRotate);
        imgAlpha        = (ImageView) findViewById(R.id.imageViewAlpha);
        imgScale        = (ImageView) findViewById(R.id.imageviewScale);
        imgTranslate    = (ImageView) findViewById(R.id.imageviewTranslate);
        imgZoom         = (ImageView) findViewById(R.id.imageViewZoom);
    }
}
