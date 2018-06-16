package com.isdl.spajam2019.DI.Module;

import com.isdl.spajam2019.Camera.CameraContract;
import com.isdl.spajam2019.Camera.CameraPermissionContract;
import com.isdl.spajam2019.Gps.GpsPermissionContract;
import com.isdl.spajam2019.Gps.LocationContract;
import com.isdl.spajam2019.Main.Fragment.Cheer.CheerContract;
import com.isdl.spajam2019.Main.Fragment.Cross.CrossContract;
import com.isdl.spajam2019.Main.Fragment.MusicList.MusicListContract;
import com.isdl.spajam2019.Main.Fragment.Profile.ProfileContract;
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
    private CameraContract.View cameraView;
    private CameraPermissionContract.View cameraPermissionView;

    private CheerContract.View cheerContractView;
    private CrossContract.View crossContractView;
    private MusicListContract.View musicListContractView;
    private ProfileContract.View profileContractView;

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

    public ActivityModule(CameraContract.View cameraView) {
        this.cameraView = cameraView;
    }

    public ActivityModule(CameraPermissionContract.View cameraPermissionView) {
        this.cameraPermissionView = cameraPermissionView;
    }

    public ActivityModule(CheerContract.View cheerContractView) {
        this.cheerContractView = cheerContractView;
    }

    public ActivityModule(CrossContract.View crossContractView) {
        this.crossContractView = crossContractView;
    }

    public ActivityModule(MusicListContract.View musicListContractView) {
        this.musicListContractView = musicListContractView;
    }

    public ActivityModule(ProfileContract.View profileContractView) {
        this.profileContractView = profileContractView;
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

    @PerActivity
    @Provides
    public CameraContract.View provideCameraContractView() {
        return cameraView;
    }

    @PerActivity
    @Provides
    public CameraPermissionContract.View provideCameraPermissionContractView() {
        return cameraPermissionView;
    }

    @PerActivity
    @Provides
    public CheerContract.View provideCheerContractView() {
        return cheerContractView;
    }

    @PerActivity
    @Provides
    public CrossContract.View provideCrossContractView() {
        return crossContractView;
    }

    @PerActivity
    @Provides
    public MusicListContract.View provideMusicListContractContractView() {
        return musicListContractView;
    }

    @PerActivity
    @Provides
    public ProfileContract.View provideProfileContractContractView() {
        return profileContractView;
    }


}
