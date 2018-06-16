package com.isdl.spajam2019.Main.Fragment.Cross;

import android.app.Application;
import android.util.Log;

import com.isdl.spajam2019.Models.User;
import com.isdl.spajam2019.Services.ApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CrossPresenter implements CrossContract.Presenter {
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

    @Override
    public List<User> createDataset() {
        List<User> dataset = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setName("カサレアル　太郎" + i + "号");

            dataset.add(user);
        }
        return dataset;
    }
}
