package com.a1.demo24.media_online;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
Button btnMp3, btnMp4;
    VideoView vvMp4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    btnMp3 = (Button)findViewById(R.id.button);
        btnMp4 =(Button)findViewById(R.id.button2);
        vvMp4 =(VideoView) findViewById(R.id.videoView);
        btnMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayNhacMp3("http://mp3.zing.vn/bai-hat/Dong-Diu-Ngot-Beat-Quoc-Thien/ZW78CUFA.html");
            }
        });
        btnMp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://khoapham.vn/download/vuoncaovietnam.mp4");
                vvMp4.setMediaController(new MediaController(MainActivity.this));
                vvMp4.setVideoURI(uri);
                vvMp4.start();
            }
        });
    }

    public void PlayNhacMp3(String url){
        //url = "http://khoapham.vn/download/vietnamoi.mp3";
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
