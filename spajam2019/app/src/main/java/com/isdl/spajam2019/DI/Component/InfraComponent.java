package com.isdl.spajam2019.DI.Component;

import com.isdl.spajam2019.DI.Module.AppModule;
import com.isdl.spajam2019.DI.Module.InfraModule;
import com.isdl.spajam2019.Main.MainActivity;
import com.isdl.spajam2019.Recycler.RecyclerActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by takayayuuki on 2018/05/26.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                InfraModule.class
        })
public interface InfraComponent {
    void inject(MainActivity mainActivity);
    void inject(RecyclerActivity recyclerActivity);
}
