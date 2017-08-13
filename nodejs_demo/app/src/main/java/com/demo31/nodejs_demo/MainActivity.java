package com.demo31.nodejs_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.38.1:3000/");
        } catch (URISyntaxException e) {}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSocket.connect();
    }
}
