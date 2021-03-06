package com.isdl.spajam2019;

import android.app.Application;

import com.isdl.spajam2019.DI.Component.AppComponent;
import com.isdl.spajam2019.DI.Component.DaggerAppComponent;
import com.isdl.spajam2019.DI.Module.ApiModule;
import com.isdl.spajam2019.DI.Module.AppModule;

/**
 * Created by takayayuuki on 2018/05/25.
 */

public class Spajam2019Application extends Application {
    private AppComponent appComponent;

    private static Spajam2019Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(instance))
                .apiModule(new ApiModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
