package com.isdl.spajam2019.Main.Fragment.MusicList;

import android.app.Application;
import android.util.Log;

import com.isdl.spajam2019.Models.User;
import com.isdl.spajam2019.Services.ApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MusicListPresenter implements MusicListContract.Presenter {
    Application app;
    ApiService apiService;
    MusicListContract.View view;

    @Inject
    public MusicListPresenter(Application app, ApiService apiService, MusicListContract.View view) {
        this.app = app;
        this.apiService = apiService;
        this.view = view;

        Log.d("MusicListPresenter", app.toString());

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
