package com.bcit.dongmin_midterm;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public Button movieButton;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        movieButton = itemView.findViewById(R.id.button_movie_display);
    }
}
