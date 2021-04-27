package com.bcit.dongmin_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String BREEDS_OPTIONS = "com.bcit.lab3.BREEDS_OPTIONS";
    public static final String[] CAT_BREEDS = {"bengal", "persian", "sphynx"};
    public static final String[] HORSE_BREEDS = {"appaloosa", "arabian", "thoroughbred"};
    public static final Map<String, String> ANIMAL_FACTS= new HashMap<String, String>() {{
        this.put("bengal", "Bengal cats don’t have your typical meow. They make a raspy noise that can sound more like a bark.\n");
        this.put("persian", "The average Persian cat lives for around 12 years.");
        this.put("sphynx", "Sphynx cats are not actually bald.");
        this.put("appaloosa", "The Appaloosa is an American horse breed best known for its colorful spotted coat pattern.");
        this.put("arabian", "Arabian horses are one of the oldest breeds that are known");
        this.put("thoroughbred", "Thoroughbreds are one of the fastest animals in the entire world at 40mph");
    }};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GoToBreeds(View v) {
        Intent intent = new Intent(this, BreedsActivity.class);

        if (v.getId() == R.id.button_main_cat) {
            intent.putExtra(BREEDS_OPTIONS, CAT_BREEDS);
        } else {
            intent.putExtra(BREEDS_OPTIONS, HORSE_BREEDS);
        }
        startActivity(intent);
    }
}