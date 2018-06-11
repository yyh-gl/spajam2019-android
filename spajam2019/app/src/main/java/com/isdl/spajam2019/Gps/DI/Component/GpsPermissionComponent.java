package com.isdl.spajam2019.Gps.DI.Component;

import com.isdl.spajam2019.DI.Component.AppComponent;
import com.isdl.spajam2019.Gps.DI.Module.GpsPermissionModule;
import com.isdl.spajam2019.Gps.GpsPermissionActivity;
import com.isdl.spajam2019.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = GpsPermissionModule.class)
public interface GpsPermissionComponent {
    void inject(GpsPermissionActivity gpsPermissionActivity);
}