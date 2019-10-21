package com.amriksinghpadam.myplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoExoPlayer extends AppCompatActivity {

    private PlayerView exoplayerView;
    private SimpleExoPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_exo_player);
        getSupportActionBar().hide();

        exoplayerView = findViewById(R.id.exo_player_id);

        BandwidthMeter meter =new DefaultBandwidthMeter();
        TrackSelection.Factory factory = new AdaptiveTrackSelection.Factory(meter);
        TrackSelector trackSelector = new DefaultTrackSelector(factory);

        player = ExoPlayerFactory.newSimpleInstance(this,trackSelector);
        exoplayerView.setPlayer(player);

        DataSource.Factory datasourcefactory = new DefaultDataSourceFactory(
                this, Util.getUserAgent(this,null));

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        Uri videoURL = Uri.parse(
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");

        MediaSource mediaSource = new ExtractorMediaSource(
                videoURL, datasourcefactory, extractorsFactory, null,null);

        player.prepare(mediaSource);
        player.setPlayWhenReady(true);

    }
}
