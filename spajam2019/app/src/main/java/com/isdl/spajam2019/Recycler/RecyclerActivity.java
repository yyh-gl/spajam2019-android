package com.isdl.spajam2019.Recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import javax.inject.Inject;

public class RecyclerActivity extends AppCompatActivity implements RecyclerContract.View {

    @Inject
    RecyclerPresenter recyclerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        DaggerActivityComponent.builder()
                .appComponent(((Spajam2019Application) getApplicationContext())
                        .getAppComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }
}
