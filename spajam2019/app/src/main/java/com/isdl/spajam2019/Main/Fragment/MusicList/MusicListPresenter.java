package com.isdl.spajam2019.Main.Fragment.MusicList;

import android.app.Application;
import android.util.Log;

import com.isdl.spajam2019.Models.Music;
import com.isdl.spajam2019.Services.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MusicListPresenter implements MusicListContract.Presenter {
    Application app;
    ApiService apiService;
    MusicListContract.View view;

    @Inject
    public MusicListPresenter(Application app, ApiService apiService, MusicListContract.View view) {
        this.app = app;
        this.apiService = apiService;
        this.view = view;
    }

    @Override
    public void getPossessedCrossMusic(int userid) {
        apiService.getPossessedMusic(userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Music>>() {
                    @Override
                    public void onSuccess(List<Music> possessedMusics) {
                        view.setAdapter(possessedMusics);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });
    }
}
