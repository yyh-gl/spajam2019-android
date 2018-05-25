package com.isdl.spajam2019.Main;

import android.util.Log;

import com.isdl.spajam2019.Models.QiitaItem;
import com.isdl.spajam2019.Services.QiitaApiService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by takayayuuki on 2018/05/25.
 */

public class MainPresenter {
    OkHttpClient okHttpClient;
    Retrofit retrofit;

    public MainPresenter(OkHttpClient okHttpClient, Retrofit retrofit) {
        this.okHttpClient = okHttpClient;
        this.retrofit = retrofit;
    }

    public void apiRequest() {
        retrofit.create(QiitaApiService.class).items()
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


}

