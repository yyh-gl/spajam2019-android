package com.isdl.spajam2019.Main;

import android.util.Log;

import com.isdl.spajam2019.Models.QiitaItem;
import com.isdl.spajam2019.Services.ApiService;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        apiService.items()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<QiitaItem>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("QiitaItem:", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error:", e.toString());
                    }

                    @Override
                    public void onNext(List<QiitaItem> qiitaItems) {
                        Log.d("QiitaItem:", "onNext");

                    }
                });
    }

    public void apiPost() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "ytakaya");
        hashMap.put("age", "25");

        apiService.post(hashMap)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Post:", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Post:", "onError");
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        Log.d("Post:", "onNext");
                    }
                });
    }


}

