package com.bcit.dongmin_lab8;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LootViewHolder extends RecyclerView.ViewHolder {

    public TextView loot1;
    public TextView loot2;
    public TextView loot3;

    public LootViewHolder(@NonNull View itemView) {
        super(itemView);

        loot1 = itemView.findViewById(R.id.textView_row_1);
        loot2 = itemView.findViewById(R.id.textView_row_2);
        loot3 = itemView.findViewById(R.id.textView_row_3);

    }
}
