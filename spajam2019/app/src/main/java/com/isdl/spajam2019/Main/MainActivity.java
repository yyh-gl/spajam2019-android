package com.isdl.spajam2019.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.isdl.spajam2019.Models.QiitaItem;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Services.QiitaApiService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private QiitaApiService qiitaApiService = null;
    OkHttpClient okHttpclient = new OkHttpClient.Builder().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qiitaApiService = getQiitaApiService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Qiitaの新着アイテムを取得
        qiitaApiService.items()
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


    public QiitaApiService getQiitaApiService() {
        if (qiitaApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://radiant-reaches-45097.herokuapp.com/") // ここのURLは本田が立てるサーバURL
                    .client(okHttpclient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            qiitaApiService = retrofit.create(QiitaApiService.class);
        }
        return qiitaApiService;
    }
}
