package com.isdl.spajam2019.Recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Recycler.DI.DaggerRecyclerComponent;
import com.isdl.spajam2019.Recycler.DI.RecyclerModule;
import com.isdl.spajam2019.Spajam2019Application;

import javax.inject.Inject;

public class RecyclerActivity extends AppCompatActivity implements RecyclerContract.View {

    @Inject
    RecyclerPresenter recyclerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        DaggerRecyclerComponent.builder()
                .appComponent(((Spajam2019Application) getApplicationContext())
                        .getAppComponent())
                .recyclerModule(new RecyclerModule(this))
                .build()
                .inject(this);
    }
}
