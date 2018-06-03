package com.isdl.spajam2019.DI.Component;

import com.isdl.spajam2019.DI.Module.AppModule;
import com.isdl.spajam2019.DI.Module.InfraModule;
import com.isdl.spajam2019.Gps.GpsPermissionActivity;
import com.isdl.spajam2019.Gps.LocationActivity;
import com.isdl.spajam2019.Main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by takayayuuki on 2018/05/25.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                InfraModule.class
        })
public interface AppComponent {
    void inject(MainActivity mainActivity);

//    void inject(RecyclerActivity recyclerActivity);

    void inject(GpsPermissionActivity gpsPermissionActivity);

    void inject(LocationActivity locationActivity);
}
