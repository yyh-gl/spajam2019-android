package com.isdl.spajam2019.DI.Component;

import com.isdl.spajam2019.DI.Module.ApplicationModule;
import com.isdl.spajam2019.DI.Module.InfraModule;
import com.isdl.spajam2019.Main.MainActivity;

import dagger.Component;

/**
 * Created by takayayuuki on 2018/05/25.
 */

@Component(
        modules = {
                ApplicationModule.class,
                InfraModule.class
        })
public interface ApplicationComponent {
    void inject(MainActivity mainPresenter);
}
