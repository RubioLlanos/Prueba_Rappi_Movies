package com.app.rubio.movie.viewModel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.app.rubio.movie.models.Movie;
import com.bumptech.glide.Glide;

import java.util.Observable;

/**
 * Created by Rubio on 25/03/2018.
 */

public class MovieDetailViewModel extends Observable {
    private Movie movie;

    public MovieDetailViewModel(Movie movie) {
        this.movie = movie;

    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    public String getTitle() {
        return movie.getTitle();

    }

    public String getOverView() {
        return movie.getOverview();
    }

    public String getMovieThumb() {
        return "https://image.tmdb.org/t/p/w500" + movie.getBackdrop_path();
    }

    public String getVoteCount() {
        return movie.getVote_count();
    }

    public String getReleaseDate() {
        return movie.getRelease_date();
    }
}
