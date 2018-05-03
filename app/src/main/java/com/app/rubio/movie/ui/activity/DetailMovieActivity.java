package com.app.rubio.movie.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.app.rubio.movie.R;
import com.app.rubio.movie.databinding.ActivityDetailMovieBinding;
import com.app.rubio.movie.models.Movie;
import com.app.rubio.movie.viewModel.MovieDetailViewModel;

import org.parceler.Parcels;

public class DetailMovieActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private ActivityDetailMovieBinding activityDetailMovieBinding;

    public static Intent fillDetail(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailMovieActivity.class);
        intent.putExtra(EXTRA_MOVIE, Parcels.wrap(movie));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }

    private void displayHomeAsUpEnabled() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getExtrasFromIntent() {
        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_MOVIE));
        MovieDetailViewModel movieDetailViewModel = new MovieDetailViewModel(movie);
        activityDetailMovieBinding.setMovieDetailViewModel(movieDetailViewModel);
        setTitle(movie.getTitle());
    }

}
