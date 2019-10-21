package com.amriksinghpadam.myplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.exoplayer2.ui.PlayerView;

public class VideoExoPlayer extends AppCompatActivity {

    private PlayerView exoplayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_exo_player);
        getSupportActionBar().hide();

        exoplayerView = findViewById(R.id.exo_player_id);



    }
}
