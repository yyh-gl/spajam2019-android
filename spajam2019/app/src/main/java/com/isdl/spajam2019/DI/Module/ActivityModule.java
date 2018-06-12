package com.isdl.spajam2019.DI.Module;

import com.isdl.spajam2019.Gps.GpsPermissionContract;
import com.isdl.spajam2019.Gps.LocationContract;
import com.isdl.spajam2019.Main.MainContract;
import com.isdl.spajam2019.PerActivity;
import com.isdl.spajam2019.Recycler.RecyclerContract;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private MainContract.View mainView;
    private LocationContract.View locationView;
    private GpsPermissionContract.View gpsPermissionView;
    private RecyclerContract.View recyclerView;

    public ActivityModule(MainContract.View mainView) {
        this.mainView = mainView;
    }

    public ActivityModule(LocationContract.View locationView) {
        this.locationView = locationView;
    }

    public ActivityModule(GpsPermissionContract.View gpsPermissionView) {
        this.gpsPermissionView = gpsPermissionView;
    }

    public ActivityModule(RecyclerContract.View recyclerView) {
        this.recyclerView = recyclerView;
    }

    @PerActivity
    @Provides
    public MainContract.View provideMainContractView() {
        return mainView;
    }

    @PerActivity
    @Provides
    public LocationContract.View provideLocationContractView() {
        return locationView;
    }

    @PerActivity
    @Provides
    public GpsPermissionContract.View provideGpsPermissionContractView() {
        return gpsPermissionView;
    }

    @PerActivity
    @Provides
    public RecyclerContract.View provideRecyclerContractView() {
        return recyclerView;
    }
}
