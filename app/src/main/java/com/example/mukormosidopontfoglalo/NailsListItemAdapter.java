package com.example.mukormosidopontfoglalo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NailsListItemAdapter extends RecyclerView.Adapter<NailsListItemAdapter.ViewHolder> {
    private ArrayList<NalisListItem> listItems;
    private Context context;
    private int lastPosition = -1;
    private boolean rotate = false;

    public NailsListItemAdapter(Context context, ArrayList<NalisListItem> items) {
        listItems = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NailsListItemAdapter.ViewHolder holder, int position) {
        NalisListItem currentItem = listItems.get(position);

        holder.bindTo(currentItem);
        if (holder.getAdapterPosition() > lastPosition) {
            if (((Math.random() * 100)) % 2 == 0) {
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.other_animation);
                rotate = false;
                holder.itemView.startAnimation(animation);
                lastPosition = holder.getAdapterPosition();
            } else {
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_row);
                rotate = true;
                holder.itemView.startAnimation(animation);
                lastPosition = holder.getAdapterPosition();
            }
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.itemImage);
        }

        public void bindTo(NalisListItem currentItem) {
            Glide.with(context).load(currentItem.getImageResource()).into(imageView);
        }
    }
}


