package com.isdl.spajam2019.Gps.DI.Component;

import com.isdl.spajam2019.DI.Component.AppComponent;
import com.isdl.spajam2019.Gps.DI.Module.LocationModule;
import com.isdl.spajam2019.Gps.LocationActivity;
import com.isdl.spajam2019.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = LocationModule.class)
public interface LocationComponent {
    void inject(LocationActivity locationActivity);
}
