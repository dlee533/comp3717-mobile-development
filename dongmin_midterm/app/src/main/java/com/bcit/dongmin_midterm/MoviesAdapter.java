package com.bcit.dongmin_midterm;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder>  {

    private List<Movie> movieList;

    public MoviesAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View lectureView = inflater.inflate(R.layout.item_movie, parent, false);

        MovieViewHolder viewHolder = new MovieViewHolder(lectureView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        Button movieButton = holder.movieButton;
        movieButton.setText(movie.getName());
        movieButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MovieActivity.class);
                intent.putExtra(MovieActivity.EXTRA_NAME, movie.getName());
                intent.putExtra(MovieActivity.EXTRA_ID, movie.getId());
                intent.putExtra(MovieActivity.EXTRA_IMAGE, movie.getImageResourceId());
                intent.putExtra(MovieActivity.EXTRA_YEAR, movie.getYear());
                intent.putExtra(MovieActivity.EXTRA_DESC, movie.getDescription());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
