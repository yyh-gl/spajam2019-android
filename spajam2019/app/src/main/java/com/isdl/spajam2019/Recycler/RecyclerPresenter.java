package com.isdl.spajam2019.Recycler;

import com.isdl.spajam2019.Services.ApiService;

import javax.inject.Inject;

public class RecyclerPresenter {
    ApiService apiService;
    RecyclerContract.View view;

    @Inject
    public RecyclerPresenter(ApiService apiService, RecyclerContract.View view) {
        this.apiService = apiService;
        this.view = view;
    }
}
