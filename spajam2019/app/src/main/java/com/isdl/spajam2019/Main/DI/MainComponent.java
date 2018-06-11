package com.isdl.spajam2019.Main.DI;

import com.isdl.spajam2019.DI.Component.AppComponent;
import com.isdl.spajam2019.Main.MainActivity;
import com.isdl.spajam2019.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
