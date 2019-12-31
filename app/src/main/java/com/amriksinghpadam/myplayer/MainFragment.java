package com.amriksinghpadam.myplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amriksinghpadam.myplayer.api.APIConstent;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

public class MainFragment extends Fragment {
    private View layout1,layout2;
    private Context context;
    private int tempCount=0;
    private TextView songTitleView,videoTitleView;
    private View songBannerView,videoBannerView;
    private String songBannerUrl,videoBannerUrl;

    public MainFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        layout1 = view.findViewById(R.id.linearLayout1);
        layout2 = view.findViewById(R.id.linearLayout2);
        songTitleView = view.findViewById(R.id.songTitleId);
        videoTitleView = view.findViewById(R.id.videoTitleId);
        songBannerView = view.findViewById(R.id.songImageView);
        videoBannerView = view.findViewById(R.id.videoImageView);

        Bundle bundle = getArguments();
        if(bundle!=null) {
            String songTitle = bundle.getString(APIConstent.SONG_TITLE);
            String videoTitle = bundle.getString(APIConstent.VIDEO_TITLE);
            songBannerUrl = bundle.getString(APIConstent.SONG_BANNER);
            videoBannerUrl = bundle.getString(APIConstent.VIDEO_BANNER);
            songTitleView.setText(songTitle.toUpperCase());
            videoTitleView.setText(videoTitle.toUpperCase());
            loadBackgroundImage();
        }
        layout1.setOnClickListener  (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tempCount==0){
                    Intent intent = new Intent(context , PlayerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("section",1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    tempCount++;
                }
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tempCount==0) {
                    Intent intent = new Intent(context, PlayerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("section", 2);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    tempCount++;
                }
            }
        });
        return view;
    }

    public void loadBackgroundImage(){
        Glide.with(context).load(songBannerUrl).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                songBannerView.setBackground(resource);
            }
            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {}
        });
        Glide.with(context).load(videoBannerUrl).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                videoBannerView.setBackground(resource);
            }
            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {}
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        tempCount=0;
    }

}
