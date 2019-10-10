package com.amriksinghpadam.myplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class VideoHeaderPagerAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    ArrayList<VideoHeaderModel> models;
    Context context;

    public VideoHeaderPagerAdapter(ArrayList<VideoHeaderModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() { return models.size(); }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) { return view.equals(object); }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_view_viewpager_item,container,false);
        ImageView image;
        image = view.findViewById(R.id.videoBannerImageId);
        image.setImageResource(models.get(position).getImage());
        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        try {
            super.destroyItem(container, position, object);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
