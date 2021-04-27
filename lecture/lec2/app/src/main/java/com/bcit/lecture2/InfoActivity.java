package com.bcit.lecture2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //get intent
        Intent intent = getIntent();
        //get the data from the intent
        String data = intent.getStringExtra(MainActivity.EXTRA_SOMEDATA);

        // do something with the intent data
        TextView textView = findViewById(R.id.textView_info);
        String str = textView.getText() + " and I also like " + data;
        textView.setText(str);

    }
}