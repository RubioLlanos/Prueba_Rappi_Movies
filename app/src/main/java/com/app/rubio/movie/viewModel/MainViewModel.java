package com.app.rubio.movie.viewModel;

import android.databinding.BaseObservable;

import com.app.rubio.movie.app.AppController;
import com.app.rubio.movie.ui.activity.ListMovieActivity;

/**
 * Created by Rubio on 30/04/2018.
 */

public class MainViewModel extends BaseObservable {

    public void onClick(String action) {
        AppController.getAppContext().startActivity(ListMovieActivity.create(AppController.getAppContext(), action));
    }
}
