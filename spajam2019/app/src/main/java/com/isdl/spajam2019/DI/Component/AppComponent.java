package com.isdl.spajam2019.DI.Component;

import android.app.Application;

import com.isdl.spajam2019.DI.Module.ApiModule;
import com.isdl.spajam2019.DI.Module.AppModule;
import com.isdl.spajam2019.Services.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by takayayuuki on 2018/05/25.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                ApiModule.class
        })
public interface AppComponent {
    Application application();

    ApiService apiService();
}
