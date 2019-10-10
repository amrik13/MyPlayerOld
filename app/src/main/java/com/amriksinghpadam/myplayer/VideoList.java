package com.amriksinghpadam.myplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class VideoList extends AppCompatActivity {
    TextView vCount,sName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        vCount = findViewById(R.id.vCountTextId);
        sName = findViewById(R.id.sNameTextId);

        String count = getIntent().getStringExtra("vCount");
        String name = getIntent().getStringExtra("sName");
        vCount.setText(count);
        sName.setText(name);

    }
}
