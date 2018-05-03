package com.app.rubio.movie.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.app.rubio.movie.models.Movie;
import com.app.rubio.movie.ui.activity.DetailMovieActivity;
import com.bumptech.glide.Glide;

/**
 * Created by Rubio on 25/03/2018.
 */

public class ItemMovieViewModel extends BaseObservable {
    private Movie movie;
    private Context context;

    public ItemMovieViewModel(Movie movie, Context context) {
        this.movie = movie;
        this.context = context;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public String getOverview() {
        return movie.getOverview();
    }

    public String getPoster_Path() {
        return "https://image.tmdb.org/t/p/w500" + movie.getPoster_path();
    }

    public void onItemClick(View v) {
        context.startActivity(DetailMovieActivity.fillDetail(v.getContext(), movie));
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        notifyChange();
    }
}
