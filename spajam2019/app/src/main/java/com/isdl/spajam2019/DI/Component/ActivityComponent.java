package com.isdl.spajam2019.DI.Component;

import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.Gps.GpsPermissionActivity;
import com.isdl.spajam2019.Gps.LocationActivity;
import com.isdl.spajam2019.Main.MainActivity;
import com.isdl.spajam2019.PerActivity;
import com.isdl.spajam2019.Recycler.RecyclerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(LocationActivity locationActivity);

    void inject(GpsPermissionActivity gpsPermissionActivity);

    void inject(RecyclerActivity recyclerActivity);
}
