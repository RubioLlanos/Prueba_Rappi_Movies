package com.app.rubio.movie.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import com.app.rubio.movie.R;
import com.app.rubio.movie.databinding.ActivityListMovieBinding;
import com.app.rubio.movie.models.Movie;
import com.app.rubio.movie.ui.adapter.MovieAdapter;
import com.app.rubio.movie.utils.RxSearchObservable;
import com.app.rubio.movie.viewModel.MovieViewModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ListMovieActivity extends AppCompatActivity implements MovieViewModel.NotifiedDataBase {
    private static final String CATEGORY_NAME = "categoryName";
    private ActivityListMovieBinding activityListMovieBinding;
    private MovieViewModel movieViewModel;
    private MovieAdapter movieAdapter;

    public static Intent create(Context context, String categoryName) {
        return new Intent(context, ListMovieActivity.class)
                .putExtra(CATEGORY_NAME, categoryName)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }


    private String getCategoryName() {
        if (getIntent().getExtras() != null)
            return getIntent().getExtras().getString(CATEGORY_NAME);
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieAdapter = new MovieAdapter();
        initDataBinding();
        setUpListOfMoviesView(activityListMovieBinding.listMovies);
        setUpSearchObservable();
    }

    private void initDataBinding() {
        activityListMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_movie);
        movieViewModel = new MovieViewModel(this, getCategoryName());
        activityListMovieBinding.setListMovieViewModel(movieViewModel);

    }

    private void setUpListOfMoviesView(RecyclerView listMovie) {
        listMovie.setAdapter(movieAdapter);
        listMovie.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieViewModel.reset();
    }

    @Override
    public void onDataSourceChange(List<Movie> movies) {
        if (movies != null) {
            movieAdapter.setMovieList(movies);
            return;
        }
        movieAdapter.reset();
    }

    @SuppressLint("CheckResult")
    private void setUpSearchObservable() {
        RxSearchObservable.fromView(activityListMovieBinding.searchView)
                .debounce(200, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap((Function<String, ObservableSource<String>>) this::dataFromNetwork)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieViewModel.searchMoviesByName(result));

    }

    private Observable<String> dataFromNetwork(final String query) {
        return Observable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .map(value -> query);
    }


}
