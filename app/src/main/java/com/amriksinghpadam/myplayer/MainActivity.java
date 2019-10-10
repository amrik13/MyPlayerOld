package com.amriksinghpadam.myplayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout1,layout2;
    private CardView songCard, videoCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = findViewById(R.id.linearLayout1);
        layout2 = findViewById(R.id.linearLayout2);
        songCard = findViewById(R.id.songCardViewId);
        videoCard = findViewById(R.id.videoCardViewId);

        layout1.setOnClickListener  (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("section",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("section",2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
