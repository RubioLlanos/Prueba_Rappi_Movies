package com.app.rubio.movie.viewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.app.rubio.movie.api.RestMovieService;
import com.app.rubio.movie.app.AppController;
import com.app.rubio.movie.models.Movie;
import com.app.rubio.movie.models.MovieResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

import static com.app.rubio.movie.utils.Util.validateNetwork;

/**
 * Created by Rubio on 24/03/2018.
 */

public class MovieViewModel extends Observable {
    private static final String MOVIE_RESPONSE_ID = "movieResponseId";
    public ObservableInt progressBar;
    public ObservableInt movieRecycler;
    public ObservableField<String> titleToolbar = new ObservableField<>();
    private Context context;
    private String categoryName;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Realm realm;
    private NotifiedDataBase notifiedDataBase;

    public MovieViewModel(@NonNull Context context, String categoryName) {
        realm = Realm.getDefaultInstance();
        this.context = context;
        this.categoryName = categoryName;
        notifiedDataBase = (NotifiedDataBase) context;
        progressBar = new ObservableInt(View.GONE);
        movieRecycler = new ObservableInt(View.GONE);
        initializeViews();
        initTitleToolbar();
        initDataSource();
    }

    private void initDataSource() {
        if (validateNetwork())
            fetchMovieList();
        else
            getMoviesByCategory();
    }

    private void initTitleToolbar() {
        if (categoryName.equals("popular")) titleToolbar.set("Popular");
        if (categoryName.equals("top_rated")) titleToolbar.set("Top Rated");
        if (categoryName.equals("upcoming")) titleToolbar.set("Un comming");
    }

    private void initializeViews() {
        movieRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }

    private void fetchMovieList() {
        AppController appController = AppController.create(context);
        RestMovieService restMovieService = appController.getRestMovieService();
        Disposable disposable = restMovieService.getMovies(categoryName)
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResponse -> {
                    saveDataMovie(movieResponse);
                    progressBar.set(View.GONE);
                    movieRecycler.set(View.VISIBLE);
                    notifiedDataBase.onDataSourceChange(movieResponse.getMovieList());
                }, throwable -> {
                    progressBar.set(View.GONE);
                    movieRecycler.set(View.GONE);
                });
        compositeDisposable.add(disposable);
    }

    private boolean saveDataMovie(MovieResponse movieResponse) {
        try {
            movieResponse.setMovieResponseId(categoryName);
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(movieResponse);
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void searchMoviesByName(String text) {
        MovieResponse copyMoviesFromReal;
        MovieResponse results = realm.where(MovieResponse.class).equalTo(MOVIE_RESPONSE_ID, categoryName).findFirst();
        if (results != null) {
            copyMoviesFromReal = realm.copyFromRealm(results);
            if (copyMoviesFromReal.getMovieList().size() == 0)
                return;
            notifiedDataBase.onDataSourceChange(filter(copyMoviesFromReal.getMovieList(), text));
        }

    }

    private void getMoviesByCategory() {
        MovieResponse copyMoviesFromReal = new MovieResponse();
        try {
            MovieResponse results = realm.where(MovieResponse.class).equalTo(MOVIE_RESPONSE_ID, categoryName).findFirst();
            copyMoviesFromReal = realm.copyFromRealm(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
        progressBar.set(View.GONE);
        movieRecycler.set(View.VISIBLE);
        notifiedDataBase.onDataSourceChange(copyMoviesFromReal.getMovieList());
    }

    private List<Movie> filter(List<Movie> mov, String query) {
        query = query.toLowerCase();
        final List<Movie> filterModeList = new ArrayList<>();
        for (Movie model : mov) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filterModeList.add(model);
            }
        }
        return filterModeList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }

    public interface NotifiedDataBase {
        void onDataSourceChange(List<Movie> movies);
    }
}

