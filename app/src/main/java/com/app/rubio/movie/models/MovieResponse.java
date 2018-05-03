package com.app.rubio.movie.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Rubian Llanos 2018
 */

public class MovieResponse extends RealmObject {
    @PrimaryKey
    private String movieResponseId;
    @SerializedName("results")
    private RealmList<Movie> movieList;

    public MovieResponse() {
    }


    public void setMovieResponseId(String movieResponseId) {
        this.movieResponseId = movieResponseId;
    }

    public RealmList<Movie> getMovieList() {
        return movieList;
    }

}
