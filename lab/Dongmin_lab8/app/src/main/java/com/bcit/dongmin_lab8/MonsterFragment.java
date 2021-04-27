package com.bcit.dongmin_lab8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class MonsterFragment extends Fragment {
    private TextView hpTextView;
    private ImageView monsterImageView;

    public MonsterFragment(){
        super(R.layout.fragment_monster);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hpTextView = getActivity().findViewById(R.id.textView_monster_hp);
        monsterImageView = getActivity().findViewById(R.id.imageView_monster_monster);
        monsterImageView.setOnClickListener(AttackMonster);

    }

    @Override
    public void onStart() {
        super.onStart();
        hpTextView.setText("HP: " + MainActivity.monsterHp + "/100");

    }

    public View.OnClickListener AttackMonster = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int attack = (new Random()).nextInt(19)+1;
            MainActivity.monsterHp = Math.max(MainActivity.monsterHp - attack, 0);
            hpTextView.setText("HP: " + MainActivity.monsterHp + "/100");
            if (MainActivity.monsterHp == 0) {
                Intent intent = new Intent(getContext(), FindLootActivity.class);
                startActivity(intent);
                MainActivity.monsterHp = 100;
            }
        }
    };
}
