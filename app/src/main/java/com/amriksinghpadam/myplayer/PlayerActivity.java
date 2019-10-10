package com.amriksinghpadam.myplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

public class PlayerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TabLayout tabLayout;
    private int section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().hide();

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayoutId);
        tabLayout.setupWithViewPager(viewPager);

        if(getIntent()!=null){
            section = getIntent().getExtras().getInt("section");

        }

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),section);
        viewPager.setAdapter(myViewPagerAdapter);


    }



}
