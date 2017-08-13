package com.demo30.demoplayerytube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    EditText edtKey;
    Button btnPlay;
    YouTubePlayerView YTBView;
    String idKey = "AIzaSyDuukezcuQj95wu8Wv4CW_EnsdfOlU2KIA";
    int RequestCode = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtKey = (EditText) findViewById(R.id.editTextKey);
        btnPlay = (Button) findViewById(R.id.buttonPlay);
        YTBView = (YouTubePlayerView) findViewById(R.id.YTView);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YTBView.initialize(idKey, MainActivity.this);
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.loadVideo(edtKey.getText().toString().trim());
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Oh no! " + youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RequestCode){
            YTBView.initialize(idKey, MainActivity.this);
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
