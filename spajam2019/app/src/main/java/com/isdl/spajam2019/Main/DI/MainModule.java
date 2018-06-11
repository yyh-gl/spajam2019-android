package com.isdl.spajam2019.Main.DI;

import com.isdl.spajam2019.Main.MainContract;
import com.isdl.spajam2019.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    public MainContract.View provideMainView() {
        return view;
    }
}