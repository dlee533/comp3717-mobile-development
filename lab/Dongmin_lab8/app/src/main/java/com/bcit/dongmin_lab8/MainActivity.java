package com.bcit.dongmin_lab8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button lootButton;
    private Button monsterButton;
    public static int monsterHp;

    public DatabaseReference databaseReference;
    public static List<Loot> lootList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        monsterHp = 100;

        lootButton = findViewById(R.id.button_main_loots);
        monsterButton = findViewById(R.id.button_main_monster);

        monsterButton.setOnClickListener(onClickMonster);
        lootButton.setOnClickListener(onClickLoot);

        lootList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("loots");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot lootsSnapshot : snapshot.getChildren()) {
                    Loot loot = lootsSnapshot.getValue(Loot.class);
                    lootList.add(loot);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private View.OnClickListener onClickMonster = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_main_fragment, new MonsterFragment());
            fragmentTransaction.commit();
        }
    };

    private View.OnClickListener onClickLoot = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_main_fragment, new LootFragment(lootList, databaseReference));
            fragmentTransaction.commit();
        }
    };

}