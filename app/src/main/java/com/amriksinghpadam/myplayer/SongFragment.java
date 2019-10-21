package com.amriksinghpadam.myplayer;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class SongFragment extends Fragment {

    private CarouselView carouselView;
    private int[] carouselImages = {R.drawable.aaaae,R.drawable.bbb,R.drawable.ccc,R.drawable.ddd,R.drawable.image,R.drawable.images};
    private RecyclerView recyclerView,recyclerView2, recyclerView3,recyclerView4;
    private TextView moreFeature,moreLatest,moreDiscover;
    private ArrayList imageArrayList = new ArrayList();
    private ArrayList textArrayList = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setActionBar(null);
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(carouselImages.length);
        carouselView.setImageListener(imageListener);

        recyclerView = view.findViewById(R.id.artistRecyclerViewId);
        recyclerView2 = view.findViewById(R.id.latestSongRecyclerViewId);
        recyclerView3 = view.findViewById(R.id.generRecyclerViewId);
        moreFeature = view.findViewById(R.id.more_fetured_id);
        moreLatest = view.findViewById(R.id.more_latest_id);
        moreDiscover = view.findViewById(R.id.more_discover_id);

        moreFeature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CommonPlayerGridView.class);
                Bundle bundle = new Bundle();
                bundle.putString("title","Featured Artists");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        moreLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CommonPlayerGridView.class);
                Bundle bundle = new Bundle();
                bundle.putString("title","Latest Songs");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        moreDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CommonPlayerGridView.class);
                Bundle bundle = new Bundle();
                bundle.putString("title","Discover");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        initRecyclerView();
        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(final int position, ImageView imageView) {
            imageView.setImageResource(carouselImages[position]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"Image - "+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    public void initRecyclerView(){
        int x = 1;
        while(x<=15){
            textArrayList.add("Image-"+x);
            imageArrayList.add(getActivity().getDrawable(R.drawable.ic_launcher_background));
            x++;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        PlayerLayoutAdapter playerLayoutAdapter = new PlayerLayoutAdapter(getContext(),imageArrayList,textArrayList);
        recyclerView.setAdapter(playerLayoutAdapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(layoutManager2);
        PlayerLayoutAdapter playerLayoutAdapter2 = new PlayerLayoutAdapter(getContext(),imageArrayList,textArrayList);
        recyclerView2.setAdapter(playerLayoutAdapter2);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView3.setLayoutManager(layoutManager3);
        PlayerLayoutAdapter playerLayoutAdapter3 = new PlayerLayoutAdapter(getContext(),imageArrayList,textArrayList);
        recyclerView3.setAdapter(playerLayoutAdapter3);

    }

}
