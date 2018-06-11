package com.isdl.spajam2019.Gps;

import android.location.LocationManager;

public interface LocationContract {
    interface View {
        void setText(String text);

        void finishActivity();

        void enableLocationSettings();
    }

    interface Presenter {
        void startGPS(LocationManager locationManager);

        void stopGPS(LocationManager locationManager);
    }
}
