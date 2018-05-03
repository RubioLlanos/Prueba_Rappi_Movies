package com.app.rubio.movie.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.app.rubio.movie.R;
import com.app.rubio.movie.databinding.ItemLisMovieBinding;
import com.app.rubio.movie.models.Movie;
import com.app.rubio.movie.viewModel.ItemMovieViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Rubio on 24/03/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private List<Movie> movieList;

    public MovieAdapter() {
        this.movieList = Collections.emptyList();
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLisMovieBinding itemLisMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_lis_movie, parent, false);
        return new MovieAdapterViewHolder(itemLisMovieBinding);
    }


    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        holder.bindMovie(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public void reset() {
        if (this.movieList.size() > 0)
            this.movieList.clear();
        notifyDataSetChanged();
    }

    public static class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemLisMovieBinding itemLisMovieBinding;

        public MovieAdapterViewHolder(ItemLisMovieBinding itemMovieBinding) {
            super(itemMovieBinding.itemMovie);
            this.itemLisMovieBinding = itemMovieBinding;

        }

        public void bindMovie(Movie movie) {
            if (itemLisMovieBinding.getMovieViewModel() == null) {
                itemLisMovieBinding.setMovieViewModel(new ItemMovieViewModel(movie, itemView.getContext()));
            } else {
                itemLisMovieBinding.getMovieViewModel().setMovie(movie);

            }
        }
    }
}

