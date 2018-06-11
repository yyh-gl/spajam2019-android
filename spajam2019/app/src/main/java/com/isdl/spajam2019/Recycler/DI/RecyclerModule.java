package com.isdl.spajam2019.Recycler.DI;

import com.isdl.spajam2019.PerActivity;
import com.isdl.spajam2019.Recycler.RecyclerContract;

import dagger.Module;
import dagger.Provides;

@Module
public class RecyclerModule {
    private RecyclerContract.View view;

    public RecyclerModule(RecyclerContract.View view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    public RecyclerContract.View provideRecyclerContractView() {
        return view;
    }
}