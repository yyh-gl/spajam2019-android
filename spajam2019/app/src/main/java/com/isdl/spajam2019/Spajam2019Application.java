package com.isdl.spajam2019;

import android.app.Application;

import com.isdl.spajam2019.DI.Component.AppComponent;
import com.isdl.spajam2019.DI.Component.DaggerAppComponent;
import com.isdl.spajam2019.DI.Module.AppModule;
import com.isdl.spajam2019.DI.Module.InfraModule;

/**
 * Created by takayayuuki on 2018/05/25.
 */

public class Spajam2019Application extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .infraModule(new InfraModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
