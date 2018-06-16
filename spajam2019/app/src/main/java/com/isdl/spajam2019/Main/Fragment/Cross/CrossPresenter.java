package com.isdl.spajam2019.Main.Fragment.Cross;

import android.app.Application;
import android.util.Log;

import com.isdl.spajam2019.Services.ApiService;

import javax.inject.Inject;

public class CrossPresenter {
    Application app;
    ApiService apiService;
    CrossContract.View view;

    @Inject
    public CrossPresenter(Application app, ApiService apiService, CrossContract.View view) {
        this.app = app;
        this.apiService = apiService;
        this.view = view;

        Log.d("CrossPresenter", app.toString());
    }
}
