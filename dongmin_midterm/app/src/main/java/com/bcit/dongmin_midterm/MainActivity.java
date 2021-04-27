package com.bcit.dongmin_midterm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = createSpinner();
        spinner.setOnItemSelectedListener(onItemSelectedListener);

        RecyclerView movieRecycler = findViewById(R.id.recycler_main_movies);
        MoviesAdapter moviesAdapter = new MoviesAdapter(Arrays.asList(Movie.movies));
        movieRecycler.setAdapter(moviesAdapter);
        movieRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private Spinner createSpinner() {
        List<String> selections = new ArrayList<>();
        selections.add("Profile");
        selections.add("High School");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, selections);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinner_main_selections);
        spinner.setAdapter(adapter);

        return spinner;
    }

    private Spinner.OnItemSelectedListener onItemSelectedListener = new Spinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            String item = adapterView.getItemAtPosition(i).toString();
            if (item.equals("Profile")) {
                transaction.replace(R.id.fragment_main_info, new Profile());
            } else {
                transaction.replace(R.id.fragment_main_info, new Highschool());
            }
            transaction.commit();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}
