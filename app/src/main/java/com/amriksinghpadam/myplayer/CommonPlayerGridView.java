package com.amriksinghpadam.myplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class CommonPlayerGridView extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList bannerList = new ArrayList();
    private ArrayList tittleList = new ArrayList();
    private RecyclerView commonRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_player_grid_view);

        toolbar = findViewById(R.id.gridplayerid);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("title");
        //Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.whiteColor));
        commonRecyclerView = findViewById(R.id.common_grid_recycler_id);

        bannerList.clear();
        tittleList.clear();
        Drawable resImage;

        for (int i=0;i<16;i++){
            switch (i){
                case 0:
                    resImage = getResources().getDrawable(R.drawable.d);
                    break;
                case 1:
                    resImage = getResources().getDrawable(R.drawable.c);
                    break;
                case 2:
                    resImage = getResources().getDrawable(R.drawable.b);
                    break;
                case 3:
                    resImage = getResources().getDrawable(R.drawable.a);
                    break;
                default:
                    resImage = getResources().getDrawable(R.drawable.punjabi);
                    break;
            }
            bannerList.add(resImage);
            tittleList.add( title+" - "+(i+1));
        }
        GridLayoutManager layoutManager = new GridLayoutManager(this,3, LinearLayoutManager.VERTICAL,false);
        commonRecyclerView.setLayoutManager(layoutManager);
        CommonGridPlayerRecyclerViewAdapter adapter =
                new CommonGridPlayerRecyclerViewAdapter(this,bannerList,tittleList);
        commonRecyclerView.setAdapter(adapter);
    }
}
