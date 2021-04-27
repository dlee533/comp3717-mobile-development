package com.bcit.toonsland;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToonViewHolder extends RecyclerView.ViewHolder {

    public TextView nameTextView;
    public TextView occupationTextView;
    public TextView genderTextView;
    public ImageView imgOnePhoto;

    public ToonViewHolder(@NonNull View itemView) {
        super(itemView);

        nameTextView = itemView.findViewById(R.id.row_name);
        occupationTextView = itemView.findViewById(R.id.row_occupation);
        genderTextView = itemView.findViewById(R.id.row_gender);
        imgOnePhoto = itemView.findViewById(R.id.row_image);

    }
}
