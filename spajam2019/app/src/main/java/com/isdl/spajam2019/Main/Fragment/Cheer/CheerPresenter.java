package com.isdl.spajam2019.Main.Fragment.Cheer;

import android.app.Application;

import com.isdl.spajam2019.Services.ApiService;

import javax.inject.Inject;

public class CheerPresenter {
    Application app;
    ApiService apiService;
    CheerContract.View view;

    @Inject
    public CheerPresenter(Application app, ApiService apiService, CheerContract.View view) {
        this.app = app;
        this.apiService = apiService;
        this.view = view;
    }
}
