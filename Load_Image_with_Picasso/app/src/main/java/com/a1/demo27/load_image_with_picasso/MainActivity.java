package com.a1.demo27.load_image_with_picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    Button btnLoad;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    anhxa();
        btnLoad.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           LoadImage_Picasso();
       }
   }
        );
}
    public void LoadImage_Picasso(){
        Picasso.with(this).load("http://webgamenb.wap.sh/hinh-nen/hinh-nen-girl-xinh-hd/hinh-nen-girl-xinh-hd-03.jpg")
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imgView);

    }
    public void anhxa(){
        btnLoad =(Button)findViewById(R.id.buttonLoad);
        imgView = (ImageView)findViewById(R.id.imageView);
    }
}
