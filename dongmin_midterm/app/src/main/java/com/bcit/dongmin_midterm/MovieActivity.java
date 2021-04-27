package com.bcit.dongmin_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.bcit.dongmin_midterm.EXTRA_NAME";
    public static final String EXTRA_ID = "com.bcit.dongmin_midterm.EXTRA_ID";
    public static final String EXTRA_IMAGE = "com.bcit.dongmin_midterm.EXTRA_IMAGE";
    public static final String EXTRA_YEAR = "com.bcit.dongmin_midterm.EXTRA_YEAR";
    public static final String EXTRA_DESC = "com.bcit.dongmin_midterm.EXTRA_DESC";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent intent = getIntent();

        TextView tvDesc = findViewById(R.id.textView_movie_desc);
        tvDesc.setText(intent.getStringExtra(MovieActivity.EXTRA_DESC));

        TextView tvName = findViewById(R.id.textView_movie_name);
        tvName.setText(intent.getStringExtra(MovieActivity.EXTRA_NAME));
        System.out.println(intent.getExtras().get(MovieActivity.EXTRA_NAME));

        TextView tvYear = findViewById(R.id.textView_movie_year);
        tvYear.setText(Integer.toString(intent.getIntExtra(MovieActivity.EXTRA_YEAR, 0)));

        TextView tvId = findViewById(R.id.textView_movie_id);
        tvId.setText(Integer.toString(intent.getIntExtra(MovieActivity.EXTRA_ID, 0)));

        ImageView ivPoster = findViewById(R.id.imageView_movie_poster);
        ivPoster.setImageResource(intent.getIntExtra(MovieActivity.EXTRA_IMAGE, 0));
    }
}