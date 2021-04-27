package com.bcit.lecture2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_SOMEDATA = "com.bcit.lecture2.SOMEDATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        SetupSpinner();
    }

    void SetupSpinner()
    {
        Spinner spinner = findViewById(R.id.spinner_main);
        List<String> categories = new ArrayList<>();
        categories.add("Canada");
        categories.add("China");
        categories.add("India");
        categories.add("USA");
        categories.add("United Kingdom");

        ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrAdapter);

    }

    public void GoToInfo(View v)
    {
        //example of a explicit intent
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra(EXTRA_SOMEDATA, "pancakes");
        startActivity(intent);
    }

    public void GoToURL(View v)
    {
        //example of a implicit intent
        String str = "http://Google.com";
        Uri uri = Uri.parse(str);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void PassText(View v)
    {
        //Class Activity 1: Dongmin Lee
        TextView textView = findViewById(R.id.textView_main);
        String text = (String) textView.getText();
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra(EXTRA_SOMEDATA, text);
        startActivity(intent);
    }


}