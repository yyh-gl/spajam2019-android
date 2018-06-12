package com.isdl.spajam2019.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.isdl.spajam2019.DI.Component.DaggerActivityComponent;
import com.isdl.spajam2019.DI.Module.ActivityModule;
import com.isdl.spajam2019.Gps.GpsPermissionActivity;
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

        Intent intent = new Intent(this, GpsPermissionActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
