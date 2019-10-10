package com.amriksinghpadam.myplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PlayerLayoutAdapter extends RecyclerView.Adapter<PlayerLayoutAdapter.PlayerViewHolder> {

    private Context context;
    private ArrayList imageList = new ArrayList();
    private ArrayList textList = new ArrayList();


    PlayerLayoutAdapter(Context context, ArrayList imageList , ArrayList textList){
        this.context = context;
        this.imageList.addAll(imageList);
        this.textList.addAll(textList);
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_item_list,parent,false);
        PlayerViewHolder holder = new PlayerViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {

        holder.bindView(position);
    }

    @Override
    public int getItemCount() {return imageList.size();}


    class PlayerViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageRecyclerViewId);
            textView = itemView.findViewById(R.id.textRecyclerViewId);


        }

        public void bindView(int position){
            Glide.with(context).asBitmap().load(imageList.get(position)).load(imageView);
            textView.setText(textList.get(position).toString());
        }

    }

}
