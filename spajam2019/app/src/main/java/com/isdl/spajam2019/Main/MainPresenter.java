package com.isdl.spajam2019.Main;


import android.app.Activity;
import android.content.Intent;

import com.isdl.spajam2019.Camera.CameraPermissionActivity;
import com.isdl.spajam2019.Gps.GpsPermissionActivity;
import com.isdl.spajam2019.Recycler.RecyclerActivity;
import com.isdl.spajam2019.Services.ApiService;

import javax.inject.Inject;


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

    }

    public void apiPost() {

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

}

