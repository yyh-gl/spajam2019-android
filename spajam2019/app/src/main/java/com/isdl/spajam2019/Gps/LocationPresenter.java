package com.isdl.spajam2019.Gps;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class LocationPresenter implements LocationListener {
    private StringBuilder strBuf = new StringBuilder();

    @Override
    public void onLocationChanged(Location location) {
        strBuf.append("----------\n");

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

//        textView.setText(strBuf);//ここはLocationContract.Viewでメソッド記述
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.AVAILABLE:
                strBuf.append("LocationProvider.AVAILABLE\n");
//                textView.setText(strBuf);
                break;
            case LocationProvider.OUT_OF_SERVICE:
                strBuf.append("LocationProvider.OUT_OF_SERVICE\n");
//                textView.setText(strBuf);
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                strBuf.append("LocationProvider.TEMPORARILY_UNAVAILABLE\n");
//                textView.setText(strBuf);
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
