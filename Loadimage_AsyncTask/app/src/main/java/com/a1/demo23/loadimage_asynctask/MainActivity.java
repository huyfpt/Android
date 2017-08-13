package com.a1.demo23.loadimage_asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button btnLoadImage;
    ImageView imgImage;
    ProgressBar bgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoadImage = (Button) findViewById(R.id.button);
        imgImage = (ImageView) findViewById(R.id.imageViewLoad);
        bgView = (ProgressBar)findViewById(R.id.progressBar);
        bgView.setVisibility(View.INVISIBLE);
    btnLoadImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   new LoadImage().execute();
                }
            });
        }
    });
    }
    private class LoadImage extends AsyncTask<String, String, Bitmap> {//result's image return Bitmap

        @Override
        protected void onPreExecute() {
            bgView.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmapImage =null;
            try {
                URL url = new URL("https://farm9.staticflickr.com/8562/16385658581_c4a27b0f07_o.jpg");
                bitmapImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapImage;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgImage.setImageBitmap(bitmap);
            bgView.setVisibility(View.GONE);
        }
    }

}
