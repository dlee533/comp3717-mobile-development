package com.bcit.dongmin_lab8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindLootActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_loot);

        getSupportActionBar().hide();

        List<View.OnClickListener> listeners = new ArrayList<>();
        listeners.add(onClickFail);
        listeners.add(onClickFail);
        listeners.add(onClickLoot);
        findViewById(R.id.imageButton_loot_1).setOnClickListener(listeners.remove(new Random().nextInt(listeners.size())));
        findViewById(R.id.imageButton_loot_2).setOnClickListener(listeners.remove(new Random().nextInt(listeners.size())));
        findViewById(R.id.imageButton_loot_3).setOnClickListener(listeners.get(0));
        System.out.println(MainActivity.lootList);

        findViewById(R.id.button_loot_back).setVisibility(View.GONE);
        findViewById(R.id.button_loot_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("finishActivity()");
                finish();
            }
        });

    }


    private View.OnClickListener onClickFail = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showFailDialog();
            findViewById(R.id.button_loot_back).setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener onClickLoot = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.out.println(MainActivity.lootList.size());
            Loot loot = MainActivity.lootList.get(new Random().nextInt(MainActivity.lootList.size()));
            showLootDialog(loot.getLootId(), loot.getItemOne(), loot.getItemTwo(), loot.getItemThree());
            findViewById(R.id.button_loot_back).setVisibility(View.VISIBLE);
        }
    };

    private void showFailDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.fail_dialog, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Loots");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void showLootDialog(final String lootId, String loot1, String loot2, String loot3) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.loot_dialog, null);
        dialogBuilder.setView(dialogView);

        TextView textViewLoot1 = dialogView.findViewById(R.id.textView_loot_item1);
        textViewLoot1.setText(loot1);

        TextView textViewLoot2 = dialogView.findViewById(R.id.textView_loot_item2);
        textViewLoot2.setText(loot2);

        TextView textViewLoot3 = dialogView.findViewById(R.id.textView_loot_item3);
        textViewLoot3.setText(loot3);

        dialogBuilder.setTitle("Loots");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}