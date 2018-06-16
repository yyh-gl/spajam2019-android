package com.isdl.spajam2019.Main.Fragment.Cheer;

import android.app.Application;
import android.util.Log;

import com.isdl.spajam2019.Models.Live;
import com.isdl.spajam2019.Services.ApiService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CheerPresenter implements CheerContract.Presenter {
    Application app;
    ApiService apiService;
    CheerContract.View view;

    @Inject
    public CheerPresenter(Application app, ApiService apiService, CheerContract.View view) {
        this.app = app;
        this.apiService = apiService;
        this.view = view;
    }

    public void postLikes(int liveId) {
        apiService.postLike(liveId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Live>() {
                    @Override
                    public void onSuccess(Live live) {
                        Log.d("onSuccess", live.getLike().toString());
                        //ここでlive.getLike()の数に応じてviewの画像変更等を行う
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });

    }
}
