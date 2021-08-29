package com.example.paginations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.paginations.model.ImageModel;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private ArrayList<ImageModel> list;

    public ImageAdapter(Context context, ArrayList<ImageModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ImageAdapter.ImageViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getUrls().getRegular())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

         ImageView imageView;

        public ImageViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }

}
