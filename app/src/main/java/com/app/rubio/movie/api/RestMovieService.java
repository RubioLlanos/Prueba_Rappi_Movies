package com.app.rubio.movie.api;

import com.app.rubio.movie.models.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Rubio on 24/03/2018.
 */

public interface RestMovieService {
    @GET("movie/{category}?api_key=009737aeeda7122f00f514b33ae59e16")
    Observable<MovieResponse> getMovies(@Path("category") String category);

}
