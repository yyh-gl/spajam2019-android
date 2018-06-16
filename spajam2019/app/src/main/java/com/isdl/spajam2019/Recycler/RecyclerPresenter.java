package com.isdl.spajam2019.Recycler;

import android.util.Log;

import com.isdl.spajam2019.Models.User;
import com.isdl.spajam2019.Services.ApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class RecyclerPresenter {
    ApiService apiService;
    RecyclerContract.View view;

    @Inject
    public RecyclerPresenter(ApiService apiService, RecyclerContract.View view) {
        this.apiService = apiService;
        this.view = view;
    }

    public void getUsers() {
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

    public List<User> createDataset() {

        List<User> dataset = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setName("カサレアル　太郎" + i + "号");
            user.setMessage("カサレアル　太郎は" + i + "個の唐揚げが好き");

            dataset.add(user);
        }
        return dataset;
    }

}
