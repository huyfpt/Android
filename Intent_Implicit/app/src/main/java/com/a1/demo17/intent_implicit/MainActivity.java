package com.a1.demo17.intent_implicit;

import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView imageView, imgMess, imgcall, imgWifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageButton);
        imgMess = (ImageView)findViewById(R.id.imageView1);
        imgcall = (ImageView)findViewById(R.id.imageView);
        imgWifi = (ImageView)findViewById(R.id.imageView2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com.vn/"));
                startActivity(intent);
            }
        });
        imgMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                intent.putExtra(intent.EXTRA_TEXT, "Contents.....22..2.2.233.3..");//nội dung được ghi sẳn trong thanh textMessage
//                intent.setType("text/plain");
//                startActivity(intent);
//                ---------sms----------
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_SENDTO);
//                intent.putExtra("sms_body","fuck uuuuuuuuuu");//sms_body thể hiện nội dung thanh text
//                intent.setData(Uri.parse("sms:33333333"));//sms thể hiện cho sdt trong message
//                startActivity(intent);
//                ---------smsMail-------
                Uri smsUri = Uri.parse("tel:333333");
                    Intent intent = new Intent(Intent.ACTION_VIEW, smsUri);
                    intent.putExtra("address","duchuyfptvn@gmail.com");
                    intent.putExtra("sms_body","ddddd");
                    intent.setType("vnd.android-dir/mms-sms");//here setType will set the previous data null.
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                }
            }
        });
imgcall.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0377778888"));
        if (callIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(callIntent);
        }
    }
});

imgWifi.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent wifi = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
        //Settings.ACTION_WIFI_Settings = WifiManager.ACTION_PICK_WIFI_NETWORK
        startActivity(wifi);
    }
});
    }
}
