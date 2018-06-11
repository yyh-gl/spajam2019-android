package com.isdl.spajam2019.Gps;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.isdl.spajam2019.Services.ApiService;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

public class LocationPresenter implements LocationContract.Presenter, LocationListener {
    Application app;
    ApiService apiService;

    private LocationContract.View view;


    private StringBuilder strBuf = new StringBuilder();


    private static final int MinTime = 1000;
    private static final float MinDistance = 50;

    @Inject
    public LocationPresenter(Application app, ApiService apiService, LocationContract.View view) {
        this.app = app;
        this.apiService = apiService;
        this.view = view;
    }

    @Override
    public void startGPS(LocationManager locationManager) {
        strBuf.append("startGPS\n");

        view.setText(strBuf.toString());
        Log.d("GPS", strBuf.toString());

        Log.d("LocationActivity", "gpsEnabled");
        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            // GPSを設定するように促す
            view.enableLocationSettings();
        }

        if (locationManager != null) {
            Log.d("LocationActivity", "locationManager.requestLocationUpdates");

            try {
                // minTime = 1000msec, minDistance = 50m
                if (ActivityCompat.checkSelfPermission(app.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    return;
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MinTime, MinDistance, this);
            } catch (Exception e) {
                e.printStackTrace();

                Toast toast = Toast.makeText(app.getApplicationContext(), "例外が発生、位置情報のPermissionを許可していますか？", Toast.LENGTH_SHORT);
                toast.show();
                view.finishActivity();
            }
        }
    }

    @Override
    public void stopGPS(LocationManager locationManager) {
        if (locationManager != null) {
            Log.d("LocationActivity", "onStop()");
            strBuf.append("stopGPS\n");
            view.setText(strBuf.toString());
            Log.d("GPS", strBuf.toString());

            // update を止める
            if (ActivityCompat.checkSelfPermission(app.getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(app.getApplicationContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        strBuf.append("-----動いたよ〜〜〜〜-----\n");

        String str = "Latitude = " + String.valueOf(location.getLatitude()) + "\n";
        strBuf.append(str);

        str = "Longitude = " + String.valueOf(location.getLongitude()) + "\n";
        strBuf.append(str);

        str = "Accuracy = " + String.valueOf(location.getAccuracy()) + "\n";
        strBuf.append(str);

        str = "Altitude = " + String.valueOf(location.getAltitude()) + "\n";
        strBuf.append(str);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss", Locale.JAPAN);
        String currentTime = sdf.format(location.getTime());

        str = "Time = " + currentTime + "\n";
        strBuf.append(str);

        str = "Speed = " + String.valueOf(location.getSpeed()) + "\n";
        strBuf.append(str);

        str = "Bearing = " + String.valueOf(location.getBearing()) + "\n";
        strBuf.append(str);

        strBuf.append("----------\n");

        view.setText(strBuf.toString());
        Log.d("GPS", strBuf.toString());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.AVAILABLE:
                strBuf.append("LocationProvider.AVAILABLE\n");
                view.setText(strBuf.toString());
                Log.d("GPS", strBuf.toString());
                break;
            case LocationProvider.OUT_OF_SERVICE:
                strBuf.append("LocationProvider.OUT_OF_SERVICE\n");
                view.setText(strBuf.toString());
                Log.d("GPS", strBuf.toString());
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                strBuf.append("LocationProvider.TEMPORARILY_UNAVAILABLE\n");
                view.setText(strBuf.toString());
                Log.d("GPS", strBuf.toString());
                break;
        }

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}

