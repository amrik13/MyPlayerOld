package com.amriksinghpadam.myplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

public class VideoExoPlayer extends AppCompatActivity {

    private PlayerView exoplayerView;
    private SimpleExoPlayer player;
    private ImageView shareBtnImg;
    private TextView videoTitle;
    private String vTitle;
    private RecyclerView relatedContentRecyclerView;
    private ArrayList imageArrayList = new ArrayList();
    private ArrayList nameArrayList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_exo_player);
        getSupportActionBar().hide();

        exoplayerView = findViewById(R.id.exo_player_id);
        shareBtnImg = findViewById(R.id.exoShareBtn);
        videoTitle = findViewById(R.id.exoVideoTitle);
        relatedContentRecyclerView = findViewById(R.id.related_content_recyclerview_id);
        initializePlayer();
        initRelatedRecyclerView();
        shareBtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "https://www.amriksinghpadam.com/main";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Your Video");
                intent.putExtra(Intent.EXTRA_TEXT,"Hello, I am watching \""+vTitle+"\" Show on " +
                        "Myplayer App and " +"sharing to you. Please click on link below to " +
                        "enjoy the episode on Myplayer app..\n"+uri);
                Intent.createChooser(intent,"Share To");
                startActivity(intent);
//                ShareCompat.IntentBuilder.from(VideoExoPlayer.this)
//                        .setType("text/plain")
//                        .setChooserTitle("Share To")
//                        .setText(uri)
//                        .startChooser();
            }
        });
    }
    public void initializePlayer(){
        vTitle = getIntent().getExtras().getString("title");
        videoTitle.setText(vTitle);
        BandwidthMeter meter =new DefaultBandwidthMeter();
        TrackSelection.Factory factory = new AdaptiveTrackSelection.Factory(meter);
        TrackSelector trackSelector = new DefaultTrackSelector(factory);
        player = ExoPlayerFactory.newSimpleInstance(this,trackSelector);
        exoplayerView.setPlayer(player);
        //PlaybackControlView controlView = new PlaybackControlView(this);
        DataSource.Factory datasourcefactory = new DefaultDataSourceFactory(
                this, Util.getUserAgent(this,"CloudinaryExoplaye"));
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        Uri videoURL = Uri.parse(
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        MediaSource mediaSource = new ExtractorMediaSource(
                videoURL, datasourcefactory, extractorsFactory, null,null);
        player.prepare(mediaSource);
        player.setPlayWhenReady(false);
    }

    public void initRelatedRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        relatedContentRecyclerView.setLayoutManager(layoutManager);
        for (int i = 0; i < 8; i++) {
            imageArrayList.add(getResources().getDrawable(R.drawable.image));
            nameArrayList.add("Singer Name " + (i + 1));
        }
        RelatedViewAdapter videoRecyclerViewAdapter = new RelatedViewAdapter(
                this, imageArrayList,
                nameArrayList);
        relatedContentRecyclerView.setAdapter(videoRecyclerViewAdapter);
    }
}
