package com.a3.demo23.read_rss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class NewReaderActivity extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reader);
        webView = (WebView)findViewById(R.id.webViewReader);
        Intent myIntent = getIntent();
        String url = myIntent.getStringExtra("link");
        if (url == ""|| url ==null){
            Toast.makeText(NewReaderActivity.this, "Error", Toast.LENGTH_LONG).show();
        }else {
            webView.loadUrl(url);
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
        }
    }
}
