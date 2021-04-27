package com.bcit.dongmin_lab8;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class LootFragment extends Fragment {

    EditText loot1;
    EditText loot2;
    EditText loot3;

    RecyclerView rvLoots;

    List<Loot> lootList;
    DatabaseReference databaseReference;

    public LootFragment(List<Loot> lootList, DatabaseReference databaseReference) {
        super(R.layout.fragment_loot);
        this.lootList = lootList;
        this.databaseReference = databaseReference;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loot1 = getActivity().findViewById(R.id.editText_loot_1);
        loot2 = getActivity().findViewById(R.id.editText_loot_2);
        loot3 = getActivity().findViewById(R.id.editText_loot_3);

        rvLoots = getActivity().findViewById(R.id.rv_loot_loots);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lootList.clear();
                for (DataSnapshot lootsSnapshot : snapshot.getChildren()) {
                    Loot loot = lootsSnapshot.getValue(Loot.class);
                    lootList.add(loot);
                }

                LootAdapter adapter = new LootAdapter(lootList);
                rvLoots.setAdapter(adapter);
                rvLoots.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter.setOnAdapterItemListener(new OnAdapterItemListener() {
                    @Override
                    public void OnLongClick(Loot loot) {
                        showDeleteDialog(loot.getLootId());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        getActivity().findViewById(R.id.button_loot_add).setOnClickListener(addLoot);
    }

    private void showDeleteDialog(String id) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Delete loot");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        dialogView.findViewById(R.id.button_dialog_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteLoot(id, alertDialog);
            }
        });

        dialogView.findViewById(R.id.button_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private View.OnClickListener addLoot = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String item1 = loot1.getText().toString();
            String item2 = loot2.getText().toString();
            String item3 = loot3.getText().toString();

            if (item1.isEmpty()) {
                loot1.setError("Loot 1 is required");
            }
            if (item2.isEmpty()) {
                loot2.setError("Loot 2 is required");
            }
            if (item3.isEmpty()) {
                loot3.setError("Loot 3 is required");
            }
            if (item1.isEmpty() || item2.isEmpty() || item3.isEmpty()) {
                return;
            }

            String id = databaseReference.push().getKey();
            Loot loot = new Loot(id, item1, item2, item3);

            Task<Void> setValueTask = databaseReference.child(id).setValue(loot);

            setValueTask.addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object c) {
                    Toast.makeText(getContext(), "Loot added", Toast.LENGTH_LONG).show();
                    loot1.setText("");
                    loot2.setText("");
                    loot3.setText("");
                }
            });

            setValueTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    private void deleteLoot(String id, AlertDialog alertDialog) {
        Task<Void> setValueTask = databaseReference.child(id).removeValue();
        setValueTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Loot deleted", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        setValueTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
