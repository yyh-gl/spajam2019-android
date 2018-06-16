package com.isdl.spajam2019.Gps;

import android.app.Activity;

public interface GpsPermissionContract {
    interface View {

    }

    interface Presenter {
        void checkPermission(Activity activity);

        void requestLocationPermission(Activity activity);
    }
}
