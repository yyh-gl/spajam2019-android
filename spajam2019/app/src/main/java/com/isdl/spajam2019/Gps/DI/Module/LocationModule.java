package com.isdl.spajam2019.Gps.DI.Module;

import com.isdl.spajam2019.Gps.LocationContract;
import com.isdl.spajam2019.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class LocationModule {
    private LocationContract.View view;

    public LocationModule(LocationContract.View view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    public LocationContract.View provideLocationView() {
        return view;
    }
}
