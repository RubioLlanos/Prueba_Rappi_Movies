package com.app.rubio.movie.app;

import android.app.Application;
import android.content.Context;

import com.app.rubio.movie.api.ClientFactory;
import com.app.rubio.movie.api.RestMovieService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * Created by Rubio on 25/03/2018.
 */

public class AppController extends Application {
    private static Context context;
    private RestMovieService restMovieService;
    private Scheduler scheduler;

    public static Context getAppContext() {
        return AppController.context;
    }

    public static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
      /*  RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);*/
        Realm.init(this);
        AppController.context = getApplicationContext();
    }

    public RestMovieService getRestMovieService() {
        if (restMovieService == null) {
            restMovieService = ClientFactory.create();
        }
        return restMovieService;
    }

    public void setRestMovieService(RestMovieService restMovieService) {
        this.restMovieService = restMovieService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
