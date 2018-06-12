package com.isdl.spajam2019.Main;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.isdl.spajam2019.Gps.GpsPermissionActivity;
import com.isdl.spajam2019.Models.User;
import com.isdl.spajam2019.Recycler.RecyclerActivity;
import com.isdl.spajam2019.Services.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by takayayuuki on 2018/05/25.
 */

public class MainPresenter {
    ApiService apiService;

    @Inject
    public MainPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    public void apiRequest() {
        apiService.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        for (int i = 0; i < users.size(); i++) {
                            Log.d("test", users.get(i).getName());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });
    }

    public void apiPost() {
        User user = new User();

        user.setId(000000);
        user.setName("testくん");
        user.setAge(24);
        user.setMessage("やっほ〜");
        user.setCreatedAt("2018");
        user.setUpdatedAt("2019");

        apiService.postUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.d("test", "post success");
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


}

