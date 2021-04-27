package com.bcit.toonsland;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToonsAdapter extends RecyclerView.Adapter<ToonViewHolder> {

    private List<Toon> toons;

    public ToonsAdapter(List<Toon> students) {
        this.toons = students;
    }

    @NonNull
    @Override
    public ToonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View toonView = inflater.inflate(R.layout.recycler_row, parent, false);

        ToonViewHolder viewHolder = new ToonViewHolder(toonView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ToonViewHolder holder, int position) {
        Toon toon = toons.get(position);

        ImageView imgOnePhoto = holder.imgOnePhoto;
        if (toon.getPictureUrl() != null) {
            new ImageDownloaderTask(imgOnePhoto).execute(toon.getPictureUrl());
        }

        TextView textViewToonName = holder.nameTextView;
        textViewToonName.setText(toon.getFirstName());

        TextView textViewOccupation = holder.occupationTextView;
        textViewOccupation.setText(toon.getOccupation());

        TextView textViewGender = holder.genderTextView;
        textViewGender.setText(toon.getGender());
    }

    @Override
    public int getItemCount() {
        return toons.size();
    }

}
