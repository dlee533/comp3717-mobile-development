package com.bcit.lec4;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    public TextView nameTextView;
    public TextView gradeTextView;

    public StudentViewHolder(View itemView) {
        super(itemView);

        nameTextView = itemView.findViewById(R.id.textView_studentName);
        gradeTextView = itemView.findViewById(R.id.textView_grade);
    }
}
