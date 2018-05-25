package com.isdl.spajam2019;

import android.app.Application;

import com.isdl.spajam2019.DI.Component.ApplicationComponent;
import com.isdl.spajam2019.DI.Component.DaggerApplicationComponent;
import com.isdl.spajam2019.DI.Module.ApplicationModule;
import com.isdl.spajam2019.DI.Module.InfraModule;

/**
 * Created by takayayuuki on 2018/05/25.
 */

public class Spajam2019Application extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // AppComponentでdeprecatedエラーが出るとき
        // そのモジュールが使用されていないため、no-opになる。無視可
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .infraModule(new InfraModule())
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return applicationComponent;
    }
}
