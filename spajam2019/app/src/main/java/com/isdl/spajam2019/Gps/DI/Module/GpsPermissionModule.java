package com.isdl.spajam2019.Gps.DI.Module;

import com.isdl.spajam2019.Gps.GpsPermissionContract;
import com.isdl.spajam2019.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class GpsPermissionModule {
    private GpsPermissionContract.View view;

    public GpsPermissionModule(GpsPermissionContract.View view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    public GpsPermissionContract.View provideGpsPermissionView() {
        return view;
    }
}
