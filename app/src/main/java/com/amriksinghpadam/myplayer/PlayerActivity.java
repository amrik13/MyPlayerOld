package com.amriksinghpadam.myplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.amriksinghpadam.api.APIConstent;
import com.amriksinghpadam.api.NavigationItemRequest;
import com.amriksinghpadam.api.SharedPrefUtil;
import com.google.android.material.tabs.TabLayout;

public class PlayerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TabLayout tabLayout;
    private int section;
    private RelativeLayout refreshIconLayout,progressBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().hide();

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayoutId);
        tabLayout.setupWithViewPager(viewPager);
        refreshIconLayout = findViewById(R.id.refresh_layout_id);
        progressBarLayout = findViewById(R.id.progressBar_layout_id);
        refreshIconLayout.setVisibility(View.GONE);
        progressBarLayout.setVisibility(View.GONE);

        if(getIntent()!=null){
            section = getIntent().getExtras().getInt(APIConstent.SECTION);
        }

        NavigationItemRequest navRequest = new NavigationItemRequest(PlayerActivity.this,
                progressBarLayout,refreshIconLayout,null,null,false);
        navRequest.startNavItemActivity(getResources().getString(R.string.artist_title),
                getResources().getString(R.string.song),APIConstent.ARTIST_URL_PARAM,
                SharedPrefUtil.ARTIST_JSON_RESPONSE);
        navRequest.startNavItemActivity(getResources().getString(R.string.latest_song),
                getResources().getString(R.string.song),APIConstent.LATEST_URL_PARAM,
                SharedPrefUtil.LATEST_JSON_RESPONSE);
        navRequest.startNavItemActivity(getResources().getString(R.string.discover),
                getResources().getString(R.string.song),APIConstent.DISCOVER_URL_PARAM,
                SharedPrefUtil.DISCOVER_JSON_RESPONSE);

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),section);
        viewPager.setAdapter(myViewPagerAdapter);

    }



}
