package com.bcit.musicmania;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MusicFunAdapter extends RecyclerView.Adapter<MusicFunAdapter.MusicFunViewHolder>  {

    private List<String> songList;
    public MusicFunAdapter(List<String> songList)
    {
        this.songList = songList;
    }

    @NonNull
    @Override
    public MusicFunViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_layout, parent, false);

        MusicFunViewHolder viewHolder = new MusicFunViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicFunViewHolder holder, int position)
    {
        TextView tvName = holder.nameTextView;
        tvName.setText(songList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return songList.size();
    }


    public class MusicFunViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public MusicFunViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.textViewName);
        }
    }

}

