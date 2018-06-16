package com.isdl.spajam2019.Recycler;

import com.isdl.spajam2019.Models.User;
import com.isdl.spajam2019.Services.ApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecyclerPresenter {
    ApiService apiService;
    RecyclerContract.View view;

    @Inject
    public RecyclerPresenter(ApiService apiService, RecyclerContract.View view) {
        this.apiService = apiService;
        this.view = view;
    }

    public void getUsers() {

    }

    public List<User> createDataset() {

        List<User> dataset = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user = new User();


            dataset.add(user);
        }
        return dataset;
    }

}
