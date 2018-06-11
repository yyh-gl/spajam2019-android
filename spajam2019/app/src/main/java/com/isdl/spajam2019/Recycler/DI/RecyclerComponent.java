package com.isdl.spajam2019.Recycler.DI;

import com.isdl.spajam2019.DI.Component.AppComponent;
import com.isdl.spajam2019.PerActivity;
import com.isdl.spajam2019.Recycler.RecyclerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = RecyclerModule.class)
public interface RecyclerComponent {
    void inject(RecyclerActivity recyclerActivity);
}
