package com.bcit.dongmin_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        Intent intent = getIntent();
        String breed = intent.getStringExtra(BreedsActivity.BREED);

        TextView textView = findViewById(R.id.textView_facts_uniqueFacts);
        textView.setText(MainActivity.ANIMAL_FACTS.get(breed));

        ImageView imageView = findViewById(R.id.imageView_facts_animal);
        switch(breed) {
            case "appaloosa":
                imageView.setImageResource(R.drawable.facts_appaloosa_medium);
                break;
            case "arabian":
                imageView.setImageResource(R.drawable.facts_arabian_medium);
                break;
            case "bengal":
                imageView.setImageResource(R.drawable.facts_bengal_medium);
                break;
            case "sphynx":
                imageView.setImageResource(R.drawable.facts_sphynx_medium);
                break;
            case "thoroughbred":
                imageView.setImageResource(R.drawable.facts_thoroughbred_medium);
                break;
            default:
                imageView.setImageResource(R.drawable.facts_persian_medium);
        }
    }
}