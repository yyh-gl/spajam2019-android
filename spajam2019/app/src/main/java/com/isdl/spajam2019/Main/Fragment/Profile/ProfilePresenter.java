package com.isdl.spajam2019.Main.Fragment.Profile;

import android.app.Application;
import android.util.Log;

import com.isdl.spajam2019.Models.User;
import com.isdl.spajam2019.Services.ApiService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter implements ProfileContract.Presenter {
    Application app;
    ApiService apiService;
    ProfileContract.View view;

    @Inject
    public ProfilePresenter(Application app, ApiService apiService, ProfileContract.View view) {
        this.app = app;
        this.apiService = apiService;
        this.view = view;
    }

    @Override
    public void getUserInfo(int userId) {
        apiService.getUserInfo(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(User user) {
                        Log.d("Profile", user.getUserId().toString());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }
                });
    }
}
