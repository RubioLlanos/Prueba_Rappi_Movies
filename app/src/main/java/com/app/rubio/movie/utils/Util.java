package com.app.rubio.movie.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.app.rubio.movie.app.AppController;

public class Util {
    public static boolean validateNetwork() {
        ConnectivityManager conMgr = (ConnectivityManager) AppController.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null)
            return false;
        else
            return true;

    }
}
