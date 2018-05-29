package com.isdl.spajam2019.Main;


import android.util.Log;

import com.isdl.spajam2019.Models.User;
import com.isdl.spajam2019.Services.ApiService;

import java.util.HashMap;
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
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "ytakaya");
        hashMap.put("age", "25");

        apiService.post(hashMap)
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


}

