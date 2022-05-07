package com.example.mukormosidopontfoglalo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NailsListItemAdapter extends RecyclerView.Adapter<NailsListItemAdapter.ViewHolder> {
    private ArrayList<NalisListItem> listItems;
    private Context context;
    private int lastPosition = -1;

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


