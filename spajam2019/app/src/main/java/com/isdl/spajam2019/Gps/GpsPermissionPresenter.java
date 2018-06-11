package com.isdl.spajam2019.Gps;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.isdl.spajam2019.Services.ApiService;

import javax.inject.Inject;

public class GpsPermissionPresenter implements GpsPermissionContract.Presenter {

    public LocationManager locationManager;
    private static final int MinTime = 1000;
    private static final float MinDistance = 50;

    private final int REQUEST_PERMISSION = 1000;
    GpsPermissionContract.View view;

    ApiService apiService;
    Application app;


    @Inject
    public GpsPermissionPresenter(Application app, ApiService apiService) {
        this.app = app;
        this.apiService = apiService;
    }


    @Override
    public void checkPermission(Activity activity) {
        // 既に許可している
        if (ContextCompat.checkSelfPermission(app.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            startLocationActivity(activity);
        }
        // 拒否していた場合
        else {
            requestLocationPermission(activity);
        }
    }

    @Override
    public void requestLocationPermission(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION);


        } else {
            Toast toast = Toast.makeText(activity,
                    "許可されないとアプリが実行できません", Toast.LENGTH_SHORT);
            toast.show();

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,},
                    REQUEST_PERMISSION);

        }

    }


    public void startLocationActivity(Activity activity) {
        Log.d("TEST", "startLocationActivity()に入ったよ");
        Intent intent = new Intent(app.getApplicationContext(), LocationActivity.class);
        activity.startActivity(intent);
    }
}
