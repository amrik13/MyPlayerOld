package com.amriksinghpadam.myplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class VideoRecyclerViewAdapter extends RecyclerView.Adapter<VideoRecyclerViewAdapter.SingerListAdapterVH> {

    private Context context;
    private ArrayList singerImageArrayList = new ArrayList();
    private ArrayList singerNameArrayList = new ArrayList();
    private ArrayList videoCountArrayList = new ArrayList();
    private View view;

    public VideoRecyclerViewAdapter(
            Context context,ArrayList singerImageArrayList,
            ArrayList singerNameArrayList, ArrayList videoCountArrayList) {
            this.context = context;
            this.singerImageArrayList.addAll(singerImageArrayList);
            this.singerNameArrayList.addAll(singerNameArrayList);
            this.videoCountArrayList.addAll(videoCountArrayList);
    }

    @NonNull
    @Override
    public VideoRecyclerViewAdapter.SingerListAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.video_recyclerview_item,parent,false);
        SingerListAdapterVH holder = new SingerListAdapterVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoRecyclerViewAdapter.SingerListAdapterVH holder, final int position) {
        Glide.with(context).asBitmap().load(singerImageArrayList.get(position)).load(holder.singerImage);
        holder.singerName.setText(singerNameArrayList.get(position).toString());
        holder.videoCount.setText(videoCountArrayList.get(position).toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String vCount= videoCountArrayList.get(position).toString().trim();
            String sName = singerNameArrayList.get(position).toString().trim();
            Bundle bundle = new Bundle();
            bundle.putString("vCount",vCount);
            bundle.putString("sName",sName);
            Intent intent = new Intent(context,VideoList.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() { return singerImageArrayList.size(); }

    class SingerListAdapterVH extends RecyclerView.ViewHolder{
        ImageView singerImage;
        TextView singerName,videoCount;

        public SingerListAdapterVH(@NonNull View itemView) {
            super(itemView);
            singerImage = itemView.findViewById(R.id.videoItemImageId);
            singerName = itemView.findViewById(R.id.singerNameTextId);
            videoCount = itemView.findViewById(R.id.videoCountTextId);

        }

    }

}
