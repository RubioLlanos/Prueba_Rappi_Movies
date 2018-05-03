package com.app.rubio.movie.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Rubio on 25/03/2018.
 */

public class Picture implements Serializable {
    @SerializedName("backdrop_path")
    public String backdrop_path;
    @SerializedName("poster_path")
    public String poster_path;
}
