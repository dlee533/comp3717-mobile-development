package com.bcit.dongmin_lab8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LootAdapter extends RecyclerView.Adapter<LootViewHolder> {

    private List<Loot> lootList;

    private OnAdapterItemListener onAdapterItemListener;

    public void setOnAdapterItemListener(OnAdapterItemListener listener) {
        this.onAdapterItemListener = listener;
    }

    public LootAdapter(List<Loot> loots) {
        this.lootList = loots;
    }

    @NonNull
    @Override
    public LootViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View movieView = inflater.inflate(R.layout.row_layout, parent, false);

        LootViewHolder viewHolder = new LootViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LootViewHolder holder, int position) {
        TextView tv1 = holder.loot1;
        TextView tv2 = holder.loot2;
        TextView tv3 = holder.loot3;

        Loot loot = lootList.get(position);
        tv1.setText(loot.getItemOne());
        tv2.setText(loot.getItemTwo());
        tv3.setText(loot.getItemThree());

        View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                onAdapterItemListener.OnLongClick(loot);

                return false;
            }
        };

        tv1.setOnLongClickListener(onLongClickListener);
        tv2.setOnLongClickListener(onLongClickListener);
        tv3.setOnLongClickListener(onLongClickListener);
    }


    @Override
    public int getItemCount() {
        return lootList.size();
    }
}
