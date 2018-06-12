package com.isdl.spajam2019.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.R;
import com.isdl.spajam2019.Spajam2019Application;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerActivityComponent.builder()
                .appComponent(((Spajam2019Application) getApplicationContext())
                        .getAppComponent())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);

        mainPresenter.apiRequest();
        mainPresenter.apiPost();

        Button buttonRecycler = findViewById(R.id.toRecycler);
        Button buttonGps = findViewById(R.id.toGps);

        buttonRecycler.setOnClickListener(view -> mainPresenter.toRecycler(this));
        buttonGps.setOnClickListener(view -> mainPresenter.toGps(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
