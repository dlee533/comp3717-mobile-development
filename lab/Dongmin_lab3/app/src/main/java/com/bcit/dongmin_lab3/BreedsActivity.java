package com.bcit.dongmin_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BreedsActivity extends AppCompatActivity {

    private final int[] button_ids = new int[]{R.id.button_breeds_breed1, R.id.button_breeds_breed2, R.id.button_breeds_breed3};
    public static final String BREED = "com.bcit.lab3.BREED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeds);

        Intent intent = getIntent();
        String[] breeds = intent.getStringArrayExtra(MainActivity.BREEDS_OPTIONS);

        for (int i = 0; i < button_ids.length; i++) {
            Button button = findViewById(button_ids[i]);
            button.setText(breeds[i]);
        }
    }

    public void GoToActivity(View v) {
        Intent intent = new Intent(this, FactsActivity.class);

        Button button = findViewById(v.getId());
        String breed = (String) button.getText();
        intent.putExtra(BREED, breed);
        startActivity(intent);
    }
}