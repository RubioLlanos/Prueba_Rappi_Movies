package com.app.rubio.movie.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Rubio on 25/03/2018.
 */

public class MovieResponse extends RealmObject {
    @PrimaryKey
    private String movieResponseId;
    @SerializedName("results")
    private RealmList<Movie> movieList;

    public MovieResponse() {
    }

    public MovieResponse(String movieResponseId, RealmList<Movie> movieList) {
        this.movieResponseId = movieResponseId;
        this.movieList = movieList;
    }

    public String getMovieResponseId() {
        return movieResponseId;
    }


    public void setMovieResponseId(String movieResponseId) {
        this.movieResponseId = movieResponseId;
    }

    public RealmList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(RealmList<Movie> mMovieList) {
        this.movieList = mMovieList;
    }

}
