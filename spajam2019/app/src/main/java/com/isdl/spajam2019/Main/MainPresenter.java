package com.isdl.spajam2019.Main;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.isdl.spajam2019.Camera.CameraPermissionActivity;
import com.isdl.spajam2019.Gps.GpsPermissionActivity;
import com.isdl.spajam2019.Main.Fragment.Cheer.CheerFragment;
import com.isdl.spajam2019.Models.Live;
import com.isdl.spajam2019.Models.UserCrossMusic;
import com.isdl.spajam2019.Recycler.RecyclerActivity;
import com.isdl.spajam2019.Services.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by takayayuuki on 2018/05/25.
 */

public class MainPresenter implements MainContract.Presenter {
    ApiService apiService;
    MainContract.View view;


    @Inject
    public MainPresenter(ApiService apiService, MainContract.View view) {
        this.apiService = apiService;
        this.view = view;
    }

    public void postCrossMusic(int senderId, int recieverId) {
        apiService.postCrossMusic(senderId, recieverId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<UserCrossMusic>>() {
                    @Override
                    public void onSuccess(List<UserCrossMusic> possessedMusics) {
                        Log.w("succsess", "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });
    }

    public void apiRequest() {

    }

    public void apiPost() {

    }

    public void getLiveInfo(int liveId) {
        apiService.getLiveInfo(liveId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Live>() {
                    @Override
                    public void onSuccess(Live live) {
                        view.showToast("今回のライブのいいね数は" + live.getLike().toString() + "!!!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });
    }

    public void switchLiveStatus(int liveId) {
        apiService.switchLiveStatus(liveId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(Boolean liveStatus) {
                        if (liveStatus) {
                            view.showToast("ライブスタート！！！");
                        } else {
                            getLiveInfo(1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });

    }


    public void toGps(Activity activity) {
        Intent intent = new Intent(activity, GpsPermissionActivity.class);
        activity.startActivity(intent);
    }

    public void toRecycler(Activity activity) {
        Intent intent = new Intent(activity, RecyclerActivity.class);
        activity.startActivity(intent);

    }

    public void toCamera(Activity activity) {
        Intent intent = new Intent(activity, CameraPermissionActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void openFragment(CheerFragment cheerFragment) {

    }
}

