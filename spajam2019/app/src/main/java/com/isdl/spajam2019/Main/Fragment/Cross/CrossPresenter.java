package com.isdl.spajam2019.Main.Fragment.Cross;

import android.app.Application;
import android.util.Log;

import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.Services.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CrossPresenter implements CrossContract.Presenter {
    Application app;
    ApiService apiService;
    CrossContract.View view;

    @Inject
    public CrossPresenter(Application app, ApiService apiService, CrossContract.View view) {
        this.app = app;
        this.apiService = apiService;
        this.view = view;
    }


    public void getPossessedCrossMusic(int userid) {
        apiService.getCrossMusic(userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Music>>() {
                    @Override
                    public void onSuccess(List<Music> crossMusics) {
                        view.setAdapter(crossMusics);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });
    }
}
